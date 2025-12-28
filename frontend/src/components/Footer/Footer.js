import { Layout } from "antd";
import Paragraph from "antd/es/typography/Paragraph";

const Footer = () => {
  return (
    <Layout.Footer
      style={{
        background: "#00213A",
        display: "flex",
        justifyContent: "end",
        padding: "10px calc((100% - 1280px)/2) 10px 0",
      }}
    >
      <Paragraph style={{ color: "#4E83AC", margin: "0 20px" }}>
        Made by Nadya Sadovskaya 2023
      </Paragraph>
    </Layout.Footer>
  );
};

export default Footer;
