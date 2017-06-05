package cn.cup.dao;

import cn.cup.bean.Region;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/27.
 */
public class RegionDao extends BaseDao {
    // 显示所有省
    public ArrayList<Region> getProvince(){
        ArrayList<Region> regions = new ArrayList<Region>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("SELECT regioncode , fullname , shortname FROM regions WHERE LENGTH(regioncode) = 2");
            rs = psmt.executeQuery();
            while (rs.next()){
                regions.add(new Region(rs.getInt(1) , rs.getString(2) , rs.getString(3)));
            }
        }catch (Exception e){
            System.out.println("查找省失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }
        return regions;
    }

    // 再根据省查找并显示市
    public ArrayList<Region> getCityByProvince(int provinceCode){
        ArrayList<Region> regions = new ArrayList<Region>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("SELECT regioncode , fullname , shortname FROM regions WHERE LENGTH(regioncode) = 4 AND regionCode LIKE ?");
            psmt.setString(1 , provinceCode+"%");
            rs = psmt.executeQuery();
            while (rs.next()){
                regions.add(new Region(rs.getInt(1) , rs.getString(2) , rs.getString(3)));
            }
        }catch (Exception e){
            System.out.println("查找省失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }
        return regions;
    }

    // 再根据市查找并显示县
    public ArrayList<Region> getCountryByCity(int cityCode){
        ArrayList<Region> regions = new ArrayList<Region>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("SELECT regioncode , fullname , shortname FROM regions WHERE LENGTH(regioncode) >= 6 AND regionCode LIKE ?");
            psmt.setString(1 , cityCode+"%");
            rs = psmt.executeQuery();
            while (rs.next()){
                regions.add(new Region(rs.getInt(1) , rs.getString(2) , rs.getString(3)));
            }
        }catch (Exception e){
            System.out.println("查找省失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }
        return regions;
    }


    // 根据省code查找省
    public String getProvinceBycode(int provinceCode){
        String province = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("SELECT fullname FROM regions WHERE RegionCode = ?");
            psmt.setInt(1 , provinceCode);
            rs = psmt.executeQuery();
            if (rs.next()){
                province = rs.getString(1);
            }
        }catch (Exception e){
            System.out.println("根据省code查找省失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }
        return province;
    }

    // 根据市code查找市
    public String getCityBycode(int cityCode){
        String city = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("SELECT fullname FROM regions WHERE RegionCode = ?");
            psmt.setInt(1 , cityCode);
            rs = psmt.executeQuery();
            if (rs.next()){
                city = rs.getString(1);
            }
        }catch (Exception e){
            System.out.println("根据市code查找市失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }
        return city;
    }

    // 根据县code查找县
    public String getCountryBycode(int countryCode){
        String country = null;
        if (countryCode != 0){
            Connection con = null;
            PreparedStatement psmt = null;
            ResultSet rs = null;
            try {
                con = getConnection();
                psmt = con.prepareStatement("SELECT fullname FROM regions WHERE RegionCode = ?");
                psmt.setInt(1 , countryCode);
                rs = psmt.executeQuery();
                if (rs.next()){
                    country = rs.getString(1);
                }
            }catch (Exception e){
                System.out.println("根据县code查找县失败，原因："+e.getMessage());
            }finally {
                closeAll(rs , psmt , con);
            }
        }

        return country;
    }

}
