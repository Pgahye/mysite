package com.jx372.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


//@WebListener
public class ContextLoaderListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextevent)  { 
		servletContextevent.getServletContext().getInitParameter("contextConfigLocation"); //web.xml 객체 가지고 오기 ? 변수명...보기 
		
		System.out.println("컨테이너 시작하였습니다. " ); //어플리케이션 전체적으로 써야되는 객체를 초기화 
	   }
		
	
    public ContextLoaderListener() {
     
    }


    public void contextDestroyed(ServletContextEvent arg0)  { 
       
    }


  
}
