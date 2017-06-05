package cn.cup.cupServlet;

import cn.cup.bean.Member;
import cn.cup.dao.MemberDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static cn.cup.security.MD5.md5;

/**
 * Created by Administrator on 2016/10/28.
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/CupWeb/register.do")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputEmail = request.getParameter("inputEmail");//获取登录的用户名
        String inputPassword = request.getParameter("inputPassword");//获取登录密码
        String nickName = request.getParameter("nickName");//获取用户的昵称
        String tel = request.getParameter("tel");//获取用户的手机号码

        MemberDao memberDao = new MemberDao();
        Member member =new Member();

        member.setMemberEmail(inputEmail);
        member.setNickName(nickName);
        String pwd = null;
        try {
            pwd = md5(inputPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        member.setPassword(pwd);
        member.setTel(tel);

        memberDao.MemberRegister(member);

        response.sendRedirect("login.jsp");//跳转
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
