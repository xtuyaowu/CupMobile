package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/31.
 */
public class Info {
    private String name;
    private  int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Info(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public Info() {
    }
}
