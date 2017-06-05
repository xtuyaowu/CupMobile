package cn.cup.cupServlet;

import cn.cup.bean.Address;
import cn.cup.bean.Cart;
import cn.cup.bean.Order;
import cn.cup.dao.AddressDao;
import cn.cup.dao.CartDao;
import cn.cup.dao.CupDao;
import cn.cup.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
//@WebServlet(name = "AddOrdersServlet" , urlPatterns = "/CupWeb/addOrder.do")
@WebServlet("/CupWeb/addOrder.do")

public class AddOrdersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 获取用户id
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        // 获取地址id
        int addressId = Integer.parseInt(request.getParameter("addressId"));
        // 根据addressId获取address
        Address address = new AddressDao().getAddressByAddressId(addressId);


        // int memberId,int cupId,double cupPrice,int cupQuantity,int addressId

        // 从session中获取所有购物项
        ArrayList<Cart> carts = (ArrayList<Cart>)request.getSession().getAttribute("CurrentMemberCarts");
        OrderDao orderDao = new OrderDao();
        CupDao cupDao = new CupDao();
        CartDao cartDao = new CartDao();
        ArrayList<Order> orders = new ArrayList<Order>();
//        Order order = null;
        int val = 0;
        if (carts.size() != 0){
            for (int i = 0 ; i<carts.size() ; i++){
                int cupId = carts.get(i).getCup().getCupId();
                double cupPrice = carts.get(i).getCupPrice();
                int quantity = carts.get(i).getCupQuantity();
                val = orderDao.addOrder(memberId , cupId , cupPrice , quantity , addressId);
                if (val == 0){
                    break;
                }
                val = cupDao.addCupHotTime(cupId);
                if (val == 0){
                    break;
                }
                val = cartDao.deleteCartById(carts.get(i).getCartId());
                if (val == 0){
                    break;
                }
//                System.out.println("val:"+val);

            }
        }
        request.getSession().removeAttribute("CurrentMemberCarts");

        // 响应
        PrintWriter out = response.getWriter();
        out.print(val);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request , response);
    }
}
