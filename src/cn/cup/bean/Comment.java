package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Comment {
    private int commentId;//评论的编号
    private int cupId;//杯子
//    private int memberId;//会员的编号
    private String memberName;// 会员名
    private String comment;//商品的评价

    public Comment() {
    }

    public Comment(int commentId, int cupId, String memberName, String comment) {
        this.commentId = commentId;
        this.cupId = cupId;
        this.memberName = memberName;
        this.comment = comment;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getCupId() {
        return cupId;
    }

    public void setCupId(int cupId) {
        this.cupId = cupId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
