package com.dskj.spread.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dskj.base.Base;
import com.dskj.spread.entity.Carousel;
import com.dskj.spread.entity.CarouselDetail;
import com.dskj.util.HttpUtil;

@Controller
public class CarouselControll extends Base {
	/*
	 * 上传轮播图页
	 */
	@RequestMapping(value = "/carousel/add/page", method = RequestMethod.GET)
	public String toAddCoursel(HttpServletRequest request,
			HttpServletResponse response) {
		return "pages/platforms/Carousel_add";
	}

	/*
	 * 上传轮播图{"path":"","val":"","context":""}
	 */
	@RequestMapping(value = "/carousel/add", method = RequestMethod.POST)
	public String uploadCoursel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "path") String path,
			@RequestParam(value = "context") String context,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "val") String val) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("path", path);
			map.put("val", val);
			map.put("context",
					Base64.encodeBase64String(context.getBytes("UTF-8")));
			map.put("title", title);
			logger.info(objToString(map));
			HttpUtil.post(server + "/carousel/add", "carousel="
					+ objToString(map));
			return getCourselList(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			write(response, false, 911, null, null);
		}
		return defaultPage;
	}

	/*
	 * 获取轮播图
	 */
	@RequestMapping(value = "/carousel/list", method = RequestMethod.GET)
	public String getCourselList(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String result = HttpUtil.post(server + "/carousel/list", "");
			JsonNode jsonNode = objectMapper.readTree(result);
			List<Carousel> list = jsonToList(
					jsonNode.path("result").toString(), ArrayList.class,
					Carousel.class);
			request.setAttribute("list", list);
			return "pages/platforms/Carousel_list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultPage;
	}

	/*
	 * 批量删除轮播图
	 */
	@RequestMapping(value = "/carousel/delete", method = RequestMethod.GET)
	public void delCourselFecth(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String jsoString = request.getParameter("id");
			logger.info(jsoString);
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(Integer.valueOf(jsoString));
			HttpUtil.post(server + "/carousel/delete", "carousel="
					+ objToString(ids));
			write(response, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			write(response, false, 911, null, null);
		}
	}

	/*
	 * 获取轮播图的详细
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/carousel/get", method = RequestMethod.GET)
	public String getCourselDetail(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("userId", ((HashMap<String, Object>) request.getSession()
					.getAttribute("user")).get("id"));
			logger.info(objToString(map));
			String result = HttpUtil.post(server + "/carousel/get", "carousel="
					+ objToString(map));
			CarouselDetail carouselDetail = stringToObj(
					readTree(result, "result"), CarouselDetail.class);
			request.setAttribute("carousel", carouselDetail);
			return "pages/platforms/Carousel_update";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultPage;
	}

	/*
	 * 修改轮播图
	 */
	@RequestMapping(value = "/carousel/update", method = RequestMethod.POST)
	public String updateCoursel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "path") String path,
			@RequestParam(value = "context") String context,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "val") String val) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("path", path);
			map.put("val", val);
			map.put("context",
					Base64.encodeBase64String(context.getBytes("UTF-8")));
			map.put("title", title);
			logger.info(objToString(map));
			HttpUtil.post(server + "/carousel/update", "carousel="
					+ objToString(map));
			return getCourselList(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			write(response, false, 911, null, null);
		}
		return defaultPage;
	}

}
