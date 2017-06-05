package cn.cup.cupServlet;

import cn.cup.bean.Collect;
import cn.cup.bean.Member;
import cn.cup.bean.RecordSet;
import cn.cup.dao.CollectionDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/10/27.
 */
@WebServlet(name = "SaveServlet" , urlPatterns = "/CupWeb/save.html")
public class SaveServlet extends HttpServlet {
    private int pageSize = 6;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        Member member = (Member)request.getSession().getAttribute("member");
        if (member != null){
            int pageCount = 1;
            if(null != request.getParameter("page")){
                pageCount = Integer.parseInt(request.getParameter("page"));
            }
            CollectionDao collectionDao = new CollectionDao();
        RecordSet<Collect> collectRecordSet = collectionDao.getCollectionsByMemberId(member.getMemberId(), pageCount, pageSize);
//            RecordSet<Collect> collectRecordSet = collectionDao.getCollectionsByMemberId(1, pageCount, pageSize);
//            request.setAttribute("CollectList" , collectRecordSet.getRecordSet());

            request.setAttribute("CollectList" , collectRecordSet);
            request.setAttribute("Page" , pageCount + 1);
            request.getRequestDispatcher("save.jsp").forward(request , response);
        }else {
            response.sendRedirect("login.jsp");
        }


    }
}
