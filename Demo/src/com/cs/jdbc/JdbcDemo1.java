package com.cs.jdbc;

import com.sun.codemodel.internal.JStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) {
         Connection connection =null;
        Statement statement =null;

        try {
            // 1 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2 获取注册驱动的连接
                 connection = DriverManager.getConnection("jdbc:mysql://localhost/java18", "root", "123456");
            // 3.定义sql语句
            String sql = "insert into stu values (4,'yifan',20)";
            // 4 获取执行sql的对象，Statement
             statement = connection.createStatement();
            // 5 执行sql语句
            int count = statement.executeUpdate(sql);
            // 6 处理结果并释放资源
            System.out.println(count);
            // 对sql语句的结果进行判断，避免空指针异常
            if (count>0){
                System.out.println("添加成功！");
            }else{
                System.out.println("添加失败！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 7 释放资源
            if (statement !=null){
                try {
                    statement.close();
                } catch (SQLException e) {
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
