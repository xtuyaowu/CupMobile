package cn.cup.dao;

import cn.cup.bean.Member;
import cn.cup.bean.RecordSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class MemberDao extends BaseDao{

    /**
     * 会员注册
     * @param member
     * @return
     */
    public int MemberRegister(Member member){
        Connection con = null;
        PreparedStatement psmt = null;
        int val =0;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("insert into members(memberEmail,nickName,password,tel) values (?,?,?,?)");
            psmt.setString(1, member.getMemberEmail());
            psmt.setString(2,member.getNickName());
            psmt.setString(3,member.getPassword());
            psmt.setString(4,member.getTel());
            val = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("会员注册数据库错误："+e.getMessage());
        } finally {
            super.closeAll(null,psmt,con);
        }
        return val;
    }


    /**
     * 会员登陆
     * @param memberName
     * @param password
     * @return
     */
    public Member loginMember(String memberName,String password){
        Member member = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select * from members where memberEmail = ? and password = ?");
            psmt.setString(1,memberName);
            psmt.setString(2,password);
            rs = psmt.executeQuery();
            if(rs.next()){
                member = new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("会员登录数据库出错："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return member;
    }

    /**
     * 根据用户名来判断用户是否注册过
     * @param memberEmail
     * @return
     */
    public Member existMember(String memberEmail){
        Member member = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select * from members where memberEmail = ?");
            psmt.setString(1,memberEmail);
            rs = psmt.executeQuery();
            if(rs.next()){
                member = new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("根据用户名来判断用户是否注册过数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return  member;
    }

    /**
     * 显示所有会员
     */
    public ArrayList<Member> getAllMembers(){
        ArrayList<Member> members=new ArrayList<Member>();
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select * from  members ORDER BY memberId ASC ");
            rs = psmt.executeQuery();
            while(rs.next()){
                Member member=new Member();
                member.setMemberId(rs.getInt(1));
                member.setMemberEmail(rs.getString(2));
                member.setNickName(rs.getString(3));
                member.setPassword(rs.getString(4));
                member.setTel(rs.getString(5));
                members.add(member);

            }
        } catch (SQLException e) {
            System.out.println("获取所有会员数据库错误:"+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return  members;
    }


    /**
     * 分页显示所有会员
     */
    public RecordSet<Member> getAllMembersByPage(int count,int pageSize){
        RecordSet<Member> memberSet = new RecordSet<Member>();
        ArrayList<Member> memberList = new ArrayList<Member>();
        memberSet.setPageSize(pageSize);
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = super.getConnection();
        try {
            psmt = con.prepareStatement("select * from  members ORDER BY memberId ASC limit ?,?");
            psmt.setInt(1,count);
            psmt.setInt(2,pageSize);
            rs = psmt.executeQuery();
            while(rs.next()){
                Member member=new Member();
                member.setMemberId(rs.getInt(1));
                member.setMemberEmail(rs.getString(2));
                member.setNickName(rs.getString(3));
                member.setPassword(rs.getString(4));
                member.setTel(rs.getString(5));
                memberList.add(member);
            }
            memberSet.setRecordSet(memberList);
            rs = psmt.executeQuery("select count(1) from members");
            if(rs.next()){
                memberSet.setRowCount(rs.getInt(1));//设置一共的行数
            }
        } catch (SQLException e) {
            System.out.println("分页显示所有会员信息数据库错误："+e.getMessage());
        } finally {
            super.closeAll(rs,psmt,con);
        }
        return  memberSet;
    }

}
