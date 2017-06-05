package cn.cup.cupServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/10/22.
 */
@WebServlet("/CupWeb/ChkAuthImg.do")
public class ChkAuthImgServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rand = (String)request.getSession().getAttribute("rand");//获取当前的验证码
//        System.out.println("rand:"+rand);
        String userRand = (String)request.getParameter("userRandom");//获得用户输入的验证码
//        System.out.println("userRand:"+userRand);
        boolean result = false;
        if(userRand!=null && !"".equals(userRand)){
            if(rand.equalsIgnoreCase(userRand)){
                result =true;
            }
        }
//        System.out.println("result:"+result);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);

    }
}
