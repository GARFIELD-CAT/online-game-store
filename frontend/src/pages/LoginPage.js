import { Layout, Form, Input, Button } from "antd";
import { useDispatch, useSelector } from "react-redux";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../store/actions";

const GamePage = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleSubmit = (data) => {
    const { email, password } = data;

    loginUser(dispatch, email, password);
    navigate("/");
  };

  return (
    <Layout
      style={{
        background: "#112D43",
        color: "#4E83AC",
        minHeight: "100vh",
        marginTop: "300px",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <Form
        name="basic"
        labelCol={{ span: 8 }}
        wrapperCol={{ span: 16 }}
        style={{ maxWidth: 600 }}
        initialValues={{ remember: true }}
        onFinish={handleSubmit}
        //         onFinishFailed={onFinishFailed}
        autoComplete="off"
      >
        <Form.Item
          label="Email"
          name="email"
          rules={[{ required: true, message: "Обязательное поле" }]}
        >
          <Input />
        </Form.Item>

        <Form.Item
          label="Password"
          name="password"
          rules={[{ required: true, message: "Обязательное поле" }]}
        >
          <Input.Password />
        </Form.Item>

        <Form.Item label={null}>
          <Button
            type="primary"
            htmlType="submit"
            //             onClick={(data) => handleSubmit(data)}
          >
            Submit
          </Button>
        </Form.Item>
      </Form>
    </Layout>
  );
};

export default GamePage;
