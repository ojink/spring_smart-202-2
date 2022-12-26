<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>방명록 목록</h3>

<form method='post'>
<div id='list-top' class='w-px1200'>
<ul>
	<li><select name='search' class='w-px100'>
		<option value='all'>전체</option>
		<option value='title'>제목</option>
		<option value='content'>내용</option>
		</select>
	</li>
	<li><input type='text' name='keyword' class='w-px300'></li>
	<li><a class='btn-fill'>검색</a></li>
</ul>
<ul>
	<li><select name='pageList' class='w-px100'>
		<c:forEach var='i' begin="1" end='6'>
		<option ${page.pageList eq i*5 ? 'selected' : ''} value='${i*5}'>${5*i}개씩</option>		
		</c:forEach>
		</select>
	</li>
	<!-- 로그인한 경우 글쓰기 가능 -->
	<c:if test='${not empty loginInfo}'>
	<li><a class='btn-fill' href='new.bo'>글쓰기</a></li>
	</c:if>
</ul>
</div>
<input type='hidden' name='curPage' value='1'>
</form>

<table class='w-px1200 tb-list'>
<colgroup>
	<col width="100px">
	<col>
	<col width="160px">
	<col width="160px">
</colgroup>
<tr><th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일자</th>
</tr>


<c:forEach items='${page.list}' var='vo'>
<tr>
	<td>${vo.no}</td>
	<td class='text-left'>${vo.title }</td>
	<td>${vo.name }</td>
	<td>${vo.writedate }</td>
</tr>
</c:forEach>
</table>
<script>
$('[name=pageList]').on('change', function(){
	$('form').submit();
});
</script>

<div class='btnSet'>
	<jsp:include page="/WEB-INF/views/include/page.jsp" />
</div>

</body>
</html>