package cn.cup.cupServlet;

import cn.cup.bean.Cart;
import cn.cup.bean.Member;
import cn.cup.bean.RecordSet;
import cn.cup.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/10/27.
 */
@WebServlet(name = "CartServlet" , urlPatterns = "/CupWeb/cart.html")
public class CartServlet extends HttpServlet {
    private int pageSize = 6;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
       Member member = (Member)request.getSession().getAttribute("member");
       // System.out.println("member"+member.getNickName());
        if (member != null){
            int pageCount = 1;
            if(null != request.getParameter("page")){
                pageCount = Integer.parseInt(request.getParameter("page"));
            }
            CartDao cartDao = new CartDao();
//        RecordSet<Cart> cartRecordSet = null;
//        if (member != null){
            //System.out.println("memberId"+member.getMemberId());
            RecordSet<Cart> cartRecordSet = cartDao.getAllCarts(member.getMemberId(), pageCount, pageSize);
//
//        }
//            RecordSet<Cart> cartRecordSet = cartDao.getAllCarts(1, pageCount, pageSize);
//        System.out.println(cartRecordSet.getRecordSet().size());
            request.setAttribute("CartList" , cartRecordSet);

//            request.setAttribute("CartList" , cartRecordSet.getRecordSet());

            request.setAttribute("Page" , pageCount + 1);
            request.getRequestDispatcher("cart.jsp").forward(request , response);
        }else {
            response.sendRedirect("login.jsp");
        }



    }
}
