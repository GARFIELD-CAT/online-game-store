import { Button } from "antd";
import { useNavigate } from "react-router-dom";

const OrderListButton = () => {
  const navigate = useNavigate();

  return (
    <Button
      type="primary"
      ghost
      onClick={() => navigate("/order-list")}
      style={{ margin: "18px 10px 12px 10px" }}
    >
      Список заказов
    </Button>
  );
};
export default OrderListButton;
