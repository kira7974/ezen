<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Swiper -->
<div class="swiper-container">
	<div class="swiper-wrapper">
		<div class="swiper-slide">Slide 1</div>
		<div class="swiper-slide">Slide 2</div>
		<div class="swiper-slide">Slide 3</div>
		<div class="swiper-slide">Slide 4</div>
	</div>
</div>
<!-- Swiper JS -->
<script src="${pageContext.request.contextPath}/resources/js/swiper.js"></script>
<!-- Initialize Swiper -->
<script>
			/*autoplay 추가*/
				var swiper = new Swiper('.swiper-container', {
					spaceBetween: 0,
					centeredSlides: true,
					autoplay: {
						delay: 2500,
						disableOnInteraction: false,
					},
				});
			</script>

<div id="container">
	<section id="con1">
		<h2 class="con_title">제품 소개 최근 글</h2>
		<article>
			<div>
				<h3>SMART PHONE</h3>
				<p>article text 1-1</p>
			</div>
		</article>
		<article>
			<div>
				<h3>TABLET</h3>
				<p>article text 1-2</p>
			</div>
		</article>
		<article>
			<div>
				<h3>SMART WATCH</h3>
				<p>article text 1-3</p>
			</div>
		</article>
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
				<h3>자유게시판</h3>
				<p>article text 2-2</p>
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