package com.cs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * stu表，进行修改删除记录
 */
public class JdbcDemo3 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            // 1 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
          // 2 获取连接对象
            connection = DriverManager.getConnection("jdbc:mysql://localhost/java18", "root", "123456");
          // 3 定义sql语句
          String sql = "delete from stu where id =4";
          // 4 获取sql语句的连接功能
            statement = connection.createStatement();
            // 5 执行sql语句对象
            int update = statement.executeUpdate(sql);
            // 6 处理结果
            System.out.println(update);
            // 避免程序空指针，进行异常处理判断
            if (update>0){
                System.out.println("修改成功");
            }else{
                System.out.println("修改失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
        }finally {
            // 7 释放资源
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
                }
            }
        }

    }
}
