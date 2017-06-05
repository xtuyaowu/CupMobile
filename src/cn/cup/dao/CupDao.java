package cn.cup.dao;

import cn.cup.bean.Cup;
import cn.cup.bean.Material;
import cn.cup.bean.RecordSet;
import cn.cup.bean.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class CupDao extends BaseDao {

    /**
     * 显示全部的杯子
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public RecordSet<Cup> getAllCups(int pageNumber,int pageSize){
        RecordSet<Cup> cupSet = new RecordSet<Cup>();
        ArrayList<Cup> cupList = new ArrayList<Cup>();
        cupSet.setPageSize(pageSize);//设置每页的个数
        int limitNumbers =pageNumber*pageSize;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId order by cupId desc limit ?,?");
            //psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId ORDER BY c.cupId ASC ");
            psmt.setInt(1,(pageNumber-1)*pageSize);
            psmt.setInt(2,pageSize);
            rs = psmt.executeQuery();
            while(rs.next()){
                Material material = new Material(rs.getInt(3),rs.getString(4));
                Type type = new Type(rs.getInt(5),rs.getString(6));
                Cup cup = new Cup();
                cup.setCupId(rs.getInt(1));
                cup.setCupName(rs.getString(2));
                cup.setMaterial(material);
                cup.setType(type);
                cup.setCapacity(rs.getDouble(7));
                cup.setPrice(rs.getInt(8));
                cup.setCupImages(rs.getString(9));
                cup.setClickTime(rs.getInt(10));
                cup.setSaleNumber(rs.getInt(11));
                cup.setIntroduce(rs.getString(12));
                cupList.add(cup);
            }
            cupSet.setRecordSet(cupList);
            rs = psmt.executeQuery("select count(1) from cups");
            if(rs.next()){
                cupSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("显示全部的杯子数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return cupSet;
    }

    /**
     * 显示全部的杯子后台
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public RecordSet<Cup> getAllCupsManager(int pageNumber,int pageSize){
        RecordSet<Cup> cupSet = new RecordSet<Cup>();
        ArrayList<Cup> cupList = new ArrayList<Cup>();
        cupSet.setPageSize(pageSize);//设置每页的个数
        int limitNumbers =pageNumber*pageSize;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId order by cupId desc limit ?,?");
            //psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId ORDER BY c.cupId ASC ");
            psmt.setInt(1,pageNumber);
            psmt.setInt(2,pageSize);
            rs = psmt.executeQuery();
            while(rs.next()){
                Material material = new Material(rs.getInt(3),rs.getString(4));
                Type type = new Type(rs.getInt(5),rs.getString(6));
                Cup cup = new Cup();
                cup.setCupId(rs.getInt(1));
                cup.setCupName(rs.getString(2));
                cup.setMaterial(material);
                cup.setType(type);
                cup.setCapacity(rs.getDouble(7));
                cup.setPrice(rs.getInt(8));
                cup.setCupImages(rs.getString(9));
                cup.setClickTime(rs.getInt(10));
                cup.setSaleNumber(rs.getInt(11));
                cup.setIntroduce(rs.getString(12));
                cupList.add(cup);
            }
            cupSet.setRecordSet(cupList);
            rs = psmt.executeQuery("select count(1) from cups");
            if(rs.next()){
                cupSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("显示全部的杯子数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return cupSet;
    }

    /**
     * 根据材质id显示杯子
     * @param materialId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public RecordSet<Cup> getCupByMaterial(int materialId,int pageNumber,int pageSize ){
        RecordSet<Cup> cupSet = new RecordSet<Cup>();
        ArrayList<Cup> cupList = new ArrayList<Cup>();
        cupSet.setPageSize(pageSize);//设置每页的个数
        int limitNumbers =pageNumber*pageSize;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId where c.cupMaterialId =? limit ?");
            psmt.setInt(1,materialId);
            psmt.setInt(2,limitNumbers);
            rs = psmt.executeQuery();
            while(rs.next()){
                Material material = new Material(rs.getInt(3),rs.getString(4));
                Type type = new Type(rs.getInt(5),rs.getString(6));
                Cup cup = new Cup();
                cup.setCupId(rs.getInt(1));
                cup.setCupName(rs.getString(2));
                cup.setMaterial(material);
                cup.setType(type);
                cup.setCapacity(rs.getDouble(7));
                cup.setPrice(rs.getInt(8));
                cup.setCupImages(rs.getString(9));
                cup.setClickTime(rs.getInt(10));
                cup.setSaleNumber(rs.getInt(11));
                cup.setIntroduce(rs.getString(12));
                cupList.add(cup);
            }
            cupSet.setRecordSet(cupList);
            rs = psmt.executeQuery("select count(1) from cups where cupMaterialId ="+materialId);
            if(rs.next()){
                cupSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("根据材质id显示杯子数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return cupSet;
    }


    /**
     * 根据类型id显示杯子
     * @param typeId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public RecordSet<Cup> getCupByType(int typeId,int pageNumber,int pageSize ){
        RecordSet<Cup> cupSet = new RecordSet<Cup>();
        ArrayList<Cup> cupList = new ArrayList<Cup>();
        cupSet.setPageSize(pageSize);//设置每页的个数
        int limitNumbers =pageNumber*pageSize;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId where c.cupTypeId =? limit ?");
            psmt.setInt(1,typeId);
            psmt.setInt(2,limitNumbers);
            rs = psmt.executeQuery();
            while(rs.next()){
                Material material = new Material(rs.getInt(3),rs.getString(4));
                Type type = new Type(rs.getInt(5),rs.getString(6));
                Cup cup = new Cup();
                cup.setCupId(rs.getInt(1));
                cup.setCupName(rs.getString(2));
                cup.setMaterial(material);
                cup.setType(type);
                cup.setCapacity(rs.getDouble(7));
                cup.setPrice(rs.getInt(8));
                cup.setCupImages(rs.getString(9));
                cup.setClickTime(rs.getInt(10));
                cup.setSaleNumber(rs.getInt(11));
                cup.setIntroduce(rs.getString(12));
                cupList.add(cup);
            }
            cupSet.setRecordSet(cupList);
            rs = psmt.executeQuery("select count(1) from cups where cupMaterialId ="+typeId);
            if(rs.next()){
                cupSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("根据类型id显示杯子数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return cupSet;
    }

    /**
     * 根据容量范围显示杯子
     * @param
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public RecordSet<Cup> getCupByCapacity(double capacityStart,double capacityEnd, int pageNumber,int pageSize ){
        RecordSet<Cup> cupSet = new RecordSet<Cup>();
        ArrayList<Cup> cupList = new ArrayList<Cup>();
        cupSet.setPageSize(pageSize);//设置每页的个数
        int limitNumbers =pageNumber*pageSize;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId where c.cupCapacity BETWEEN ? and ? limit ?");
            psmt.setDouble(1, capacityStart);
            psmt.setDouble(2, capacityEnd);
            psmt.setInt(3,limitNumbers);
            rs = psmt.executeQuery();
            while(rs.next()){
                Material material = new Material(rs.getInt(3),rs.getString(4));
                Type type = new Type(rs.getInt(5),rs.getString(6));
                Cup cup = new Cup();
                cup.setCupId(rs.getInt(1));
                cup.setCupName(rs.getString(2));
                cup.setMaterial(material);
                cup.setType(type);
                cup.setCapacity(rs.getDouble(7));
                cup.setPrice(rs.getInt(8));
                cup.setCupImages(rs.getString(9));
                cup.setClickTime(rs.getInt(10));
                cup.setSaleNumber(rs.getInt(11));
                cup.setIntroduce(rs.getString(12));
                cupList.add(cup);
            }
            cupSet.setRecordSet(cupList);
            rs = psmt.executeQuery("select count(1) from cups where cupMaterial BETWEEN "+capacityStart+" and"+capacityEnd);
            if(rs.next()){
                cupSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("根据容量id显示杯子数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return cupSet;
    }

    /**
     * 根据点击次数倒序显示杯子
     * @return
     */
    public RecordSet<Cup> getCupsByClickTime(int pageNumber,int pageSize){
        RecordSet<Cup> cupSet = new RecordSet<Cup>();
        ArrayList<Cup> cupList = new ArrayList<Cup>();
        cupSet.setPageSize(pageSize);//设置每页的个数
        int limitNumbers =pageNumber*pageSize;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId order by clickTime desc limit ?");
            psmt.setInt(1,limitNumbers);
            rs = psmt.executeQuery();
            while(rs.next()){
                Material material = new Material(rs.getInt(3),rs.getString(4));
                Type type = new Type(rs.getInt(5),rs.getString(6));
                Cup cup = new Cup();
                cup.setCupId(rs.getInt(1));
                cup.setCupName(rs.getString(2));
                cup.setMaterial(material);
                cup.setType(type);
                cup.setCapacity(rs.getDouble(7));
                cup.setPrice(rs.getInt(8));
                cup.setCupImages(rs.getString(9));
                cup.setClickTime(rs.getInt(10));
                cup.setSaleNumber(rs.getInt(11));
                cup.setIntroduce(rs.getString(12));
                cupList.add(cup);
            }
            cupSet.setRecordSet(cupList);
            rs = psmt.executeQuery("select count(1) from cups");
            if(rs.next()){
                cupSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("根据点击次数倒序显示杯子数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return cupSet;
    }


    /**
     * 根据杯子的id，杯子的点击次数+1
     * @param cupId
     * @return
     */
    public int addCupClickTime(int cupId){
        int val =0;
        Connection con = null;
        PreparedStatement psmt = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("update cups set clickTime = clickTime + 1 where cupId = ?");
            psmt.setInt(1,cupId);
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(" 根据杯子的id，杯子的点击次数+1数据库出错："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }

    /**
     * 添加杯子
     * @param cup
     * @return
     */
    public int addCup(Cup cup){
        int val =0;
        Connection con = null;
        PreparedStatement psmt = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("insert into cups(cupName,cupMaterialId,cupTypeId,cupCapacity,price,cupImages,introduce) values(?,?,?,?,?,?,?)");
            psmt.setString(1,cup.getCupName());
            psmt.setInt(2, cup.getMaterial().getMaterialId());
            psmt.setInt(3,cup.getType().getTypeId());
            psmt.setDouble(4,cup.getCapacity());
            psmt.setDouble(5,cup.getPrice());
            psmt.setString(6,cup.getCupImages());
            psmt.setString(7,cup.getIntroduce());
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("添加杯子数据库错误："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }

    /**
     * 根据杯子的ID修改杯子
     * @param cup
     * @return
     */
    public int modifyCup(Cup cup){
        int val =0;
        Connection con = null;
        PreparedStatement psmt = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("update cups set cupName = ?,cupMaterialId= ?,cupTypeId=?,cupCapacity = ? ,price = ?,cupImages=?,introduce =?  where cupId =?");
            psmt.setString(1,cup.getCupName());
            psmt.setInt(2, cup.getMaterial().getMaterialId());
            psmt.setInt(3, cup.getType().getTypeId());
            psmt.setDouble(4, cup.getCapacity());
            psmt.setDouble(5,cup.getPrice());
            psmt.setString(6, cup.getCupImages());
            psmt.setString(7,cup.getIntroduce());
            psmt.setInt(8,cup.getCupId());
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("根据杯子的ID修改杯子数据库出错:"+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }

    /**
     * 根据热销倒序显示杯子
     * @return
     */
    public RecordSet<Cup> getCupsByHotTime(int pageNumber,int pageSize){
        RecordSet<Cup> cupSet = new RecordSet<Cup>();
        ArrayList<Cup> cupList = new ArrayList<Cup>();
        cupSet.setPageSize(pageSize);//设置每页的个数
        int limitNumbers =pageNumber*pageSize;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName," +
                    "c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId order by c.saleNumber desc limit ?,?");
            psmt.setInt(1,(pageNumber-1)*2);
            //最后改成pagesize
            psmt.setInt(2,pageSize);
            rs = psmt.executeQuery();
            while(rs.next()){
                Material material = new Material(rs.getInt(3),rs.getString(4));
                Type type = new Type(rs.getInt(5),rs.getString(6));
                Cup cup = new Cup();
                cup.setCupId(rs.getInt(1));
                cup.setCupName(rs.getString(2));
                cup.setMaterial(material);
                cup.setType(type);
                cup.setCapacity(rs.getDouble(7));
                cup.setPrice(rs.getInt(8));
                cup.setCupImages(rs.getString(9));
                cup.setClickTime(rs.getInt(10));
                cup.setSaleNumber(rs.getInt(11));
                cup.setIntroduce(rs.getString(12));
                cupList.add(cup);
            }
            cupSet.setRecordSet(cupList);
            rs = psmt.executeQuery("select count(1) from cups");
            if(rs.next()){
                cupSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("根据热销倒序显示杯子数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return cupSet;
    }


    /**
     * 根据杯子的id，杯子订单提交，次数+1
     * @param cupId
     * @return
     */
    public int addCupHotTime(int cupId){
        int val =0;
        Connection con = null;
        PreparedStatement psmt = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("update cups set saleNumber = saleNumber + 1 where cupId = ?");
            psmt.setInt(1,cupId);
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(" 根据杯子的id，杯子订单提交+1数据库出错："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }
    /**
     * 根据杯子id查找杯子
     *
     * @param cupId
     * @return
     */
    public Cup getCupByCupId(int cupId){
        Cup cup = new Cup();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId," +
                    "m.materialName,c.cupTypeId,t.typeName,c.cupCapacity," +
                    "c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce " +
                    "from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId " +
                    "INNER JOIN cuptypes t on c.cupTypeId = t.typeId " +
                    "WHERE c.cupId = ?");
            psmt.setInt(1 , cupId);
            rs = psmt.executeQuery();
            if (rs.next()){
                Material material = new Material(rs.getInt(3),rs.getString(4));
                Type type = new Type(rs.getInt(5),rs.getString(6));
                cup.setCupId(rs.getInt(1));
                cup.setCupName(rs.getString(2));
                cup.setMaterial(material);
                cup.setType(type);
                cup.setCapacity(rs.getDouble(7));
                cup.setPrice(rs.getInt(8));
                cup.setCupImages(rs.getString(9));
                cup.setClickTime(rs.getInt(10));
                cup.setSaleNumber(rs.getInt(11));
                cup.setIntroduce(rs.getString(12));
            }
        }catch (Exception e){
            System.out.println("根据杯子id获取杯子失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }

        return cup;
    }
    /**
     * 根据杯子的材质、类型和容量显示杯子
     * @param materialId
     * @param typeId
     * @param startCapatity
     * @param endCapatity
     * @param pageNum
     * @param pageSize
     * @return
     */
    public RecordSet<Cup> getCupByFilter(int materialId,int typeId,double startCapatity,double endCapatity,int pageNum,int pageSize,int order){
        RecordSet<Cup> cupSet = new RecordSet<Cup>();
        ArrayList<Cup> cupList = new ArrayList<Cup>();
        cupSet.setPageSize(pageSize);
//        String sql = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            if(materialId != 0 && typeId==0 && startCapatity==0 && endCapatity==0){
                if(order == 2){
                    //只选择了材质
//                    System.out.println("order=2");
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupMaterialId = ? order by c.saleNumber desc limit ?,?");
                }else{
                    //只选择了材质
//                    System.out.println("order=1");
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupMaterialId = ? limit ?,?");
//                    sql = "select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupMaterialId = ? limit ?,?";
                }
                psmt.setInt(1,materialId);
//                System.out.println((pageNum-1)*pageSize);
                psmt.setInt(2,(pageNum-1)*pageSize);
                psmt.setInt(3,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                psmt = con.prepareStatement("select count(1) from cups where cupMaterialId = ?");
                psmt.setInt(1 , materialId);
                rs = psmt.executeQuery();
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }else if(materialId == 0 && typeId!=0 &&  startCapatity==0 && endCapatity==0){
                if(order == 2){
                    //只选择了类型
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupTypeId = ? order by c.saleNumber desc limit ?,?");
                }else{
                    //只选择了类型
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupTypeId = ? limit ?,?");
                }
                psmt.setInt(1,typeId);
                psmt.setInt(2,(pageNum-1)*pageSize);
                psmt.setInt(3,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                psmt = con.prepareStatement("select count(1) from cups where cupTypeId = ?");
                psmt.setInt(1 , typeId);
                rs = psmt.executeQuery();
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }else if(materialId == 0 && typeId==0 &&  startCapatity>=0 && endCapatity>0){
                if(order == 2){
                    //只选择了容量
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupCapacity  BETWEEN ? and ? order by c.saleNumber desc limit ?,?");
                }else{
                    //只选择了容量
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupCapacity BETWEEN ? and ? limit ?,?");
                }
                psmt.setDouble(1,startCapatity);
                psmt.setDouble(2,endCapatity);
                psmt.setInt(3,(pageNum-1)*pageSize);
                psmt.setInt(4,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                psmt = con.prepareStatement("select count(1) from cups where cupCapacity BETWEEN "+startCapatity+" and "+endCapatity);
                rs = psmt.executeQuery();
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }else if(materialId != 0 && typeId!=0 && startCapatity==0 && endCapatity==0){
                if(order == 2){
                    //选择了材质和类型
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupMaterialId=? and c.cupTypeId=? order by c.saleNumber desc limit ?,?");
                }else{
                    //选择了材质和类型
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupMaterialId=? and c.cupTypeId=? limit ?,?");
                }
                psmt.setInt(1,materialId);
                psmt.setInt(2,typeId);
                psmt.setInt(3,(pageNum-1)*pageSize);
                psmt.setInt(4,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                psmt = con.prepareStatement("select count(1) from cups where cupMaterialId ="+materialId+" and cupTypeId="+typeId);
                rs = psmt.executeQuery();
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }else if(materialId != 0 && typeId==0 && startCapatity>=0 && endCapatity>0){
                if(order == 2){
                    //选择了材质和容量
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupMaterialId=? and c.cupCapacity BETWEEN ? and ? order by c.saleNumber desc limit ?,?");
                }else{
                    //选择了材质和容量
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupMaterialId=? and c.cupCapacity BETWEEN ? and ? limit ?,?");
                }
                psmt.setInt(1,materialId);
                psmt.setDouble(2,startCapatity);
                psmt.setDouble(3,endCapatity);
                psmt.setInt(4,(pageNum-1)*pageSize);
                psmt.setInt(5,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                psmt = con.prepareStatement("select count(1) from cups where cupMaterialId = "+materialId+" and cupCapacity BETWEEN "+startCapatity+" and "+endCapatity);
                rs = psmt.executeQuery();
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }else if(materialId == 0 && typeId!=0 && startCapatity>=0 && endCapatity>0){
                if(order == 2){
                    //选择了类型和容量
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupTypeId=? and c.cupCapacity BETWEEN ? and ? order by c.saleNumber desc limit ?,?");
                }else{
                    //选择了类型和容量
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupTypeId=? and c.cupCapacity BETWEEN ? and ? limit ?,?");
                }
                psmt.setInt(1,materialId);
                psmt.setDouble(2,startCapatity);
                psmt.setDouble(3,endCapatity);
                psmt.setInt(4,(pageNum-1)*pageSize);
                psmt.setInt(5,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                psmt = con.prepareStatement("select count(1) from cups where cupTypeId = "+typeId+" and cupCapacity BETWEEN "+startCapatity+" and "+endCapatity);
                rs = psmt.executeQuery();
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }else if(materialId != 0 && typeId!=0 && startCapatity>=0 && endCapatity>0){
                if(order == 2){
                    //三个都选择了
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupMaterialId=? and c.cupTypeId=? and c.cupCapacity BETWEEN ? and ? order by c.saleNumber desc limit ?,?");
                }else{
                    //三个都选择了
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupMaterialId=? and c.cupTypeId=? and c.cupCapacity BETWEEN ? and ? limit ?,?");
                }

                psmt.setInt(1,materialId);
                psmt.setInt(2,typeId);
                psmt.setDouble(3,startCapatity);
                psmt.setDouble(4,endCapatity);
                psmt.setInt(5,(pageNum-1)*pageSize);
                psmt.setInt(6,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                psmt = con.prepareStatement("select count(1) from cups where cupMaterialId = "+materialId+" and cupTypeId="+typeId+" and cupCapacity BETWEEN "+startCapatity+" and "+endCapatity);
                rs = psmt.executeQuery();
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }else if(materialId == 0 && typeId==0 && startCapatity==0 && endCapatity==0){
                System.out.println("默认全部");
                if(order == 2){
                    //默认全部
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId order by c.saleNumber desc limit ?,?");
                }else{
                    //默认全部
                    psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId limit ?,?");
                }

                psmt.setInt(1,(pageNum-1)*pageSize);
                psmt.setInt(2,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                psmt = con.prepareStatement("select count(1) from cups");
                rs = psmt.executeQuery();
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println(" 根据杯子的材质、类型和容量显示杯子数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return  cupSet;
    }

    /**
     * 后台多条件筛选杯子，根据材质、类型和关键字进行搜索
     * @param materialId
     * @param typeId
     * @param keyWords
     * @param pageCount
     * @param pageSize
     * @return
     */
    public RecordSet<Cup> getCupByMoreConditions(int materialId,int typeId,String keyWords,int pageCount,int pageSize){
        RecordSet<Cup> cupSet = new RecordSet<Cup>();
        ArrayList<Cup> cupList = new ArrayList<Cup>();
        cupSet.setPageSize(pageSize);
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            //只选择材质
            if(materialId != 0 && typeId==0 && (keyWords==null || "".equals(keyWords))){
                psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupMaterialId = ? order by c.cupId desc limit ?,?");
                psmt.setInt(1,materialId);
                psmt.setInt(2,pageCount);
                psmt.setInt(3,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                rs = psmt.executeQuery("select count(1) from cups where cupMaterialId = "+materialId);
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }
            //只选择类型
            else if(materialId == 0 && typeId!=0 && (keyWords==null || "".equals(keyWords))){
                psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupTypeId = ? order by c.cupId desc limit ?,?");
                psmt.setInt(1,typeId);
                psmt.setInt(2,pageCount);
                psmt.setInt(3,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                rs = psmt.executeQuery("select count(1) from cups where cupTypeId = "+typeId);
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }
            //只选择关键字
            else if(materialId == 0 && typeId ==0 && (keyWords!=null && !"".equals(keyWords))){
                psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupName like ? order by c.cupId desc limit ?,?");
                psmt.setString(1, "%" + keyWords + "%");
                psmt.setInt(2,pageCount);
                psmt.setInt(3,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                rs = psmt.executeQuery("select count(1) from cups where cupName like  '%" +keyWords+ "%'");
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }
            //选择材质和类型
            else if(materialId != 0 && typeId !=0 && (keyWords==null || "".equals(keyWords))){
                psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupMaterialId = ? and c.cupTypeId = ? order by c.cupId desc limit ?,?");
                psmt.setInt(1,materialId);
                psmt.setInt(2,typeId);
                psmt.setInt(3,pageCount);
                psmt.setInt(4,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                rs = psmt.executeQuery("select count(1) from cups where cupMaterialId ="+materialId+" and cupTypeId ="+typeId);
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }
            //选择材质和关键字
            else if(materialId != 0 && typeId ==0 && (keyWords!=null && !"".equals(keyWords))){
                psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupMaterialId = ? and c.cupName like ? order by c.cupId desc limit ?,?");
                psmt.setInt(1,materialId);
                psmt.setString(2, "%" + keyWords + "%");
                psmt.setInt(3,pageCount);
                psmt.setInt(4,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                rs = psmt.executeQuery("select count(1) from cups where cupMaterialId ="+materialId+" and cupName like '"+keyWords+"'");
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }
            //选择类型和关键字
            else if(materialId == 0 && typeId !=0 && (keyWords!=null && !"".equals(keyWords))){
                psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE c.cupTypeId = ? and c.cupName like ? order by c.cupId desc limit ?,?");
                psmt.setInt(1,typeId);
                psmt.setString(2, "%" + keyWords + "%");
                psmt.setInt(3,pageCount);
                psmt.setInt(4,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                rs = psmt.executeQuery("select count(1) from cups where cupTypeId ="+typeId+" and cupName like '%" +keyWords+ "%'");
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }
            //选择全部
            else if(materialId != 0 && typeId !=0 && (keyWords!=null && !"".equals(keyWords))){
                psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId WHERE  c.cupMaterialId = ? and c.cupTypeId = ? and c.cupName like ? order by c.cupId desc limit ?,?");
                psmt.setInt(1,materialId);
                psmt.setInt(2,typeId);
                psmt.setString(3, "%" + keyWords + "%");
                psmt.setInt(4,pageCount);
                psmt.setInt(5,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                rs = psmt.executeQuery("select count(1) from cups where  c.cupMaterialId ="+materialId+" cupTypeId ="+typeId+" and cupName like '"+keyWords+"'");
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }
            //三个都不选
            else if(materialId == 0 && typeId ==0 && (keyWords ==null || "".equals(keyWords))){
                psmt = con.prepareStatement("select c.cupId,c.cupName,c.cupMaterialId,m.materialName,c.cupTypeId,t.typeName,c.cupCapacity,c.price,c.cupImages,c.clickTime,c.saleNumber,c.introduce from cups c INNER JOIN cupmaterials m on c.cupMaterialId = m.materialId INNER JOIN cuptypes t on c.cupTypeId = t.typeId  order by c.cupId desc limit ?,?");
                psmt.setInt(1,pageCount);
                psmt.setInt(2,pageSize);
                rs = psmt.executeQuery();
                while(rs.next()){
                    Material material = new Material(rs.getInt(3),rs.getString(4));
                    Type type = new Type(rs.getInt(5),rs.getString(6));
                    Cup cup = new Cup();
                    cup.setCupId(rs.getInt(1));
                    cup.setCupName(rs.getString(2));
                    cup.setMaterial(material);
                    cup.setType(type);
                    cup.setCapacity(rs.getDouble(7));
                    cup.setPrice(rs.getInt(8));
                    cup.setCupImages(rs.getString(9));
                    cup.setClickTime(rs.getInt(10));
                    cup.setSaleNumber(rs.getInt(11));
                    cup.setIntroduce(rs.getString(12));
                    cupList.add(cup);
                }
                cupSet.setRecordSet(cupList);
                rs = psmt.executeQuery("select count(1) from cups");
                if(rs.next()){
                    cupSet.setRowCount(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("后台多条件筛选杯子，根据材质、类型和关键字进行搜索错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return  cupSet;
    }

    /**
     * 根据材质的ID获得杯子的数量
     * @param materialId
     * @return
     */
    public int getMaterialCupNumberByMaterialId(int materialId){
        int number = 0;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select count(*) from cups where cupMaterialId =?");
            psmt.setInt(1,materialId);
            rs = psmt.executeQuery();
            if(rs.next()){
                number = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("根据材质的ID获得杯子的数量数据库错误:"+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return  number;
    }

    /**
     * 根据类型的ID获得杯子的数量
     * @param typeId
     * @return
     */
    public int getTypeCupNumberByTypeId(int typeId){
        int number = 0;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select count(*) from cups where cupTypeId =?");
            psmt.setInt(1,typeId);
            rs = psmt.executeQuery();
            if(rs.next()){
                number = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("根据类型的ID获得杯子的数量数据库错误:"+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return  number;
    }


}
