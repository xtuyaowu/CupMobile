package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/27.
 */
public class Region {
    private int regionCode;// 行政编号
    private String fullName;// 全名
    private String shortName;// 简称
//    private int parentCode;// 上级编号


    public Region(int regionCode, String fullName, String shortName) {
        this.regionCode = regionCode;
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public int getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(int regionCode) {
        this.regionCode = regionCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
