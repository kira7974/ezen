<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div class="page-main-style align-center">
	<div class="align-right">
		<c:if test="${!empty user && user.id.equals('admin')}">
		<input type="button" value="글쓰기" onclick="location.href='writePhone.do'" class="write_btn">
		</c:if>
	</div>
	<div class="align-right">
		<input type="button" value="비교" class="write_btn">
	</div>
	
	<c:if test="${count == 0}">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<c:forEach var="phone" items="${list}">
		<div class="itemList">
			<c:if test="${!empty phone.phone_titleimgname}">
			<div class="listTitle">
				<a href="detailPhone.do?phone_num=${phone.phone_num}"><img src="imageListView.do?phone_num=${phone.phone_num}" style="max-width:150px; max-height: 150px;" class="img_center"></a>
				<br>
				${phone.phone_name}
			</div>
			</c:if>
			<c:if test="${empty phone.phone_titleimgname}">
			<div class="listTitle">
				<a href="detailPhone.do?phone_num=${phone.phone_num}"><img src="${pageContext.request.contextPath}/resources/images/noimage.gif" style="max-width:150px; height: 150px;" class="img_center"></a>
				<br>
				${phone.phone_name}
			</div>
			</c:if>
			<div class="listWriter">${phone.id} (${phone.reg_date}) <input type="checkbox" id="check" name="check" class="item_Check"></div>
		</div>
	</c:forEach>
	<div class="align-center">${pagingHtml}</div>
	</c:if>
</div>
<form action="listPhone.do" id="search_form" method="get" class="serch_center">
	<ul class="search">
		<li><select name="keyfield" id="keyfield">
				<option value="1">제목</option>
				<option value="2">ID</option>
				<option value="3">내용</option>
				<option value="4">전체</option>
		</select></li>
		<li><input type="text" name="keyword" id="keyword"></li>
		<li><input type="submit" value="찾기"> <input type="button" value="목록" onclick="location.href='listPhone.do'"></li>
	</ul>
</form>

