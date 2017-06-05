package cn.cup.dao;

import cn.cup.bean.Cart;
import cn.cup.bean.Cup;
import cn.cup.bean.RecordSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class CartDao extends BaseDao {

    /**
     * 根据用户Id显示购物车的所有商品
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public RecordSet<Cart> getAllCarts(int memberId,int pageNumber,int pageSize){
        RecordSet<Cart> cartSet = new RecordSet<Cart>();
        ArrayList<Cart> cartList = new ArrayList<Cart>();
        cartSet.setPageSize(pageSize);
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = super.getConnection();
            psmt = con.prepareStatement("select ca.memberId,ca.cupId,c.cupName,c.price,c.cupImages,ca.cupQuantity,ca.subTotal,ca.cartid from carts ca INNER JOIN members m on ca.memberId = m.memberId INNER JOIN cups c on ca.cupId = c.cupId where ca.memberId = ? limit ?,?");
            psmt.setInt(1,memberId);
            psmt.setInt(2,(pageNumber-1)*pageSize);
            psmt.setInt(3,pageSize);
            rs = psmt.executeQuery();
            while(rs.next()){
                Cup cup = new Cup(rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getString(5));
                Cart cart = new Cart();
                cart.setMemberId(rs.getInt(1));
                cart.setCup(cup);
                cart.setCupPrice(rs.getInt(4));
                cart.setCupQuantity(rs.getInt(6));
                cart.setSubTotal(rs.getDouble(7));
                cart.setCartId(rs.getInt(8));
                cartList.add(cart);
            }
            cartSet.setRecordSet(cartList);
            rs = psmt.executeQuery("select count(1) from carts where memberId ="+memberId);
            if(rs.next()){
                cartSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("根据用户Id显示购物车的所有商品数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return cartSet;

    }


    /**
     * 将商品加入购物车
     * @param memberId
     * @param cupId
     * @param quantity
     * @param cup
     * @return
     */
    public int addCart(int memberId , int cupId , int quantity , Cup cup){
        int val = 0;
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = super.getConnection();
            psmt = con.prepareStatement("insert into carts(memberId,cupId,cupPrice,cupQuantity,subTotal) values(?,?,?,?,?)");
            psmt.setInt(1 , memberId);
            psmt.setInt(2, cupId);
            psmt.setDouble(3,cup.getPrice());
            psmt.setInt(4,quantity);
            psmt.setDouble(5,quantity*cup.getPrice());
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("将商品加入购物车数据库错误："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }

    /**
     *根据memberId和cupid将商品从购物车中移出
     * @return
     */
    public int deleteCart(int memberId,int cupId){
        int val =0;
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = super.getConnection();
            psmt = con.prepareStatement("delete from carts where memberId = ? and cupId = ?");
            psmt.setInt(1,memberId);
            psmt.setInt(2,cupId);
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("根据memberId和cupid将商品从购物车中移出数据库错误:"+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }

    /**
     * 根据用户的Id和杯子的id判断该商品是否存在
     * @param memberId
     * @param cupId
     * @return
     */
    public Cart existCart(int memberId,int cupId){
        Cart cart = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("select ca.memberId,ca.cupId,c.cupName,c.price,c.cupImages,ca.cupQuantity,ca.subTotal from carts ca INNER JOIN members m on ca.memberId = m.memberId INNER JOIN cups c on ca.cupId = c.cupId where ca.memberId = ? and ca.cupId = ?");
            psmt.setInt(1,memberId);
            psmt.setInt(2,cupId);
            rs = psmt.executeQuery();
            if(rs.next()){
                cart = new Cart();
                Cup cup = new Cup(rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getString(5));

//                int mId = rs.getInt(rs.getInt(1));
//                System.out.println("MID:" + mId);
                cart.setMemberId(rs.getInt(1));
                cart.setCup(cup);
                cart.setCupPrice(rs.getDouble(4));
                cart.setCupQuantity(rs.getInt(6));
                cart.setSubTotal(rs.getDouble(7));
            }
        } catch (SQLException e) {
            System.out.println("根据用户的Id和杯子的id判断该商品是否存在数据库出错："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return  cart;
    }


    /**
     * 根据用户Id,杯子id和杯子数量将购物车的商品的数量增加
     * @param
     * @param
     * @param cupQuantity
     * @return
     */
    public int updateQuantity(int memberId,int cupId,int cupQuantity){
        int val =0;
        Connection con = null;
        PreparedStatement psmt = null;
//        ResultSet rs = null;
        try {
            con = super.getConnection();
            psmt = con.prepareStatement("update carts set cupQuantity = cupQuantity + ? where memberId = ? and cupId = ?");
            psmt.setInt(1,cupQuantity);
            psmt.setInt(2,memberId);
            psmt.setInt(3,cupId);
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("根据用户Id,杯子id和杯子数量将购物车的商品的数量增加数据库错误："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }



    public int updateCupQuantity(int cartId,int cupQuantity){
        int val =0;
        Connection con = null;
        PreparedStatement psmt = null;
//        ResultSet rs = null;
        try {
            con = super.getConnection();
            psmt = con.prepareStatement("update carts set cupQuantity = ? where cartId = ?");
            psmt.setInt(1,cupQuantity);
            psmt.setInt(2,cartId);
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("根据用户Id,杯子id和杯子数量将购物车的商品的数量增加数据库错误："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }

    /**
     * 根据购物车的Id删除购物项
     * @param cartId
     * @return
     */
    public int deleteCartById(int cartId){
        int val =0;
        Connection con = null;
        PreparedStatement psmt = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("delete from carts where cartId = ?");
            psmt.setInt(1,cartId);
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("根据购物车的Id删除购物项数据库错误："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return  val;
    }

    /**
     * 根据用户查找购物车总数量
     * @param memberId
     * @return
     */
    public int getCartCountByMember(int memberId){
        int count = 0;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("SELECT count(1) FROM carts WHERE memberId = ?");
            psmt.setInt(1 , memberId);
            rs = psmt.executeQuery();
            if (rs.next()){
                count = rs.getInt(1);
            }
        }catch (Exception e){
            System.out.println("根据用户查看购物车数量失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }
        return count;
    }

}
