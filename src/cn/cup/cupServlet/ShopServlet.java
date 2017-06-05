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
 * Created by Administrator on 2016/10/27.
 */
@WebServlet(name = "ShopServlet" , urlPatterns = "/CupWeb/shop.html")
public class ShopServlet extends HttpServlet {
    private int pageSize = 8;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        RecordSet<Cup> hotCup = new CupDao().getCupsByHotTime(pageSize , 1);

        //
        int pageCount = 1;
        if(null != request.getParameter("page")){
            pageCount = Integer.parseInt(request.getParameter("page"));
        }
        int option = 1;
        if(null != request.getParameter("option")&& !"".equals(request.getParameter("option"))){
            option = Integer.parseInt(request.getParameter("option"));
        }
        int materia = 0;
        if(null != request.getParameter("materia") && !"".equals(request.getParameter("materia"))){
            materia = Integer.parseInt(request.getParameter("materia"));
        }
        int type = 0;
        if(null != request.getParameter("type") && !"".equals(request.getParameter("type"))){
            type = Integer.parseInt(request.getParameter("type"));
        }
        double capStart=0;
        if(null != request.getParameter("capStart") && !"".equals(request.getParameter("capStart"))){
            capStart = Double.parseDouble(request.getParameter("capStart"));
        }
        double capEnd = 0;
        if(null != request.getParameter("capEnd") && !"".equals(request.getParameter("capEnd"))){
            capEnd = Double.parseDouble(request.getParameter("capEnd"));
        }
        CupDao cupDao = new CupDao();
        RecordSet<Cup> cups = null;
        int maxPage = 0;
        if(materia == 0 && type == 0 && capStart ==0 && capEnd == 0){
            if(option == 1) {
                // 默认排序
                cups = cupDao.getAllCups(pageCount, pageSize);
            }else if(option == 2){
                // 热销排序
                cups = cupDao.getCupsByHotTime(pageCount, pageSize);
            }
        }else{
            cups = cupDao.getCupByFilter(materia,type,capStart,capEnd,pageCount,pageSize,option);
        }
        if(cups != null){
            //把2改成pageSize
            maxPage = cups.getRowCount()%pageSize==0?cups.getRowCount()/pageSize:cups.getRowCount()/pageSize+1;
        }
//        System.out.println("1111111");
        request.setAttribute("Cups" , cups);
        request.setAttribute("maxPage",maxPage);

        request.setAttribute("Page" , pageCount + 1);
        request.setAttribute("option",option);
        request.setAttribute("materia",materia);
        request.setAttribute("type",type);
        request.setAttribute("capStart",capStart);
        request.setAttribute("capEnd",capEnd);
        request.getRequestDispatcher("goodsItem.jsp").forward(request , response);
    }
}
