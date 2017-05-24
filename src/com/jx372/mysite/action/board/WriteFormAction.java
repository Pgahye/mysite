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

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		String no = request.getParameter("no");
		
		if (no == null) { // 신규등록과 답글의 구분

			if (session == null) {

				WebUtils.redirect("/mysite/board", request, response);
				return;

			}

			if (authUser == null) {

				WebUtils.redirect("/mysite/board", request, response);
				return;

			}

		} else {

			if (session == null) {

				WebUtils.redirect("/mysite/boarda=view&no=" + no, request, response);
				return;

			}

			if (authUser == null) {

				WebUtils.redirect("/mysite/board?a=view&no=" + no, request, response);
				return;

			}
			
			BoardVo vo = new BoardDao().getNo(Long.valueOf(no));
			

			request.setAttribute("vo", vo);
		}
		
		
		
		
		WebUtils.forward("/WEB-INF/views/board/write.jsp", request, response);
	}

}
