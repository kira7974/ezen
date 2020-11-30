<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//UI처리 -> 버튼
		$('#photo_btn').click(function() {
			//이미지 파일 선택 태그가 노출
			$('#photo_choice').show();
			//수정 버튼을 숨김
			$(this).hide();
		});
		
		//미리보기 처리
		var photo_path;	//원래 이미지를 보관
		var my_photo;	//변경 이미지를 보관
		$('#upload').change(function() {
			var upload = document.getElementById('upload');
			my_photo = upload.files[0];	//사진 하나만 업로드
			if (my_photo) {
				var reader = new FileReader();
				reader.readAsDataURL(my_photo);
				
				//사진 업로드전 미리보기 처리
				reader.onload = function() {
					//원래 이미지를 보관
					photo_path = $('.my-photo').attr('src');
					$('.my-photo').attr('src', reader.result); 	//변경된 이미지를 미리보기에 셋팅
				};
			}
		});
		
		//이미지 초기화
		//취소를 선택할 경우 전 사진으로 돌아감 -> 원래 이미지와 변경 이미지를 둘 다 보관하고 있어야 함
		$('#photo_reset').click(function() {
			$('.my-photo').attr('src', photo_path);	//원래 경로로 셋팅
			$('#upload').val('');
			$('#photo_choice').hide();
			$('#photo_btn').show();
		});
		
		$('#photo_submit').click(function() {
			if ($('#upload').val() == '') {
				alert('파일을 선택하세요.');
				$('#upload').focus();
				return;
			}
			//파일 전송
			//바이너리 데이터 전송
			var form_data = new FormData();
			form_data.append('upload', my_photo);
			$.ajax({
				url: 'updateMyPhoto.do',
				type: 'post',
				data: form_data,
				dataType: 'json',
				cache: false,
				contentType: false,
				enctype: 'multipart/form-data',
				processData: false,
				success: function(data) {
					if (data.result == 'logout') {
						alert('로그인 후 사용하세요.');	//세션 만료 시간이 지나면 로그아웃 됨
					}
					else if (data.result == 'success') {
						alert('프로필 사진이 수정되었습니다.');
						$('#upload').val('');
						$('#photo_choice').hide();
						$('#photo_btn').show();
					}
					else {
						alert('파일 전송 오류 발생');
					}
				},
				error: function() {
					alert('네트워크 오류 발생');
				}
			});	
		});
		
	});
</script>

<div class="page-main-style">
	<h2>프로필 사진</h2>
	<ul>
		<li>
			<c:if test="${ empty member.photoname }">
				<img src="${ pageContext.request.contextPath }/resources/images/blank.jpg" width="100" height="100" class="my-photo">
			</c:if>
			<c:if test="${ !empty member.photoname }">
				<img src="${ pageContext.request.contextPath }/member/photoView.do" width="100" height="100" class="my-photo">
			</c:if>
		</li>
		<li>
			<div class="align-center">
				<input type="button" value="수정" id="photo_btn">
			</div>
			<div id="photo_choice" style="display: none;">
				<input type="file" id="upload" accept="image/gif, image/png, imgae/jpeg">	<!-- ajax방식 -->
				<input type="button" value="전송" id="photo_submit">
				<input type="button" value="취소" id="photo_reset">
			</div>
		</li>
	</ul>
	<h2>회원상세정보</h2>
	<ul>
		<li>이름 : ${ member.name }</li>
		<li>전화번호 : ${ member.phone }</li>
		<li>이메일 : ${ member.email }</li>
		<li>우편번호 : ${ member.zipcode }</li>
		<li>주소 : ${ member.address1 }</li>
		<li>상세주소 : ${ member.address2 }</li>
		<li>가입날짜 : ${ member.reg_date }</li>
		<li>정보 수정일 : ${ member.modify_date }</li>
	</ul>
	<hr size="1" width="100%">
	<p class="align-right">
		<input type="button" value="수정" onclick="location.href='update.do'">
		<input type="button" value="비밀번호변경" onclick="location.href='changePassword.do'">
		<input type="button" value="회원탈퇴" onclick="location.href='delete.do'">
	</p>
</div>