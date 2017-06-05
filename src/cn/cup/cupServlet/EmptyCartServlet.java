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
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
@WebServlet(name = "EmptyCartServlet" , urlPatterns = "/CupWeb/emptyCart.do")
public class EmptyCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取当前登录用户
        Member member = (Member)request.getSession().getAttribute("member");
        if (member != null){
            // 根据该用户查看其所有购物车项
            CartDao cartDao = new CartDao();
            RecordSet<Cart> cartRecordSet = cartDao.getAllCarts(member.getMemberId(), 1, 1000);
            ArrayList<Cart> carts = (ArrayList<Cart>)cartRecordSet.getRecordSet();

//            // 逐条删除
//            int val = 0;
//            for (int i = 0 ; i<carts.size() ; i++){
//                val = cartDao.deleteCartById(carts.get(i).getCartId());
//                if (val == 0){
//                    break;
//                }
//            }
            request.getSession().setAttribute("CurrentMemberCarts" , carts);
//            System.out.println("val:"+val);
            // 删除成功，响应
            PrintWriter out = response.getWriter();
            out.print(1);

        }


    }
}
