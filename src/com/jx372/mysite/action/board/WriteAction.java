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

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
	
		String title = WebUtils.checkParameter(request.getParameter("title"), "");
		String content =  WebUtils.checkParameter(request.getParameter("content"), "");
		
		String no = request.getParameter("no");
		String order_no = request.getParameter("order_no");
		String dep = request.getParameter("dep");
		String group_no=request.getParameter("group_no");
		

		HttpSession session = request.getSession(true); //세션주세요 없으면 만드세요 
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		
		if(no == ""){
			
			BoardVo vo=new BoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setUser_no(authUser.getNo());
			
			new BoardDao().insert(vo);
		
			
		}else{
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setGroup_no(Long.valueOf(group_no));
			vo.setOrder_no(Long.valueOf(order_no));
			vo.setDep(Long.valueOf(dep));
			vo.setUser_no(authUser.getNo());

			new BoardDao().replyinsert(vo);
			
			new BoardDao().replyupdate(vo);
			
			
			
		}
		

		
		//리다이렉트 응답
		WebUtils.redirect(request.getContextPath()+"/board", request, response);
		//response.sendRedirect("/guestbook2/gb");  //밑의 html은 필요가 없어짐 

	}

}
