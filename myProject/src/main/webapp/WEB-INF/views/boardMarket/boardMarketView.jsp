<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<div class="page-main-style">
	<h1 class="title_view">${market.market_title}</h1>
	<h1></h1>
	<ul>
		<li class="writer_board">작성자 :${market.id}</li>
	</ul>
	<br>
	<div class="ordermenu">
		<ul>
			<li class="writer_board">제품명 :${market.market_itemname}</li>
			<li class="writer_board">가격 :${market.market_price}</li>
			<input type="button" value="구매요청" id="order_btn" class="order_btn">
		</ul>
	</div>
	<hr size="1" width="100%">
	<c:if test="${!empty market.market_filename}">
	<div class="align-center">
		<img src="imageView.do?market_num=${market.market_num}" style="max-width:500px;" class="img_center">
	</div>
	</c:if>
	<div class="view_content">
	<p class="align-center">
		${market.market_content}
	</p>
	</div>
	
	<hr size="1" width="100%">
	<div>
	<div class="align-right view_btn_modi_del">
		<%--수정 삭제의 경우는 로그인이 되어있고 로그인한 회원번호와 작성자 회원번호가
	               일치해야 함 --%>
	    <input type="button" value="<">
		<input type="button" value="목록" onclick="location.href='listMarket.do'">
		<input type="button" value=">" class="btn_list">
		<c:if test="${!empty user && user.mem_num == market.mem_num}">
		<input type="button" value="수정" onclick="location.href='updateMarket.do?market_num=${market.market_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script>
			var delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				var choice = window.confirm('삭제하시겠습니까?');
				if(choice){
					location.href='deleteMarket.do?market_num=${market.market_num}';
				}
			};
		</script>
		<br><br>
		<input type="button" value="찜하기">           
		</c:if>
	</div>
	</div>
</div>







