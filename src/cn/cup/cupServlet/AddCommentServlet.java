package cn.cup.cupServlet;

import cn.cup.dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/10/30.
 */
@WebServlet(name = "AddCommentServlet" , urlPatterns = "/CupWeb/addComment.do")
public class AddCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取杯子id，用户名、评论
        int cupId = Integer.parseInt(request.getParameter("cupId"));
        String memberName = request.getParameter("memberName");
        String comment = request.getParameter("comment");

        // 新增评论
        int val = new CommentDao().addCommentByCupId(cupId , memberName , comment);

        // 响应
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        out.print(val);

    }
}
