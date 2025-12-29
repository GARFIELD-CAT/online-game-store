import { Col, Image, Row, Typography, Descriptions } from "antd";
import ScreenShotsCarousel from "../ScreenShotsCarousel/ScreenShotsCarousel";
import ErrorMessage from "../ErrorMessage/ErrorMessage";
import Paragraph from "antd/es/typography/Paragraph";
import { formateDate } from "../../utils/formatDate";
import { useSelector } from "react-redux";
const { Title } = Typography;

const OrderInfo = ({ currentOrder }) => {
  const gameNames = currentOrder.games.map((el) => el.title).join(", ");

  const info = [
    {
      key: "1",
      label: "Номер заказа",
      style: { padding: "1px 5px 5px 1px" },
      children: currentOrder?.id,
    },
    {
      key: "2",
      label: "Дата создания",
      style: { padding: "1px 5px 5px 1px" },
      children: formateDate(currentOrder.created),
    },
    {
      key: "3",
      label: "Названия игр",
      style: { padding: "1px 5px 5px 1px" },
      children: gameNames,
    },
    {
      key: "4",
      label: "Общая стоимость",
      style: { padding: "1px 5px 5px 1px" },
      children: currentOrder.totalAmount,
    },
  ];

  // if (error) {
  //   return <ErrorMessage />;
  // }
  return (
    <Row
      style={{
        display: "flex",
        flexWrap: "wrap",
        justifyContent: "space-around",
      }}
    >
      {/* <Row style={{ marginRight: 20, marginBottom: 30 }}> */}
      {/* <Image src={currentGame.thumbnail} width={"100%"} /> */}
      {/* </Row> */}
      <Col style={{ maxWidth: 744 }}>
        <Row
          style={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <Title
            style={{
              color: "#4E83AC",
              textAlign: "left",
              marginBottom: 10,
            }}
          >
            Заказ
          </Title>
        </Row>
        <Title level={4} style={{ color: "#4E83AC" }}>
          Описание
        </Title>
        <Descriptions
          layout="vertical"
          column={3}
          labelStyle={{ color: "#2B74AC", fontSize: 16 }}
          contentStyle={{ color: "#E8E8E8", fontSize: 14 }}
          items={info}
        />
      </Col>
    </Row>
  );
};
export default OrderInfo;
