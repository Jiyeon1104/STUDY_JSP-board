<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
<!--
	Altitude by Pixelarity
	pixelarity.com | hello@pixelarity.com
	License: pixelarity.com/license
-->
<html>
	<head>
		<title>게시글 목록</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
		<style>
			table tbody tr{
				background-color:rgb(245 246 246 / 48%) !important;
			}
			.web-view {
				display:table-cell;
			}
			
			.mobile-view {
				display:none;
			}
			
			@media(max-width:660px){
				.web-view {
					display:none;
				}
				
				.mobile-view {
					display:table-cell;
				}
			}
			
		</style>
	</head>
	<body class="is-preload">
	
		<c:set var="boardList" value="${boardList}"/>
		<c:set var="page" value="${page}"/>
		<c:set var="startPage" value="${startPage}"/>
		<c:set var="endPage" value="${endPage}"/>
		<c:set var="realEndPage" value="${realEndPage}"/>
		<c:set var="total" value="${total}"/>
	
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
									<a href="#" class="image main"><img src="${pageContext.request.contextPath}/images/boardMain.png" alt="" /></a>
									<header class="major">
										<h1>게시판</h1>
										<p>자유 게시판</p>
									</header>

									<div class="table-wrapper">
										<div style="display:flex; justify-content:space-between;">
											<span>글 개수 : ${total}개</span>
											<button style="border-radius:0;" onclick="location.href = '${pageContext.request.contextPath}/board/BoardWrite.bo'">글 쓰기</button>
										</div>
										<table>
											<caption style="text-align:left; margin-bottom:3%;">
												
											</caption>
											<thead>
												<tr>
													<th>번호</th>
													<th>제목</th>
													<th>작성자</th>
													<th class="web-view">날짜</th>
													<th class="web-view">조회수</th>
												</tr>
											</thead>
											<tbody>
												<c:choose>
													<c:when test="${boardList != null and fn:length(boardList) > 0}">
														<c:forEach var="board" items="${boardList}">
															<tr>
																<td>${board.getBoardNumber()}</td>
																<td>
																	<a href="${pageContext.request.contextPath }/board/BoardDetailOk.bo?boardNumber=${board.getBoardNumber()}&page=${page}">${board.getBoardTitle()}</a>
																</td>
																<td>${board.getMemberId()}</td>
																<td class="web-view">${board.getBoardDate()}</td>
																<td class="web-view">${board.getReadCount()}</td>
															</tr>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<tr>
															<td colspan="5" align="center">등록된 게시물이 없습니다.</td>
														</tr>
													</c:otherwise>
												</c:choose>
											</tbody>
										</table>
										
										<!-- 페이징 처리 -->
										<table style="font-size:1.3rem">
											<tr align="center" valign="middle">
												<td class="mobile-view">
													<c:if test="${page > 1}">
														<a href="${pageContext.request.contextPath}/board/BoardListOk.bo?page=${page - 1}">&lt;</a>
													</c:if>
												
													<c:out value="${page}"/>&nbsp;&nbsp;
													
													<c:if test="${page < realEndPage}">
														<a href="${pageContext.request.contextPath}/board/BoardListOk.bo?page=${page + 1}">&gt;</a>
													</c:if>
												</td>
												<td class="web-view">
													<c:if test="${startPage > 1}">
														<a href="${pageContext.request.contextPath}/board/BoardListOk.bo?page=${startPage - 1}">&lt;</a>
													</c:if>
												
													<c:forEach var="i" begin="${startPage}" end="${endPage}">
														<c:choose>
															<c:when test="${i eq page}">
																<c:out value="${i}"/>&nbsp;&nbsp;
															</c:when>
															<c:otherwise>
																<a href="${pageContext.request.contextPath}/board/BoardListOk.bo?page=${i}"><c:out value="${i}"/></a>&nbsp;&nbsp;
															</c:otherwise>
														</c:choose>
													</c:forEach>
													
													<c:if test="${endPage < realEndPage}">
														<a href="${pageContext.request.contextPath}/board/BoardListOk.bo?page=${endPage + 1}">&gt;</a>
													</c:if>
												</td>
											</tr>
										</table>
									</div>
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

		<!-- Scripts -->
			<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/browser.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/breakpoints.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

	</body>
</html>