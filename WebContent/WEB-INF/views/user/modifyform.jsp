<%@page import="com.jx372.mysite.dao.UserDao"%>
<%@page import="com.jx372.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
 
	UserVo authUser = (UserVo)session.getAttribute("authUser");
	
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		
		<div id="content">
			<div id="user">

				<form id="join-form" name="modifyForm" method="post" action="/mysite/user?a=modify">
					<label class="block-label" for="name">이름</label>
					<input type="hidden" name="no" value="<%=authUser.getNo()  %>">
					<input id="name" name="name" type="text" value="<%=authUser.getName()  %>">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="<%=authUser.getEmail()  %>">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					
					<fieldset>
						<legend>성별</legend>
						<%
						if(authUser.getGender().equals("female")){
						
						
						%>
						<label>남</label> <input type="radio" name="gender" value="male" >
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						
						<%
						}
						else if(authUser.getGender().equals("male")){
						%>
						
						<label>남</label> <input type="radio" name="gender" value="male" checked ="checked">
						<label>여</label> <input type="radio" name="gender" value="female">
						<%
						}
						%>
					</fieldset>
					
				
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		
		
		<jsp:include page="/WEB-INF/views/include/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/include/foot.jsp"/>
		
	</div>
</body>
</html>