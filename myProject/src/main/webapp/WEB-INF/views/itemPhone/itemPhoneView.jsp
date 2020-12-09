<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<div class="page-main-style">
	<h1 class="title_view">${phone.phone_name}</h1>
	<h1></h1>
	<ul>
		<li class="writer_board">작성자 :${phone.id}</li>
	</ul>
	<hr size="1" width="100%">
	<div class="align-center">
		<table class="item_text">
			<tr>
				<th colspan="2">핸드폰명</th>
				<td colspan="2">${phone.phone_name}</td>
			</tr>
			<tr>
				<th>제조사</th>
				<td>${phone.phone_company}</td>
				<th>출시일</th>
				<td>${phone.phone_date}</td>
			</tr>
			<tr>
				<th>프로세서</th>
				<td>${phone.phone_apu}</td>
				<th>OS</th>
				<td>${phone.phone_os}</td>
			</tr>
			<tr>
				<th>RAM</th>
				<td>${phone.phone_ram}</td>
				<th>내장용량</th>
				<td>${phone.phone_memory}</td>
			</tr>
			<tr>
				<th>화면크기</th>
				<td>${phone.phone_display}</td>
				<th>충전단자</th>
				<td>${phone.phone_type}</td>
			</tr>
		</table>
	</div>
	<c:if test="${!empty phone.phone_contentimgname}">
	<div class="align-center">
		<img src="imageView.do?phone_num=${phone.phone_num}" style="max-width:500px;" class="img_center">
	</div>
	</c:if>
	<div class="view_content">
	<p class="align-center">
		${phone.phone_content}
	</p>
	</div>
	
	<hr size="1" width="100%">
	<div>
	<div class="align-right view_btn_modi_del">
		<%--수정 삭제의 경우는 로그인이 되어있고 로그인한 회원번호와 작성자 회원번호가
	               일치해야 함 --%>
	    <input type="button" value="<">
		<input type="button" value="목록" onclick="location.href='listPhone.do'">
		<input type="button" value=">" class="btn_list">
		<c:if test="${!empty user && user.mem_num == phone.mem_num}">
		<input type="button" value="수정" onclick="location.href='updatePhone.do?phone_num=${phone.phone_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script>
			var delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				var choice = window.confirm('삭제하시겠습니까?');
				if(choice){
					location.href='deletePhone.do?phone_num=${phone.phone_num}';
				}
			};
		</script>              
		</c:if>
	</div>
	</div>
</div>







