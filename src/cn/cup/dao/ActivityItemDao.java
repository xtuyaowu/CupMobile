package cn.cup.dao;

import cn.cup.bean.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class ActivityItemDao extends BaseDao {
    // 加载活动
    public ArrayList<ActivityItem> getActivityItem(int id){
        ArrayList<ActivityItem> activityItems = new ArrayList<ActivityItem>();

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("SELECT activities.activityid , activityname , " +
                    "activities.cupid , cupname , " +
                    "cups.materialid , materialName , cups.typeid , typeName , " +
                    "cupCapacity , price , cupimages , introduce , clicktime , salenumber " +
                    "FROM activitytitems " +
                    "INNER JOIN activities ON activitytitems.activityid = activities.activityid " +
                    "INNER JOIN cups ON activitytitems.cupid = cups.cupid " +
                    "INNER JOIN cupmaterials ON cupmaterials.materialid = cups.materialid " +
                    "INNER JOIN cuptypes ON cuptypes.typeid = cups.typeid ");
            rs = psmt.executeQuery();
            while (rs.next()){
                Material material = new Material(rs.getInt(5) , rs.getString(6));
                Type type = new Type(rs.getInt(7) , rs.getString(8));
                //int cupId, String cupName, Material material, Type type,
                // double cupCapacity, double price,
                // String cupImages, String introduce, int clickTime, int saleNumber
                Cup cup = new Cup(rs.getInt(3) , rs.getString(4) , material , type ,
                        rs.getDouble(9) , rs.getDouble(10) , rs.getString(11) ,
                        rs.getString(12) , rs.getInt(13) , rs.getInt(14));
                activityItems.add(new ActivityItem(rs.getInt(1) , cup));
            }
        }catch (Exception e){
            System.out.println("添加活动项失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }

        return activityItems;
    }
    // 添加活动
    public int addActivityItem(ActivityItem activityItem){
        int val = 0;
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("INSERT INTO activitytitems(activityid , cupid) VALUES(? , ?)");
            psmt.setInt(1 , activityItem.getActivityId());
            psmt.setInt(2 , activityItem.getCup().getCupId());
            val = psmt.executeUpdate();
        }catch (Exception e){
            System.out.println("添加活动项失败，原因："+e.getMessage());
        }finally {
            closeAll(null , psmt , con);
        }
        return val;
    }
    // 编辑活动
    public int modifyActivityItem(ActivityItem activityItem){
        int val = 0;

        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("UPDATE activitytitems SET activityid = ? , cupid = ?" +
                    "WHERE activityid = ? AND cupid = ?");
            psmt.setInt(1 , activityItem.getActivityId());
            psmt.setInt(2 , activityItem.getCup().getCupId());
            val = psmt.executeUpdate();
        }catch (Exception e){
            System.out.println("修改活动项失败，原因："+e.getMessage());
        }finally {
            closeAll(null , psmt , con);
        }
        return val;
    }
    // 分页加载
    public RecordSet<ActivityItem> getActivitiesByLimit(int start , int count){
        RecordSet<ActivityItem> activityItemRecordSet = new RecordSet<ActivityItem>();
        activityItemRecordSet.setPageSize(count);
        ArrayList<ActivityItem> activityItems = new ArrayList<ActivityItem>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("SELECT activities.activityid , activityname , " +
                    "activities.cupid , cupname , " +
                    "cups.materialid , materialName , cups.typeid , typeName , " +
                    "cupCapacity , price , cupimages , introduce , clicktime , salenumber " +
                    "FROM activitytitems " +
                    "INNER JOIN activities ON activitytitems.activityid = activities.activityid " +
                    "INNER JOIN cups ON activitytitems.cupid = cups.cupid " +
                    "INNER JOIN cupmaterials ON cupmaterials.materialid = cups.materialid " +
                    "INNER JOIN cuptypes ON cuptypes.typeid = cups.typeid " +
                    "limit ? , ?");
            psmt.setInt(1 , start);
            rs = psmt.executeQuery();
            psmt.setInt(2 , count);
            while (rs.next()){
//                Activity activity = new Activity(rs.getInt(1) , rs.getString(2));
                Material material = new Material(rs.getInt(5) , rs.getString(6));
                Type type = new Type(rs.getInt(7) , rs.getString(8));
                //int cupId, String cupName, Material material, Type type,
                // double cupCapacity, double price,
                // String cupImages, String introduce, int clickTime, int saleNumber
                Cup cup = new Cup(rs.getInt(3) , rs.getString(4) , material , type ,
                        rs.getDouble(9) , rs.getDouble(10) , rs.getString(11) ,
                        rs.getString(12) , rs.getInt(13) , rs.getInt(14));
                activityItems.add(new ActivityItem(rs.getInt(1) , cup));
            }
            activityItemRecordSet.setRecordSet(activityItems);
            rs = psmt.executeQuery("SELECT COUNT(*) FROM activitytitems");
            if (rs.next()){
                activityItemRecordSet.setRowCount(rs.getInt(1));
            }
        }catch (Exception e){
            System.out.println("分页失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }
        return activityItemRecordSet;
    }
}
