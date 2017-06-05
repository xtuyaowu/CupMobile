package cn.cup.mCupServlet;

import cn.cup.dao.CollectionDao;

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
@WebServlet(name = "DelStarServlet" , urlPatterns = "/delStar")
public class DelStarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int collectionId = Integer.parseInt(request.getParameter("collectionId"));
        CollectionDao collectionDao = new CollectionDao();
        int val = collectionDao.deleteCollection(collectionId);

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(val);
    }
}
