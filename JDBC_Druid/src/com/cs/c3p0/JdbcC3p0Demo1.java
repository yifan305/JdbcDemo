package com.cs.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0演示操作
 */
public class JdbcC3p0Demo1 {
    public static void main(String[] args) throws SQLException {
        //  1 创建数据库的连接池对象
        DataSource ds = new ComboPooledDataSource();
        // 2 获取连接池对象
        Connection connection = ds.getConnection();
        // 3 打印数据库连接池
        System.out.println(connection);
    }
}
