package cn.cup.dao;

import cn.cup.bean.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class ActivityDao extends BaseDao {
    // 加载所有活动名称
    public ArrayList<Activity> getAllActivity(){
        ArrayList<Activity> activities = new ArrayList<Activity>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            psmt = con.prepareStatement("SELECT activityid , activityname FROM activities ORDER BY activityid");
            rs = psmt.executeQuery();
            while (rs.next()){
                activities.add(new Activity(rs.getInt(1) , rs.getString(2)));
            }
        }catch (Exception e){
            System.out.println("查询所有活动失败，原因："+e.getMessage());
        }finally {
            closeAll(rs , psmt , con);
        }
        return activities;
    }
    // 添加活动
    public int addActivity(Activity activity){
        int val = 0;
        Connection con = null;
        PreparedStatement psmt = null;

        try {
            con = getConnection();
            psmt = con.prepareStatement("INNER INTO activities(activityname) VALUES(?)");
            psmt.setString(1 , activity.getActivityName());
            val = psmt.executeUpdate();
        }catch (Exception e){
            System.out.println("插入活动失败，原因："+e.getMessage());
        }finally {
            closeAll(null , psmt , con);
        }
        return val;
    }

    // 编辑活动名称
    public int modifyActivity(Activity activity){
        int val = 0;
        Connection con = null;
        PreparedStatement psmt = null;

        try {
            con = getConnection();
            psmt = con.prepareStatement("UPDATE activities SET activityname = ? WHERE activityid = ?");
            psmt.setString(1 , activity.getActivityName());
            psmt.setInt(2 , activity.getActivityId());
            val = psmt.executeUpdate();
        }catch (Exception e){
            System.out.println("更新活动失败，原因："+e.getMessage());
        }finally {
            closeAll(null , psmt , con);
        }
        return val;
    }
}
