package cn.cup.mCupServlet;

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
 * Created by yc on 2017/3/6.
 */
@WebServlet(name = "AddCartServlet" , urlPatterns = "/addCart")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int memberId = Integer.parseInt(request.getParameter("memberId"));
        int cupId = Integer.parseInt(request.getParameter("cupId"));
        int quantity = 1;
        Cup cup = new CupDao().getCupByCupId(cupId);
        int flag = 0;
        // 判断是否已添加
        Cart cart = new CartDao().existCart(memberId , cupId);
        if (cart != null){
            // 已存在，数量增加
            int val = new CartDao().updateQuantity(memberId , cupId , quantity);
            if (val == 0){
                // 增加失败
                flag = 0;
            } else {
                // 增加成功
                flag = 2;
            }
        } else {
            int val = new CartDao().addCart(memberId , cupId , quantity , cup);
            if (val == 0){
                // 加入购物车失败
                flag = 0;
            }else {
                // 加入购物车成功
                flag = 1;

            }
        }

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(flag);
    }
}
