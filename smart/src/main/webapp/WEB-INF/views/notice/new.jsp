<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지글등록</h3>
<form method='post' action='insert.no'>
<table class='w-px1200'>
<tr><th class='w-px140'>제목</th>
	<td><input type='text' name='title' class='full'></td>
</tr>
<tr><th>내용</th>
	<td><textarea name='content' class='full'></textarea></td>
</tr>
</table>
</form>
<div class='btnSet'>
	<a class='btn-fill save'>저장</a>
	<a class='btn-empty' href='list.no'>취소</a>
</div>
<script>
$('.save').click(function(){
	$('form').submit();
});
</script>
</body>
</html>