import { Col, Image, Row, Typography, Descriptions } from "antd";
import ScreenShotsCarousel from "../ScreenShotsCarousel/ScreenShotsCarousel";
import ErrorMessage from "../ErrorMessage/ErrorMessage";
import Paragraph from "antd/es/typography/Paragraph";
import { formateDate } from "../../utils/formatDate";
const { Title } = Typography;

const GameInfo = ({ currentGame, error }) => {
  const sysReq = currentGame.minimum_system_requirements;
  const info = [
    {
      key: "1",
      label: "Publisher",
      style: { padding: "1px 5px 5px 1px" },
      children: `${currentGame.publisher}`,
    },
    {
      key: "2",
      label: "Developer",
      style: { padding: "1px 5px 5px 1px" },
      children: `${currentGame.developer}`,
    },
    {
      key: "3",
      label: "Genre",
      style: { padding: "1px 5px 5px 1px" },
      children: `${currentGame.genre}`,
    },
    {
      key: "4",
      label: "Platform",
      style: { padding: "1px 5px 5px 1px" },
      children: `${currentGame.platform}`,
    },
    {
      key: "5",
      label: "Release Date",
      style: { padding: "1px 5px 5px 1px" },
      children: `${formateDate(currentGame.release_date)}`,
    },
  ];

  const systemReq = [
    {
      key: "1",
      label: "OS",
      style: { padding: "1px 5px 5px 1px" },
      children: `${sysReq?.os}`,
    },
    {
      key: "2",
      label: "Memory",
      style: { padding: "1px 5px 5px 1px" },
      children: `${sysReq?.memory}`,
    },
    {
      key: "3",
      label: "Storage",
      style: { padding: "1px 5px 5px 1px" },
      children: `${sysReq?.storage}`,
    },
    {
      key: "4",
      label: "Processor",
      style: { padding: "1px 5px 5px 1px" },
      children: `${sysReq?.processor}`,
    },
    {
      key: "5",
      label: "Graphics",
      style: { padding: "1px 5px 5px 1px" },
      children: `${sysReq?.graphics}`,
    },
  ];
  if (error) {
    return <ErrorMessage />;
  }
  return (
    <Row
      style={{
        display: "flex",
        flexWrap: "wrap",
        justifyContent: "space-around",
      }}
    >
      <Row style={{ marginRight: 20, marginBottom: 30 }}>
        <Image src={currentGame.thumbnail} width={"100%"} />
      </Row>
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
            {currentGame.title}
          </Title>
        </Row>
        <Title level={4} style={{ color: "#4E83AC" }}>
          Information
        </Title>
        <Descriptions
          layout="vertical"
          column={3}
          labelStyle={{ color: "#2B74AC", fontSize: 16 }}
          contentStyle={{ color: "#E8E8E8", fontSize: 14 }}
          items={info}
        />
        <ScreenShotsCarousel currentGame={currentGame} />

        <Title level={4} style={{ color: "#4E83AC" }}>
          Minimum System Requirements
        </Title>
        {sysReq ? (
          <Descriptions
            layout="vertical"
            column={3}
            labelStyle={{ color: "#2B74AC", fontSize: 16 }}
            contentStyle={{ color: "#E8E8E8", fontSize: 14 }}
            items={systemReq}
          />
        ) : (
          <Paragraph style={{ color: "#2B74AC", marginTop: 40 }}>
            No information
          </Paragraph>
        )}
      </Col>
    </Row>
  );
};
export default GameInfo;
