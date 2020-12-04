<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>

<div class="mypage_sub">
	<nav>
		<ul id="main_nav">
			<li>
				<a href="${pageContext.request.contextPath}/member/myBoard.do">자유게시판 작성내역</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/member/myBoardQA.do">질문내역</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/member/myLike.do">내가 찜한 글</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/member/myOrdersBuy.do">중고거래 내역</a>
			</li>
		</ul>
	</nav>
</div>

<div class="page-main-style">
	<h2 class="align-center">중고거래 내역</h2>
	<div class="align-center">
		<input type="button" value="구매내역" onclick="location.href='memberOrdersBuy.do'">
		<input type="button" value="판매내역" onclick="location.href='memberOrdersSell.do'">
	</div>
		
</div>