import { Button } from "antd";
import { useNavigate } from "react-router-dom";

const BackButton = () => {
  const navigate = useNavigate();

  return (
    <Button
      type="primary"
      ghost
      onClick={() => navigate(-1)}
      style={{ margin: "18px 20px 12px 20px" }}
    >
      Back to games
    </Button>
  );
};
export default BackButton;
