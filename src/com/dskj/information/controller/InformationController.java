package com.dskj.information.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dskj.base.Base;
import com.dskj.information.entity.Information;
import com.dskj.util.HttpUtil;
import com.dskj.util.Page;

/**
 * Created by shenwenjun on 2016/4/28.
 */
@Controller
public class InformationController extends Base {
	/**
	 * 资讯列表
	 * 
	 * @param request
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/information/list.do", method = RequestMethod.GET)
	public String list(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "9") int pageSize) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", (pageNo - 1) * pageSize);
			map.put("pageSize", pageSize);
			map.put("userId", ((HashMap<String, Object>) request.getSession()
					.getAttribute("user")).get("id"));
			logger.info(objToString(map));
			String result = HttpUtil.post(server + "/information/list",
					"information=" + objToString(map));
			List<Information> list = jsonToList(readTree(result, "result"),
					ArrayList.class, Information.class);
			String resultCount = HttpUtil.post(server + "/information/count",
					"");
			int count = readTreeAsInt(resultCount, "result");
			Page page = new Page();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotleCount(count);
			page.setPageCount((count + pageSize - 1) / pageSize);
			page.setUrl("/information/list.do");
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			return "pages/platforms/information_list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	/**
	 * 增加资讯
	 * 
	 * @param request
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/information/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "path") String path,
			@RequestParam(value = "context") String context,
			@RequestParam(value = "title") String title) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("imgUrls", Arrays.asList(path));
			map.put("title", title);
			map.put("context", context);
			map.put("userId", ((HashMap<String, Object>) request.getSession()
					.getAttribute("user")).get("id"));
			logger.info(objToString(map));
			HttpUtil.post(server + "/information/add",
					"information=" + objToString(map));
			return list(request, response, 1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	/**
	 * 修改资讯
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/information/update.do", method = RequestMethod.POST)
	public String update(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id") int id,
			@RequestParam(value = "path") String path,
			@RequestParam(value = "context") String context,
			@RequestParam(value = "title") String title) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("imgUrl", path);
			map.put("title", title);
			map.put("context", context);
			map.put("id", id);
			logger.info(objToString(map));
			HttpUtil.post(server + "/information/update",
					"information=" + objToString(map));
			return list(request, response, 1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	/**
	 * 获取资讯
	 * 
	 * @param request
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/information/get.do", method = RequestMethod.GET)
	public String get(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id") int id) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("infoId", id);
			map.put("userId", ((HashMap<String, Object>) request.getSession()
					.getAttribute("user")).get("id"));
			logger.info(objToString(map));
			String result = HttpUtil.post(server + "/information/get",
					"information=" + objToString(map));
			Information information = stringToObj(readTree(result, "result"), Information.class);
			request.setAttribute("information", information);
			return "pages/platforms/information_update";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	/**
	 * 删除资讯
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/information/delete.do", method = RequestMethod.GET)
	public void delete(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id") int id) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("infoId", id);
			logger.info(objToString(map));
			HttpUtil.post(server + "/information/delete",
					"information=" + objToString(map));
			write(response, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
