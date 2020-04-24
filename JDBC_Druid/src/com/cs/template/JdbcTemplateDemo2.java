package com.cs.template;

import com.cs.bean.Emp;
import com.cs.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 练习使用Spring Jdbc的方法
 */

public class JdbcTemplateDemo2 {

    // Junit单元测试，可以让每个方法都可以独立执行
    // 获取JDBCTemplate对象
    private  JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 修改1号数据中salary为1000
     */
    @Test
    public void test1(){
        // 定义sql语句
        String sql = "update emp set salary =10000 where id = ?";
        int count = template.update(sql, 1);
        //执行sql语句
        System.out.println(count);

    }

    /**
     *  2 增加一条记录
     */
    @Test
    public void test2(){
        // 定义sql语句
        String sql = "insert into emp(id,name,salary) values(?,?,?)";
        //执行sql语句
        int count = template.update(sql, 5, "警察", 10000);
        System.out.println(count);
    }
    /**
     * 3 删除刚刚增加的一条记录
     */
    @Test
    public void test3(){
        // 1 定义sql语句
        String sql = "delete from emp where id = ?";
        // 2 执行sql语句
        int count = template.update(sql, 5);
        System.out.println(count);
    }
    /**
     * 4 查询id=1的记录，将其封装为Map集合
     */
    @Test
    public void test4(){
        // 1 定义sql语句
        String sql  = "select * from emp where id =?";
        Map<String, Object> map = template.queryForMap(sql, 1);
        System.out.println(map);
    }
    /**
     * 5 查询所有记录，将其封装为List
     */
    @Test
    public void test5(){
        // 1 定义sql语句
        String sql = "select * from emp ";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
    }
    /**
     * 6 查询所有的记录，将其封装为Emp对象的List集合
     */
    @Test
    public void test6(){
        // 执行sql语句
        String sql = "select * from emp";
        List<Emp> list = template.query(sql, new RowMapper<Emp>() {

            @Override
            public Emp mapRow(ResultSet resultSet, int i) throws SQLException {
                Emp emp = new Emp();
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                double salary = resultSet.getDouble("salary");
                Date join_date = resultSet.getDate("join_date");
                int dept_id = resultSet.getInt("dept_id");
                emp.setId(id);
                emp.setName(name);
                emp.setGender(gender);
                emp.setSalary(salary);
                emp.setJoin_date(join_date);
                emp.setDept_id(dept_id);

                return emp;
            }
        });
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }
    /**
     * 查询所有记录，将其封装为Emp对象的list集合
     */
    @Test
    public void test6_1(){
        // 定义sql语句
        String sql = "select * from emp";
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    /**
     * 7 查询Emp表中的所有记录数--查询总记录数
     */
    @Test
    public void test7(){
        // 定义sql语句
        String sql = "select count(id) from emp";
        Long aLong = template.queryForObject(sql, Long.class);
        System.out.println(aLong);
    }



}
