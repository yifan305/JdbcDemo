package com.cs.jdbc;

import java.sql.*;

/**
 * 下面进行查询语句的练习 executeQuery语句
 */
public class JdbcDemo4 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
                // 1 首先数据注册
            Class.forName("com.mysql.jdbc.Driver");
                // 2 注册数据库驱动
             connection = DriverManager.getConnection("jdbc:mysql://localhost/java18", "root", "123456");
            // 3 编写sql语句
            String sql ="select * from stu";
            // 4 获取sql语句的连接功能
            statement = connection.createStatement();
            // 5 执行sql语句的连接对象
            resultSet = statement.executeQuery(sql);
            // 6 对语句进行判断处理
          /*  resultSet.next();
            // 6.1 获取数据
            int i = resultSet.getInt(1);
            String name = resultSet.getString("name");
            String age = resultSet.getString("age");
            System.out.println(i+"---"+name+"--"+age);
          */
          while(resultSet.next()){
              int i = resultSet.getInt(1);
              String name = resultSet.getString("name");
              String age = resultSet.getString("age");
              System.out.println(i+"---"+name+"--"+age);
          }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 7 释放资源
            if (resultSet !=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement !=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection !=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
