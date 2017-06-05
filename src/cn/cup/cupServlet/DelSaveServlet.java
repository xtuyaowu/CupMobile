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
@WebServlet(name = "DelSaveServlet" , urlPatterns = "/CupWeb/delSave.do")
public class DelSaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取需要删除的收藏id
//        int[] collectId = (Array)request.getParameter("collectId");
        String collectIds = request.getParameter("collectId");
        String[] idArr = collectIds.split(",");
        int val = 0;
        for (int i = 0 ; i<idArr.length ; i++){
            int collectId = Integer.parseInt(idArr[i]);
            val = new CollectionDao().deleteCollection(collectId);
            if (val == 0){
                break;
            }
        }
        PrintWriter out = response.getWriter();
        out.print(val);

    }
}
