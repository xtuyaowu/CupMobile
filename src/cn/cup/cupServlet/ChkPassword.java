package cn.cup.cupServlet;

import cn.cup.bean.Member;
import cn.cup.dao.MemberDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import static cn.cup.security.MD5.md5;

/**
 * Created by Administrator on 2016/10/28.
 */
@WebServlet("/CupWeb/ChkPassword.do")
public class ChkPassword extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");//获取当前的用户名
        String password = request.getParameter("password");//获得用户的密码

        String pwd = null;
        try {
            pwd = md5(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        MemberDao memberDao =new MemberDao();
        Member member = memberDao.loginMember(userName,pwd);
        boolean result = false;

        if(member!=null){
            result = true;
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request,response);
    }
}
