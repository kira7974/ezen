<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>    
<div class="page-main-style">
	<div class="align-right">
		<c:if test="${!empty user}">
		<input type="button" value="글쓰기" onclick="location.href='writeQA.do'" class="write_btn btn btn-primary">
		</c:if>
	</div>
	
	<c:if test="${count == 0}">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="type11">
		<tr>
			<th>번호</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>등록일</th>
		</tr>
		<c:forEach var="qa" items="${list}">
		<tr>
			<td>${qa.qa_num}</td>
			<c:choose>
			<c:when test="${qa.qa_secret.equals('no')}">
			<td><a href="detailQA.do?qa_num=${qa.qa_num}">${qa.qa_title}</a></td>
			</c:when>
			<c:when test="${qa.qa_secret.equals('yes')}">
				<c:if test="${user.id.equals('admin') || user.id.equals(qa.id)}">
					<td><a href="detailQA.do?qa_num=${qa.qa_num}">${qa.qa_title}</a></td>
				</c:if>
				<c:if test="${!user.id.equals('admin') && !user.id.equals(qa.id)}">
					<td>${qa.qa_title}</td>
				</c:if>
			</c:when>
			</c:choose>
			<td>${qa.id}</td>
			<td>${qa.qa_date}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>
	</c:if>
</div>
<form action="listQA.do" id="search_form" method="get" class="serch_center">
	<ul class="search">
		<li><select name="keyfield" id="keyfield">
				<option value="1">제목</option>
				<option value="2">ID</option>
				<option value="3">내용</option>
				<option value="4">전체</option>
		</select></li>
		<li><input type="text" name="keyword" id="keyword"></li>
		<li><input type="submit" value="찾기" class="btn btn-link"> <input type="button" value="목록" onclick="location.href='listQA.do'" class="btn btn-link"></li>
	</ul>
</form>









