package com.cs.jdbc;

import com.cs.util.JdbcUtils;

import javax.security.auth.login.CredentialNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 登陆方法使用PreparedStatement来实现
 */
public class JdbcDemo8 {

    public static void main(String[] args) {
        // 键盘输入登陆的用户名和密码
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入登陆密码");
        String password = sc.nextLine();
        // 调用login方法
        boolean flag = new JdbcDemo8().login(username,password);
        // 判断结果，并输出不同的语句
        if (flag){
            System.out.println("登陆成功！");
        }else{
            System.out.println("用户名或密码错误！");
        }

    }
    public boolean login(String username,String password){
        if (username ==null ||password ==null){
            return false;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 1 获取连接方式
            connection = JdbcUtils.getConnection();
            // 2 定义sql语句
            String sql = "select * from user where username=? and password = ?";
            // 3 获取执行sql的对象
            preparedStatement = connection.prepareStatement(sql);
            // 4 对sql语句中的username和password进行赋值处理
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            // 5 执行查询，不需要传递sql语句 跟statement不一样
            resultSet = preparedStatement.executeQuery();
            // 6 如果有下一行，则返回true；
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 7 关闭服务，并释放资源
            JdbcUtils.close(resultSet,preparedStatement,connection);
        }
        return false;
    }



}
