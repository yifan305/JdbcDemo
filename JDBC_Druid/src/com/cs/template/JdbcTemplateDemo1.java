package com.cs.template;

import com.cs.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate的快速入门
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        // 1 导入jar包
        //2 创建JdbcTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        // 3 调用方法
        String sql = "update account set balance = 5000 where id = ?";
        int count = template.update(sql, 1);
        System.out.println(count);


    }
}
