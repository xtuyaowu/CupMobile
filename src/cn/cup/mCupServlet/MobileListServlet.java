package cn.cup.mCupServlet;

import cn.cup.bean.Cup;
import cn.cup.bean.RecordSet;
import cn.cup.dao.CupDao;
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
 * Created by yc on 2017/2/6.
 */
@WebServlet(name = "MobileListServlet" , urlPatterns = "/defaultList")
public class MobileListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        CupDao cupDao = new CupDao();
        RecordSet<Cup> cupRecordSet = cupDao.getAllCups(1, 10000);
        JSONArray cups = new JSONArray(cupRecordSet.getRecordSet());
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(cups);
    }
}
