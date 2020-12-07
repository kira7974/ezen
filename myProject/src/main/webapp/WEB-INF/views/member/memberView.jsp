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
				<a href="${pageContext.request.contextPath}/member/myBoardLike.do">내가 찜한 글</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/member/myBoardOrder.do">중고거래 내역</a>
			</li>
		</ul>
	</nav>
</div>

<div class="page-main-style" id="show_myinfo">
	<h2 class="align-center" style="font-size:2.0em;"> [ 회원정보 ] </h2><br><br>
	<ul>
		<li>이름 : ${ member.name }</li><br>
		<li>전화번호 : ${ member.phone }</li><br>
		<li>이메일 : ${ member.email }</li><br>
		<li>우편번호 : ${ member.zipcode }</li><br>
		<li>주소 : ${ member.address }</li><br>
		<li>가입날짜 : ${ member.reg_date }</li>
	</ul>
	<br><br>
	<p class="align-center">
		<input type="button" value="회원정보수정" onclick="location.href='update.do'">
		<input type="button" value="비밀번호변경" onclick="location.href='changePassword.do'">
		<input type="button" value="회원탈퇴" onclick="location.href='delete.do'">
	</p>
</div>