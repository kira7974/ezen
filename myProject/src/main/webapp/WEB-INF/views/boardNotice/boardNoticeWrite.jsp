<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<form:form cssClass="board_form_write">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="title">제목</label>
				<input type="text" class="title">
			</li>
			<li>
				<label for="content">내용</label>
				<textarea rows="5" cols="100" class="content"></textarea>
			</li>
			<li>
				<label for="upload">이미지 파일업로드</label>
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='listNotice.do'">
		</div>
	</form:form>
	<%-- <form:form commandName="boardMarketVO" action="writeMarket.do" enctype="multipart/form-data" cssClass="board_form_write">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="title" cssClass="title"/>
				<form:errors path="title" cssClass="error-color"/>
			</li>
			<li>
				<label for="content">내용</label>
				<form:textarea path="content" cssClass="content"/>
				<form:errors path="content" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload">이미지 파일업로드</label>
				<input type="file" name="upload" id="upload"
				                     accept="image/gif,image/png,image/jpeg">
			</li>
		</ul>	
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</form:form> --%>
</div>






