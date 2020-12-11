<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//비밀번호 변경 체크
		$('#passwd').keyup(function() {
			if ($('#confirm_passwd').val() != '' && $('#confirm_passwd').val() != $(this).val()) {
				$('#message_id').text('비밀번호 불일치').css('color', 'red');
			}
			else if ($('#confirm_passwd').val() != '' && $('#confirm_passwd').val() == $(this).val()) {
				$('#message_id').text('비밀번호 일치').css('color', '#000');
			}
		});
		
		$('#confirm_passwd').keyup(function() {
			if ($('#passwd').val() != '' && $('#passwd').val() != $(this).val()) {
				$('#message_id').text('비밀번호 불일치').css('color', 'red');
			}
			else if ($('#passwd').val() != '' && $('#passwd').val() == $(this).val()) {
				$('#message_id').text('비밀번호 일치').css('color', '#000');
			}
		});
		
		$('#change_form').submit(function() {
			if ($('#now_passwd').val() == '') {
				alert('현재 비밀번호를 입력하세요.');
				$('#now_passwd').focus();
				return false;
			}
			if ($('#passwd').val() == '') {
				alert('변경 비밀번호를 입력하세요.');
				$('#passwd').focus();
				return false;
			}
			if ($('#confirm_passwd').val() == '') {
				alert('변경 비밀번호 확인을 입력하세요.');
				$('#confirm_passwd').focus();
				return false;
			}
			if ($('#passwd').val() != $('#confirm_passwd').val()) {
				alert('변경 비밀번호와 변경 비밀번호 확인 불일치');
				return false;
			}
		});

	});
</script>

<div class="mypage_sub">
	<nav>
		<ul id="main_nav">
			<li>
				<a href="${pageContext.request.contextPath}/member/myBoard.do" class="mypage_button">자유게시판 작성내역</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/member/myBoardQA.do" class="mypage_button">질문내역</a>
			</li>
<%-- 			<li>
				<a href="${pageContext.request.contextPath}/member/myBoardLike.do" class="mypage_button">내가 찜한 글</a>
			</li> --%>
			<li>
				<a href="${pageContext.request.contextPath}/member/myBoardOrder.do" class="mypage_button">중고거래 내역</a>
			</li>
		</ul>
	</nav>
</div>

<div class="page-main-style">
	<br>
	<h2 class="align-center" style="font-size:2.0em;"> [ 비밀번호 변경 ] </h2><br><br>
	<form:form action="changePassword.do" commandName="memberVO" id="change_form">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<br>
			<li>
				<label for="now_passwd">현재 비밀번호</label>
				<form:password path="now_passwd"/>
				<form:errors path="now_passwd" cssClass="error-color"/>
			</li>
			<br>
			<li>
				<label for="passwd">변경 비밀번호</label>
				<form:password path="passwd"/>
				<form:errors path="passwd" cssClass="error-color"/>
			</li>
			<br>
			<li>
				<label for="confirm_passwd">변경 비밀번호 확인</label>
				<input type="password" id="confirm_passwd">
				<span id="message_id" class="error-color"></span>
			</li>
			<br>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="취소" onclick="location.href='${pageContext.request.contextPath}/member/myPage.do'">
		</div>
	</form:form>
</div>