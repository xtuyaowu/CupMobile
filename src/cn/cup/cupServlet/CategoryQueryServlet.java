package cn.cup.cupServlet;

import cn.cup.bean.Material;
import cn.cup.bean.Type;
import cn.cup.dao.MaterialDao;
import cn.cup.dao.TypeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/30.
 */
@WebServlet(name = "CategoryQueryServlet" , urlPatterns = "/CupWeb/categoryQuery.do")
public class CategoryQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 材质
        ArrayList<Material> materials = new MaterialDao().getAllMaterials();
        request.setAttribute("Material" , materials);
        // 分类
        ArrayList<Type> types = new TypeDao().getAllTypes();
        request.setAttribute("Type" , types);
        request.getRequestDispatcher("category.jsp").forward(request,response);
    }
}
