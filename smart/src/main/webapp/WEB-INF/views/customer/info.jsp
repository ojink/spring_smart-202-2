<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp"/>
<h3>고객정보</h3>
<table class='w-px600'>
<tr><th>고객명</th>
	<td>${vo.name}</td>
</tr>
<tr><th>성별</th>
	<td>${vo.gender}</td>
</tr>
<tr><th>연락처</th>
	<td>${vo.phone}</td>
</tr>
<tr><th>이메일</th>
	<td>${vo.email}</td>
</tr>
</table>
<div class='btnSet'>
	<a class='btn-fill' href='modify.cu?id=${vo.id}'>정보수정</a>
	<a class='btn-fill'>정보삭제</a>
	<a class='btn-fill' href='list.cu'>고객목록</a>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>