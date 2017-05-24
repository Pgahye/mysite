<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%

	pageContext.setAttribute("newLine", "\n");
%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		
		
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.servletContext.contextPath }/guestbook?a=add" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="pwd"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>	
							
				<br>
	
				<c:set var="count" value="${fn:length(list) }"/>
				<c:forEach items="${list }" var="vo" varStatus="status">
					<ul>
					<li>
						<table>
							<tr>
								<td>[${count - status.index }]</td>
								<td>${vo.name }</td>
								<td>${vo.date }</td>
								<td><a href="${pageContext.servletContext.contextPath }/guestbook?no=${vo.no }&a=deleteform">삭제</a></td>
							</tr>
							<tr>
								<td colspan="4">${fn:replace(vo.message, newLine, "<br>") }</td>
							</tr>
						</table>
						<br>
					</li>
				</ul>
					
				</c:forEach>
	

			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="guestbook"/>
		</c:import>	
		<c:import url="/WEB-INF/views/include/foot.jsp"/>
	</div>
</body>
</html>