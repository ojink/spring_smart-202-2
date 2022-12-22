<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지사항</h3>
<div id='list-top' class='w-px1200'>
<ul>
	<!-- 관리자회원으로 로그인한 경우만 글쓰기 가능 -->
	<c:if test='${loginInfo.admin eq "Y"}'>	
	<li><a class='btn-fill' href='new.no'>글쓰기</a></li>
	</c:if>
</ul>
</div>
<table class='w-px1200 tb-list'>
<colgroup>
	<col width='100px'>
	<col>
	<col width='140px'>
	<col width='160px'>
	<col width='80px'>
</colgroup>
<tr><th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일자</th>	
	<th>첨부파일</th>	
</tr>
<c:forEach items="${page.list}" var='vo'>
<tr><td>${vo.no}</td>
	<td class='text-left'><a href='info.no?id=${vo.id}'>${vo.title}</a></td>
	<td>${vo.name}</td>
	<td>${vo.writedate}</td>
	<td><c:if test='${!empty vo.filename}'><i class="font-c fa-solid fa-paperclip"></i></c:if></td>
</tr>
</c:forEach>
</table>
</body>
</html>






