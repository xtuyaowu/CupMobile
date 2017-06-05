package cn.cup.dao;

import cn.cup.bean.Collect;
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
public class CollectionDao extends BaseDao {
//杯子的Id,名字，价格，图片，用户id
    /**
     * 根据用户id显示所有收藏
     * @param memberId
     * @return
     */
    public RecordSet<Collect> getCollectionsByMemberId(int memberId,int pageNumber,int pageSize){
        RecordSet<Collect> collectionSet = new RecordSet<Collect>();
        ArrayList<Collect> collectList = new ArrayList<Collect>();
        collectionSet.setPageSize(pageSize);
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = super.getConnection();
            psmt = con.prepareStatement("select co.collectionId,co.memberId,co.cupId,c.cupName,c.price,c.cupImages from collections co INNER JOIN members m on co.memberId = m.memberId INNER JOIN cups c on co.cupId = c.cupId where co.memberId=? limit ?,?");
            psmt.setInt(1,memberId);
            psmt.setInt(2 , (pageNumber-1)*pageSize);
            psmt.setInt(3 , pageSize);
            rs = psmt.executeQuery();
            while(rs.next()){
                Cup cup = new Cup();
                cup.setCupId(rs.getInt(3));
                cup.setCupName(rs.getString(4));
                cup.setPrice(rs.getDouble(5));
                cup.setCupImages(rs.getString(6));
                Collect collect = new Collect(rs.getInt(1),rs.getInt(2),cup);
                collectList.add(collect);
            }
            collectionSet.setRecordSet(collectList);
            rs = psmt.executeQuery("select count(1) from collections where memberId ="+memberId);
            if(rs.next()){
                collectionSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("根据用户id显示所有收藏数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return collectionSet;
    }

    /**
     * 根据会员编号和杯子的编号收藏商品
     * @param memberId
     * @param cupId
     * @return
     */
    public int addCollection(int memberId,int cupId){
        int val =0;
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = super.getConnection();
            psmt = con.prepareStatement("insert into collections(memberId,cupId) values(?,?)");
            psmt.setInt(1,memberId);
            psmt.setInt(2,cupId);
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("根据会员编号和杯子的编号收藏商品数据库错误："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }

    /**
     * 根据收藏的id删除收藏
     * @param collectionId
     * @return
     */
    public int deleteCollection(int collectionId){
        int val =0;
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = super.getConnection();
            psmt = con.prepareStatement("delete from collections where collectionId = ?");
            psmt.setInt(1,collectionId);
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(" 根据收藏的id删除收藏数据库错误："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }

    /**
     * 根据用户id和杯子id查看是否已收藏
     * @param memberId
     * @param cupId
     * @return
     */
      public int searchCollect(int memberId , int cupId){
//          Collect collect = new Collect();
          int val = 0;
          Connection con = null;
          PreparedStatement psmt = null;
          ResultSet rs = null;
          try {
              con = getConnection();
              psmt = con.prepareStatement("SELECT collectionId , memberid , cupid " +
                      "FROM collections " +
                      "WHERE memberid = ? AND cupid = ?");
              psmt.setInt(1 , memberId);
              psmt.setInt(2 , cupId);
              rs = psmt.executeQuery();
              while (rs.next()){
                  val++;
              }
          }catch (Exception e){
              System.out.println("查看是否收藏失败，原因："+e.getMessage());
          }finally {
              closeAll(rs , psmt , con);
          }
//          return collect;
          return val;
      }
}
