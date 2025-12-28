import React from "react";
import { Space, Spin } from "antd";
import { LoadingOutlined } from "@ant-design/icons";

const Preloader = () => {
  const antIcon = (
    <LoadingOutlined
      style={{
        marginTop: 100,
        fontSize: 50,
      }}
      spin
    />
  );
  return (
    <Space
      direction="vertical"
      style={{
        display: "flex",
        flexDirection: "row",
        justifyContent: "center",
      }}
    >
      <Spin indicator={antIcon} />
    </Space>
  );
};

export default Preloader;
