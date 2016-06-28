package com.dskj.share.controller;

import com.dskj.base.Base;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by shenwenjun on 2016/4/28.
 */
@Controller
public class ShareController extends Base {

    @RequestMapping(value = "/share.do", method = RequestMethod.GET)
    public void share(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = "";
            String head = request.getParameter("head");
            String server = request.getParameter("server");
            url = head + "://" + server + "@";
            Map<String, String[]> map = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                url = url + entry.getKey().toString() + "=" + request.getParameter(entry.getKey().toString()) + "!";
            }
            response.sendRedirect("/admin/share.html?u="+url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
