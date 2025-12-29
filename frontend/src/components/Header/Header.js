import { Button, Layout, Row, Typography } from "antd";
import { useDispatch } from "react-redux";
import { getTokenAction } from "../../store/gameReducer";
import { useNavigate } from "react-router-dom";
import { logoutUser } from "../../store/actions";
const { Title } = Typography;

const Header = ({ children }) => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const logout = () => {
    localStorage.clear();
    dispatch(getTokenAction(null));
    logoutUser(dispatch);
    navigate("/login");
  };

  return (
    <Layout.Header
      style={{
        height: "fit-content",
        display: "flex",
        alignItems: "start",
        background: "#00335A",
        padding: "0 calc((100% - 1280px)/2)",
        justifyContent: "space-between",
        flexWrap: "wrap",
      }}
    >
      <Title
        level={3}
        style={{
          color: "#2B74AC",
          marginTop: 18,
          marginLeft: 20,
        }}
      >
        Онлайн-магазин игр
      </Title>
      <Row>
        {children}
        <Button
          onClick={logout}
          style={{ margin: "18px 10px 12px 10px" }}
          type="primary"
        >
          Выйти
        </Button>
      </Row>
    </Layout.Header>
  );
};

export default Header;
