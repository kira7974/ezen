<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var count_ck;
		
		$('#compare_btn').click(function(event){
			if($("input:checkbox[name=phoneNumber]:checked").length <= 1){
				   alert("비교할 항목을 2개 이상 선택하세요");
				   return false;;
			}else if($("input:checkbox[name=phoneNumber]:checked").length > 5) {
				alert('비교 갯수 초과 5개 이하만 가능');
				$("input:checkbox[name=phoneNumber]").prop('checked',false);
				return false;;
			}else {
				count_ck = $("input:checkbox[name=phoneNumber]:checked").length;
			}
		});
	});
	
</script> 
<div class="page-main-style align-center">
	<div class="align-right">
		<c:if test="${!empty user && user.id.equals('admin')}">
		<input type="button" value="글쓰기" onclick="location.href='writePhone.do'" class="write_btn btn btn-primary">
		</c:if>
	</div>
	
	<c:if test="${count == 0}">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<form action="comparePhone.do" method="post" id="compare" name="compare" class="item_MainList">
	<input type="submit" value="비교" style="display: block; margin-left: 40px;" id="compare_btn" name="compare_btn" class="btn btn-default">
	<c:forEach var="phone" items="${list}">
		<div class="itemList">
			<c:if test="${!empty phone.phone_titleimgname}">
			<div class="listTitle">
				<a href="detailPhone.do?phone_num=${phone.phone_num}"><img src="imageListView.do?phone_num=${phone.phone_num}" style="max-width:150px; height: 150px;" class="img_center"></a>
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
			<div class="listWriter">${phone.id} (${phone.reg_date}) <input type="checkbox" name="phoneNumber" class="item_Check" value="${phone.phone_num}"></div>
		</div>
	</c:forEach>
	</form>
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
		<li><input type="submit" value="찾기" class="btn btn-link"> <input type="button" value="목록" onclick="location.href='listPhone.do'" class="btn btn-link"></li>
	</ul>
</form>

