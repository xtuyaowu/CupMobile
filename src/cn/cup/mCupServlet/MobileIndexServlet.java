package cn.cup.mCupServlet;

import cn.cup.bean.Cup;
import cn.cup.bean.RecordSet;
import cn.cup.bean.Type;
import cn.cup.dao.CupDao;
import cn.cup.dao.TypeDao;
import cn.cup.view.GoodsView;
import cn.cup.view.IndexView;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by yc on 2017/2/6.
 */
@WebServlet(name = "MobileIndexServlet" , urlPatterns = "/index")
public class MobileIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int pageCount = 1;
        CupDao cupDao = new CupDao();
        IndexView iv = new IndexView(); // 用三个ArrayList存储三个模块
        GoodsView gv = new GoodsView(); // 存储杯子信息以及第一张图片地址信息

        // 新品
        ArrayList<Cup> newCups = (ArrayList<Cup>)cupDao.getAllCups(pageCount , 4).getRecordSet();
        ArrayList<GoodsView> newGoodsViews = new ArrayList<GoodsView>();
        for (int i = 0 ; i<newCups.size() ; i++){
            gv = new GoodsView(newCups.get(i));
//            gv.setCup(newCups.get(i));
            newGoodsViews.add(gv);
        }

        // 热销
        ArrayList<Cup> hotCups = (ArrayList<Cup>)cupDao.getCupsByHotTime(pageCount , 3).getRecordSet();
        ArrayList<GoodsView> hotGoodsViews = new ArrayList<GoodsView>();
//        System.out.println(hotCups.size());
        for (int i = 0 ; i<hotCups.size() ; i++){
            gv = new GoodsView(hotCups.get(i));
            hotGoodsViews.add(gv);
        }

        // 款式多多
        // 根据所有类型获取一个杯子
        ArrayList<Cup> typeCups = new ArrayList<Cup>();
        // 获取所有类型
        ArrayList<Type> types = new TypeDao().getAllTypes();
        Cup cup = new Cup();
        for (int  i = 0 ; i<4 ; i++){
            cup = cupDao.getCupByType(types.get(i).getTypeId(),1,1).getRecordSet().get(0);
            typeCups.add(cup);
        }
//        System.out.println(typeCups.size());
        ArrayList<GoodsView> typeGoodsViews = new ArrayList<GoodsView>();
        for (int i = 0 ; i<typeCups.size() ; i++){
            gv = new GoodsView(typeCups.get(i));
            typeGoodsViews.add(gv);
        }

        iv.setNewCups(newGoodsViews);
        iv.setHotCups(hotGoodsViews);
        iv.setTypeCups(typeGoodsViews);

        JSONObject jsonObject = new JSONObject(iv);



        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
    }
}
