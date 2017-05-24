package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		HttpSession session = request.getSession(); //세션주세요 없으면 만드세요 
	
		String no = request.getParameter("no");
		
		if(session == null){
			
			WebUtils.redirect("/mysite/boarda=view&no="+no, request, response);
			return;
			
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null){
			
			WebUtils.redirect("/mysite/board?a=view&no="+no, request, response);
			return;
			
		}
		


		BoardVo vo = new BoardDao().get(Long.valueOf(no));

		request.setAttribute("vo", vo);
		
		//System.out.println(vo.getUser_no());
		//System.out.println(authUser.getNo());
		
		if(vo.getUser_no() == authUser.getNo()){
			
			WebUtils.forward("/WEB-INF/views/board/modify.jsp", request, response); //새창띄우기 
			
		}else{
			
			WebUtils.redirect("/mysite/board?a=view&no="+no, request, response);
		}
		
		
		
	}

}
