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

/**
 * Created by Administrator on 2016/10/28.
 */
@WebServlet( "/CupWeb/ChkUserName.do")
public class UserNameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");//获取当前的用户名
        MemberDao memberDao = new MemberDao();
        Member member = memberDao.existMember(userName);
        boolean result = false;
        if(member!=null){
            result = true;
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out =response.getWriter();
        out.print(result);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request,response);
    }

}
