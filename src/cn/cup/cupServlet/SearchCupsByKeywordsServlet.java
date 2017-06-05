package cn.cup.cupServlet;

import cn.cup.bean.Cup;
import cn.cup.bean.RecordSet;
import cn.cup.dao.CupDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/1.
 */
@WebServlet(name = "SearchCupsByKeywordsServlet" , urlPatterns = "/CupWeb/searchCups.html")
public class SearchCupsByKeywordsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 根据关键字查找杯子
        String keywords = request.getParameter("keywords");


//        int materialId,int typeId,String keyWords,int pageCount,int pageSize
        RecordSet<Cup> cupRecordSet = new CupDao().getCupByMoreConditions(0,0,keywords,1,1000);
        request.setAttribute("SearchCups" , cupRecordSet.getRecordSet());
        request.getRequestDispatcher("searchCup.jsp").forward(request , response);


    }
}
