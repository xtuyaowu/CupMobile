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
@WebServlet(name = "DelAllCartServlet" , urlPatterns = "/CupWeb/delAllCart.do")
public class DelAllCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String cartIds = request.getParameter("cartId");
        String[] idArr = cartIds.split(",");
        int val = 0;
        for (int i = 0 ; i<idArr.length ; i++){
            int cartId = Integer.parseInt(idArr[i]);
            val = new CartDao().deleteCartById(cartId);
            if (val == 0){
                break;
            }
        }
        PrintWriter out = response.getWriter();
        out.print(val);
    }
}
