package cn.cup.cupServlet;

import cn.cup.bean.Member;
import cn.cup.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/11/1.
 */
@WebServlet(name = "GetCartCountServlet" , urlPatterns = "/CupWeb/getCartCount.do")
public class GetCartCountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int count = 0;
        // 获取当前登录用户
        Member member = (Member)request.getSession().getAttribute("member");
        if (member != null){
            count = new CartDao().getCartCountByMember(member.getMemberId());
        }

        PrintWriter out = response.getWriter();
        out.print(count);

    }
}
