package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Type {
    private int typeId;//杯子的分类的ID
    private String typeName;//杯子的分类的名称

    public Type() {
    }

    public Type(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
