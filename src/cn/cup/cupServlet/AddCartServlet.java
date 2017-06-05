package cn.cup.cupServlet;

import cn.cup.bean.Cart;
import cn.cup.bean.Cup;
import cn.cup.dao.CartDao;
import cn.cup.dao.CupDao;

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
@WebServlet(name = "AddCartServlet" , urlPatterns = "/CupWeb/addCart.do")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 接收用户id和杯子id以及数量
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        int cupId = Integer.parseInt(request.getParameter("cupId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Cup cup = new CupDao().getCupByCupId(cupId);
        int flag = 0;
        // 判断是否已添加
        Cart cart = new CartDao().existCart(memberId , cupId);
        if (cart != null){
//            System.out.println(cart.getCartId());

            // 已存在,数量加
            int val = new CartDao().updateQuantity(memberId , cupId , quantity);
            if (val == 0){
                // 增加失败
                flag = 0;
            }else {
                flag = 2;
//                System.out.println("增加成功");
            }
        }else {
            int val = new CartDao().addCart(memberId , cupId , quantity , cup);
            if (val == 0){
                flag = 0;
            }else {
                flag = 1;

            }
        }

        // 加入购物车

        // 响应
        PrintWriter out = response.getWriter();
        out.print(flag);
    }
}
