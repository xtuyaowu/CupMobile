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
@WebServlet(name="LoginServlet" , urlPatterns = "/CupWeb/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");//获取登录的用户名
        String password = request.getParameter("password");//获取登录密码
        String pwd = null;
        try {
            pwd = md5(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        MemberDao memberDao =new MemberDao();
        Member member = new Member();
//        System.out.println("pwd:"+pwd);
//        int count = 0;
        member = memberDao.loginMember(userName,pwd);//登录操作
        if(null!=member){
            request.getSession().setAttribute("member",member);//将登录名存入session
//            count = new CartDao().getCartCountByMember(member.getMemberId());
//            request.getSession().setAttribute("CartCount" , count);
            response.sendRedirect("index.html");//跳转
        }else{
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost(request,response);
    }
}
