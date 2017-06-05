package cn.cup.mCupServlet;

import cn.cup.bean.Member;
import cn.cup.dao.MemberDao;
import org.json.JSONObject;

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
 * Created by yc on 2017/2/24.
 */
@WebServlet(name = "MobileLoginServlet" , urlPatterns = "/login.do")
public class MobileLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //获取用户名和密码
        String userName = request.getParameter("userName");
        String password = request.getParameter("pwd");

//        System.out.println(userName + "&&&" + password);

        String pwd = null;
        PrintWriter out = response.getWriter();

        try {
            pwd = md5(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        System.out.println(pwd);
        MemberDao memberDao =new MemberDao();
        Member member = new Member();
//        System.out.println("pwd:"+pwd);
//        int count = 0;
        member = memberDao.loginMember(userName,pwd);//登录操作
//        System.out.println(member);
        if(null!=member){
//            request.getSession().setAttribute("member",member);//将登录名存入session
            JSONObject user = new JSONObject(member);
            out.print(user);

        }else{
            out.print("");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request , response);
    }
}
