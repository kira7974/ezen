<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style align-center">
	<div>
		<table>
			<tr>
				<th>제품명</th>
				<th>제조사</th>
				<th>출시일</th>
				<th>프로세서</th>
				<th>OS</th>
				<th>RAM</th>
				<th>내장용량</th>
				<th>화면크기</th>
				<th>충전단자</th>
			</tr>
			<c:forEach var="phone" items="${list}">
			<tr>
				<td>${phone.phone_name}</td>
				<td>${phone.phone_company}</td>
				<td>${phone.phone_date}</td>
				<td>${phone.phone_apu}</td>
				<td>${phone.phone_os}</td>
				<td>${phone.phone_ram}</td>
				<td>${phone.phone_memory}</td>
				<td>${phone.phone_display}</td>
				<td>${phone.phone_type}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
	<ul class="search align-center">
		<li><input type="button" value="목록" onclick="location.href='listPhone.do'"></li>
	</ul>
</form>

