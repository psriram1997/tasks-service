"use client";
import { BASE_URL, END_POINTS } from "@/config";
import {
  Form,
  Input,
  Button,
  List,
  Select,
  message,
  Layout,
  Menu,
  Row,
  Col,
  Spin,
} from "antd";
import Column from "antd/es/table/Column";
import axios from "axios";
import { useState } from "react";
import { STATUS } from "../constants";

export default () => {
  const [loading, setLoading] = useState(false);
  const handleSubmit = async (values) => {
    // Get the title, description, and status from the form values
    const { title, description, status } = values;
    console.log(values);
    // Create a new task object with a unique id
    const newTask = {
      title,
      description,
      status,
    };
    let url = BASE_URL + END_POINTS.todo;
    setLoading(true);
    try {
      let response = await axios.post(url, newTask);
      message.success("Task created successfully!");
    } catch (error) {
      console.log(error);
      message.error(error.message);
    }
    setLoading(false);
  };

  return (
    <Spin spinning={loading}>
      <Row justify="center" align="middle">
        <Col lg={12} md={16} sm={24}>
          <Form
            onFinish={handleSubmit}
            layout="vertical"
            initialValues={{
              status: STATUS.TODO,
            }}
          >
            <Form.Item
              name="title"
              label="Title"
              rules={[{ required: true, message: "Please enter a title" }]}
            >
              <Input placeholder="Enter a title for the task" />
            </Form.Item>
            <Form.Item name="description" label="Description">
              <Input.TextArea placeholder="Enter a description for the task" />
            </Form.Item>
            <Form.Item
              name="status"
              label="Status"
              rules={[{ required: true, message: "Please select a status" }]}
            >
              <Select>
                <Select.Option value={STATUS.TODO}>To Do</Select.Option>
                <Select.Option value={STATUS.IN_PROGRESS}>
                  In Progress
                </Select.Option>
                <Select.Option value={STATUS.TODO}>Done</Select.Option>
              </Select>
            </Form.Item>
            <Form.Item>
              <Button type="primary" htmlType="submit">
                Create Task
              </Button>
            </Form.Item>
          </Form>
        </Col>
      </Row>
    </Spin>
  );
};
