package cn.cup.cupServlet;

import cn.cup.bean.Address;
import cn.cup.dao.AddressDao;
import cn.cup.dao.RegionDao;
import org.json.JSONObject;

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
@WebServlet(name = "AddAddressServlet" , urlPatterns = "/CupWeb/addAddress.do")
public class AddAddressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //{'province' : $province.val() , 'city' : $city.val() ,
        // 'country' : $country.val() , 'phone' : $phone.val() , 'address' : $addr.val()},
        // 获取信息

        int province = Integer.parseInt(request.getParameter("province"));
        int city = Integer.parseInt(request.getParameter("city"));
        int country = Integer.parseInt(request.getParameter("country"));
        int memberId = Integer.parseInt(request.getParameter("memberId"));

        String receiverName = request.getParameter("receiverName");
        String phone = request.getParameter("phone");
        String addr = request.getParameter("address");
        //int addressId, int memberId, String receiverName,
        // String province, String city, String county, String addressName, String receiverTel

        RegionDao regionDao = new RegionDao();

        String pro = regionDao.getProvinceBycode(province);
        String ci = regionDao.getCityBycode(city);
        String cou = "";
        if (country != 0){
            cou = regionDao.getCountryBycode(country);
        }

        Address address = new Address(0 , memberId , receiverName , pro , ci , cou , addr , phone);

        // 增加到数据库
        int val = new AddressDao().addAddress(address);

        Address address1 = new Address();
        String result = null;
        if (val >= 1){
            address1 = new AddressDao().getLastAddress(memberId);
//            System.out.println("address1:"+address1);
////            JSONArray jsonArray = "";
//            try {
//                JSONArray jsonArray = new JSONArray(address1);
//                result = jsonArray.toString();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            result = jsonArray.toString();

            JSONObject jsonObject = new JSONObject(address1);
            result = jsonObject.toString();
        }


        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);


    }
}
