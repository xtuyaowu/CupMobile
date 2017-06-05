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
 * Created by Administrator on 2016/10/31.
 */
@WebServlet(name = "ChangeQuantityServlet" , urlPatterns = "/CupWeb/changeQuantity.do")
public class ChangeQuantityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 获取cartId 和 quantity
//        {'cartId' : cartId , 'quantity' : quantity},
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // 更新数量
        int val = new CartDao().updateCupQuantity(cartId , quantity);
        PrintWriter out = response.getWriter();
        out.print(val);

    }
}
