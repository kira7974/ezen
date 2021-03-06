<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>

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
	<h2 class="align-center" style="font-size:2.0em;"> [ 판매 중고거래 글 ] </h2><br><br>
	<c:if test="${count == 0}">
	<div class="align-center">작성한 판매 글이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<th>번호</th>
			<th width="400">제목</th>
			<th>작성일</th>
			<th>거래 상태</th>
		</tr>
		<c:forEach var="orders" items="${list}">
		<tr>
			<td>${orders.market_num}</td>
			<td><a href="${pageContext.request.contextPath}/boardMarket/detailMarket.do?market_num=${orders.market_num}">${orders.market_title}</a></td>
			<td>${orders.market_date}</td>
			<td>
			<c:if test="${orders.valid == 0 || orders.valid == null}">
				거래 요청 없음
			</c:if>
			<c:if test="${orders.valid == 1}">
				거래 진행 중
				<input type="button" value="배송" id="shipping_btn">
				<script>
					var shipping_btn = document.getElementById('shipping_btn');
					//이벤트 연결
					shipping_btn.onclick=function(){
						var choice = window.confirm('배송하시겠습니까?');
						if(choice) {
							location.href='shipping.do?orders_id=${orders.orders_id}'
						}
					}
				</script>
			</c:if>
			<c:if test="${orders.valid == 2}">
				배송중
			</c:if>
			<c:if test="${orders.valid == 3}">
				거래 완료
			</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>
	</c:if>
	<br><br>
	<div class="align-center">
		<input type="button" value="판매" onclick="location.href='myBoardOrder.do'">
		<input type="button" value="구매" onclick="location.href='myBoardOrderBuy.do'">
	</div>
</div>