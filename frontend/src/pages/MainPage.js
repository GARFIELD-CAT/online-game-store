import { Layout, Checkbox, Button } from "antd";
import Header from "../components/Header/Header";
import Footer from "../components/Footer/Footer";
import FilterContainer from "../components/FilterContainer/FilterContainer";
import CardList from "../components/CardList/CardList";
import { useDispatch, useSelector } from "react-redux";
import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getGames, createOrder } from "../store/actions";
import Preloader from "../components/Preloader/Preloader";
import useLimit from "../hooks/useLimit";
import Api from "../utils/Api";
import {
  getOrderAction,
  setIsLoadingAction,
  setError,
} from "../store/gameReducer";
import OrderListButton from "../components/OrderListButton/OrderListButton";

const MainPage = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const lastElement = useRef();
  const observer = useRef();
  const games = useSelector((state) => state.games);
  const isLoading = useSelector((state) => state.isLoading);
  const error = useSelector((state) => state.error);
  const { limit, setLimit, countOfAdd } = useLimit();
  const token = useSelector((state) => state.token);
  const [orderGames, setOrderGames] = useState([]);
  const order = useSelector((state) => state.order);

  useEffect(() => {
    getGames(dispatch, token);
  }, [dispatch, token]);

  const onChange = (checkedValues) => {
    setOrderGames(checkedValues);
  };

  const handleSubmit = async () => {
    dispatch(setIsLoadingAction(true));

    try {
      const res = await Api.createOrder(orderGames);
      dispatch(getOrderAction(order));
      dispatch(setIsLoadingAction(false));
      navigate(`/order/${res.id}`);
    } catch (err) {
      dispatch(setError(true));
      console.error(err);
      dispatch(setIsLoadingAction(false));
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
      </Header>
      <Layout.Content
        style={{
          padding: "20px 40px",
          maxWidth: 1280,
          margin: "0 auto",
          width: "100vw",
        }}
      >
        {isLoading ? (
          <Preloader />
        ) : (
          <>
            <Checkbox.Group style={{ width: "100%" }} onChange={onChange}>
              <CardList data={games.slice(0, limit)} error={error} />
            </Checkbox.Group>
          </>
        )}
        <div ref={lastElement} style={{ height: 20 }}></div>
        <div
          style={{
            width: "100%",
            display: "flex",
            justifyContent: "right",
          }}
        >
          <Button onClick={handleSubmit} disabled={!orderGames.length}>
            Сформировать заказ
          </Button>
        </div>
      </Layout.Content>
    </Layout>
  );
};

export default MainPage;
