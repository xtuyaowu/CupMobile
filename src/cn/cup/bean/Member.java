package cn.cup.bean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Member {
    private int memberId;//会员的编号
    private String memberEmail;//会员的邮箱
    private String nickName;//会员的昵称
    private String password;//会员的密码
    private String tel;//会员的电话

    public Member() {
    }

    public Member(int memberId, String memberEmail, String nickName, String password, String tel) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.nickName = nickName;
        this.password = password;
        this.tel = tel;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
