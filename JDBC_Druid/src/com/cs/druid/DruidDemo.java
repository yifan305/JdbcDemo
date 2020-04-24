package com.cs.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.cs.utils.JDBCUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 练习使用Druid数据库连接池新的工具类
 */
public class DruidDemo {

    public static void main(String[] args)  {
        /**
         * 完成添加操作，给account表添加一条记录
         */
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1 获取连接对象
            conn = JDBCUtils.getConnection();
            // 2 定义sql语句
            String sql = "insert into account values(null ,?,?)";
            // 3 获取sql数据库的对象
            pstmt = conn.prepareStatement(sql);
            // 4 给sql语句中的？进行赋值处理
            pstmt.setString(1,"张焱");
            pstmt.setDouble(2,5000);
            // 5 执行sql语句操作
            int count = pstmt.executeUpdate();
            // 6 数据处理操作
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
    }

}
