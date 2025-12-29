import { Layout, Form, Input, Button, Row } from "antd";
import Header from "../components/Header/Header";
import Footer from "../components/Footer/Footer";
import OrderInfo from "../components/OrderInfo/OrderInfo";
import { useDispatch, useSelector } from "react-redux";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { createPayment, getOrder } from "../store/actions";
import { useParams } from "react-router-dom";
import Preloader from "../components/Preloader/Preloader";
import { getCurrentGameAction } from "../store/gameReducer";
import BackButton from "../components/BackButton/BackButton";
import { getOrderAction, setError } from "../store/gameReducer";
import Api from "../utils/Api";
import "./OrderPage.css";
import OrderListButton from "../components/OrderListButton/OrderListButton";

const OrderPage = () => {
  const dispatch = useDispatch();
  const { id } = useParams();
  const [orderStatus, setOrderState] = useState("");
  const [currentOrder, setCurrentOrder] = useState(null);
  const [error, setError] = useState(false);
  const [keys, setKeys] = useState(null);

  const getOrder = async (id) => {
    try {
      const res = await Api.getOrder(id);
      setOrderState(res.status);
      setCurrentOrder(res);
    } catch (err) {
      setError(true);
      console.error(err);
    }
  };

  useEffect(() => {
    getOrder(id);
  }, []);

  const handleSubmit = async (form) => {
    const data = { ...form, order_id: id };

    try {
      await Api.createPayment(data);
      getOrder(id);
    } catch (err) {
      setError(true);
      console.error(err);
    }
  };

  const getKeys = async () => {
    try {
      const data = await Api.getKeys(id);
      setKeys(data.keys);

      getOrder(id);
    } catch (err) {
      setError(true);
      console.error(err);
    }
  };

  return (
    <Layout
      style={{
        background: "#112D43",
        color: "#4E83AC",
        minHeight: "100vh",
      }}
    >
      <Header>
        <OrderListButton />
        <BackButton />
      </Header>
      <Layout.Content
        style={{ padding: "40px 20px", maxWidth: 1280, margin: "0 auto" }}
      >
        {!currentOrder ? (
          <Preloader />
        ) : (
          <OrderInfo currentOrder={currentOrder} />
        )}
        {(orderStatus === "SUCCESS" || orderStatus === "PAID") && (
          <div>
            <h2>Оплачен</h2>
            {keys?.length ? (
              <>
                <h3 style={{ color: "white" }}>Ключи:</h3>
                {keys.map(({ gameName, gameKey }) => (
                  <Row key={gameKey}>
                    <div style={{ marginRight: 20, fontWeight: "bold" }}>
                      {gameName}:
                    </div>
                    <div>{gameKey}</div>
                  </Row>
                ))}
              </>
            ) : (
              <Button onClick={getKeys}>Получить ключи</Button>
            )}
          </div>
        )}

        {orderStatus === "PENDING" && (
          <div style={{ marginLeft: 20 }}>
            <h3>Форма оплаты</h3>
            <Form
              name="basic"
              labelCol={{ span: 8 }}
              wrapperCol={{ span: 16 }}
              style={{ maxWidth: 600 }}
              initialValues={{ remember: true }}
              onFinish={handleSubmit}
              autoComplete="off"
              layout="vertical"
              className="form"
            >
              <Form.Item
                label="Номер карты"
                name="cardNumber"
                rules={[
                  { required: true, message: "Обязательное поле" },
                  {
                    pattern: /^\d{16}$/,
                    message: "Номер карты должен содержать 16 цифр",
                  },
                ]}
                className="label"
              >
                <Input />
              </Form.Item>

              <Form.Item
                label="Срок действия карты"
                name="cardValidityPeriod"
                rules={[
                  { required: true, message: "Обязательное поле" },
                  {
                    pattern: /^(0[1-9]|1[0-2])\/\d{2}$/,
                    message: "Формат должен быть MM/YY",
                  },
                ]}
              >
                <Input />
              </Form.Item>

              <Form.Item
                label="CVC"
                name="cvcCode"
                rules={[
                  { required: true, message: "Обязательное поле" },
                  {
                    pattern: /^\d{3}$/,
                    message: "CVC должен содержать 3 цифры",
                  },
                ]}
              >
                <Input />
              </Form.Item>

              <Form.Item
                label="Имя держателя карты"
                name="cartHolderName"
                rules={[
                  { required: true, message: "Обязательное поле" },
                  {
                    max: 100,
                    message:
                      "Имя должно состоять только из латинских букв и 1 пробела",
                    pattern: /^[A-Za-z]+( [A-Za-z]+)+$/,
                  },
                ]}
              >
                <Input />
              </Form.Item>

              <Form.Item label={null}>
                <Button type="primary" htmlType="submit">
                  Оплатить заказ
                </Button>
              </Form.Item>
            </Form>

            {error && (
              <h3 style={{ color: "red" }}>
                Не удалось провести оплату. Повторите попытку.
              </h3>
            )}
          </div>
        )}
      </Layout.Content>
    </Layout>
  );
};

export default OrderPage;
