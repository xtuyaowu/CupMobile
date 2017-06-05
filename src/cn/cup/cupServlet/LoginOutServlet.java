package cn.cup.cupServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/10/28.
 */
@WebServlet(name ="LoginOutServlet", urlPatterns = "/CupWeb/loginOut.do")
public class LoginOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //删除记录
        request.getSession().removeAttribute("member");
        request.getSession().invalidate();
        //返回登录
        response.sendRedirect("login.jsp");
    }
}
