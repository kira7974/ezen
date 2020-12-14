<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#writeMarket').submit(function(){
		if($('#market_title').val() == '') {
			alert('제목 입력하세요.');
			$('#market_title').focus();
			return false;
		}
		if($('#market_itemname').val() == '') {
			alert('제품명을 입려하세요.');
			$('#market_itemname').focus();
			return false;
		}
		if($('#market_price').val() == '') {
			alert('제품가격을 입려하세요.');
			$('#market_price').focus();
			return false;
		}
		if($('#market_content').val() == '') {
			alert('내용 입력하세요.');
			$('#market_content').focus();
			return false;
		}
		/* if($('input:radiobutton[name=qa_secret]:checked')) {
			alert('비밀글 여부를 선택하세요.');
			return false;
		} */
	});
});
</script>
<div class="page-main-style">
	<form:form commandName="boardMarketVO" action="writeMarket.do" enctype="multipart/form-data" cssClass="board_form_write" id="writeMarket">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="market_title" cssClass="title" id="market_title"/>
				<form:errors path="market_title" cssClass="error-color"/>
			</li>
			<div class="market_pay">
				<ul>
					<li>
						<label for="itemname">제품명</label>
						<form:input path="market_itemname" id="market_itemname"/>
					</li>
					<li>
						<label for="price">가격</label>
						<form:input path="market_price" id="market_price"/>원
					</li>
				</ul>
			</div>
			<li>
				<label for="content">내용</label>
				<form:textarea path="market_content" cssClass="content" id="market_content"/>
				<form:errors path="market_content" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload">상품 이미지 파일업로드</label>
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='listMarket.do'">
		</div>
	</form:form>
</div>







