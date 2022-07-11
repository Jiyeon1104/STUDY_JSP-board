<%
	// 로그아웃 시 비밀번호를 쿠키에서 삭제해준다.
	// 만약 삭제하지 않으면 비밀번호가 계속 작성되기 때문에
	// 로그아웃 자체가 불가능해진다(로그인 화면에서 비밀번호가 쿠키에 있는 지로 판단하기 때문)
	String cookieCheck = request.getHeader("Cookie");
	if(cookieCheck != null){
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("memberPw")){
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}
	
	session.invalidate();
	//session.removeAttribute("memberNum"); 
	response.sendRedirect("login.jsp");
%>