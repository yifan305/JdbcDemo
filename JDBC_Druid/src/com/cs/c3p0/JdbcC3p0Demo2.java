package com.cs.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0演示操作
 */
public class JdbcC3p0Demo2 {
    public static void main(String[] args) throws SQLException {
        /*
        //  1 创建数据库的连接池对象
        DataSource ds = new ComboPooledDataSource();
        // 2 获取连接池对象
        Connection connection = ds.getConnection();
        // 3 打印数据库连接池
        System.out.println(connection);
         */
        // 1 创建c3p0的数据库连接池对象，获取datasource，使用指定的名称
        DataSource ds = new ComboPooledDataSource("otherc3p0");
        // 2. 获取连接池对象，并且获取最大的连接池对象
        for (int i = 1; i <=8 ; i++) {
            Connection connection = ds.getConnection();
            System.out.println(i+":"+connection);
        }


    }
}
