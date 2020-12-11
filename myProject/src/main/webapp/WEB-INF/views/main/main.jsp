<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.swiper-container {
	width: 100%; /*이미지 너비*/
	height: 100%;
	margin-left: auto;
	margin-right: auto;
}

.swiper-slide {
	text-align: center;
	font-size: 18px;
	background: #fff;
	/* Center slide text vertically */
	display: -webkit-box;
	display: -ms-flexbox;
	display: -webkit-flex;
	display: flex;
	-webkit-box-pack: center;
	-ms-flex-pack: center;
	-webkit-justify-content: center;
	justify-content: center;
	-webkit-box-align: center;
	-ms-flex-align: center;
	-webkit-align-items: center;
	align-items: center;
}
.swiper-slide>img {
	width: 100%;
	height: 700px;
}
</style>

<!-- Swiper JS -->
<script src="${pageContext.request.contextPath}/resources/js/swiper.js"></script>
<!-- Initialize Swiper -->

<!-- Swiper -->
<div class="swiper-container">
	<div class="swiper-wrapper">
		<div class="swiper-slide">
			<img
				src="${pageContext.request.contextPath}/resources/images/swiper_img1.jpg"
				alt="Slide1">
		</div>
		<div class="swiper-slide">
			<img
				src="${pageContext.request.contextPath}/resources/images/swiper_img2.jpg"
				alt="Slide2">
		</div>
		<div class="swiper-slide">
			<img
				src="${pageContext.request.contextPath}/resources/images/swiper_img3.jpg"
				alt="Slide3">
		</div>
		<div class="swiper-slide">
			<img
				src="${pageContext.request.contextPath}/resources/images/swiper_img4.jpg"
				alt="Slide4">
		</div>
	</div>
</div>

<script>
	var swiper = new Swiper('.swiper-container', {
		slidesPerView : 1,
		spaceBetween : 0,
		loop : true,
		pagination : {
			el : '.swiper-pagination',
			clickable : true,
		},
		navigation : {
			nextEl : '.swiper-button-next',
			prevEl : '.swiper-button-prev',
		},
		autoplay : { /*자동으로 넘어가게*/
			delay : 2500,
			disableOnInteraction : false,
		},
	});
</script>

<div id="container">
	<section id="con1">
		<h2 class="con_title">스마트폰 정보 최근 글</h2>
		
		<c:if test="${count == 0}">
		<article class="art_back">
		<div class="align-center">등록된 게시물이 없습니다.</div>
		</article>
		</c:if>
		
		<c:if test="${count > 0}">
		<c:forEach var="phone" items="${list}">
		<article class="art_back">
		<div class="itemList2">
			<c:if test="${!empty phone.phone_titleimgname}">
			<div class="listTitle">
				<a href="${pageContext.request.contextPath}/itemPhone/detailPhone.do?phone_num=${phone.phone_num}"><img src="imageListView.do?phone_num=${phone.phone_num}" style="max-width:150px; max-height: 150px;" class="img_center"></a>
				<br>
				${phone.phone_name}
			</div>
			</c:if>
			<c:if test="${empty phone.phone_titleimgname}">
			<div class="listTitle">
				<a href="${pageContext.request.contextPath}/itemPhone/detailPhone.do?phone_num=${phone.phone_num}"><img src="${pageContext.request.contextPath}/resources/images/noimage.gif" style="max-width:150px; height: 150px;" class="img_center"></a>
				<br>
				${phone.phone_name}
			</div>
			</c:if>
			<div class="listWriter">${phone.id} (${phone.reg_date})</div>
		</div>
		</article>
		</c:forEach>
		</c:if>
	</section>

	<section id="con2">
		<h2 class="con_title">최근 게시물</h2>
		<article>
			<div>
				<h3>공지사항</h3>
				<p>article text 2-1</p>
			</div>
		</article>
		<article>
			<div>
				<c:if test="${countBoard == 0}">
				<h3>등록된 게시물이 없습니다.</h3>
				</c:if>
				<c:if test="${countBoard > 0}">
				<h3 class="align-center">자유게시판</h3>
				<table>
					<tr>
						<th>제목</th>
						<th>작성자</th>
					</tr>
					<c:forEach var="board" items="${listBoard}">
					<tr>
						<td><a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}">${board.title}</a></td>
						<td>${board.id}</td>
					</tr>
					</c:forEach>
				</table>
				</c:if>
			</div>
		</article>
		<article>
			<div>
				<h3>질문게시판</h3>
				<p>article text 2-3</p>
			</div>
		</article>
		<article>
			<div>
				<h3>중고거래 게시판</h3>
				<p>article text 2-4</p>
			</div>
		</article>
	</section>
</div>