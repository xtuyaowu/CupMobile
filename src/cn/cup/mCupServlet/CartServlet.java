package cn.cup.mCupServlet;

import cn.cup.bean.Cart;
import cn.cup.dao.CartDao;
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
@WebServlet(name = "CartServlet" , urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int memberId = Integer.parseInt(request.getParameter("memberId"));

        CartDao cartDao = new CartDao();
        ArrayList<Cart> carts = (ArrayList<Cart>)cartDao.getAllCarts(memberId , 1 ,1000).getRecordSet();

//        System.out.println(cartDao.getAllCarts(memberId , 1 ,1000));
//        System.out.println(carts);

        JSONArray result = new JSONArray(carts);

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
