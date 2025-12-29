import { Layout, Checkbox, Button } from "antd";
import Header from "../components/Header/Header";
import Footer from "../components/Footer/Footer";
import FilterContainer from "../components/FilterContainer/FilterContainer";
import CardList from "../components/CardList/CardList";
import { useDispatch, useSelector } from "react-redux";
import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import Preloader from "../components/Preloader/Preloader";
import useLimit from "../hooks/useLimit";
import BackButton from "../components/BackButton/BackButton";
import Api from "../utils/Api";
import OrderList from "../components/OrderList/OrderList";

const OrderListPage = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const games = useSelector((state) => state.games);
  const isLoading = useSelector((state) => state.isLoading);
  const error = useSelector((state) => state.error);
  const { limit, setLimit, countOfAdd } = useLimit();
  const [orders, setOrders] = useState(null);

  const getOrders = async () => {
    try {
      const data = await Api.getOrders();
      setOrders(data);
    } catch (err) {
      //   setError(true);
      console.error(err);
    }
  };

  useEffect(() => {
    getOrders();
  }, []);

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
        style={{
          padding: "0 40px",
          maxWidth: 1280,
          margin: "0 auto",
          width: "100vw",
        }}
      >
        {!orders ? <Preloader /> : <OrderList data={orders} error={error} />}
      </Layout.Content>
    </Layout>
  );
};

export default OrderListPage;
