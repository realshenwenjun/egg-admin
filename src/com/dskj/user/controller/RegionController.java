package com.dskj.user.controller;

import com.dskj.base.Base;
import com.dskj.user.entity.RegionEntity;
import com.dskj.util.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2016/11/16.
 */
@Controller
public class RegionController extends Base {

    /**
     * 地区列表
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/region/list.do", method = RequestMethod.GET)
    public void getRegionList(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId", parentId);
            logger.info(objToString(map));
            String result = HttpUtil.post(server + "/region/get",
                    "region=" + objToString(map));
            List<RegionEntity> list = jsonToList(
                    readTree(result, "result"), ArrayList.class,
                    RegionEntity.class);
            write(response, true, null, null, list);
        } catch (Exception e) {
            e.printStackTrace();
            write(response, false, 911, e.getMessage(), null);
        }
    }
}
