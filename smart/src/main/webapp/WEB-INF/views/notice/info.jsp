<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지글안내</h3>
<table class='w-px1200'>
<tr><th class='w-px140'>제목</th>
	<td>${vo.title}</td>
</tr>
<tr><th>내용</th>
	<td>${vo.content}</td>
</tr>
</table>
<div class='btnSet'>
	<a class='btn-fill'>공지글목록</a>
	<!-- 작성자가 로그인한 경우만 수정/삭제 가능 -->
	<c:if test='${loginInfo.userid eq vo.writer}'>
	<a class='btn-fill'>수정</a>
	<a class='btn-fill'>삭제</a>
	</c:if>
</div>
</body>
</html>