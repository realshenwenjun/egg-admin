package com.dskj.user.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.dskj.filter.AdminFilter;
import com.dskj.filter.AdminFilterIface;

@Service
public class LoginFilter implements AdminFilterIface, InitializingBean {

	public String doFilter(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		String url = ((HttpServletRequest) request).getRequestURI();
		if (url.contains("user/login.do"))
			return null;
		if (url.contains("user/regist.do"))
			return null;
		if (url.contains("sms/send.do"))
			return null;
		if (url.contains("user/getPassword.do"))
			return null;
		if (url.contains("share.do"))
			return null;
		if (url.contains(".do") || url.contains(".jsp")) {
			Object o = ((HttpServletRequest) request).getSession()
					.getAttribute("user");
			if (o == null) {
				return "/admin/login.html";
			}
		}
		return null;
	}

	public void afterPropertiesSet() throws Exception {
		AdminFilter.addFilter(this);
	}

}
