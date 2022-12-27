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
<form method='post' action='list.no'>
<div id='list-top' class='w-px1200'>
<ul>
	<li><select class='w-px100' name='search'>
			<option value='all' ${page.search eq 'all' ? 'selected':''}>전체</option>
			<option value='title' ${page.search eq 'title' ? 'selected':''}>제목</option>
			<option value='content' ${page.search eq 'content' ? 'selected':''}>내용</option>
			<option value='tandc' ${page.search eq 'tandc' ? 'selected':''}>제목+내용</option>
			<option value='writer' ${page.search eq 'writer' ? 'selected':''}>작성자</option>
		</select>
	</li>
	<li><input type='text' class='w-px300' name='keyword' value='${page.keyword}'></li>
	<li><a class='btn-fill btn-search'>검색</a></li>
</ul>
<ul>
	<!-- 관리자회원으로 로그인한 경우만 글쓰기 가능 -->
	<c:if test='${loginInfo.admin eq "Y"}'>	
	<li><a class='btn-fill' href='new.no'>글쓰기</a></li>
	</c:if>
</ul>
</div>
<input type='hidden' name='curPage' value='1'>
</form>

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
<c:if test='${empty page.list}'>
<tr><td colspan='5'>검색결과가 없습니다</td></tr>
</c:if>
<c:forEach items="${page.list}" var='vo'>
<tr><td>${vo.no}</td>
	<td class='text-left'>
	<span style='margin-right:${vo.indent*10}px'></span>
	<c:if test='${vo.indent gt 0}'>
		<i class="font-b fa-regular fa-comment-dots"></i>
	</c:if>
		<a href='info.no?id=${vo.id}&curPage=${page.curPage}&search=${page.search}&keyword=${page.keyword}'>${vo.title}</a>
	</td>
	<td>${vo.name}</td>
	<td>${vo.writedate}</td>
	<td><c:if test='${!empty vo.filename}'><i class="font-c fa-solid fa-paperclip"></i></c:if></td>
</tr>
</c:forEach>
</table>
<script>
$('.btn-search').click(function(){
	$('form').submit()
});
</script>
<jsp:include page="/WEB-INF/views/include/page.jsp"/>

</body>
</html>






