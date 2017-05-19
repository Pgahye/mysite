package com.jx372.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.dao.UserDao;
import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		//로그인된 사람만 접속할수 있음 
		//1. 인증여부를 확인
		
		HttpSession session = request.getSession();
		
		if(session == null){
			
			WebUtils.redirect("/mysite/user?a=loginform", request, response);
			return;
			
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null){
			
			WebUtils.redirect("/mysite/user?a=loginform", request, response);
			return;
			
		}
		
		Long no = authUser.getNo();	
		UserVo vo =new UserDao().get(no);
		
		session.setAttribute("authUser", vo);
		

		
		
		WebUtils.forward("/WEB-INF/views/user/modifyform.jsp", request, response);
		

	}

}
