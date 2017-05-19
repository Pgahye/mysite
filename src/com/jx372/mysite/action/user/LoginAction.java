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

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		
		String password = request.getParameter("password");
		
		UserVo vo = new UserDao().get(email, password);
		
		if(vo == null){ //인증실패 
			
			//WebUtils.redirect("/mysite/user?a=loginform&result=fail", request, response); //fail에 따라 로그인 실패했습니다. 유무 , 리다이렉트 방식 
			
			request.setAttribute("result", "fail");
			WebUtils.forward("/WEB-INF/views/user/loginform.jsp", request, response); //포워드 방식 , 뷰만 바꾸기 
			
			return ;  // 끝나지 않으면 코드가 끊나지 않아서 오류남 
		}
		
		// 인증처리 
		HttpSession session = request.getSession(true); //세션주세요 없으면 만드세요 
		session.setAttribute("authUser", vo);
		
		// 메인으로 이동 
		
		WebUtils.redirect("/mysite/main", request, response);

	}

}
