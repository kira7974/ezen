<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		listComment();
		
		$('#reply_form').submit(function(event){
			if($('#content').val()==''){
				alert('내용을 입력하세요');
				$('#free_com_content').focus();
				return false;
			}
			
			$.ajax({
				url:'commentFreeWrite.do',
				type:'post',
				data:{board_num:$('#board_num').val(),free_com_content:$('#free_com_content').val()},
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result=='success'){
						alert('댓글 등록 완료');
						$('#free_com_content').val('');//내용 초기화
					}else if(data.result == 'logout'){
						alert('로그인 후 작성하세요');
						location.reload();
					}else{
						alert('댓글 등록시 오류 발생');
					}
				},
				error:function(){
					alert('네트워크 오류 발생');
				}
			});
			event.preventDefault();
		});
	});
	
	function listComment(){
		$.ajax({
			url:'commentFreeList.do?board_num=${board.board_num}',
			type:'get',
			data:{board_num:$('#board_num').val()},
			dataType:'json',
			success:function(result){
				console.log(result);
				var output = "<table>";
				for(var i in result) {
					output += "<tr>";
					output += "<td>"+result[i].id;
					output += "<br>";
					output += result[i].free_com_content+"</td>";
					output += "</tr>"
				}
				output += "</table>";
				$("#commentList").html(output);
			}
		});
	}
</script>  
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
	
	<!-- 댓글 작성 -->
	<c:if test="${!empty user}">
	<div class="page-main-style">
		<form method="post" class="coment_width" id="reply_form">
		    <input type="hidden" id="board_num" value="${board.board_num}">
		    <ul>
		    	<li class="coment_position">
				${user.id}
				</li>
				<li>
					<textarea rows="5" cols="100" id="free_com_content"></textarea>
				</li>
				<input type="submit" value="등록" class="coment_sub_pos">
		    </ul>
		</form>
	</div>
	</c:if>
	<!-- 댓글 작성 -->
	
	<!-- 댓글 출력 -->
	<div id="commentList"></div>
	<!-- 댓글 출력 -->
	
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







