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
 * Created by yc on 2017/2/28.
 */
@WebServlet(name = "RegisterServlet" , urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String email = request.getParameter("email");//获取登录的用户名
        String password = request.getParameter("pwd");//获取登录密码
        String nickName = request.getParameter("nickName");//获取用户的昵称
        String tel = request.getParameter("tel");//获取用户的手机号码


        MemberDao memberDao = new MemberDao();
        Member member =new Member();

        member.setMemberEmail(email);
        member.setNickName(nickName);
        String pwd = null;
        try {
            pwd = md5(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        member.setPassword(pwd);
        member.setTel(tel);

        memberDao.MemberRegister(member);

        JSONObject result = new JSONObject(member);

        response.setContentType("utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
