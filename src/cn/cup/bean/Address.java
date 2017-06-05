package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Address {
    private int addressId;//商品的编号
    private int memberId;//会员的编号
    private String receiverName;//收货人的姓名
    private String province;//省
    private String city;//市
    private String county;//县
    private String addressName;//收货人的详细地址
    private String receiverTel;//收货人的电话

    public Address() {
    }

    public Address(int addressId, int memberId, String receiverName, String province, String city, String county, String addressName, String receiverTel) {
        this.addressId = addressId;
        this.memberId = memberId;
        this.receiverName = receiverName;
        this.province = province;
        this.city = city;
        this.county = county;
        this.addressName = addressName;
        this.receiverTel = receiverTel;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }
}
