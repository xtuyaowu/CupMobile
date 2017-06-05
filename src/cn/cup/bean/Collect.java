package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Collect {
    private int collectionId;//收藏的编号
    private int MemberId;//会员的编号
    private Cup cup;//收藏的杯子

    public Collect() {
    }

    public Collect(int collectionId, int memberId, Cup cup) {
        this.collectionId = collectionId;
        MemberId = memberId;
        this.cup = cup;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getMemberId() {
        return MemberId;
    }

    public void setMemberId(int memberId) {
        MemberId = memberId;
    }

    public Cup getCup() {
        return cup;
    }

    public void setCup(Cup cup) {
        this.cup = cup;
    }
}
