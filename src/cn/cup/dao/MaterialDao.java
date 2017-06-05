package cn.cup.dao;

import cn.cup.bean.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class MaterialDao extends BaseDao{

    /**
     * 获取杯子所有的材质
     * @return
     */
   public ArrayList<Material> getAllMaterials(){
       ArrayList<Material> materialList = new ArrayList<Material>();
       Connection con = null;
       PreparedStatement psmt = null;
       ResultSet rs = null;
       con = super.getConnection();
       try {
           psmt = con.prepareStatement("select * from  cupmaterials order by materialId asc");
           rs = psmt.executeQuery();
           while(rs.next()){
               Material material = new Material(rs.getInt(1),rs.getString(2));
               materialList.add(material);
           }
       } catch (SQLException e) {
           System.out.println("获取杯子所有的材质数据库错误:"+e.getMessage());
       } finally {
           super.closeAll(rs,psmt,con);
       }
       return  materialList;
   }

    /**
     * 添加杯子的材质分类
     * @return
     */
    public int addMaterial(Material material){
        Connection con = null;
        PreparedStatement psmt = null;
        int val =0;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("insert into cupmaterials(materialName) values (?)");
            psmt.setString(1,material.getMaterialName());
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("添加杯子的材质分类数据库错误："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return  val;
    }

    /**
     * 根据材质的id,修改杯子的材质分类
     * @return
     */
    public int modifyMaterial(Material material){
        Connection con = null;
        PreparedStatement psmt = null;
        int val =0;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("update cupmaterials set materialName = ? where materialId= ?");
            psmt.setString(1,material.getMaterialName());
            psmt.setInt(2,material.getMaterialId());
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("根据材质的id,修改杯子的材质分类数据库出错："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return  val;
    }

    /**
     * 根据材质的名字获取材质
     * @param Material
     * @return
     */
    public Material getMaterialByName(String materialName){
        Material material = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select * from cupmaterials where materialName = ?");
            psmt.setString(1,materialName);
            rs = psmt.executeQuery();
            if(rs.next()){
                material = new Material(rs.getInt(1),rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("根据材质的名字获取材质数据库错误:"+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return material;
    }


    /**
     * 根据材质的id,显示杯子
     * @return
     */
    public Material getMaterialById(int materialId){
        Material material = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select * from cupmaterials where materialId = ?");
            psmt.setInt(1,materialId);
            rs = psmt.executeQuery();
            if(rs.next()){
                material = new Material(rs.getInt(1),rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("根据材质的id获取材质数据库错误:"+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return material;
    }

}
