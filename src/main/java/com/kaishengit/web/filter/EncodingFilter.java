package com.kaishengit.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter extends AbstractFilter {
	String encode = "UTF-8";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String configValue = filterConfig.getInitParameter("encode");
		if(configValue != null){
			encode = configValue;
		}
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		arg0.setCharacterEncoding(encode);
		arg1.setCharacterEncoding("UTF-8");
		arg2.doFilter(arg0, arg1);
		
	}

}
