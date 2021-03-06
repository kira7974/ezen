<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
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

<div class="page-main-style">
	<h2 class="align-center" style="font-size:2.0em;"> [ 로그인 ] </h2><br><br>
	<br>
	<form:form action="login.do" commandName="memberVO" id="login_form">
		<form:errors element="div" cssClass="error-color"/>
		<br>
		<ul>
			<li>
				<label for="id">아이디</label>
				<form:input path="id"/>
				<%-- form:errors path="id" cssClass="error-color"/>    --%>
			</li>
			<br>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd"/>
				<%-- <form:errors path="passwd" cssClass="error-color"/> --%>
			</li>
		</ul>
		<br>
		<div class="align-center">
			<input type="submit" value="로그인">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>