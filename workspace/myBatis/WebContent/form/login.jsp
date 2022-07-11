<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
</head>
<body>
	<%
		String cookieCheck = request.getHeader("Cookie");
		String memberId = "", memberPw = "", saveId = "", autoLogin = "";
		if(cookieCheck != null){
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies){
				//쿠키에 이전 로그인 아이디가 있는 지 검사
				if(cookie.getName().equals("memberId")){
					//이전 로그인 아이디를 memberId에 저장
					memberId = cookie.getValue();
				}
				//쿠키에 이전 로그인 비밀번호가 있는 지 검사
				if(cookie.getName().equals("memberPw")){
					//이전 로그인 비밀번호를 복호화 한 뒤 memberPw에 저장
					memberPw = new String(Base64.getDecoder().decode(cookie.getValue()));
				}
				//쿠키에 아이디 저장이 체크되어 있는 지 검사
				if(cookie.getName().equals("saveId")){
					//아이디 저장 상태를 saveId에 저장
					saveId = cookie.getValue();
				}
				//쿠키에 자동 로그인이 체크되어 있는 지 검사
				if(cookie.getName().equals("autoLogin")){
					//자동 로그인 상태를 autoLogin에 저장
					autoLogin = cookie.getValue();
				}
			}
		}
	%>
	<!-- JS에서 사용하고자 하는 변수들 -->
	<input type="hidden" name="checkPw" value="<%=memberPw%>">
	<input type="hidden" name="checkSaveId" value="<%=saveId %>">
	<input type="hidden" name="checkAutoLogin" value="<%=autoLogin %>">
	
    <form action="loginOk.jsp" method="post" name="loginForm">
        <table border="1" style="text-align: center; margin:0 auto;">
            <tr>
                <th>아이디</th>
                <!-- 쿠키가 있으면 아이디를 value에 넣기 -->
                <td><input type="text" name="memberId" value="<%=memberId%>"></td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td><input type="password" name="memberPw" value="<%=memberPw%>"></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align:center;">
                    <label><input type="checkbox" name="saveId" value="true"> 아이디 저장</label>
                    <label><input type="checkbox" name="autoLogin" value="true"> 자동 로그인</label>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="button" value="로그인" onclick="send()">
                </td>
            </tr>
        </table>
    </form>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>
<script>

	// 쿠키에 아이디 저장이 체크되어 있으면
	if(document.querySelector("input[name='checkSaveId']").value){
		// 현재 페이지의 아이디 저장 체크박스를 체크상태로 변경
		loginForm.saveId.checked = true;
	}
	// 쿠키에 자동 로그인이 체크되어 있으면
	if(document.querySelector("input[name='checkAutoLogin']").value){
		// 현재 페이지의 자동 로그인 체크박스를 체크상태로 변경
		loginForm.autoLogin.checked = true;
	}
	// 이전 로그인 비밀번호가 쿠키에 저장되어 있다면 자동 로그인을 체크한 사용자이다.
	if(document.querySelector("input[name='checkPw']").value){
		//아이디와 비밀번호 모두 쿠키를 사용해서 입력해놨기 때문에 바로 send()를 사용하여 전송한다.
		send();
	}
	
    function send(){
        var form = loginForm;
        if(!form.memberId.value){
            alert("아이디를 입력해주세요.");
            form.memberId.focus();
            return;
        }
        if(!form.memberPw.value){
            alert("패스워드를 입력해주세요.");
            form.memberPw.focus();
            return;
        }

        form.memberPw.value = btoa(form.memberPw.value);

        form.submit();
    }
</script>
</html>