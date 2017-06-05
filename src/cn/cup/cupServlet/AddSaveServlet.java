package cn.cup.cupServlet;

import cn.cup.dao.CollectionDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/10/28.
 */
@WebServlet(name = "AddSaveServlet" , urlPatterns = "/CupWeb/save.do")
public class AddSaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 接收用户id和杯子id
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        int cupId = Integer.parseInt(request.getParameter("cupId"));
        PrintWriter out = response.getWriter();
        CollectionDao collectionDao = new CollectionDao();
        int isSaved = collectionDao.searchCollect(memberId , cupId);
        if (isSaved == 0){
            // 未收藏
            int save = collectionDao.addCollection(memberId , cupId);
            if (save == 0){
                // 收藏失败
                out.print(2);
            }else {
                // 收藏成功
                out.print(1);
            }
        }else {
            // 已收藏
            out.print(0);
        }

    }
}
