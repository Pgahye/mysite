package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.dao.UserDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	
			
		String no = request.getParameter("no");

		BoardVo vo = new BoardDao().get(Long.valueOf(no));
		
		new BoardDao().hitupdate(vo);

		request.setAttribute("vo", vo);

		WebUtils.forward("/WEB-INF/views/board/view.jsp", request, response);
		
	}

}
