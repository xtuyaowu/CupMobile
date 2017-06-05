package cn.cup.dao;

import cn.cup.bean.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class AddressDao extends BaseDao {
    /**
     * 根据会员的id显示所有的地址
     * @param memberId
     * @return
     */
    public ArrayList<Address> getAllAdrress(int memberId){
        ArrayList<Address> addressList = new ArrayList<Address>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select addressId,memberId,receiverName,province,city,county,addressName,receiverTel from addresses where memberId=?");
            psmt.setInt(1,memberId);
            rs = psmt.executeQuery();
            while(rs.next()){
                Address address = new Address();
                address.setAddressId(rs.getInt(1));
                address.setMemberId(rs.getInt(2));
                address.setReceiverName(rs.getString(3));
                address.setProvince(rs.getString(4));
                address.setCity(rs.getString(5));
                address.setCounty(rs.getString(6));
                address.setAddressName(rs.getString(7));
                address.setReceiverTel(rs.getString(8));
                addressList.add(address);
            }
        } catch (SQLException e) {
            System.out.println("根据会员的id显示所有的地址数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return  addressList;
    }

    /**
     * 根据会员的id添加地址
     * @return
     */
    public int addAddress(Address address){
        int val =0;
        Connection con = null;
        PreparedStatement psmt = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("insert into addresses(memberId,receiverName,province,city,county,addressName,receiverTel) values (?,?,?,?,?,?,?)");
            psmt.setInt(1,address.getMemberId());
            psmt.setString(2,address.getReceiverName());
            psmt.setString(3,address.getProvince());
            psmt.setString(4,address.getCity());
            psmt.setString(5,address.getCounty());
            psmt.setString(6,address.getAddressName());
            psmt.setString(7,address.getReceiverTel());
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("根据会员的id添加地址数据库错误："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }

    public Address getLastAddress(int memberId){
        Address address = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("select addressId,memberId,receiverName,province,city,county,addressName,receiverTel from addresses where memberId=? ORDER BY addressId DESC limit 1");
            psmt.setInt(1 , memberId);
            rs = psmt.executeQuery();
            if (rs.next()){
                address = new Address();
                address.setAddressId(rs.getInt(1));
                address.setMemberId(rs.getInt(2));
                address.setReceiverName(rs.getString(3));
                address.setProvince(rs.getString(4));
                address.setCity(rs.getString(5));
                address.setCounty(rs.getString(6));
                address.setAddressName(rs.getString(7));
                address.setReceiverTel(rs.getString(8));
            }
        }catch (Exception e){
            System.out.println("获取最后一条地址失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }
        return address;
    }


    public Address getAddressByAddressId(int addressId){
        Address address = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("select addressId,memberId,receiverName,province,city,county,addressName,receiverTel from addresses where addressId=?");
            psmt.setInt(1 , addressId);
            rs = psmt.executeQuery();
            if (rs.next()){
                address = new Address();
                address.setAddressId(rs.getInt(1));
                address.setMemberId(rs.getInt(2));
                address.setReceiverName(rs.getString(3));
                address.setProvince(rs.getString(4));
                address.setCity(rs.getString(5));
                address.setCounty(rs.getString(6));
                address.setAddressName(rs.getString(7));
                address.setReceiverTel(rs.getString(8));
            }
        }catch (Exception e){
            System.out.println("根据addressId获取地址信息错误，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }
        return address;
    }

}
