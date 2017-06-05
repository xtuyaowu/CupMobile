package cn.cup.dao;

import java.sql.*;

/**
 * Created by Administrator on 2016/10/26.
 */
public class BaseDao {
    private static final String  DRIVER = "com.mysql.jdbc.Driver";//数据库的驱动
    private static final String URL ="jdbc:mysql://127.0.0.1/cupdb?characterEncoding=utf-8&userEncode=true";//数据库的地址
    private static final String USER = "root";//用户名称
    private static final String PASSWORD = "123456";//用户密码

    /**
     * 数据库连接操作
     * @return
     */
    public Connection getConnection(){
        Connection con = null;
        try {
            Class.forName(DRIVER);//加载驱动
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("加载驱动出错："+e.getMessage());
        }
        return con;
    }

    /**
     * 关闭数据库的方法
     * @param rs
     * @param psmt
     * @param con
     */
    public void closeAll(ResultSet rs,PreparedStatement psmt,Connection con){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库的时候rs的错误："+e.getMessage());
            }
        }
        if(psmt != null){
            try {
                psmt.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库的时候的psmt错误："+e.getMessage());
            }
        }
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库的时候的con错误:"+e.getMessage());
            }
        }
    }

}
