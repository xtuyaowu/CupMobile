package cn.cup.cupServlet;

import cn.cup.bean.Region;
import cn.cup.dao.RegionDao;
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
 * Created by Administrator on 2016/10/29.
 */
@WebServlet(name = "CountryServlet" , urlPatterns = "/CupWeb/country.do")
public class CountryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取市
        int city = Integer.parseInt(request.getParameter("city"));

        // 根据省获取市
        ArrayList<Region> country = new RegionDao().getCountryByCity(city);
        JSONArray jsonArray = new JSONArray(country);
        String result = jsonArray.toString();
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
