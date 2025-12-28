import { Layout, Form, Input, Button } from "antd";
import Header from "../components/Header/Header";
import Footer from "../components/Footer/Footer";
import GameInfo from "../components/GameInfo/GameInfo";
import { useDispatch, useSelector } from "react-redux";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"
import { createPayment, getOrder } from "../store/actions";
import { useParams } from "react-router-dom";
import Preloader from "../components/Preloader/Preloader";
import { getCurrentGameAction } from "../store/gameReducer";
import BackButton from "../components/BackButton/BackButton";
import {
  getOrderAction,
  setError,
} from "../store/gameReducer";
import Api from "../utils/Api";

const OrderPage = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const currentGame = useSelector((state) => state.currentGame);
  const isLoading = useSelector((state) => state.isLoading);
  const error = useSelector((state) => state.error);
  const order = useSelector((state) => state.order);
  const [orderStatus, setOrderState] = useState("")
  const {id} = useParams();
  console.log(orderStatus)

const getOrder = async (id) => {
   try {
     const res = await Api.getOrder(id)
     dispatch(getOrderAction(order));
     setOrderState(res.status)
   } catch(err) {
     dispatch(setError(true));
     console.error(err);
   }
}

useEffect(() => {
  getOrder(id)
}, [])

  const handleSubmit = (data) => {
      console.log(data)
      createPayment(dispatch, data)
    }

  const getKeys = () => {
    console.log('getKeys')
  }

  return (
    <Layout
      style={{
        background: "#112D43",
        color: "#4E83AC",
        minHeight: "100vh",
      }}
    >
      <Header>
        <BackButton />
      </Header>
      <Layout.Content
        style={{ padding: "40px 20px", maxWidth: 1280, margin: "0 auto" }}
      >
        {isLoading ? (
          <Preloader />
        ) : (
          <GameInfo currentGame={currentGame} error={error}></GameInfo>
        )}
{orderStatus === "SUCCESS" && (
   <div>
     <h2>Оплачен</h2>
     <Button onClick={getKeys}>
       Получить ключи
     </Button>
   </div>
)}
{orderStatus === "PENDING" && (
  <Form
    name="basic"
    labelCol={{ span: 8 }}
    wrapperCol={{ span: 16 }}
    style={{ maxWidth: 600 }}
    initialValues={{ remember: true }}
    onFinish={handleSubmit}
    autoComplete="off"
  >
    <Form.Item
      label="Email"
      name="email"
      rules={[{ required: true, message: 'Please input your email!' }]}
    >
      <Input />
    </Form.Item>

    <Form.Item
      label="Password"
      name="password"
      rules={[{ required: true, message: 'Please input your password!' }]}
    >
      <Input.Password />
    </Form.Item>

    <Form.Item label={null}>
      <Button
        type="primary"
        htmlType="submit"
      >
        Оплатить заказ
      </Button>
    </Form.Item>
  </Form>
)}

      </Layout.Content>
    </Layout>
  );
};

export default OrderPage;
