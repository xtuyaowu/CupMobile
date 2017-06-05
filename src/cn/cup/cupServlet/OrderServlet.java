package cn.cup.cupServlet;

import cn.cup.bean.Member;
import cn.cup.bean.Order;
import cn.cup.bean.RecordSet;
import cn.cup.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/10/27.
 */
@WebServlet(name = "OrderServlet" , urlPatterns = "/CupWeb/order.html")
public class OrderServlet extends HttpServlet {
    private int pageSize = 4;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Member member = (Member)request.getSession().getAttribute("member");
        if(member != null){
            int pageCount = 1;
            if(null != request.getParameter("page")){
                pageCount = Integer.parseInt(request.getParameter("page"));
            }
            OrderDao orderDao = new OrderDao();
//            System.out.println(member.getMemberId());
            RecordSet<Order> orderRecordSet = orderDao.getOrdersByMember(member.getMemberId(), pageCount, pageSize);
            request.setAttribute("OrderList" , orderRecordSet);
            request.setAttribute("Page" , pageCount + 1);
            request.getRequestDispatcher("allOrders.jsp").forward(request , response);
        }else{
            response.sendRedirect("login.jsp");
        }
    }
}
