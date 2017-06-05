package cn.cup.cupServlet;

import cn.cup.bean.Cup;
import cn.cup.bean.RecordSet;
import cn.cup.bean.Type;
import cn.cup.dao.CupDao;
import cn.cup.dao.TypeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/27.
 */
@WebServlet(name = "IndexServlet" , urlPatterns = "/CupWeb/index.html")
public class IndexServlet extends HttpServlet {
//    private int pageSize = 4;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
//        RecordSet<Cup> hotCup = new CupDao().getCupsByHotTime(pageSize , 1);
        int pageCount = 1;
        if(null != request.getParameter("page")){
            pageCount = Integer.parseInt(request.getParameter("page"));
        }
        CupDao cupDao = new CupDao();
        // 热销爆款
        RecordSet<Cup> hotCup = cupDao.getCupsByHotTime(pageCount, 3);
        request.setAttribute("hotCupList" , hotCup.getRecordSet());
        // 推荐
        RecordSet<Cup> recCup = cupDao.getAllCups(2 , 1);
        request.setAttribute("recCupList" , recCup.getRecordSet());
        // 新品
        RecordSet<Cup> newCup = cupDao.getAllCups(pageCount , 4);
        request.setAttribute("newCupList" , newCup.getRecordSet());
        // 我们
        ArrayList<Type> types = new TypeDao().getAllTypes();
//        int[] cupTypes = new int[types.size()];
//        for (int i = 0 ; i<types.size() ; i++){
//            cupTypes[i] = types.get(i).getTypeId();
//        }

        ArrayList<RecordSet<Cup>> usCup = new ArrayList<RecordSet<Cup>>();

        // 获取所有杯子类型
        for(int i = 0 ; i<types.size() ; i++){
            RecordSet<Cup> cupByType = cupDao.getCupByType(types.get(i).getTypeId() , pageCount , 1);
//            System.out.println(cupByType.getRowCount());
            //System.out.println("cupyType"+cupByType.getRecordSet().get(i).getCupName());
            usCup.add(cupByType);
        }
//        System.out.println(usCup);
        request.setAttribute("usCupList" , usCup);
        request.setAttribute("Page" , pageCount + 1);

        request.getRequestDispatcher("index.jsp").forward(request , response);
    }
}
