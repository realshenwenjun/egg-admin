package com.dskj.institution.controller;

import com.dskj.base.Base;
import com.dskj.institution.entity.InstitutionEntity;
import com.dskj.institution.entity.InstitutionWithPropa;
import com.dskj.util.HttpUtil;
import com.dskj.util.Page;
import com.dskj.util.StringUtil;
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

@Controller
public class InstitutionController extends Base {

    /**
     * 机构列表
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/institution/list.do", method = RequestMethod.GET)
    public String getInstitutionList(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                     @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                     @RequestParam(value = "key", defaultValue = "") String key) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pageNo", (pageNo - 1) * pageSize);
            map.put("pageSize", pageSize);
            map.put("key", key);
            logger.info(objToString(map));
            String result = HttpUtil.post(server + "/institution/list",
                    "institution=" + objToString(map));
            List<InstitutionWithPropa> list = jsonToList(
                    readTree(result, "result"), ArrayList.class,
                    InstitutionWithPropa.class);
            String resultCount = HttpUtil.post(server + "/institution/count",
                    "institution=" + objToString(map));
            int count = readTreeAsInt(resultCount, "result");
            Page page = new Page();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            page.setTotleCount(count);
            page.setPageCount((count + pageSize - 1) / pageSize);
            page.setUrl("/institution/list.do?key=" + key);
            request.setAttribute("list", list);
            request.setAttribute("page", page);
            request.setAttribute("key", key);
            return "pages/platforms/institution_list";
        } catch (Exception e) {
            e.printStackTrace();
            write(response, false, 911, e.getMessage(), null);
        }
        return "index";
    }

    /**
     * 添加机构
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/institution/add.do", method = RequestMethod.POST)
    public String addInstitution(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "logo", defaultValue = "") String logo,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "address", defaultValue = "") String address,
            @RequestParam(value = "summary", defaultValue = "") String summary,
            @RequestParam(value = "courseType", defaultValue = "") String courseType,
            @RequestParam(value = "tel", defaultValue = "") String tel,
            @RequestParam(value = "teacherCount", defaultValue = "") String teacherCount) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            map.put("summary", summary);
            map.put("phone", StringUtil.generateUUID8().toLowerCase());
            map.put("password", "123456");
            map.put("regionId", 0);
            map.put("logo", logo);
            map.put("pageNo", 0);
            map.put("pageSize", 5);
            map.put("address",address);
            map.put("courseType",courseType);
            map.put("teacherCount",teacherCount);
            map.put("tel",tel);
            logger.info(objToString(map));
            HttpUtil.post(server + "/institution/add", "institution="
                    + objToString(map));
            //按机构名称查询机构
            String result = HttpUtil.post(server + "/institution/list/by/name", "institution="
                    + objToString(map));
            if (result != null) {
                List<InstitutionEntity> list = jsonToList(
                        readTree(result, "result"), ArrayList.class,
                        InstitutionEntity.class);
                if (list != null && list.size() != 0) {
                    InstitutionEntity institutionEntity = list.get(0);
                    map.put("id", institutionEntity.getId());
                    HttpUtil.post(server + "/institution/update/info", "institution="
                            + objToString(map));
                }
            }
            return getInstitutionList(request, response, 1, 5, "");
        } catch (Exception e) {
            e.printStackTrace();
            write(response, false, 911, e.getMessage(), null);
        }
        return "index";
    }

    /**
     * 删除机构
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/institution/delete.do", method = RequestMethod.GET)
    public void deleteInstitution(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "institutionId", defaultValue = "") String institutionId) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("institutionId", institutionId);
            logger.info(objToString(map));
            String result = HttpUtil.post(server + "/institution/delete",
                    "institution=" + objToString(map));
            String message = readTree(result, "result");
            if (message == null || "".equals(message) || "null".equals(message)) {
                write(response, null, null, null, null);
            } else {
                write(response, false, null, "删除失败", message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            write(response, false, 911, e.getMessage(), null);
        }
    }
}
