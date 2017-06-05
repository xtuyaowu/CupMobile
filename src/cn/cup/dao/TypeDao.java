package cn.cup.dao;


import cn.cup.bean.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class TypeDao extends BaseDao{
    /**
     * 获取杯子所有的类型
     * @return
     */
    public ArrayList<Type> getAllTypes(){
        ArrayList<Type> typeList = new ArrayList<Type>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select * from  cuptypes order by typeId");
            rs = psmt.executeQuery();
            while(rs.next()){
                Type type = new Type(rs.getInt(1),rs.getString(2));
                typeList.add(type);
            }
        } catch (SQLException e) {
            System.out.println("获取杯子所有的类型数据库错误:"+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return  typeList;
    }

    /**
     * 添加杯子的类型分类
     * @return
     */
    public int addType(Type type){
        Connection con = null;
        PreparedStatement psmt = null;
        int val =0;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("insert into cuptypes(typeName) values (?)");
            psmt.setString(1,type.getTypeName());
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("添加杯子的类型分类数据库错误："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return  val;
    }

    /**
     * 根据材质的id,修改杯子的类型分类
     * @return
     */
    public int modifyType(Type type){
        Connection con = null;
        PreparedStatement psmt = null;
        int val =0;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("update cuptypes set typeName = ? where typeId= ?");
            psmt.setString(1,type.getTypeName());
            psmt.setInt(2,type.getTypeId());
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("根据材质的id,修改杯子的类型分类数据库出错："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return  val;
    }

    /**
     * 根据类型的名字获取类型
     * @param typeName
     * @return
     */
    public Type getTypeByName(String typeName) {
        Type type = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();

        try {
            psmt = con.prepareStatement("select * from cuptypes where typename = ?");
            psmt.setString(1, typeName);
            rs = psmt.executeQuery();
            if (rs.next()) {
                type = new Type(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("根据类型的名字获取类型数据库出错" + e.getMessage());
        } finally {
            super.closeAll(rs, psmt, con);
        }
        return type;
    }

    /**
     * 根据类型的id获取类型
     * @param typeId
     * @return
     */
    public Type getTypeById(int typeId) {
        Type type = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();

        try {
            psmt = con.prepareStatement("select * from cuptypes where typeId = ?");
            psmt.setInt(1, typeId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                type = new Type(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("根据类型的id获取类型数据库出错" + e.getMessage());
        } finally {
            super.closeAll(rs, psmt, con);
        }
        return type;
    }


}
