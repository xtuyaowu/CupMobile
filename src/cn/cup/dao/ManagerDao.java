package cn.cup.dao;

import cn.cup.bean.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/10/26.
 */
public class ManagerDao extends BaseDao {
    /**
     * 管理员登录
     * @param managerName
     * @param password
     * @return
     */
    public Manager loginManager(String managerName,String password){
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Manager manager = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select * from managers where managerName =? and password = ?");
            psmt.setString(1,managerName);
            psmt.setString(2,password);
            rs = psmt.executeQuery();
            if(rs.next()){
                manager = new Manager(rs.getInt(1),rs.getString(2),rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("管理员登录数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return manager;
    }

    /**
     * 管理员密码修改
     * @param managerName
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public int modifyPassword(String managerName,String oldPassword,String newPassword){
        Connection con = null;
        PreparedStatement psmt = null;
        int val = 0;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("update managers set password = ? where managerName = ? and password=?");
            psmt.setString(1,newPassword);
            psmt.setString(2,managerName);
            psmt.setString(3,oldPassword);
            val = psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("管理员密码修改错误："+ e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }

    /**
     * 根据管理员姓名判断管理员是否存在
     * @param managerName
     * @return
     */
    public Manager existManager(String managerName){
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Manager manager = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select * from managers where managerName =?");
            psmt.setString(1,managerName);
            rs = psmt.executeQuery();
            if(rs.next()){
                manager = new Manager(rs.getInt(1),rs.getString(2),rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("根据管理员姓名判断管理员是否存在数据库错误："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return manager;
    }

}
