package com.dskj.institution.controller;

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
import com.dskj.institution.entity.InstitutionWithPropa;
import com.dskj.util.HttpUtil;
import com.dskj.util.Page;

@Controller
public class InstitutionController extends Base {
	
	
	/**
	 * 机构列表
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
			List<InstitutionWithPropa> list = jsonToList(readTree(result, "result"),
					ArrayList.class, InstitutionWithPropa.class);
			String resultCount = HttpUtil.post(server + "/institution/count",
					"institution="+objToString(map));
			int count = readTreeAsInt(resultCount, "result");
			Page page = new Page();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotleCount(count);
			page.setPageCount((count + pageSize - 1) / pageSize);
			page.setUrl("/institution/list.do?key="+key);
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
	 * 删除机构
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/institution/delete.do", method = RequestMethod.GET)
	public void deleteInstitution(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "institutionId", defaultValue = "") String institutionId) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("institutionId", institutionId);
			logger.info(objToString(map));
			String result = HttpUtil.post(server
					+ "/institution/delete", "institution="
					+ objToString(map));
			String message = readTree(result, "result");
			if(message == null || "".equals(message) || "null".equals(message)){
				write(response, null, null, null, null);
			}else{
				write(response, false, null, "删除失败", message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			write(response, false, 911, e.getMessage(), null);
		}
	}
}
