<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/swiper.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/menu_script.js"></script>

<input id="toggle_menu" type="checkbox">
<div id="wrap">
	<header id="main_header">
		<h1 class="logo">
			<a href="${pageContext.request.contextPath}/main/main.do"><img src="${pageContext.request.contextPath}/resources/images/logo.jpg"></a>
		</h1>
		<!-- 메뉴 햄버거 버튼 -->
		<label class="toggle_menu" for="toggle_menu"></label>
		<nav id="top_nav">
			<!-- <ul>
				<li><a href="#">로그인</a></li>
				<li><a href="#">회원가입</a></li>
			</ul> -->
			<c:if test="${!empty user}">
				<ul>
					<li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
					<li><a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a></li>
				</ul>	
			</c:if>
			<c:if test="${empty user}">
				<ul>
					<li><a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a></li>
					<li><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
				</ul>
			</c:if>
		</nav>
	</header>

	<nav id="main_nav_wrap">
		<!-- 상단메뉴: mobile용 -->
		<ul id="top_nav_m">
			<c:if test="${!empty user}">
				<li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a></li>
			</c:if>
			<c:if test="${empty user}">
				<li><a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a></li>
				<li><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
			</c:if>
		</ul>
		<!-- 메인메뉴 -->
		<ul id="main_nav">
			<li>
				<h3>제품 소개</h3> <!--mobile용-->
				<a href="#" class="myButton">제품 소개</a> <!--pc용-->
				<ul class="sub_nav">
					<li><a href="${pageContext.request.contextPath}/itemPhone/listPhone.do">SMART PHONE</a></li>
					<li><a href="#">TABLET</a></li>
					<li><a href="#">SMART WATCH</a></li>
				</ul>
			</li>
			<li>
				<h3>공지사항</h3> <a href="${pageContext.request.contextPath}/boardNotice/listNotice.do" class="myButton">공지사항</a>
			</li>
			<li>
				<h3>자유게시판</h3> <a href="${pageContext.request.contextPath}/board/list.do" class="myButton">자유게시판</a>
			</li>
			<li>
				<h3>질문게시판</h3> <a href="${pageContext.request.contextPath}/boardQA/listQA.do" class="myButton">질문게시판</a>
			</li>
			<li>
				<h3>중고거래 게시판</h3> <a href="${pageContext.request.contextPath}/boardMarket/listMarket.do" class="myButton">중고거래 게시판</a>
			</li>
		</ul>
	</nav>
</div>