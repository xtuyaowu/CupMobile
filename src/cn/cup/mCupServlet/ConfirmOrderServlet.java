package cn.cup.mCupServlet;

import cn.cup.bean.Address;
import cn.cup.bean.Member;
import cn.cup.dao.AddressDao;
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
 * Created by Administrator on 2016/10/30.
 */
@WebServlet(name = "ConfirmOrderServlet" , urlPatterns = "/confirmOrder.do")
public class ConfirmOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int memberId = Integer.parseInt(request.getParameter("memberId"));
            // 根据当前登录的用户获取其地址
//            ArrayList<Address> addresses = new AddressDao().getAllAdrress(1);
        ArrayList<Address> addresses = new AddressDao().getAllAdrress(memberId);

        JSONArray result = new JSONArray(addresses);
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);





    }
}
