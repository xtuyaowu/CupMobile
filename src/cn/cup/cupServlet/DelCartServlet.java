package cn.cup.cupServlet;

import cn.cup.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/10/29.
 */
@WebServlet(name = "DelCartServlet" , urlPatterns = "/CupWeb/delCart.do")
public class DelCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取用户id和cupid
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        int cupId = Integer.parseInt(request.getParameter("cupId"));

        // 删除
        int val = new CartDao().deleteCart(memberId , cupId);
        // 响应
        PrintWriter out = response.getWriter();
        out.print(val);
    }
}
