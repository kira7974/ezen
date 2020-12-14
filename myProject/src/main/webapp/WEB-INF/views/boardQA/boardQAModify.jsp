<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#writeQA').submit(function(){
		if($('#qa_title').val() == '') {
			alert('제목 입력하세요.');
			$('#qa_title').focus();
			return false;
		}
		if($('#qa_content').val() == '') {
			alert('질문내용 입력하세요.');
			$('#qa_content').focus();
			return false;
		}
	});
});
</script>
<div class="page-main-style">
	<<form:form commandName="boardQAVO" action="updateQA.do" enctype="multipart/form-data" cssClass="board_form_write" id="writeQA">
		<form:hidden path="qa_num"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="qa_title" cssClass="title" id="qa_title"/>
				<form:errors path="qa_title" cssClass="error-color"/>
			</li>
			<li>
				<label for="content">내용</label>
				<form:textarea path="qa_content" cssClass="content" id="qa_content"/>
				<form:errors path="qa_content" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload">이미지 파일업로드</label>
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
				<c:if test="${!empty boardQAVO.qa_filename}">
				<br>
				<span>(${boardQAVO.qa_filename})파일이 등록되어 있습니다.
				다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>
			</li>
			<li>
				<label for="qa_secret">비밀글</label>
				<input type="checkbox" name="qa_secret" id="qa_secret" value="yes"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='listQA.do'">
		</div>
	</form:form>
</div>







