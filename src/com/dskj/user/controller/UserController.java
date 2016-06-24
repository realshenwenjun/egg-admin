package com.dskj.user.controller;

import java.io.IOException;
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
import com.dskj.sms.controller.SMSController;
import com.dskj.user.entity.UserEntity;
import com.dskj.user.entity.UserInfoBack;
import com.dskj.util.HttpUtil;
import com.dskj.util.MD5Util;
import com.dskj.util.Page;

@Controller
public class UserController extends Base {
	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/user/login.do")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		try {
			String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			logger.info(phone + " " + password);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("password", MD5Util.MD5Encode(password, "UTF-8"));
			map.put("phone", phone);
			String result = HttpUtil.post(server + "/user/login", "user="
					+ objToString(map));
			int code = readTreeAsInt(result, "code");
			if (code == 000) {
				Map<String, Object> user = new HashMap<String, Object>();
				user = stringToObj(readTree(result, "result"), HashMap.class);
				request.getSession().setAttribute("user", user);
				write(response, null, null, null, null);
			} else {
				write(response, false, 908, "用户名或密码错误", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/user/login_out.do")
	public void loginOut(HttpServletRequest request,
			HttpServletResponse response) {

		request.getSession().removeAttribute("user");
		try {
			response.sendRedirect("/admin/index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注册
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/user/regist.do")
	public void regist(HttpServletRequest request, HttpServletResponse response) {
		try {
			String phone = request.getParameter("phone");
			String password1 = request.getParameter("password");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("password", MD5Util.MD5Encode(password1, "UTF-8"));
			map.put("phone", phone);
			map.put("type", 3);
			logger.info(phone + " " + password1);
			String result = HttpUtil.post(server + "/user/add", "user="
					+ objToString(map));
			int code = readTreeAsInt(result, "code");
			if (code == 000) {
				write(response, null, null, null, null);
			} else if (code == 910) {
				write(response, false, 910, "该手机号已注册", null);
			} else if (code == 896) {
				write(response, false, 896, "该手机号正在注册中！", null);
			} else {
				write(response, false, -1, "注册未成功,请重新注册！", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户反馈列表
	 */
	@RequestMapping(value = "/infoback/list", method = RequestMethod.GET)
	public String getAnnouncementlList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", (pageNo - 1) * pageSize);
			map.put("pageSize", pageSize);
			logger.info(objToString(map));
			String result = HttpUtil.post(server + "/infoback/list",
					"infoback=" + objToString(map));
			List<UserInfoBack> list = jsonToList(readTree(result, "result"),
					ArrayList.class, UserInfoBack.class);
			String resultCount = HttpUtil.post(server + "/infoback/count", "");
			int count = readTreeAsInt(resultCount, "result");

			Page page = new Page();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotleCount(count);
			page.setPageCount((count + pageSize - 1) / pageSize);
			page.setUrl("/infoback/list.do");
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			return "pages/platforms/infoback_list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultPage;
	}

	/**
	 * 获取平台管理员列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/user/supper/admin/list.do", method = RequestMethod.GET)
	public String userSupperAdminList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(value = "key", defaultValue = "") String key) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", (pageNo - 1) * pageSize);
			map.put("pageSize", pageSize);
			String userId = (String) ((HashMap<String, Object>) request
					.getSession().getAttribute("user")).get("id");
			map.put("userId", userId);
			map.put("key", key);
			logger.info(objToString(map));
			String result = HttpUtil.post(server + "/user/supper/admin/list",
					"user=" + objToString(map));
			List<UserEntity> list = jsonToList(readTree(result, "result"),
					ArrayList.class, UserEntity.class);
			String resultCount = HttpUtil.post(server
					+ "/user/supper/admin/count", "user=" + objToString(map));
			int count = readTreeAsInt(resultCount, "result");

			Page page = new Page();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotleCount(count);
			page.setPageCount((count + pageSize - 1) / pageSize);
			page.setUrl("/user/supper/admin/list.do?key=" + key);
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			return "pages/platforms/user_admin_list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultPage;
	}

	/**
	 * 搜索平台用户用于添加平台管理员
	 */
	@RequestMapping(value = "/user/nomal/search.do", method = RequestMethod.GET)
	public String userSupperAdminSearch(HttpServletRequest request,
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
			String result = HttpUtil.post(server + "/user/nomal/search",
					"user=" + objToString(map));
			List<UserEntity> list = jsonToList(readTree(result, "result"),
					ArrayList.class, UserEntity.class);
			String resultCount = HttpUtil.post(server
					+ "/user/nomal/search/count", "user=" + objToString(map));
			int count = readTreeAsInt(resultCount, "result");

			Page page = new Page();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotleCount(count);
			page.setPageCount((count + pageSize - 1) / pageSize);
			page.setUrl("/user/nomal/search.do?key=" + key);
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			request.setAttribute("key", key);
			return "pages/platforms/user_admin_add";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultPage;
	}

	/**
	 * 获取平台管理员列表用于删除
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/user/supper/admin/delete/list.do", method = RequestMethod.GET)
	public String userSupperAdminDeleteList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(value = "key", defaultValue = "") String key) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", (pageNo - 1) * pageSize);
			map.put("pageSize", pageSize);
			String userId = (String) ((HashMap<String, Object>) request
					.getSession().getAttribute("user")).get("id");
			map.put("userId", userId);
			map.put("key", key);
			logger.info(objToString(map));
			String result = HttpUtil.post(server + "/user/supper/admin/list",
					"user=" + objToString(map));
			List<UserEntity> list = jsonToList(readTree(result, "result"),
					ArrayList.class, UserEntity.class);
			String resultCount = HttpUtil.post(server
					+ "/user/supper/admin/count", "user=" + objToString(map));
			int count = readTreeAsInt(resultCount, "result");

			Page page = new Page();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotleCount(count);
			page.setPageCount((count + pageSize - 1) / pageSize);
			page.setUrl("/user/supper/admin/delete/list.do?key=" + key);
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			request.setAttribute("key", key);
			return "pages/platforms/user_admin_delete";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultPage;
	}

	/**
	 * 添加平台管理员
	 */
	@RequestMapping(value = "/user/supper/admin/add.do", method = RequestMethod.GET)
	public void userSupperAdminAdd(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userId") String userId) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			logger.info(objToString(map));
			String result = HttpUtil.post(server + "/user/platforms/admin/add",
					"user=" + objToString(map));
			String message = readTree(result, "result");
			if (message == null || "".equals(message) || "null".equals(message)) {
				write(response, null, null, null, null);
			} else {
				write(response, false, null, "添加失败", message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除平台管理员
	 */
	@RequestMapping(value = "/user/supper/admin/delete.do", method = RequestMethod.GET)
	public void userSupperAdminDelete(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userId") String userId) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			logger.info(objToString(map));
			String result = HttpUtil.post(server
					+ "/user/platforms/admin/delete", "user="
					+ objToString(map));
			String message = readTree(result, "result");
			if (message == null || "".equals(message) || "null".equals(message)) {
				write(response, null, null, null, null);
			} else {
				write(response, false, null, "删除失败", message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/user/changePassword.do", method = RequestMethod.POST)
	public void changePassword(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String oldPassword = request.getParameter("oldPassword");
			if (oldPassword == null || "".equals(oldPassword)) {
				write(response, false, 911, "原始密码不能为空", null);
				return;
			}
			String newPassword = request.getParameter("newPassword");
			String conformPassword = request.getParameter("conformPassword");
			if (newPassword == null || "".equals(newPassword)) {
				write(response, false, 911, "新密码不能为空", null);
				return;
			}
			if (!newPassword.equals(conformPassword)) {
				write(response, false, 911, "两次密码不一样", null);
				return;
			}
			String password = (String) ((HashMap<String, Object>) request
					.getSession().getAttribute("user")).get("password");
			oldPassword = MD5Util.MD5Encode(newPassword, "UTF-8");
			if (oldPassword.equals(password)) {
				write(response, false, 911, "新密码不能和老密码相同", null);
				return;
			}
			String phone = (String) ((HashMap<String, Object>) request
					.getSession().getAttribute("user")).get("phone");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("password", MD5Util.MD5Encode(newPassword, "UTF-8"));
			map.put("phone", phone);
			String result = HttpUtil.post(server + "/user/change/password",
					"user=" + objToString(map));
			int code = readTreeAsInt(result, "code");
			if (code == 000) {
				request.getSession().removeAttribute("user");
				write(response, null, null, null, null);
			} else {
				write(response, false, 911, readTree(result, "message"), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 找回密码
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/user/getPassword.do", method = RequestMethod.POST)
	public void getPassword(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			String code = request.getParameter("code");

			if (password == null || "".equals(password)) {
				write(response, false, 911, "新密码不能为空", null);
				return;
			}
			if (code == null || "".equals(code)){
				write(response, false, 911, "验证码错误", null);
				return;
			}
			if (!code.equals(SMSController.codePool.get(phone))){
				write(response, false, 911, "验证码错误", null);
				return;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("password", MD5Util.MD5Encode(password, "UTF-8"));
			map.put("phone", phone);

			String result = HttpUtil.post(server + "/user/check/phone", "user="
					+ objToString(map));
			if (readTreeAsInt(result, "code") == 906) {
				write(response, false, 911, "手机号未注册", null);
				return;
			}
			if (readTreeAsInt(result, "code") != 0) {
				write(response, false, 911, "验证手机号异常", null);
				return;
			}

			result = HttpUtil.post(server + "/user/change/password", "user="
					+ objToString(map));
			int codeR = readTreeAsInt(result, "code");
			if (codeR == 000) {
				SMSController.codePool.remove(phone);
				write(response, null, null, null, null);
			} else {
				write(response, false, 911, readTree(result, "message"), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
