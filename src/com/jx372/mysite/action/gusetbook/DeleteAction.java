package com.jx372.mysite.action.gusetbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.guestBookDao;
import com.jx372.mysite.vo.guestBookVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	
		String pwd =  WebUtils.checkParameter(request.getParameter("pwd"), "");

		Long no =  WebUtils.checkParameter(request.getParameter("no"), 0L);

		guestBookVo vo=new guestBookVo();
		vo.setNo(Long.valueOf(no));
		vo.setPwd(pwd);

		System.out.println(pwd);
		System.out.println(no);
		
		new guestBookDao().delete(vo);
		
		//리다이렉트 응답
		WebUtils.redirect(request.getContextPath()+"/guestbook", request, response);
		//response.sendRedirect("/guestbook2/gb");  //밑의 html은 필요가 없어짐 
	}

}
