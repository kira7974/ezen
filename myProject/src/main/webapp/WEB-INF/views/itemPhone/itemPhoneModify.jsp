<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main-style">
	<<form:form commandName="itemPhoneVO" action="updatePhone.do" enctype="multipart/form-data" cssClass="board_form_write">
		<form:hidden path="phone_num"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="name">스마트폰 이름</label>
				<form:input path="phone_name" cssClass="title"/>
				<form:errors path="phone_name" cssClass="error-color"/>
			</li>
			<div>
				<ul class="phone_input">
					<li>
						<label for="date">스마트폰 출시일</label>
						<form:input path="phone_date"/>
						<form:errors path="phone_date" cssClass="error-color"/>
					</li>
					<li>
						<label for="apu">스마트폰 프로세서</label>
						<form:input path="phone_apu"/>
						<form:errors path="phone_apu" cssClass="error-color"/>
					</li>
				</ul>
				<ul class="phone_input">
					<li>
						<label for="ram">스마트폰 메모리</label>
						<form:input path="phone_ram"/>
						<form:errors path="phone_ram" cssClass="error-color"/>
					</li>
					<li>
						<label for="memory">스마트폰 저장용량</label>
						<form:input path="phone_memory"/>
						<form:errors path="phone_memory" cssClass="error-color"/>
					</li>
				</ul>
				<ul class="phone_input">
					<li>
						<label for="display">스마트폰 화면크기</label>
						<form:input path="phone_display"/>
						<form:errors path="phone_display" cssClass="error-color"/>
					</li>
					<li>
						<label for="company">스마트폰 제조사</label>
						<form:input path="phone_company"/>
						<form:errors path="phone_company" cssClass="error-color"/>
					</li>
				</ul>
				<ul class="phone_input">
					<li>
						<label for="os">스마트폰 운영체제</label>
						<form:input path="phone_os"/>
						<form:errors path="phone_os" cssClass="error-color"/>
					</li>
					<li>
						<label for="type">스마트폰 충전방식</label>
						<form:input path="phone_type"/>
						<form:errors path="phone_type" cssClass="error-color"/>
					</li>
				</ul>
				<ul class="phone_input">
					<label for="upload2">리스트 이미지 업로드</label>
					<input type="file" name="upload2" id="upload2" accept="image/gif,image/png,image/jpeg">
					<c:if test="${!empty itemPhoneVO.phone_titleimgname}">
					<br>
					<span>(${itemPhoneVO.phone_titleimgname})파일이 등록되어 있습니다.
					다시 업로드하면 기존 파일은 삭제됩니다.</span>
					</c:if>
				</ul>
			</div>
			<li>
				<label for="content">내용</label>
				<form:textarea path="phone_content" cssClass="content"/>
				<form:errors path="phone_content" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload">이미지 파일업로드</label>
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
				<c:if test="${!empty itemPhoneVO.phone_contentimgname}">
				<br>
				<span>(${itemPhoneVO.phone_contentimgname})파일이 등록되어 있습니다.
				다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='listPhone.do'">
		</div>
	</form:form>
</div>







