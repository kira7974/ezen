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

<div class="page-main-style" id="show_myinfo">
	<h2 class="align-center">회원정보</h2>
	<br>
	<ul>
		<li>이름 : ${ member.name }</li>
		<li>전화번호 : ${ member.phone }</li>
		<li>이메일 : ${ member.email }</li>
		<li>우편번호 : ${ member.zipcode }</li>
		<li>주소 : ${ member.address }</li>
		<li>가입날짜 : ${ member.reg_date }</li>
	</ul>
	<br>
	<p class="align-center">
		<input type="button" value="회원정보수정" onclick="location.href='update.do'">
		<input type="button" value="비밀번호변경" onclick="location.href='changePassword.do'">
		<input type="button" value="회원탈퇴" onclick="location.href='delete.do'">
	</p>
</div>