package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Material {
    private int materialId;//材质的id
    private String materialName;//材质的名称

    public Material() {
    }

    public Material(int materialId, String materialName) {
        this.materialId = materialId;
        this.materialName = materialName;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
