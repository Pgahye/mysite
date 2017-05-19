<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
			<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="<%=request.getContextPath()%>/guestbook?a=delete">
					<input type='hidden' name="no" value="<%=request.getParameter("no") %>">
					<label>비밀번호</label>
					<input type="password" name="pwd">
					<input type="submit" value="확인">
				</form>
				<a href="<%=request.getContextPath() %>/guestbook">방명록 리스트</a>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/include/foot.jsp"/>
	</div>
</body>
</html>
	