<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<form:form commandName="boardQAVO" action="writeQA.do" enctype="multipart/form-data" cssClass="board_form_write">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="qa_title" cssClass="title"/>
				<form:errors path="qa_title" cssClass="error-color"/>
			</li>
			<li>
				<label for="content">내용</label>
				<form:textarea path="qa_content" cssClass="content"/>
				<form:errors path="qa_content" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload">이미지 파일업로드</label>
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
			</li>
			<li>
				<label for="secret">비밀글</label>
				No<form:radiobutton path="qa_secret" value="no" lable="NO"/>
				Yes<form:radiobutton path="qa_secret" value="yes" lable="YES"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='listQA.do'">
		</div>
	</form:form>
</div>







