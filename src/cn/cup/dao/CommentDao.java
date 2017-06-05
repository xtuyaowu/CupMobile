package cn.cup.dao;

import cn.cup.bean.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/27.
 */
public class CommentDao extends BaseDao {
    // 根据商品id显示评论
    public ArrayList<Comment> getAllCommentByCupId(int cupId){
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            //int commentId, int cupId, String memberName, String comment
            psmt = con.prepareStatement("SELECT commentid , memberName , comment FROM comments WHERE cupid = ?");
            psmt.setInt(1 , cupId);
            rs = psmt.executeQuery();
            while (rs.next()){
                comments.add(new Comment(rs.getInt(1) , cupId , rs.getString(2) , rs.getString(3)));
            }
        }catch (Exception e){
            System.out.println("查询评论失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }

        return comments;
    }

    // 根据商品id显示评论总数
    public int getCommentCountByCupId(int cupId){
        int val = 0;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("SELECT COUNT(*) FROM comments WHERE cupid = ?");
            psmt.setInt(1 , cupId);
//            val = psmt.executeUpdate();
            rs = psmt.executeQuery();
            if (rs.next()){
                val = rs.getInt(1);
            }
        }catch (Exception e){
            System.out.println("查询评论数量失败，原因："+e.getMessage());
        }finally {
            closeAll(null , psmt , con);
        }
        return val;
    }

    // 根据商品id增加评论
    public int addCommentByCupId(int cupId , String memberName , String comment){
        int val = 0;
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("INSERT INTO comments(cupid , memberName , comment) " +
                    "VALUES(? , ? , ?)");
            psmt.setInt(1 , cupId);
            psmt.setString(2 , memberName);
            psmt.setString(3 , comment);
            val = psmt.executeUpdate();
        }catch (Exception e){
            System.out.println("增加评论失败，原因："+e.getMessage());
        }finally {
            closeAll(null , psmt , con);
        }
        return val;
    }
}
