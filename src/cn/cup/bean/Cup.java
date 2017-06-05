package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Cup {
    private int cupId;//杯子的编号
    private String cupName;//杯子的名字
    private Material material;//杯子的材质
    private Type type;//杯子的类型
    private double cupCapacity;//杯子的容量
    private double price;//杯子的价格
    private String cupImages;//杯子的照片
    private String Introduce;//杯子的简介
    private int clickTime;//杯子的点击次数
    private int saleNumber;//销量

    public Cup(int cupId,String cupName, double price, String cupImages) {
        this.cupName = cupName;
        this.price = price;
        this.cupImages = cupImages;
        this.cupId = cupId;
    }

    public Cup() {

    }

    public Cup(int cupId, String cupName, Material material, Type type, double cupCapacity, double price, String cupImages, String introduce, int clickTime) {
        this.cupId = cupId;
        this.cupName = cupName;
        this.material = material;
        this.type = type;
        this.cupCapacity = cupCapacity;
        this.price = price;
        this.cupImages = cupImages;
        Introduce = introduce;
        this.clickTime = clickTime;
    }

    public Cup(int cupId, String cupName, Material material, Type type, double cupCapacity, double price, String cupImages, String introduce, int clickTime, int saleNumber) {
        this.cupId = cupId;
        this.cupName = cupName;
        this.material = material;
        this.type = type;
        this.cupCapacity = cupCapacity;
        this.price = price;
        this.cupImages = cupImages;
        Introduce = introduce;
        this.clickTime = clickTime;
        this.saleNumber = saleNumber;
    }

    public int getCupId() {
        return cupId;
    }

    public void setCupId(int cupId) {
        this.cupId = cupId;
    }

    public String getCupName() {
        return cupName;
    }

    public void setCupName(String cupName) {
        this.cupName = cupName;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getCapacity() {
        return cupCapacity;
    }

    public void setCapacity(double cupCapacity) {
        this.cupCapacity = cupCapacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCupImages() {
        return cupImages;
    }

    public void setCupImages(String cupImages) {
        this.cupImages = cupImages;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String introduce) {
        Introduce = introduce;
    }

    public int getClickTime() {
        return clickTime;
    }

    public void setClickTime(int clickTime) {
        this.clickTime = clickTime;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }
}
