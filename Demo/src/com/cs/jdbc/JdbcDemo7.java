package com.cs.jdbc;

import com.cs.util.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 练习
 *  需求 1， 通过键盘录入用户名
 *      2， 判断用户是否登陆成功
 */
public class JdbcDemo7 {
    public static void main(String[] args) {
        // 首先进行键盘录入
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password= scanner.nextLine();
        // 调用方法
        boolean flag = new JdbcDemo7().login(username, password);
        // 判断结束，输出不同的语句
        if (flag){
            System.out.println("登陆成功");
        }else{
            System.out.println("用户名或密码错误，请重试");
        }
    }
    public boolean login(String username, String password){
        // 首先判断用户名和密码是否为空
        if (username == null || password == null){
            return false;
        }
        // 下面进行数据的连接操作
        // 1 获取连接
        Connection conn= null;
        Statement stat = null;
        ResultSet resultSet = null;
        try {
            conn= JdbcUtils.getConnection();
            // 定义sql语句
            String sql = "select * from user where username= '"+username+"'and password='"+password+"'";
            // 获取sql的连接对象
            stat = conn.createStatement();
            // 执行sql对象
            resultSet = stat.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(resultSet,stat,conn);
        }
        return false;
    }
}
