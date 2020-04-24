package com.cs.jdbc;

import com.cs.bean.Emp;
import com.cs.util.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个方法，查询emp表封装为对象，然后封装载为集合，然后返回
 */
public class JdbcDemo6 {
    public static void main(String[] args) {
        List<Emp> list = new JdbcDemo6().findAll();
        System.out.println(list);
        System.out.println(list.size());
    }

    /**
     * 查询所有的emp对象
     * @return
     */
    public List<Emp> findAll(){
        Connection conn = null;
        Statement stat = null;
        ResultSet resultSet = null;
        List<Emp> list = null;
        try {
            // 1 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2 获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java18", "root", "123456");
            // 3 定义sql语句
            String sql = "select * from emp";
            // 4获取执行sql语句的连接对象
            stat = conn.createStatement();
            // 5 执行sql语句
            resultSet = stat.executeQuery(sql);
            // 遍历结果集 封装对象，装载集合
            Emp emp = null;

            list = new ArrayList<Emp>();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                Date join_date = resultSet.getDate("join_date");
                double salary = resultSet.getDouble("salary");
                int dept_id = resultSet.getInt("dept_id");
                emp = new Emp();
                emp.setId(id);
                emp.setName(name);
                emp.setGender(gender);
                emp.setDept_id(dept_id);
                emp.setJoin_date(join_date);
                emp.setSalary(salary);
                //装载集合
                list.add(emp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
         catch (SQLException e) {
            e.printStackTrace();
        }
        /*finally {
            if (resultSet !=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stat != null){
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        */
        JdbcUtils.close(resultSet,stat,conn);
        return list;
    }

}
