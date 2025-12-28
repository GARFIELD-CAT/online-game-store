import { CoffeeOutlined } from "@ant-design/icons";
import Title from "antd/es/typography/Title";

const ErrorMessage = () => {
  return (
    <Title
      level={4}
      style={{ textAlign: "center", color: "#2B74AC", margin: "150px 70px" }}
    >
      Во время запроса произошла ошибка. Возможно, проблема с соединением или
      сервер недоступен. Подождите немного и попробуйте ещё раз.{" "}
      <CoffeeOutlined />
    </Title>
  );
};

export default ErrorMessage;
