package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Activity {
    private int activityId;//活动的编号
    private String activityName;//活动的名称

    public Activity() {
    }

    public Activity(int activityId, String activityName) {
        this.activityId = activityId;
        this.activityName = activityName;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
