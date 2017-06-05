package cn.cup.dao;

import cn.cup.bean.Info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */
public class InfoDao extends BaseDao{
    /**
     * 获取材料信息统计
     * @return
     */
    public ArrayList<Info> getInfoForMaterial(){
        ArrayList<Info> infos = new ArrayList<Info>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try{
            con = super.getConnection();
            psmt =con.prepareStatement("SELECT cm.materialName,COUNT(1) FROM cupmaterials AS cm INNER JOIN cups AS cu ON cm.materialId = cu.cupmaterialid GROUP BY cu.cupmaterialid");
            rs = psmt.executeQuery();
            while(rs.next()){
                String name = rs.getString(1);
                int count = rs.getInt(2);
                Info info = new Info(name,count);
                infos.add(info);
            }
        }catch (Exception ex){
            System.out.print("加载材质统计信息失败，原因是："+ex.getMessage());
        }finally {
            super.closeAll(rs,psmt,con);
        }
        return infos;
    }



    /**
     * 获取分类信息统计
     * @return
     */
    public ArrayList<Info> getInfoForType(){
        ArrayList<Info> infos = new ArrayList<Info>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try{
            con = super.getConnection();
            psmt =con.prepareStatement("SELECT ct.typeName,COUNT(1) FROM cuptypes AS ct INNER JOIN cups AS cu ON ct.typeId = cu.cupTypeId GROUP BY cu.cupTypeId");
            rs = psmt.executeQuery();
            while(rs.next()){
                String name = rs.getString(1);
                int count = rs.getInt(2);
                Info info = new Info(name,count);
                infos.add(info);
            }
        }catch (Exception ex){
            System.out.print("加载类型统计信息失败，原因是："+ex.getMessage());
        }finally {
            super.closeAll(rs,psmt,con);
        }
        return infos;
    }




    /**
     * 获取点击排行信息统计
     * @return
     */
    public ArrayList<Info> getInfoForClick(){
        ArrayList<Info> infos = new ArrayList<Info>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try{
            con = super.getConnection();
            psmt =con.prepareStatement("SELECT cupName,clickTime FROM  cups  ORDER BY clickTime desc  LIMIT 10");
            rs = psmt.executeQuery();
            while(rs.next()){
                String name = rs.getString(1);
                int count = rs.getInt(2);
                Info info = new Info(name,count);
                infos.add(info);
            }
        }catch (Exception ex){
            System.out.print("加载点击统计信息失败，原因是："+ex.getMessage());
        }finally {
            super.closeAll(rs,psmt,con);
        }
        return infos;
    }




    /**
     * 获取销量排行信息统计
     * @return
     */
    public ArrayList<Info> getInfoForSales(){
        ArrayList<Info> infos = new ArrayList<Info>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try{
            con = super.getConnection();
            psmt =con.prepareStatement("SELECT cupName,saleNumber FROM  cups  ORDER BY saleNumber desc LIMIT 10");
            rs = psmt.executeQuery();
            while(rs.next()){
                String name = rs.getString(1);
                int count = rs.getInt(2);
                Info info = new Info(name,count);
                infos.add(info);
            }
        }catch (Exception ex){
            System.out.print("加载销量统计信息失败，原因是："+ex.getMessage());
        }finally {
            super.closeAll(rs,psmt,con);
        }
        return infos;
    }

}




