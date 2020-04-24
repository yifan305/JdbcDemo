package com.cs.jdbc;

import com.cs.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务操作练习数据转账操作
 */
public class JdbcDemo9 {
    public static void main(String[] args) {
             Connection connection = null;
             PreparedStatement preparedStatement = null;
             PreparedStatement preparedStatement1 = null;
        try {
            // 1 获取连接
            connection = JdbcUtils.getConnection();
            // 开启事务方式
             connection.setAutoCommit(false);
             // 2 定义sql语句
            String sql1 = "update account set balance = balance - ? where id =?";
            String sql2 = "update account set balance = balance + ? where id =?";
            // 3 获取sql的对象
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement1 = connection.prepareStatement(sql2);
            // 3.1 给sql设置参数
            preparedStatement.setDouble(1,500);
            preparedStatement.setInt(2,1);
            preparedStatement1.setDouble(1,500);
            preparedStatement1.setInt(2,2);
            // 4 执行参数
            preparedStatement.executeUpdate();
            // 当我们人为制造困难的时候，让它触发 rollback
            int i= 3/0;
            preparedStatement1.executeUpdate();
            // 4。1 提交事务
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
            // 当事务出现差错的时候，事务回滚
            try {
                if (connection !=null){
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtils.close(preparedStatement,connection);
            // 注意下面并不能关闭connection，因为上一步已经关闭，不能重复关闭
            JdbcUtils.close(preparedStatement1,null);

        }
    }
}
