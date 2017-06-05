package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Manager {
    private int managerId;//管理员id
    private String managerName;//管理员名称
    private String password;//密码

    public Manager() {
    }

    public Manager(int managerId, String managerName, String password) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.password = password;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}