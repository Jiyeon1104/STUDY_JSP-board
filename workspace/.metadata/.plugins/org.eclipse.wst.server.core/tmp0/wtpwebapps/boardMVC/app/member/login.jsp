<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<!--
	Altitude by Pixelarity
	pixelarity.com | hello@pixelarity.com
	License: pixelarity.com/license
-->
<html>
	<head>
		<title>로그인</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
		<style>
			input[type='button'], input[type='submit']{border-radius:0;}
			textarea{resize:none;}
			label{margin-top:3%;}
			div.preview{overflow:hidden;}
			div.preview img{width:90%; object-fit:cover; margin-left:10%;}
		</style>
	</head>
	<body class="is-preload">
		<c:if test="${not empty param.code}">
			<script>
				alert("아이디 또는 비밀번호를 다시 확인해주세요.");
			</script>
		</c:if>
		<!-- Page Wrapper -->
			<div id="page-wrapper">

				<!-- Wrapper -->
					<div class="wrapper">
						<div class="inner">
							<jsp:include page="/app/fix/header.jsp"/>
						</div>
					</div>

				<!-- Wrapper -->
					<div class="wrapper">
						<div class="inner">

							<!-- Main -->
							<section class="main">

								<header class="major">
									<a href="#" class="image main"><img src="${pageContext.request.contextPath}/images/login01.png" alt="" /></a>
									<h1>로그인</h1>
								</header>
								<hr />
								<form method="post" action="${pageContext.request.contextPath }/member/MemberLoginOk.me" name="loginForm">
									<div style="display:flex; justify-content:space-evenly;">
										<div style="width:40%">
												<div>
													<div class="col-6 col-12-xsmall" style="width:100%">
														<p style="margin:0;">아이디 </p>
														<input type="text" name="memberId" id="memberId"/>
														<br>
														<p style="margin:0;">비밀번호</p>
														<input type="password" name="memberPw" id="memberPw"/>
														<div style="text-align:center; margin-top:9%">
															<a href="#">아이디 찾기</a><span>&nbsp;|&nbsp;</span><a href="#">비밀번호 찾기</a>
														</div>
													</div>
												</div>
												<div class="col-12">
													<ul class="actions" style="display:block; text-align:center; margin-top:9%">
														<li><input type="button" value="로그인" class="primary" style="width:100%; font-size:1em;" onclick="send()"/></li>
														<li><input type="button" value="회원가입" style="width:100%; font-size:1em;" onclick="location.href='${pageContext.request.contextPath}/member/MemberJoin.me'" /></li>
													</ul>
												</div>
											</div>
											<div class="preview" style="width:40%">
												<img src="${pageContext.request.contextPath}/images/login02.png">
											</div>
										</div>
									</form>
							</section>
						</div>
					</div>
				<!-- Wrapper -->
					<div class="wrapper">
						<div class="inner">

							<!-- Footer -->
								<footer id="footer">
									<section class="links">
										<div>
											<h3>Magna</h3>
											<ul class="plain">
												<li><a href="#">Aliquam tempus</a></li>
												<li><a href="#">Ultrecies nul</a></li>
												<li><a href="#">Gravida ultricies</a></li>
												<li><a href="#">Commodo etiam</a></li>
												</ul>
										</div>
										<div>
											<h3>Feugiat</h3>
											<ul class="plain">
												<li><a href="#">Morbi sem lorem</a></li>
												<li><a href="#">Praes sed dapi</a></li>
												<li><a href="#">Sed adipis nullam</a></li>
												<li><a href="#">Fus dolor lacinia</a></li>
											</ul>
										</div>
										<div>
											<h3>Tempus</h3>
											<ul class="plain">
												<li><a href="#">Donecnec etiam</a></li>
												<li><a href="#">Aapibus sedun</a></li>
												<li><a href="#">Namnulla tempus</a></li>
												<li><a href="#">Morbi set amet</a></li>
											</ul>
										</div>
										<div>
											<h3>Aliquam</h3>
											<ul class="plain">
												<li><a href="#">Lorem prasent dia</a></li>
												<li><a href="#">Nellentes ipsum</a></li>
												<li><a href="#">Diamsed arcu dolor</a></li>
												<li><a href="#">Sit amet cursus</a></li>
											</ul>
										</div>
									</section>
									<ul class="contact-icons">
										<li class="icon solid fa-home">1234 Fictional Street #5432 Nashville, TN 00000-0000</li>
										<li class="icon solid fa-phone"><a href="#">(000) 000-0000</a></li>
										<li class="icon solid fa-envelope"><a href="#">info@untitled.tld</a></li>
									</ul>
									<p class="copyright">&copy; Untitled. All rights reserved.</p>
								</footer>
						</div>
					</div>
			</div>
	</body>
	<!-- Scripts -->
	<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/browser.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/breakpoints.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/login.js"></script>
</html>