<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table td { text-align: left; }
</style>
</head>
<body>
<h3>공지글안내</h3>
<table class='w-px1200'>
<tr><th class='w-px140'>제목</th>
	<td colspan='5'>${vo.title}</td>
</tr>
<tr><th>작성자</th>
	<td>${vo.name}</td>
	<th class='w-px160'>작성일자</th>
	<td class='w-px160'>${vo.writedate}</td>
	<th class='w-px140'>조회수</th>
	<td class='w-px140'>${vo.readcnt}</td>
</tr>
<tr><th>내용</th>
	<td colspan='5'>${fn: replace(  fn:replace( vo.content, lf, '<br>' )  , crlf, '<br>')}</td>
</tr>
</table>
<div class='btnSet'>
	<a class='btn-fill' href='list.no'>공지글목록</a>
	<!-- 작성자가 로그인한 경우만 수정/삭제 가능 -->
	<c:if test='${loginInfo.userid eq vo.writer}'>
	<a class='btn-fill' href='modify.no?id=${vo.id}'>정보수정</a>
	<a class='btn-fill'>정보삭제</a>
	</c:if>
</div>
</body>
</html>