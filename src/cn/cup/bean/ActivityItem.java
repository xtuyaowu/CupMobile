package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class ActivityItem {
    private int activityId;//活动的编号
    private Cup cup;//杯子

    public ActivityItem() {
    }

    public ActivityItem(int activityId, Cup cup) {
        this.activityId = activityId;
        this.cup = cup;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public Cup getCup() {
        return cup;
    }

    public void setCup(Cup cup) {
        this.cup = cup;
    }
}
