package com.dskj.announcement.controller;

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

import com.dskj.announcement.entity.Announcement;
import com.dskj.base.Base;
import com.dskj.util.HttpUtil;
import com.dskj.util.Page;

@Controller
public class AnnouncementControll extends Base {

	/*
	 * 获取公告列表
	 */
	@RequestMapping(value = "/announcement/list", method = RequestMethod.GET)
	public String getAnnouncementlList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", (pageNo - 1) * pageSize);
			map.put("pageSize", pageSize);
			logger.info(objToString(map));
			String result = HttpUtil.post(server
					+ "/announcement/platfoems/list", "announcement="
					+ objToString(map));
			List<Announcement> list = jsonToList(readTree(result, "result"),
					ArrayList.class, Announcement.class);
			String resultCount = HttpUtil.post(server + "/announcement/count",
					"");
			int count = readTreeAsInt(resultCount, "result");

			Page page = new Page();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotleCount(count);
			page.setPageCount((count + pageSize - 1) / pageSize);
			page.setUrl("/announcement/list.do");
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			return "pages/platforms/announcement_list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultPage;
	}

	/*
	 * 添加公告
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/announcement/add", method = RequestMethod.POST)
	public String addAnnouncement(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "typeId", defaultValue = "1") int typeId,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "annoImage", defaultValue = "") String annoImage,
			@RequestParam(value = "content", defaultValue = "") String content) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("belong", 0);
			map.put("typeId", typeId);
			map.put("title", title);
			map.put("annoImage", annoImage);
			map.put("content", content);
			map.put("userId", ((HashMap<String, Object>) request.getSession()
					.getAttribute("user")).get("id"));
			logger.info(objToString(map));
			HttpUtil.post(server + "/announcement/add", "announcement="
					+ objToString(map));
			return getAnnouncementlList(request, response, 1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultPage;
	}

	/*
	 * 删除公告
	 */
	@RequestMapping(value = "/announcement/delete", method = RequestMethod.GET)
	public void deleteAnnouncement(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "id") int id) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ids", Arrays.asList(id));
			logger.info(objToString(map));
			HttpUtil.post(server + "/announcement/delete", "announcement="
					+ objToString(map));
			write(response, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			write(response, false, 911, null, null);
		}
	}

	/*
	 * 公告详情
	 */
	@RequestMapping(value = "/announcement/get", method = RequestMethod.GET)
	public String getAnnouncement(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "id") int id) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			logger.info(objToString(map));
			String result = HttpUtil.post(server + "/announcement/get",
					"announcement=" + objToString(map));
			Announcement announcement = stringToObj(readTree(result, "result"),
					Announcement.class);
			request.setAttribute("announcement", announcement);
			return "pages/platforms/announcement_update";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultPage;
	}
	
	/*
	 * 编辑公告
	 */
	@RequestMapping(value = "/announcement/update", method = RequestMethod.POST)
	public String updateAnnouncement(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "id") int id,
			@RequestParam(value = "typeId", defaultValue = "1") int typeId,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "annoImage", defaultValue = "") String annoImage,
			@RequestParam(value = "content", defaultValue = "") String content) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("typeId", typeId);
			map.put("title", title);
			map.put("annoImage", annoImage);
			map.put("content", content);
			logger.info(objToString(map));
			HttpUtil.post(server + "/announcement/update",
					"announcement=" + objToString(map));
			return getAnnouncementlList(request, response, 1, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultPage;
	}
}
