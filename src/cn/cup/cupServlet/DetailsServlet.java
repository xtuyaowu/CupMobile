package cn.cup.cupServlet;

import cn.cup.bean.Comment;
import cn.cup.bean.Cup;
import cn.cup.dao.CommentDao;
import cn.cup.dao.CupDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/28.
 */
@WebServlet(name = "DetailsServlet" , urlPatterns = "/CupWeb/goodsDetails.html")
public class DetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取商品id
        int cupId = Integer.parseInt(request.getParameter("cupId"));
//        System.out.println(cupId);
        Cup cup = new CupDao().getCupByCupId(cupId);
        request.setAttribute("Cup" , cup);

        // 根据cupid查看共有多少条评论
        int count = new CommentDao().getCommentCountByCupId(cupId);
        request.setAttribute("CommentCount" , count);
//        System.out.println("count"+count);


        // 根据cupid获取所有评论
        ArrayList<Comment> comments = new CommentDao().getAllCommentByCupId(cupId);
        request.setAttribute("Comment" , comments);

        request.getRequestDispatcher("goodsDetails.jsp").forward(request , response);

    }
}
