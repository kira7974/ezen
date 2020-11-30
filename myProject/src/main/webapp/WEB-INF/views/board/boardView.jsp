<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<div class="page-main-style">
	<h1 class="title_view">${board.title}</h1>
	<h1></h1>
	<ul>
		<li class="writer_board">작성자 :${board.id}</li>
	</ul>
	<hr size="1" width="100%">
	<c:if test="${!empty board.filename}">
	<div class="align-center">
		<img src="imageView.do?board_num=${board.board_num}" style="max-width:500px;" class="img_center">
	</div>
	</c:if>
	<div class="view_content">
	<p class="align-center">
		${board.content}
	</p>
	</div>
	
	<!-- 댓글 -->
	<div class="page-main-style">
		<form:form cssClass="coment_width">
			<ul>
				<li class="coment_position">아이디</li>
				<li><textarea rows="5" cols="100"></textarea></li>
			</ul>
			<input type="submit" value="등록" class="coment_sub_pos">
		</form:form>
	</div>
	<!-- 댓글 -->
	
	<hr size="1" width="100%">
	<div>
	<%-- <div class="align-center btn_list">
		<input type="button" value="<">
		<input type="button" value="목록" onclick="location.href='list.do'">
		<input type="button" value=">">
	</div> --%>
	<div class="align-right view_btn_modi_del">
		<%--수정 삭제의 경우는 로그인이 되어있고 로그인한 회원번호와 작성자 회원번호가
	               일치해야 함 --%>
	    <input type="button" value="<">
		<input type="button" value="목록" onclick="location.href='list.do'">
		<input type="button" value=">" class="btn_list">
		<c:if test="${!empty user && user.mem_num == board.mem_num}">
		<input type="button" value="수정" onclick="location.href='update.do?board_num=${board.board_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script>
			var delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				var choice = window.confirm('삭제하시겠습니까?');
				if(choice){
					location.href='delete.do?board_num=${board.board_num}';
				}
			};
		</script>              
		</c:if>
	</div>
	</div>
</div>







