// Import React and Next JS components
"use client";
import React, { useState } from "react";
import Head from "next/head";
import Link from "next/link";

import Styles from "./page.module.css";

// Import antd components
import { Form, Input, Button, List, Select, message, Layout, Menu } from "antd";

// Define the initial state of the app
const initialState = {
  tasks: [], // An array of task objects
  filter: "All", // The current filter value
};

// Define the Home component
function Home() {
  // Use the useState hook to manage the app state
  const [state, setState] = useState(initialState);

  // Define a function to handle form submission

  // Define a function to handle status change
  const handleStatusChange = (id, value) => {
    // Find the index of the task with the given id
    const index = state.tasks.findIndex((task) => task.id === id);

    // Update the status of the task at that index
    state.tasks[index].status = value;

    // Update the state with the modified tasks array
    setState((prevState) => ({
      ...prevState,
      tasks: [...prevState.tasks],
    }));

    // Show a success message
    message.success("Task status updated successfully!");
  };

  // Define a function to handle task deletion
  const handleDelete = (id) => {
    // Filter out the task with the given id
    const filteredTasks = state.tasks.filter((task) => task.id !== id);

    // Update the state with the filtered tasks array
    setState((prevState) => ({
      ...prevState,
      tasks: filteredTasks,
    }));

    // Show a success message
    message.success("Task deleted successfully!");
  };

  // Define a function to handle filter change
  const handleFilterChange = (value) => {
    // Update the state with the new filter value
    setState((prevState) => ({
      ...prevState,
      filter: value,
    }));
  };

  // Define a function to render the task list
  const renderTaskList = () => {
    // Apply the filter to the tasks array
    const filteredTasks = state.tasks.filter((task) =>
      state.filter === "All" ? true : task.status === state.filter
    );

    // Return the JSX for the list component
    return (
      <List
        itemLayout="horizontal"
        dataSource={filteredTasks}
        renderItem={(task) => (
          <List.Item
            actions={[
              <Select
                value={task.status}
                onChange={(value) => handleStatusChange(task.id, value)}
              >
                <Select.Option value="To Do">To Do</Select.Option>
                <Select.Option value="In Progress">In Progress</Select.Option>
                <Select.Option value="Done">Done</Select.Option>
              </Select>,
              <Button
                type="primary"
                danger
                onClick={() => handleDelete(task.id)}
              >
                Delete
              </Button>,
            ]}
          >
            <List.Item.Meta title={task.title} description={task.description} />
          </List.Item>
        )}
      />
    );
  };

  // Return the JSX for the home component
  return (
    <div>
      <h1>Task Management App</h1>

      <Select
        style={{ width: 150 }}
        value={state.filter}
        onChange={handleFilterChange}
      >
        <Select.Option value="All">All</Select.Option>
        <Select.Option value="To Do">To Do</Select.Option>
        <Select.Option value="In Progress">In Progress</Select.Option>
        <Select.Option value="Done">Done</Select.Option>
      </Select>
      {renderTaskList()}
    </div>
  );
}

export default Home;
