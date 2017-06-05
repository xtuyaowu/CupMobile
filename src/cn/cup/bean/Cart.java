package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Cart {
    private int cartId;//购物车ID
    private int memberId;//会员编号
    private Cup cup;//杯子类
    private double cupPrice;//杯子的价格
    private int cupQuantity;//杯子的数量
    private double subTotal;//杯子的小计

    public Cart() {
    }

    public Cart(int cartId, int memberId, Cup cup, double cupPrice, int cupQuantity, double subTotal) {
        this.cartId = cartId;
        this.memberId = memberId;
        this.cup = cup;
        this.cupPrice = cupPrice;
        this.cupQuantity = cupQuantity;
        this.subTotal = subTotal;
    }

    public Cart(int memberId, Cup cup, double cupPrice, int cupQuantity, double subTotal) {
        this.memberId = memberId;
        this.cup = cup;
        this.cupPrice = cupPrice;
        this.cupQuantity = cupQuantity;
        this.subTotal = subTotal;
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

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
}
