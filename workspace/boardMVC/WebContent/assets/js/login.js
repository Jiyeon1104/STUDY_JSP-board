// 로그인
function send(){
	if(!loginForm.memberId.value){
		alert("아이디를 확인해주세요.");
		return;
	}
	if(!loginForm.memberPw.value){
		alert("패스워드를 확인해주세요.");
		return;
	}
	
	loginForm.submit();
}