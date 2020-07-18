package com.gxj.elsdemo.util;

import java.sql.*;

/**
 * 使用jdbc 访问MYSQL数据库
 */
public class JDBCUtils {

    //数据库用户名
    private static  final String USER="root";
    //数据库代码
    private static final  String PASSWORD="Mysql147.@";
    //数据库url
    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    private static final String URL="mysql://49.235.27.226:3339/sys?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";

    public static void main(String[] args) {
//        1.注册数据库的驱动，使用反射注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("注册驱动成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        2.获得数据库的访问连接权利
        Connection connection=null;
        try {
            connection=DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("连接数据库成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        3.向数据库发送要执行的SQL语句
        Statement statement = null;
        try {
            statement =connection.createStatement();
            String sql="select * from sys_config";
            boolean execute = statement.execute(sql);
            System.out.println(execute);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
//        4.获取执行的返回结果
//        5.关闭数据库连接
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
