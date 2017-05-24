package com.jx372.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


//@WebFilter("/*")
public class EncodingFilter implements Filter {

	
	private String encoding;
	public void init(FilterConfig fConfig) throws ServletException {
		
		//초기화 
		
		
		System.out.println("encoding filter init......");
		encoding = fConfig.getInitParameter("encoding"); //web.xml에 정의 해놓음 
		if(encoding == null){ //default filter 
			
			encoding = "UTF-8";
			
		}
		
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		// request 처리
		request.setCharacterEncoding(encoding);
		
		chain.doFilter(request, response);
		
		//response 처리 
		
		
	}
  
	
	public void destroy() {
		
	}

    public EncodingFilter() {
     
    }

	

	



	

}
