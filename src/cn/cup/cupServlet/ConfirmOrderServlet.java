package cn.cup.cupServlet;

import cn.cup.bean.Address;
import cn.cup.bean.Member;
import cn.cup.dao.AddressDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/30.
 */
@WebServlet(name = "ConfirmOrderServlet" , urlPatterns = "/CupWeb/confirmOrder.html")
public class ConfirmOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Member member = (Member)request.getSession().getAttribute("member");
        if (member != null){
            // 根据当前登录的用户获取其地址
//            ArrayList<Address> addresses = new AddressDao().getAllAdrress(1);
            ArrayList<Address> addresses = new AddressDao().getAllAdrress(member.getMemberId());

            // 获取该用户的所有购物车
//            RecordSet<Cart> cartRecordSet = new CartDao().getAllCarts(member.getMemberId() , 0 , 1000);
//            ArrayList<Cart> carts = (ArrayList<Cart>)cartRecordSet.getRecordSet();
            // 强carts放在session中
//            request.getSession().setAttribute("CurrentMemberCarts" , carts);


            request.setAttribute("AddressList" , addresses);
            request.getRequestDispatcher("confirmOrder.jsp").forward(request , response);
        }else {
            response.sendRedirect("login.jsp");
        }



    }
}
