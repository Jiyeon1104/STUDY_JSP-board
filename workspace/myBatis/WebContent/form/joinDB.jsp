<%@page import="java.util.Base64"%>
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean class="com.member.beans.MemberVO" id="member"/>
<jsp:useBean class="com.member.beans.MemberDAO" id="dao"/>
<jsp:setProperty property="*" name="member"/>
<%
	//DAO의 메소드에는 SQL에 관련된 문장만 작성해야 하며,
	//이외의 연산은 서블릿 부분에서 작성한다.
	member.setMemberPw(Base64.getEncoder().encodeToString(member.getMemberPw().getBytes()));
	dao.join(member);
	response.sendRedirect("login.jsp");
%>