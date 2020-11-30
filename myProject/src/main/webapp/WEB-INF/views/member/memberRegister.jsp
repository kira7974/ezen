<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var checkId = 0;	//1이면 중복체크를 한 걸로 간주
		
		//아이디 중복 체크
		$('#confirmId').click(function() {
			if ($('#id').val() == '') {
				$('#message_id').css('color', 'red').text('아이디를 입력하세요');
				$('#id').focus();
				return;
			}
			
			//정규표현식 시작은 ^, 끝은 $
			var regMsg = /^[A-Za-z0-9+]{4,10}$/;				//객체 생성 없이
			//var regMsg = new RegExp('^[A-Za-z0-9+]{4,10}$');	//객체 생성
			if (!regMsg.test($('#id').val())) {
				$('#message_id').css('color', 'red').text('영문, 숫자 4자 이상 10자 이하 입력');
				$('#id').focus();
				return;
			}
			
			$('#message_id').text();	//메시지 초기화
			$('#loading').show();		//로딩 이미지 노출
			
			$.ajax({
				//ajax통신
				url: 'confirmId.do',
				type: 'post',
				data: {id: $('#id').val()},	//id 데이터를 읽어옴
				dataType: 'json',
				cache: false,
				timeout: 30000,
				success: function(data) {
					$('#loading').hide();	//로딩 이미지 감추기
					//서버에서 유효성 체크 결과 오류 메시지 숨기기
					//$('id.errors').hide();
					
					if (data.result == 'idNotFound') {
						$('#message_id').css('color', '#000').text('사용 가능 아이디');
						checkId = 1;
					}
					else if (data.result == 'idDuplicated') {
						$('#message_id').css('color', 'red').text('사용 불가능 아이디');
						$('#id').val('').focus();
						checkId = 0;
					}
					else {
						checkId = 0;
						alert('아이디 중복체크 오류');
					}
				},
				error: function() {
					checkId = 0;
					$('#loading').hide();	//로딩 이미지 안보이게 감추기
					alert('네트워크 오류 발생');
				}
			});	
		});
		
		//아이디 중복 안내 메시지 초기화 및 아이디 중복값 초기화
		//키 이벤트 처리
		$('#register_form #id').keydown(function() {
			checkId = 0;
			$('#message_id').text('');
		});
		
		//submit이벤트 발생 시 아이디 중복 체크 여부 확인
		$('#register_form').submit(function() {
			//submit이벤트 발생 시 false를 리턴
			if (checkId == 0) {
				$('#message_id').css('color', 'red').text('아이디 중복 체크 필수');
				$('#id').focus();
				
				return false;
			}
		});
		
	});
</script>

<div class="page-main-style">
	<form:form action="registerUser.do" commandName="memberVO" id="register_form">
		<form:errors element="div" cssClass="error-color"/>
			<ul>
				<li>
					<label for="id">아이디</label>
					<form:input path="id"/>
					<input type="button" id="confirmId" value="ID중복체크">
					<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" width="16" height="16" style="display:none;" id="loading">
					<span id="message_id"></span>
					<form:errors path="id" cssClass="error-color"/>        
				</li>
				<li>
					<label for="name">이름</label>
					<form:input path="name"/>
					<form:errors path="name" cssClass="error-color"/>
				</li>
				<li>
					<label for="passwd">비밀번호</label>
					<form:password path="passwd"/>
					<form:errors path="passwd" cssClass="error-color"/>
				</li>
				<li>
					<label for="phone">전화번호</label>
					<form:input path="phone"/>
					<form:errors path="phone" cssClass="error-color"/>
				</li>
				<li>
					<label for="email">이메일</label>
					<form:input path="email"/>
					<form:errors path="email" cssClass="error-color"/>
				</li>
				<li>
					<label for="zipcode">우편번호</label>
					<form:input path="zipcode"/>
					<form:errors path="zipcode" cssClass="error-color"/>
				</li>
				<li>
					<label for="address1">주소</label>
					<form:input path="address1"/>
					<form:errors path="address1" cssClass="error-color"/>
				</li>
			</ul>
		
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>