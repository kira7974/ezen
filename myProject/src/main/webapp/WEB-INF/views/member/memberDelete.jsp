<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
	$(document).ready(function() {
		$('#login_form').submit(function() {
			if ($('#id').val() == '') {
				alert('아이디를 입력하세요.');
				$('#id').focus();
				return false;
			}
			if ($('#passwd').val() == '') {
				alert('비밀번호를 입력하세요.');
				$('#passwd').focus();
				return false;
			}
		});

	});
</script>

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
	<h2 class="align-center">회원탈퇴</h2>
	<form:form action="delete.do" commandName="memberVO">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="id">아이디</label>
				<form:input path="id"/>
				<%-- <form:errors path="id" cssClass="error-color"/>   --%>      
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd"/>
				<%-- <form:errors path="passwd" cssClass="error-color"/> --%>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="회원탈퇴">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>