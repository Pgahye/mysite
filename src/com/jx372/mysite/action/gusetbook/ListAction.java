package com.jx372.mysite.action.gusetbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.guestBookDao;
import com.jx372.mysite.vo.guestBookVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		List<guestBookVo> list = new guestBookDao().getList(); //ctrl +Space 
		
		request.setAttribute("list", list);
		//포워딩 (jsp 경로가 필요함) 
		
		WebUtils.forward("/WEB-INF/views/guestbook/list.jsp", request, response);

	}

}
