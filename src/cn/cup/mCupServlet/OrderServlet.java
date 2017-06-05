package cn.cup.mCupServlet;

import cn.cup.bean.Order;
import cn.cup.dao.OrderDao;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by yc on 2017/2/28.
 */
@WebServlet(name = "OrderServlet" , urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int memberId = Integer.parseInt(request.getParameter("memberId"));
//        System.out.println(memberId);
        OrderDao orderDao = new OrderDao();
        ArrayList<Order> orders = (ArrayList<Order>)orderDao.getOrdersByMember(memberId , 1 , 1000).getRecordSet();

        JSONArray result = new JSONArray(orders);
//        System.out.println(result);
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);

    }
}
