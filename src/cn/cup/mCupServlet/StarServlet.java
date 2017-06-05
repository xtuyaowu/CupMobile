package cn.cup.mCupServlet;

import cn.cup.bean.Collect;
import cn.cup.dao.CollectionDao;
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
@WebServlet(name = "StarServlet" , urlPatterns = "/star")
public class StarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int memberId = Integer.parseInt(request.getParameter("memberId"));

        CollectionDao collectionDao = new CollectionDao();
        ArrayList<Collect> collects = (ArrayList<Collect>)collectionDao.getCollectionsByMemberId(memberId , 1 , 10000).getRecordSet();

        JSONArray result = new JSONArray(collects);

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
