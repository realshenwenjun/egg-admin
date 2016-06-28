package com.dskj.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface AdminFilterIface {
	
	public String doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException;

}
