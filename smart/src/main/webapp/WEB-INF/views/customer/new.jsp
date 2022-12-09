<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>신규고객등록</h3>
<form method='post' action='insert.cu'>
<table class='w-px600'>
<tr><th class='w-px140'>고객명</th>
	<td><input type='text' name='name'></td>
</tr>
<tr><th>성별</th>
	<td><label><input type='radio' value='남' name='gender'>남</label>
		<label><input type='radio' value='여' name='gender' checked>여</label>
	</td>
</tr>
<tr><th>연락처</th>
	<td><input type='text' name='phone' maxlength="13"></td>
</tr>
<tr><th>이메일</th>
	<td><input type='text' name='email' ></td>
</tr>
</table>
</form>
<div class='btnSet'>
	<a class='btn-fill' onclick="$('form').submit()">저장</a>
	<a class='btn-empty' href='list.cu'>취소</a>
</div>
</body>
</html>