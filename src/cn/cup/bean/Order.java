package cn.cup.bean;

import java.sql.Date;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Order {
    private String orderId;//订单的编号
    private int memberId;//会员的编号
    private String memberEmail;//会员的邮箱
    private Cup cup;//杯子的类型
    private double cupPrice;//杯子的价格
    private int cupQuantity;//杯子的数量
    private double subTotal;//商品的小计
    private Date orderDate;//下单的时间
    private Address address;//收货人的地址
    private int flag;//是否发货，1表示发货，0表示未发货
    private String nickName;//会员昵称
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public Order(String orderId, int memberId, String nickName, Cup cup, double cupPrice, int cupQuantity, double subTotal, Date orderDate, Address address) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.nickName= nickName;
        this.cup = cup;
        this.cupPrice = cupPrice;
        this.cupQuantity = cupQuantity;
        this.subTotal = subTotal;
        this.orderDate = orderDate;
        this.address = address;
    }



    public Order() {
    }

    public Order(String orderId, int memberId, Cup cup, double cupPrice, int cupQuantity, double subTotal, Date orderDate, Address address) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.cup = cup;
        this.cupPrice = cupPrice;
        this.cupQuantity = cupQuantity;
        this.subTotal = subTotal;
        this.orderDate = orderDate;
        this.address = address;
    }

    public Order(String orderId, int memberId, String memberEmail, Cup cup, double cupPrice, int cupQuantity, double subTotal, Date orderDate, Address address, int flag) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.cup = cup;
        this.cupPrice = cupPrice;
        this.cupQuantity = cupQuantity;
        this.subTotal = subTotal;
        this.orderDate = orderDate;
        this.address = address;
        this.flag = flag;
    }

    public Order(String orderId, int memberId, Cup cup, double cupPrice, int cupQuantity, double subTotal, Date orderDate, Address address, int flag) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.cup = cup;
        this.cupPrice = cupPrice;
        this.cupQuantity = cupQuantity;
        this.subTotal = subTotal;
        this.orderDate = orderDate;
        this.address = address;
        this.flag = flag;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Cup getCup() {
        return cup;
    }

    public void setCup(Cup cup) {
        this.cup = cup;
    }

    public double getCupPrice() {
        return cupPrice;
    }

    public void setCupPrice(double cupPrice) {
        this.cupPrice = cupPrice;
    }

    public int getCupQuantity() {
        return cupQuantity;
    }

    public void setCupQuantity(int cupQuantity) {
        this.cupQuantity = cupQuantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }
}
