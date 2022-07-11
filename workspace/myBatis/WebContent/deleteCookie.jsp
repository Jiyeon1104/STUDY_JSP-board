<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 삭제 확인</title>
</head>
<body>
	<%
		String cookieCheck = request.getHeader("Cookie");
		if(cookieCheck == null){
	%>
		<p>쿠키가 삭제 되었습니다.</p>
	<%
		}else{
	%>
		<p>쿠키가 삭제되지 않았습니다. 브라우저에서 쿠키를 지운 후 다시 시도해보세요!</p>
	<%
		}
	%>
</body>
</html>