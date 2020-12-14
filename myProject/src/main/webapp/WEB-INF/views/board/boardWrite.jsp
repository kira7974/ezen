<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#write').submit(function(){
		if($('#title').val() == '') {
			alert('제목을 입력하세요.');
			$('#title').focus();
			return false;
		}
		if($('#content').val() == '') {
			alert('내용을 입력하세요.');
			$('#content').focus();
			return false;
		}
	});
});
</script>
<div class="page-main-style">
	<form:form commandName="boardVO" action="write.do" enctype="multipart/form-data" cssClass="board_form_write" id="write">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="title" cssClass="title" id="title"/>
				<%-- <form:errors path="title" cssClass="error-color"/ --%>>
			</li>
			<li>
				<label for="content">내용</label>
				<form:textarea path="content" cssClass="content" id="content"/>
				<%-- <form:errors path="content" cssClass="error-color"/> --%>
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
	</form:form>
</div>







