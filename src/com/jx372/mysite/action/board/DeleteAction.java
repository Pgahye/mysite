package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.dao.guestBookDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.UserVo;
import com.jx372.mysite.vo.guestBookVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		

		
		HttpSession session = request.getSession();
		
		if(session == null){
			
			WebUtils.redirect("/mysite/board", request, response);
			return;
			
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null){
			
			WebUtils.redirect("/mysite/board", request, response);
			return;
			
		}
		
		Long no =  WebUtils.checkParameter(request.getParameter("no"), 0L);
		Long user_no =  WebUtils.checkParameter(request.getParameter("userno"), 0L);
		
		
		BoardVo vo=new BoardVo();
		vo.setNo(Long.valueOf(no));
		vo.setUser_no(user_no);

	
		
		if(user_no == authUser.getNo()){
			
			new BoardDao().delete(vo);
			
			//리다이렉트 응답
			WebUtils.redirect(request.getContextPath()+"/board", request, response);
			
		}else{
			
			WebUtils.redirect("/mysite/board", request, response);
			
		}
		
		

	}

}
