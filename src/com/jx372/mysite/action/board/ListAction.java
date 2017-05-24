package com.jx372.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ListAction implements Action {

	
	private static final int LIST_SIZE = 5; //리스팅되는 게시물의 수 
	private static final int PAGE_SIZE = 5; //페이지 리스트의 페이지 수 

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		
		String num = request.getParameter("num");
		int sum=new BoardDao().getsize();
		
		
		if(num == null){
			
			num="3";
			
		}
		
	
		List<BoardVo> list = new BoardDao().getList(Integer.valueOf(num)); //ctrl +Space 
		
		request.setAttribute("list", list);
		request.setAttribute("sum", (sum/5)+1);
		//포워딩 (jsp 경로가 필요함) 
				
	
		 
		//2. dao 생성 
		BoardDao dao = new BoardDao(); 

	/*	
		int totalCount = dao.getTotalCount( keyword );  
		int pageCount = (int)Math.ceil( (double)totalCount / LIST_SIZE ); 
		int blockCount = (int)Math.ceil( (double)pageCount / PAGE_SIZE ); 
		int currentBlock = (int)Math.ceil( (double)currentPage / PAGE_SIZE ); 

		
		//5. view에서 페이지 리스트를 렌더링 하기위한 데이터 값 계산 
		int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1)*PAGE_SIZE + 1; 
		int prevPage = ( currentBlock > 1 ) ? ( currentBlock - 1 ) * PAGE_SIZE : 0; 
		int nextPage = ( currentBlock < blockCount ) ? currentBlock * PAGE_SIZE + 1 : 0; 
		int endPage = ( nextPage > 0 ) ? ( beginPage - 1 ) + LIST_SIZE : pageCount; 

		
		request.setAttribute( "totalCount", totalCount ); 
		request.setAttribute( "listSize", LIST_SIZE ); 
		request.setAttribute( "currentPage", currentPage ); 
		request.setAttribute( "beginPage", beginPage ); 
		request.setAttribute( "endPage", endPage ); 
		request.setAttribute( "prevPage", prevPage ); 
		request.setAttribute( "nextPage", nextPage ); 
		request.setAttribute( "keyword", keyword ); 
*/
		
		WebUtils.forward("/WEB-INF/views/board/list.jsp", request, response);

	}

}
