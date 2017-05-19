package com.jx372.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		//인증한사람만 로그아웃이 가능해야함 
		
		HttpSession session = request.getSession();
	
		
		//로그아웃 처리 
		if(session != null && session.getAttribute("authUser") != null){
		session.removeAttribute("authUser");
		session.invalidate(); //세션 객체를 날려버림 
	
		}
		WebUtils.redirect("/mysite/main", request, response);

	}

}
