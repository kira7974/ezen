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
	<h2 class="align-center">내가 찜한 글</h2>
	<c:if test="${count == 0}">
	<div class="align-center">등록한 찜 목록이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<th>번호</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>최근수정일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="board" items="${list}">
		<tr>
			<td>${board.board_num}</td>
			<td><a href="detail.do?board_num=${board.board_num}">${board.title}</a></td>
			<td>${board.id}</td>
			<td>${board.modify_date}</td>
			<td>${board.hit}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>
	</c:if>
</div>