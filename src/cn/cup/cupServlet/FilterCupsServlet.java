package cn.cup.cupServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/10/29.
 */
@WebServlet(name = "FilterCupsServlet" , urlPatterns = "/CupWeb/filter.do")
public class FilterCupsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取材质id、类型id、容量id
        int materialId = Integer.parseInt(request.getParameter("material"));
        int typeId = Integer.parseInt(request.getParameter("type"));
        int capacityId = Integer.parseInt(request.getParameter("capacity"));




        // 调用筛选



        // 响应
    }
}
