"use client";
import { Inter } from "next/font/google";
import { AntdRegistry } from "@ant-design/nextjs-registry";

import Head from "next/head";
import Link from "next/link";

import { Form, Input, Button, Layout, Menu } from "antd";

import "./globals.css";

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body>
        <AntdRegistry>
          <div className="container">
            <Head>
              <title>Task Management App</title>
              <link rel="icon" href="/favicon.ico" />
            </Head>

            <Layout style={{ height: "100%" }}>
              <Layout.Header>
                <Menu
                  theme="dark"
                  mode="horizontal"
                  defaultSelectedKeys={["1"]}
                >
                  <Menu.Item key="1">
                    <Link href="/">Home</Link>
                  </Menu.Item>
                </Menu>
              </Layout.Header>
              <Layout.Content style={{ padding: "50px", height: "100%" }}>
                {children}
              </Layout.Content>
              <Layout.Footer style={{ textAlign: "center" }}>
                Tasker App © 2024 Created by Sriram
              </Layout.Footer>
            </Layout>
          </div>
        </AntdRegistry>
      </body>
    </html>
  );
}
