<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean class="com.member.beans.MemberVO" id="member"/>
<jsp:useBean class="com.member.beans.MemberDAO" id="dao"/>
<jsp:setProperty property="*" name="member"/>
<%
	int memberNum = dao.login(member.getMemberId(), member.getMemberPw());
	if(memberNum == 0){
%>
	<script>
		alert("로그인 실패");
		location.href = "login.jsp";
	</script>
<%
	}else{ //로그인 성공 시
		
		// 세션에 회원 번호 저장 후 로그인 상태 유지
		session.setAttribute("memberNum", memberNum);
	
		if(request.getParameter("saveId") != null){ //아이디 저장이 체크되었다면
			//쿠키에 로그인된 사용자의 아이디 저장
			Cookie cookieId = new Cookie("memberId", member.getMemberId());
			//아이디 저장 체크 저장
			Cookie cookieCheckbox = new Cookie("saveId", "true");
			
			//1년의 기간동안 쿠키 유지
			cookieId.setMaxAge(60*60*24*365);
			cookieCheckbox.setMaxAge(60*60*24*365);
			
			//응답 데이터 저장
			response.addCookie(cookieId);
			response.addCookie(cookieCheckbox);
			
		}else{ // 아이디 저장이 체크되지 않았다면
			String cookieCheck = request.getHeader("Cookie");
			if(cookieCheck != null){
				Cookie[] cookies = request.getCookies();
				for(Cookie cookie : cookies){
					// 기존에 있던 쿠키 전부 삭제
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		
		if(request.getParameter("autoLogin") != null){ //자동 로그인이 체크되었다면
			
			//로그인 된 사용자의 아이디 쿠키에 저장
			Cookie cookieId = new Cookie("memberId", member.getMemberId());
			//로그인 된 사용자의 암호화된 비밀번호 쿠키에 저장(여기서 복호화 하면 해킹 바로 당함).
			Cookie cookiePw = new Cookie("memberPw", member.getMemberPw());
			//자동 로그인 체크 저장
			Cookie cookieCheckbox = new Cookie("autoLogin", "true");
			
			//1년의 기간동안 쿠키 유지
			cookieId.setMaxAge(60*60*24*365);
			cookiePw.setMaxAge(60*60*24*365);
			cookieCheckbox.setMaxAge(60*60*24*365);
			
			//응답 데이터 저장
			response.addCookie(cookieId);
			response.addCookie(cookiePw);
			response.addCookie(cookieCheckbox);
			
		}else{ // 자동 로그인이 체크되지 않았다면
			String cookieCheck = request.getHeader("Cookie");
			if(cookieCheck != null){
				Cookie[] cookies = request.getCookies();
				for(Cookie cookie : cookies){
					// 쿠키 전체 삭제
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		
		response.sendRedirect("index.jsp");
	}
%>



