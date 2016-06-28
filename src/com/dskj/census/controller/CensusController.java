package com.dskj.census.controller;

import java.util.ArrayList;
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
import com.dskj.census.entity.UserCensusByChildInstitution;
import com.dskj.census.entity.UserEntity;
import com.dskj.util.HttpUtil;
import com.dskj.util.Page;

/**
 * Created by shenwenjun on 2016/4/28.
 */
@Controller
public class CensusController extends Base {
	/**
	 * 学生列表
	 * 
	 * @param request
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/census/student/list.do", method = RequestMethod.GET)
	public String studentList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", (pageNo - 1) * pageSize);
			map.put("pageSize", pageSize);
			logger.info(objToString(map));
			String result = HttpUtil.post(server + "/user/platforms/list",
					"user=" + objToString(map));
			List<UserEntity> list = jsonToList(readTree(result, "result"),
					ArrayList.class, UserEntity.class);
			String resultCount = HttpUtil.post(server + "/user/platforms/count",
					"");
			int count = readTreeAsInt(resultCount, "result");
			Page page = new Page();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotleCount(count);
			page.setPageCount((count + pageSize - 1) / pageSize);
			page.setUrl("/census/student/list.do");
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			return "pages/platforms/census_student";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	/**
	 * 分机构统计
	 * 
	 * @param request
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/census/institution/list.do", method = RequestMethod.GET)
	public String institutionList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", (pageNo - 1) * pageSize);
			map.put("pageSize", pageSize);
			logger.info(objToString(map));
			String result = HttpUtil.post(server + "/census/user/by/child/institution",
					"user=" + objToString(map));
			List<UserCensusByChildInstitution> list = jsonToList(readTree(result, "result"),
					ArrayList.class, UserCensusByChildInstitution.class);
			String resultCount = HttpUtil.post(server + "/user/platforms/count",
					"");
			int count = readTreeAsInt(resultCount, "result");
			Page page = new Page();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotleCount(count);
			page.setPageCount((count + pageSize - 1) / pageSize);
			page.setUrl("/census/student/list.do");
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			return "pages/platforms/census_student";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
}
