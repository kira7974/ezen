<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main-style">
	<<form:form commandName="boardMarketVO" action="updateMarket.do" enctype="multipart/form-data" cssClass="board_form_write">
		<form:hidden path="market_num"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="market_title" cssClass="title"/>
				<form:errors path="market_title" cssClass="error-color"/>
			</li>
			<div class="market_pay">
				<ul>
					<li>
						<label for="itemname">제품명</label>
						<form:input path="market_itemname"/>
					</li>
					<li>
						<label for="price">가격</label>
						<form:input path="market_price"/>
					</li>
				</ul>
			</div>
			<li>
				<label for="content">내용</label>
				<form:textarea path="market_content" cssClass="content"/>
				<form:errors path="market_content" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload">이미지 파일업로드</label>
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
				<c:if test="${!empty boardMarketVO.market_filename}">
				<br>
				<span>(${boardMarketVO.market_filename})파일이 등록되어 있습니다.
				다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='listMarket.do'">
		</div>
	</form:form>
</div>







