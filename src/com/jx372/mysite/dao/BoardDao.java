package com.jx372.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.UserVo;
import com.jx372.mysite.vo.guestBookVo;




public class BoardDao {
	
	private Connection getConnection() throws SQLException {

		Connection conn = null;

		PreparedStatement pstmt = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			// 2. connection 하기

			String url = "jdbc:mysql://localhost:3306/webdb?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBC Driver을 찾을수 없습니다. ");

		} // 드라이버 로드
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error: " + e);
		}

		return conn;

		}
	
public boolean replyinsert(BoardVo vo){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			

			String sql = "insert into board value(null, ? , ? , 0, now(), ?, ? , ? , ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());

			pstmt.setString(2, vo.getContent());

			pstmt.setLong(3, vo.getGroup_no());

			pstmt.setLong(4, vo.getOrder_no()+1);

			pstmt.setLong(5, vo.getDep() + 1);

			pstmt.setLong(6, vo.getUser_no());

			int count = pstmt.executeUpdate();
			
			
			
			
			

			return count == 1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null) {

					pstmt.close();
				}
				if (conn != null) {
					conn.close();

				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return false;

	}
	
public boolean replyupdate(BoardVo vo) {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;


	try {
		conn = getConnection();

	
		String sql = "UPDATE board AS b INNER JOIN (select no,order_no from board  where order_no >= ? and group_no =? )  AS u ON (b.no = u.no) SET b.order_no = u.order_no+1";
		pstmt = conn.prepareStatement(sql);

		System.out.println(vo.getOrder_no());
		System.out.println(vo.getGroup_no());
		
		pstmt.setLong(1, vo.getOrder_no()+1);
		pstmt.setLong(2, vo.getGroup_no());
	

		int count = pstmt.executeUpdate();

		return count == 1;
			




	} catch (SQLException e) {

		e.printStackTrace();

	} finally {

		try {

			if (conn != null) {

				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	return false;
}
public boolean insert(BoardVo vo){
		
		Connection conn = null;
		PreparedStatement pstmt=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		
		try {
			conn= getConnection();
			stmt = conn.createStatement();
			
			
			String sql="insert into board value(null, ? , ? , 0, now(), ?, 0,0, ?)";
			
			String sql1="select ifnull(max(group_no),0) +1  from board";
		
		
			
			rs = stmt.executeQuery(sql1);
			
	
			pstmt= conn.prepareStatement(sql);
		
			
			Long group_no=null;
			
			while(rs.next()){
			
				group_no=rs.getLong(1);
			
			}
		
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, group_no);
			pstmt.setLong(4, vo.getUser_no());
			
			System.out.println(vo.getTitle()+" "+vo.getContent()+" "+group_no+" "+vo.getUser_no());
			
			int count = pstmt.executeUpdate();
			
			return count ==1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				
			if(pstmt != null){
				
				pstmt.close();
			}
			if(conn != null){
				conn.close();
				
			}
			}catch(SQLException e){
				
				e.printStackTrace();
			}
			
		}
		
		return false;
		
		
	}

public int getsize(){
	
	Connection conn = null;
	PreparedStatement pstmt=null;
	BoardVo vo = null;
	ResultSet rs = null;
	Statement stmt=null;
	
	int sum=0;
	
	try{
		
		conn=getConnection();
		
		stmt = conn.createStatement();
		
		String sql1 = "select count(*) from board"; 
		
		rs = stmt.executeQuery(sql1);
		
		while(rs.next()){
			
			sum=rs.getInt(1);
		}
		
				
			

	}catch(SQLException e){
		
		e.printStackTrace();
		
	}finally{
		
		try{
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				
				pstmt.close();
			}
			
			if(conn!=null){
				
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	return sum;
}

public List<BoardVo> getList(int num){
	
	Connection conn = null;
	Statement stmt=null;
	
	ResultSet rs=null;
	PreparedStatement pstmt=null;
	
	List<BoardVo> list=new ArrayList<BoardVo>();
	
	
	int sum=0;
	
	try{
		
		conn=getConnection();
		
		stmt = conn.createStatement();
		
		String sql1 = "select count(*) from board"; 
		
		rs = stmt.executeQuery(sql1);
		
		while(rs.next()){
			
			sum=rs.getInt(1);
		}
		
	
		
		String sql="select b.no, b.title, b.hit, b.reg_date, b.group_no, b.order_no, b.dep, u.name,b.user_no from board b, user u where b.user_no=u.no order by b.group_no desc, b.order_no, dep desc LIMIT ?, 5";
	
		conn.prepareStatement(sql);
		pstmt = conn.prepareStatement(sql);
		
		for(int i=0;i<(sum/5)+1; i++){
			
			
		}
		
		pstmt.setLong(1,(num-1)*5);
	
		
		rs = pstmt.executeQuery();
	
		
		while(rs.next()){
	
			Long no=rs.getLong(1);
			String title=rs.getString(2);
			Long hit = rs.getLong(3);
			String reg_date=rs.getString(4);
			Long group_no = rs.getLong(5);
			Long order_no = rs.getLong(6);
			Long dep = rs.getLong(7);
			String user_name = rs.getString(8);
			Long user_no=rs.getLong(9);
			
			
			BoardVo vo=new BoardVo();
			
			vo.setNo(no);
			vo.setTitle(title);
			vo.setHit(hit);
			vo.setReg_date(reg_date);
			vo.setGroup_no(group_no);
			vo.setOrder_no(order_no);
			vo.setDep(dep);
			vo.setUser_name(user_name);
			vo.setUser_no(user_no);
			
		
			
			list.add(vo);
			
		}
		
		
	}catch(SQLException e){
		e.printStackTrace();
		
	}finally{
		
		try{
			
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				
				stmt.close();
			}
			if(conn!=null){
				
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		
		
	}
	
	return list;
	
}

public BoardVo getNo (Long no){ //수정폼
	
	Connection conn = null;
	PreparedStatement pstmt=null;
	BoardVo vo = null;
	ResultSet rs = null;
	
	try{
		
		conn = getConnection();
		
		String sql="select no, title, content, group_no, order_no, dep, user_no from board where no= ? ";
		
		
		conn.prepareStatement(sql);
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, no);
	
		
		rs = pstmt.executeQuery();
		
		if(rs.next()){ //2줄이상이면 
			
			
			String title = rs.getString(2);
			String content = rs.getString(3);
			Long group_no = rs.getLong(4);
			Long order_no = rs.getLong(5);
			Long dep = rs.getLong(6);
			Long user_no=rs.getLong(7);
			
			
			vo=new BoardVo();
			
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setGroup_no(group_no);
			vo.setOrder_no(order_no);
			vo.setDep(dep);
			vo.setUser_no(user_no);
			
		}
		
		
	}catch(SQLException e){
		
		e.printStackTrace();
		
	}finally{
		
		try{
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				
				pstmt.close();
			}
			
			if(conn!=null){
				
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	return vo;
}
	
public BoardVo get (Long no){ //수정폼
	
	Connection conn = null;
	PreparedStatement pstmt=null;
	BoardVo vo = null;
	ResultSet rs = null;
	
	try{
		
		conn = getConnection();
		
		String sql="select no, title, content, user_no from board where no= ? ";
		
		
		conn.prepareStatement(sql);
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, no);
	
		
		rs = pstmt.executeQuery();
		
		if(rs.next()){ //2줄이상이면 
			
			
			String title = rs.getString(2);
			String content = rs.getString(3);
			Long user_no=rs.getLong(4);
			
			
			vo=new BoardVo();
			
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setUser_no(user_no);
			
		}
		
		
	}catch(SQLException e){
		
		e.printStackTrace();
		
	}finally{
		
		try{
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				
				pstmt.close();
			}
			
			if(conn!=null){
				
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	return vo;
}

public boolean hitupdate(BoardVo vo) {

	Connection conn = null;
	PreparedStatement pstmt = null;

	try {

		conn = getConnection();

		String sql = "update board set hit=hit+1 where no=?";
		
		pstmt = conn.prepareStatement(sql);

	
		pstmt.setLong(1, vo.getNo());

		int count = pstmt.executeUpdate();

		return count == 1;

	} catch (SQLException e) {

		e.printStackTrace();

	} finally {

		try {

			if (conn != null) {

				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	return false;
}

	public boolean update(BoardVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = getConnection();

			String sql = "update board set title=?, content =? , reg_date=now() where no=?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());

			int count = pstmt.executeUpdate();

			return count == 1;

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {

				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();

			}

		}

		return false;
	}
public boolean delete(BoardVo vo){
		
		Connection conn = null;
		PreparedStatement pstmt=null;
		
		
		
		
		try {
			conn= getConnection();
			
			String sql="delete from board where no=? ";
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
		

			
			int count = pstmt.executeUpdate();
			
			return count ==1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				
			if(pstmt != null){
				
				pstmt.close();
			}
			if(conn != null){
				conn.close();
				
			}
			}catch(SQLException e){
				
				e.printStackTrace();
			}
			
		}
		
		return false;
		
		
	}

public int getTotalCount( String keyword ) { 
		int totalCount = 0; 

 
 		Connection conn = null; 
 		PreparedStatement pstmt = null; 
 		ResultSet rs = null; 
 		 
 		try { 
 			conn = getConnection(); 
 			if( "".equals( keyword ) ) { 
 				String sql = "select count(*) from board"; 
 				pstmt = conn.prepareStatement(sql); 
 			} else {  
 				String sql = 
 					"select count(*)" + 
 					"  from board" + 
 					" where title like ? or content like ?"; 
 				pstmt = conn.prepareStatement(sql); 
 				 
				pstmt.setString(1, "%" + keyword + "%"); 
 				pstmt.setString(2, "%" + keyword + "%"); 
 			} 
			rs = pstmt.executeQuery(); 
		if( rs.next() ) { 
 				totalCount = rs.getInt( 1 ); 
 			} 
 		} catch (SQLException e) { 
 			System.out.println( "error:" + e ); 
 		} finally { 
			try { 
 				if( rs != null ) { 
 					rs.close(); 
 				} 
 				if( pstmt != null ) { 
 					pstmt.close(); 
 				} 
 				if( conn != null ) { 
 					conn.close(); 
 				} 
 			} catch ( SQLException e ) { 
				System.out.println( "error:" + e ); 
			}   
 		} 
 		 
 		return totalCount; 
 	} 



}
