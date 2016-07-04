package com.dskj.activity.controller;

import com.dskj.activity.entity.CancelReason;
import com.dskj.activity.entity.ChildActivity;
import com.dskj.activity.entity.ChildActivityAsk;
import com.dskj.activity.entity.UserActivitySign;
import com.dskj.base.Base;
import com.dskj.util.HttpUtil;
import com.dskj.util.Page;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by shenwenjun on 2016/4/28.
 */
@Controller
public class ActivityController extends Base {
    /**
     * 活动列表
     *
     * @param request
     * @param response
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/activity/list.do", method = RequestMethod.GET)
    public String list(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(value = "pageSize", defaultValue = "9") int pageSize) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pageNo", (pageNo - 1) * pageSize);
            map.put("pageSize", pageSize);
            logger.info(objToString(map));
            String result = HttpUtil.post(server + "/childActivity/list",
                    "childActivity=" + objToString(map));
            List<ChildActivity> list = jsonToList(readTree(result, "result"),
                    ArrayList.class, ChildActivity.class);
            String resultCount = HttpUtil.post(server + "/childActivity/count",
                    "");
            int count = readTreeAsInt(resultCount, "result");
            Page page = new Page();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            page.setTotleCount(count);
            page.setPageCount((count + pageSize - 1) / pageSize);
            page.setUrl("/activity/list.do");
            request.setAttribute("list", list);
            request.setAttribute("page", page);
            return "pages/platforms/activity_list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * 增加活动
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/activity/add.do", method = RequestMethod.POST)
    public String add(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(value = "name") String name,
                      @RequestParam(value = "title") String title,
                      @RequestParam(value = "contact") String contact,
                      @RequestParam(value = "url") String url,
                      @RequestParam(value = "oldPrice") String oldPrice,
                      @RequestParam(value = "price") String price,
                      @RequestParam(value = "shortDetail") String shortDetail,
                      @RequestParam(value = "thumbImg") String thumbImg,
                      @RequestParam(value = "detail") String detail,
                      @RequestParam(value = "beginDate") String beginDate,
                      @RequestParam(value = "endDate") String endDate,
                      @RequestParam(value = "reserveBeginDate") String reserveBeginDate,
                      @RequestParam(value = "reserveEndDate") String reserveEndDate) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            map.put("title", title);
            map.put("url", url);
            map.put("oldPrice", oldPrice);
            map.put("contact", contact);
            map.put("price", price);
            map.put("shortDetail", shortDetail);
            map.put("thumbImg", thumbImg);
            map.put("adverImg", thumbImg);
            map.put("detail", Base64.encodeBase64String(detail.getBytes("UTF-8")));
            map.put("beginDate", beginDate);
            map.put("endDate", endDate);
            map.put("reserveBeginDate", reserveBeginDate);
            map.put("reserveEndDate", reserveEndDate);
            logger.info(objToString(map));
            HttpUtil.post(server + "/childActivity/add", "childActivity="
                    + objToString(map));
            return list(request, response, 1, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * 修改活动
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/activity/update.do", method = RequestMethod.POST)
    public String update(HttpServletRequest request,
                         HttpServletResponse response, @RequestParam(value = "id") int id,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "title") String title,
                         @RequestParam(value = "url") String url,
                         @RequestParam(value = "oldPrice") Double oldPrice,
                         @RequestParam(value = "contact") String contact,
                         @RequestParam(value = "price") Double price,
                         @RequestParam(value = "shortDetail") String shortDetail,
                         @RequestParam(value = "thumbImg") String thumbImg,
                         @RequestParam(value = "detail") String detail,
                         @RequestParam(value = "beginDate") String beginDate,
                         @RequestParam(value = "endDate") String endDate,
                         @RequestParam(value = "reserveBeginDate") String reserveBeginDate,
                         @RequestParam(value = "reserveEndDate") String reserveEndDate) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("name", name);
            map.put("title", title);
            map.put("contact", contact);
            map.put("url", url);
            map.put("oldPrice", oldPrice);
            map.put("price", price);
            map.put("shortDetail", shortDetail);
            map.put("thumbImg", thumbImg);
            map.put("adverImg", thumbImg);
            map.put("detail", Base64.encodeBase64String(detail.getBytes("UTF-8")));
            map.put("beginDate", beginDate);
            map.put("endDate", endDate);
            map.put("reserveBeginDate", reserveBeginDate);
            map.put("reserveEndDate", reserveEndDate);
            logger.info(objToString(map));
            HttpUtil.post(server + "/childActivity/update", "childActivity="
                    + objToString(map));
            return list(request, response, 1, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * 活动统计
     *
     * @param request
     * @param response
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/activity/census.do", method = RequestMethod.GET)
    public String census(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                         @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pageNo", (pageNo - 1) * pageSize);
            map.put("pageSize", pageSize);
            logger.info(objToString(map));
            String result = HttpUtil.post(server + "/childActivity/list",
                    "childActivity=" + objToString(map));
            List<ChildActivity> list = jsonToList(readTree(result, "result"),
                    ArrayList.class, ChildActivity.class);
            String resultCount = HttpUtil.post(server + "/childActivity/count",
                    "");
            int count = readTreeAsInt(resultCount, "result");
            Page page = new Page();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            page.setTotleCount(count);
            page.setPageCount((count + pageSize - 1) / pageSize);
            page.setUrl("/activity/census.do");
            request.setAttribute("list", list);
            request.setAttribute("page", page);
            return "pages/platforms/activity_census";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * 活动咨询服务
     *
     * @param request
     * @param response
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/activity/ask/list.do", method = RequestMethod.GET)
    public String getActivityMyAsk(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                   @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pageNo", (pageNo - 1) * pageSize);
            map.put("pageSize", pageSize);
            logger.info(objToString(map));
            String result = HttpUtil.post(
                    server + "/childActivity/my/ask/list", "childActivity="
                            + objToString(map));
            List<ChildActivityAsk> list = jsonToList(
                    readTree(result, "result"), ArrayList.class,
                    ChildActivityAsk.class);
            String resultCount = HttpUtil.post(server
                    + "/childActivity/my/ask/count", "");
            int count = readTreeAsInt(resultCount, "result");
            Page page = new Page();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            page.setTotleCount(count);
            page.setPageCount((count + pageSize - 1) / pageSize);
            page.setUrl("/activity/ask/list.do");
            request.setAttribute("list", list);
            request.setAttribute("page", page);
            return "pages/platforms/activity_ask_list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * 管理员回复活动咨询
     *
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/activity/ask/answer/add.do", method = RequestMethod.POST)
    public void addAskAnswer(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "askId") int askId,
                             @RequestParam(value = "context") String context) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("askId", askId);
            map.put("context", context);
            map.put("userId", ((HashMap<String, Object>) request.getSession()
                    .getAttribute("user")).get("id"));
            logger.info(objToString(map));
            HttpUtil.post(server + "/childActivity/ask/answer/add",
                    "childActivity=" + objToString(map));
            write(response, null, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询亲子活动
     *
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/activity/get.do", method = RequestMethod.GET)
    public String getActivity(HttpServletRequest request,
                              HttpServletResponse response, @RequestParam(value = "id") int id) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("userId", ((HashMap<String, Object>) request.getSession()
                    .getAttribute("user")).get("id"));
            logger.info(objToString(map));
            String result = HttpUtil.post(server + "/childActivity/get",
                    "childActivity=" + objToString(map));
            ChildActivity childActivity = stringToObj(
                    readTree(result, "result"), ChildActivity.class);
            request.setAttribute("activity", childActivity);
            return "pages/platforms/activity_update";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * 删除亲子活动
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/activity/delete.do", method = RequestMethod.GET)
    public void deleteActivity(HttpServletRequest request,
                               HttpServletResponse response, @RequestParam(value = "id") int id) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ids", Arrays.asList(id));
            logger.info(objToString(map));
            HttpUtil.post(server + "/childActivity/delete", "childActivity="
                    + objToString(map));
            write(response, null, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 活动反馈列表
     *
     * @param request
     * @param response
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/activity/feedback/list.do", method = RequestMethod.GET)
    public String feedbackList(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pageNo", (pageNo - 1) * pageSize);
            map.put("pageSize", pageSize);
            logger.info(objToString(map));
            String result = HttpUtil.post(server
                    + "/childActivity/feedback/list", "childActivity="
                    + objToString(map));
            List<ChildActivityAsk> list = jsonToList(
                    readTree(result, "result"), ArrayList.class,
                    ChildActivityAsk.class);
            String resultCount = HttpUtil.post(server
                    + "/childActivity/feedback/count", "");
            int count = readTreeAsInt(resultCount, "result");
            String censusResult = HttpUtil.post(server
                    + "/childActivity/feedback/census", "childActivity=");
            List<CancelReason> censusList = jsonToList(
                    readTree(censusResult, "result"), ArrayList.class,
                    CancelReason.class);
            request.setAttribute("censusList", censusList);
            Page page = new Page();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            page.setTotleCount(count);
            page.setPageCount((count + pageSize - 1) / pageSize);
            page.setUrl("/activity/feedback/list.do");
            request.setAttribute("list", list);
            request.setAttribute("page", page);
            return "pages/platforms/activity_feedback_list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * 活动报名列表
     *
     * @param request
     * @param response
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/activity/user/sign/list.do", method = RequestMethod.GET)
    public String userSignList(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("activityId", 0);
            map.put("pageNo", (pageNo - 1) * pageSize);
            map.put("pageSize", pageSize);
            logger.info(objToString(map));
            String result = HttpUtil.post(server
                    + "/childActivity/sign/user/list", "childActivity="
                    + objToString(map));
            List<UserActivitySign> list = jsonToList(
                    readTree(result, "result"), ArrayList.class,
                    UserActivitySign.class);
            String resultCount = HttpUtil.post(server
                    + "/childActivity/sign/user/count", "childActivity="
                    + objToString(map));
            int count = readTreeAsInt(resultCount, "result");
            Page page = new Page();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            page.setTotleCount(count);
            page.setPageCount((count + pageSize - 1) / pageSize);
            page.setUrl("/activity/user/sign/list.do");
            request.setAttribute("list", list);
            request.setAttribute("page", page);
            return "pages/platforms/activity_sign_list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}
