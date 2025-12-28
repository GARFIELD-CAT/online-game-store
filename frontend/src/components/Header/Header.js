import { Layout, Typography } from "antd";
const { Title } = Typography;

const Header = ({ children }) => {
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
        Free to play games
      </Title>
      {children}
    </Layout.Header>
  );
};

export default Header;
