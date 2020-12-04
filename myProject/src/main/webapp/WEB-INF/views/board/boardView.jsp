<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var currentPage;
		var count;
		var rowCount;
		//댓글 목록
		function selectData(pageNum,board_num){
			currentPage = pageNum;
			
			if(pageNum == 1){
				//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
				$('#output').empty();
			}
			//로딩 이미지 노출
			$('#loading').show();
			
			$.ajax({
				type:'post',
				data:{pageNum:pageNum,board_num:board_num},
				url:'commentFreeList.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					//로딩 이미지 감추기
					$('#loading').hide();
					count = data.count;
					rowCount = data.rowCount;
					var list = data.list;
					
					if(count < 0 || list == null){
						alert('목록 호출 오류 발생!');
					}else{
						//댓글 목록 작업
						$(list).each(function(index,item){
							var output = '<div class="item">';
							output += '  <h4 class="coment_id">작성자 ' + item.id + '</h4>';
							output += '  <div class="sub-item">';
							output += '    <p>' + item.free_com_content.replace(/</gi,'&lt;').replace(/>/gi,'&gt;') + '</p>';
							output += item.free_com_date;
							
							if($('#mem_num').val()==item.mem_num){
								//로그인 한 회원 번호가 댓글 작성자 번호와 같으면
								output += '  <input type="button" data-num="'+item.free_com_id+'" data-mem="'+item.mem_num+'" value="수정" class="modify-btn">';
								output += '  <input type="button" data-num="'+item.free_com_id+'" data-mem="'+item.mem_num+'" value="삭제" class="delete-btn">';
							}
							output += '      <hr size="1" noshade>';
							output += '  </div>';
							output += '</div>';
													
							//문서 객체에 추가
							$('#output').append(output);
						});
						
						//paging button 처리
						if(currentPage>=Math.ceil(count/rowCount)){
							//다음 페이지가 없음
							$('.paging-button').hide();
						}else{
							//다음 페이지가 존재
							$('.paging-button').show();
						}
					}
				},
				error:function(){
					//로딩 이미지 감추기
					$('#loading').hide();
					alert('네트워크 오류');
				}
			});
		}
		
		//다음 댓글 보기 버튼 클릭시 데이터 추가
		$('.paging-button input').click(function(){
			var pageNum = currentPage + 1;
			selectData(pageNum,$('#board_num').val());
		});
		//댓글 등록
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
						//폼초기화
						initForm();
						//댓글 작성이 성공하면 새로 삽입한 글을
						//포함해서 첫번째 페이지의 게시글들을 다시
						//호출함
						selectData(1,$('#board_num').val());
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
		//댓글 작성 폼 초기화
		function initForm(){
			$('textarea').val('');
		}
				
		//댓글 수정 버튼 클릭시 수정폼 노출
		$(document).on('click','.modify-btn',function(){
			
			//댓글 글번호
			var re_num = $(this).attr('data-num');
			//작성자 아이디
			var mem_num = $(this).attr('data-mem');
			//댓글 내용
			var content = $(this).parent().find('p').html().replace(/<br>/gi,'\n');
			                                             //g:지정문자열 모두, i:대소문자 무시
			//댓글 수정폼 UI
			var modifyUI = '<form id="mre_form">'
				modifyUI += '   <input type="hidden" name="free_com_id" id="mre_num" value="'+re_num+'">';
				modifyUI += '   <input type="hidden" name="mem_num" id="mmem_num" value="'+mem_num+'">';
				modifyUI += '   <textarea rows="3" cols="50" name="free_com_content" id="mre_content" class="rep-content">'+content+'</textarea>';
				modifyUI += '   <div id="mre_first"><span class="letter-count">300/300</span></div>';      
				modifyUI += '   <div id="mre_second" class="align-right">';
				modifyUI += '      <input type="submit" value="수정">';
				modifyUI += '      <input type="button" value="취소" class="re-reset">';
				modifyUI += '   </div>';
				modifyUI += '   <hr size="1" noshade width="96%">';
				modifyUI += '</form>';
		
			//이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면
			//숨김 sub-item를 환원시키고 수정폼을 초기화함
			initModifyForm();
			//지금 클릭해서 수정하고자 하는 데이터는 감추기
			//수정버튼을 감싸고 있는 div
			$(this).parent().hide();
				
			//수정폼을 수정하고자하는 데이터가 있는 div에 노출
			$(this).parents('.item').append(modifyUI);

		});
		
		//수정폼에서 취소 버튼 클릭시 수정폼 초기화
		$(document).on('click','.re-reset',function(){
			initModifyForm();
		});
		
		//댓글 수정 폼 초기화
		function initModifyForm(){
			$('.sub-item').show();
			$('#mre_form').remove();
		}
		
		//댓글 수정
		$(document).on('submit','#mre_form',function(event){
			if($('#mre_content').val()==''){
				alert('내용을 입력하세요!');
				$('#mre_content').focus();
				return false;
			}
			
			//폼에 입력한 데이터 반환
			var data = $(this).serialize();
			
			//수정
			$.ajax({
				url:'updateReply.do',
				type:'post',
				data:data,
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result=='logout'){
						alert('로그인해야 수정할 수 있습니다.');
					}else if(data.result=='success'){
						//$('#mre_form').parent().find('p').html($('#mre_content').replace(/\n/g,'<br>'));
						$('#mre_form').parent().find('p').html($('#mre_content').val().replace(/</g,'&lt;').replace(/>/g,'&gt;'));
						//수정폼 초기화
						initModifyForm();
					}else if(data.result=='wrongAccess'){
						alert('타인의 글은 수정할 수 없습니다.');
					}else{
						alert('수정 오류 발생');
					}
				},
				error:function(){
					alert('댓글 수정시 네크워크 오류!');
				}
			});
			//기본 이벤트 제거
			event.preventDefault();
		});
		
		//댓글 삭제
		$(document).on('click','.delete-btn',function(){
			//댓글 번호
			var re_num = $(this).attr('data-num');
			//작성자 아이디
			var mem_num = $(this).attr('data-mem');
			
			$.ajax({
				type:'post',
				url:'deleteReply.do',
				data:{free_com_id:re_num,mem_num:mem_num},
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result == 'logout'){
						alert('로그인해야 삭제할 수 있습니다.');
					}else if(data.result == 'success'){
						alert('삭제 완료!');
						selectData(1,$('#board_num').val());
					}else if(data.result == 'wrongAccess'){
						alert('타인의 글을 삭제할 수 없습니다.');
					}else{
						alert('댓글 삭제시 오류 발생');
					}
				},
				error:function(){
					alert('네트워크 오류 발생!');
				}
			});
		});
		
		//초기 데이터(목록) 호출
		selectData(1,$('#board_num').val());
	});
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
	<hr size="1" width="100%">
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
	<hr size="1" width="100%">
	
	<!-- 댓글 작성 -->
	
	<div class="page-main-style">
		<form method="post" class="coment_width" id="reply_form">
		    <input type="hidden" id="board_num" value="${board.board_num}">
		    <input type="hidden" id="mem_num" value="${user.mem_num}">
		    <c:if test="${!empty user}">
		    <ul>
		    	<li class="coment_position">
				${user.id}
				</li>
				<li>
					<textarea rows="5" cols="100" id="free_com_content"></textarea>
				</li>
		    </ul>
		    <input type="submit" value="등록" class="coment_sub_pos">
		    </c:if>
		</form>
	</div>
	
	<!-- 댓글 작성 -->
	
	<!-- 댓글 목록 출력 -->
	<div id="output" class="comment_list"></div>
	<div class="paging-button comtent_list_btn" style="display:none;">
		<input type="button" value="다음글 보기">
	</div>
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
	</div>
</div>







