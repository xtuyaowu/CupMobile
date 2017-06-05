package cn.cup.mCupServlet;

import cn.cup.bean.Cup;
import cn.cup.dao.CupDao;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yc on 2017/2/24.
 */
@WebServlet(name = "DetailServlet" , urlPatterns = "/detail")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int cupId = Integer.parseInt(request.getParameter("id"));

        Cup cup = new Cup();
        CupDao cupDao = new CupDao();
        cupDao.addCupClickTime(cupId);

        cup = cupDao.getCupByCupId(cupId);
        JSONObject result = new JSONObject(cup);


        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
