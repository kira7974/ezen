<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<div class="page-main-style">
	<h1 class="title_view">${notice.notice_title}</h1>
	<h1></h1>
	<ul>
		<li class="writer_board">작성자 :${notice.id}</li>
	</ul>
	<hr size="1" width="100%">
	<c:if test="${!empty notice.notice_filename}">
	<div class="align-center">
		<img src="imageView.do?notice_num=${notice.notice_num}" style="max-width:500px;" class="img_center">
	</div>
	</c:if>
	<div class="view_content">
	<p class="align-center">
		${notice.notice_content}
	</p>
	</div>
	
	<hr size="1" width="100%">
	<div>
	<div class="align-right view_btn_modi_del">
		<%--수정 삭제의 경우는 로그인이 되어있고 로그인한 회원번호와 작성자 회원번호가
	               일치해야 함 --%>
		<input type="button" value="목록" onclick="location.href='listNotice.do'" class="btn btn-success">
		<c:if test="${!empty user && user.mem_num == notice.mem_num}">
		<input type="button" value="수정" onclick="location.href='updateNotice.do?notice_num=${notice.notice_num}'" class="btn btn-info">
		<input type="button" value="삭제" id="delete_btn" class="btn btn-danger">
		<script>
			var delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				var choice = window.confirm('삭제하시겠습니까?');
				if(choice){
					location.href='deleteNotice.do?notice_num=${notice.notice_num}';
				}
			};
		</script>              
		</c:if>
	</div>
	</div>
</div>