package cn.cup.cupServlet;

import cn.cup.dao.CupDao;

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
@WebServlet(name = "AddClickTimeServlet" , urlPatterns = "/CupWeb/addClickTime.do")
public class AddClickTimeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 获取杯子id
        int cupId = Integer.parseInt(request.getParameter("cupId"));
        // 增加点击次数
        int val = new CupDao().addCupClickTime(cupId);
        PrintWriter out = response.getWriter();
        out.print(val);
    }
}
