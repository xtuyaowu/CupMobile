package cn.cup.dao;

import cn.cup.bean.Address;
import cn.cup.bean.Cup;
import cn.cup.bean.Order;
import cn.cup.bean.RecordSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2016/10/27.
 */
public class OrderDao extends BaseDao {
    /**
     * 根据会员id显示所有订单
     * @param memberId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public RecordSet<Order> getOrdersByMember(int memberId,int pageNumber,int pageSize){
        RecordSet<Order> orderSet = new RecordSet<Order>();
        ArrayList<Order> orderList = new ArrayList<Order>();
        orderSet.setPageSize(pageSize);
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            //String orderId, int memberId, Cup cup, double cupPrice,
            // int cupQuantity, double subTotal, Date orderDate, Address address
            psmt = con.prepareStatement("SELECT o.orderId,o.memberId,m.memberEmail,o.cupId,c.cupName,c.price,c.cupImages,o.cupQuantity,o.subTotal,o.orderDate,o.flag,o.addressId,a.receiverName,a.province,a.city,a.county,a.addressName,a.receiverTel\n" +
                    "from orders o\n" +
                    "INNER JOIN cups c ON c.cupid = o.cupId \n" +
                    "INNER JOIN addresses a ON a.addressid = o.addressid\n" +
                    "INNER JOIN members m  on m.memberId = o.memberId where o.memberId = ? limit ?,?");
            psmt.setInt(1 , memberId);
            psmt.setInt(2 , (pageNumber-1)*pageSize);
            psmt.setInt(3 , pageSize);
            rs = psmt.executeQuery();
            while(rs.next()){
                Cup cup = new Cup();
                cup.setCupId(rs.getInt(4));
                cup.setCupName(rs.getString(5));
                cup.setPrice(rs.getDouble(6));
                cup.setCupImages(rs.getString(7));
                Address address = new Address();
                address.setAddressId(rs.getInt(12));
                address.setReceiverName(rs.getString(13));
                address.setProvince(rs.getString(14));
                address.setCity(rs.getString(15));
                address.setCounty(rs.getString(16));
                address.setAddressName(rs.getString(17));
                address.setReceiverTel(rs.getString(18));
              //  Order order = new Order(rs.getString(1),rs.getInt(2),cup,rs.getDouble(6),rs.getInt(8),rs.getDouble(9),rs.getDate(10),address,rs.getInt(11));
                Order order = new Order();
                order.setOrderId(rs.getString(1));
                order.setMemberId(rs.getInt(2));
                order.setCup(cup);
                order.setCupPrice(rs.getDouble(6));
                order.setCupQuantity(rs.getInt(8));
                order.setSubTotal(rs.getDouble(9));
                order.setOrderDate(rs.getDate(10));
                order.setAddress(address);
                order.setFlag(rs.getInt(11));
                orderList.add(order);
            }
            orderSet.setRecordSet(orderList);
            rs = psmt.executeQuery("select count(1) from orders where memberId ="+memberId);
            if(rs.next()){
                orderSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        }catch (Exception e){
            System.out.println("显示会员所有订单失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }
        return orderSet;
    }

    /**
     * 根据会员id查找订单总数
     * @param memberId
     * @return
     */
    public int getOrderCountByMember(int memberId){
        int val = 0;
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("SELECT COUNT(*) FROM orders WHERE memberid = ? ");
            psmt.setInt(1 , memberId);
            val = psmt.executeUpdate();
        }catch (Exception e){
            System.out.println("显示会员订单数量失败，原因："+e.getMessage());
        }finally {
            closeAll(null , psmt , con);
        }
        return val;
    }

    /**
     * 根据会员id增加订单
     * @param
     * @return
     */
    public int addOrder(int memberId,int cupId,double cupPrice,int cupQuantity,int addressId){
        int val = 0;
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("insert into orders(orderId,memberId,cupId,cupPrice,cupQuantity,addressId,subtotal) values(?,?,?,?,?,?,?)");
            psmt.setString(1 , UUID.randomUUID().toString());
            psmt.setInt(2,memberId);
            psmt.setInt(3,cupId);
            psmt.setDouble(4, cupPrice);
            psmt.setInt(5, cupQuantity);
            psmt.setInt(6,addressId);
            psmt.setDouble(7, cupPrice * cupQuantity);
            val = psmt.executeUpdate();
        }catch (Exception e){
            System.out.println("增加会员订单失败，原因："+e.getMessage());
        }finally {
            closeAll(null , psmt , con);
        }
        return val;
    }


    /**
     * 分页显示所有的订单
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public RecordSet<Order> getAllOrders(int pageNumber,int pageSize){
        RecordSet<Order> orderSet = new RecordSet<Order>();
        ArrayList<Order> orderList = new ArrayList<Order>();
        orderSet.setPageSize(pageSize);
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("SELECT o.orderId,o.memberId,m.memberEmail,o.cupId,c.cupName,c.price,c.cupImages,o.cupQuantity,o.subTotal,o.orderDate,o.flag,o.addressId,a.receiverName,a.province,a.city,a.county,a.addressName,a.receiverTel\n" +
                    "from orders o\n" +
                    "INNER JOIN cups c ON c.cupid = o.cupId \n" +
                    "INNER JOIN addresses a ON a.addressid = o.addressid\n" +
                    "INNER JOIN members m  on m.memberId = o.memberId limit ?,?");
            psmt.setInt(1,pageNumber);
            psmt.setInt(2,pageSize);
            rs = psmt.executeQuery();
            while(rs.next()){
                Cup cup = new Cup();
                cup.setCupId(rs.getInt(4));
                cup.setCupName(rs.getString(5));
                cup.setPrice(rs.getDouble(6));
                cup.setCupImages(rs.getString(7));
                Address address = new Address();
                address.setAddressId(rs.getInt(12));
                address.setReceiverName(rs.getString(13));
                address.setProvince(rs.getString(14));
                address.setCity(rs.getString(15));
                address.setCounty(rs.getString(16));
                address.setAddressName(rs.getString(17));
                address.setReceiverTel(rs.getString(18));
                Order order = new Order();
                order.setOrderId(rs.getString(1));
                order.setMemberId(rs.getInt(2));
                order.setMemberEmail(rs.getString(3));
                order.setCup(cup);
                order.setCupPrice(rs.getDouble(6));
                order.setCupQuantity(rs.getInt(8));
                order.setSubTotal(rs.getDouble(9));
                order.setOrderDate(rs.getDate(10));
                order.setAddress(address);
                order.setFlag(rs.getInt(11));
                orderList.add(order);
            }
            orderSet.setRecordSet(orderList);
            rs = psmt.executeQuery("select count(1) from orders");
            if(rs.next()){
                orderSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("分页显示所有的订单数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return orderSet;
    }



    /**
     * 分页显示已发货的订单
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public RecordSet<Order> getAllShippedOrders(int pageNumber,int pageSize,int flag){
        RecordSet<Order> orderSet = new RecordSet<Order>();
        ArrayList<Order> orderList = new ArrayList<Order>();
        orderSet.setPageSize(pageSize);
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("SELECT o.orderId,o.memberId,m.memberEmail,m.nickName,o.cupId,c.cupName,c.price,c.cupImages,o.cupQuantity,o.subTotal,o.orderDate,o.flag,o.addressId,a.receiverName,a.province,a.city,a.county,a.addressName,a.receiverTel\n  " +
                    "from orders o\n" +
                    "INNER JOIN cups c ON c.cupid = o.cupId \n" +
                    "INNER JOIN addresses a ON a.addressid = o.addressid\n" +
                    "INNER JOIN members m  on m.memberId = o.memberId where flag=1 ORDER BY o.orderId ASC limit ?,? ");
            psmt.setInt(1,pageNumber);
            psmt.setInt(2,pageSize);
            rs = psmt.executeQuery();
            while(rs.next()){
                Cup cup = new Cup();
                cup.setCupId(rs.getInt(5));
                cup.setCupName(rs.getString(6));
                cup.setPrice(rs.getDouble(7));
                cup.setCupImages(rs.getString(8));
                Address address = new Address();
                address.setAddressId(rs.getInt(13));
                address.setReceiverName(rs.getString(14));
                address.setProvince(rs.getString(15));
                address.setCity(rs.getString(16));
                address.setCounty(rs.getString(17));
                address.setAddressName(rs.getString(18));
                address.setReceiverTel(rs.getString(19));
                Order order = new Order();
                order.setOrderId(rs.getString(1));
                order.setMemberId(rs.getInt(2));
                order.setMemberEmail(rs.getString(3));
                order.setNickName(rs.getString(4));
                order.setCup(cup);
                order.setCupPrice(rs.getDouble(7));
                order.setCupQuantity(rs.getInt(9));
                order.setSubTotal(rs.getDouble(10));
                order.setOrderDate(rs.getDate(11));
                order.setAddress(address);
                order.setFlag(rs.getInt(12));
                orderList.add(order);
            }
            orderSet.setRecordSet(orderList);
            rs = psmt.executeQuery("select count(1) from orders where flag=1");
            if(rs.next()){
                orderSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("分页显示已发货的订单数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return orderSet;
    }



    /**
     * 分页显示未发货的订单
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public RecordSet<Order> getAllNoShippedOrders(int pageNumber,int pageSize,int flag){
        RecordSet<Order> orderSet = new RecordSet<Order>();
        ArrayList<Order> orderList = new ArrayList<Order>();
        orderSet.setPageSize(pageSize);
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("SELECT o.orderId,o.memberId,m.memberEmail,m.nickName,o.cupId,c.cupName,c.price,c.cupImages,o.cupQuantity,o.subTotal,o.orderDate,o.flag,o.addressId,a.receiverName,a.province,a.city,a.county,a.addressName,a.receiverTel\n  " +
                    "from orders o\n" +
                    "INNER JOIN cups c ON c.cupid = o.cupId \n" +
                    "INNER JOIN addresses a ON a.addressid = o.addressid\n" +
                    "INNER JOIN members m  on m.memberId = o.memberId where flag=0 ORDER BY o.orderId ASC  limit ?,?");
            psmt.setInt(1,pageNumber);
            psmt.setInt(2,pageSize);
            rs = psmt.executeQuery();
            while(rs.next()){
                Cup cup = new Cup();
                cup.setCupId(rs.getInt(5));
                cup.setCupName(rs.getString(6));
                cup.setPrice(rs.getDouble(7));
                cup.setCupImages(rs.getString(8));
                Address address = new Address();
                address.setAddressId(rs.getInt(13));
                address.setReceiverName(rs.getString(14));
                address.setProvince(rs.getString(15));
                address.setCity(rs.getString(16));
                address.setCounty(rs.getString(17));
                address.setAddressName(rs.getString(18));
                address.setReceiverTel(rs.getString(19));
                Order order = new Order();
                order.setOrderId(rs.getString(1));
                order.setMemberId(rs.getInt(2));
                order.setMemberEmail(rs.getString(3));
                order.setNickName(rs.getString(4));
                order.setCup(cup);
                order.setCupPrice(rs.getDouble(7));
                order.setCupQuantity(rs.getInt(9));
                order.setSubTotal(rs.getDouble(10));
                order.setOrderDate(rs.getDate(11));
                order.setAddress(address);
                order.setFlag(rs.getInt(12));
                orderList.add(order);
            }
            orderSet.setRecordSet(orderList);
            rs = psmt.executeQuery("select count(1) from orders where flag=0");
            if(rs.next()){
                orderSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("分页显示所有的订单数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return orderSet;
    }

    /**
     * 根据订单的ID发货
     * @param orderId
     * @return
     */
    public int sendOrderByOrderId(String orderId){
        Connection con = null;
        PreparedStatement psmt = null;
        int val =0;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("update orders set flag = 1 where orderId = ?");
            psmt.setString(1, orderId);
            val = psmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("根据订单的ID发货数据库错误："+e.getMessage());
        }finally {
            super.closeAll(null, psmt, con);
        }
        return  val;
    }

    /**
     * 返回未发货的总订单条数
     * @return
     */
    public int getAllNoShippedOrderNumbers(){
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        int val =-1;
        con = super.getConnection();
        try{
            psmt = con.prepareStatement("select count(1) from orders where flag = 0;");
            rs = psmt.executeQuery();
            if(rs.next()){
                val = rs.getInt(1);//返回的记录条数
            }
        }catch (SQLException e){
            System.out.println("返回未发货的总订单条数数据库出错："+e.getMessage());
        }finally {
            super.closeAll(rs,psmt,con);
        }
        return  val;
    }



}
