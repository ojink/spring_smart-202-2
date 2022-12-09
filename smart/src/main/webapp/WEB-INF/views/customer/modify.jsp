<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>고객정보수정</h3>
<form method='post' action='update.cu'>
<input type='hidden' name='id' value='${      vo.id      }'>
<table class='w-px600'>
<tr><th class='w-px140'>고객명</th>
	<td><input type='text' name='name' value='${vo.name}'></td>
</tr>
<tr><th>성별</th>
	<td><input type='radio' value='남' name='gender'
				 <c:if test='${     vo.gender     eq "남"  }'>checked</c:if> >남
		<input type='radio' value='여' name='gender'
				 ${vo.gender eq '여' ? 'checked' : ''}	>여
	</td>
</tr>
<tr><th>연락처</th>
	<td><input type='text' name='phone' value='${vo.phone }' maxlength="13"></td>
</tr>
<tr><th>이메일</th>
	<td><input type='text' name='email' value='${vo.email }'></td>
</tr>
</table>
</form>
<div class='btnSet'>
	<a class='btn-fill' onclick="$('form').submit()">저장</a>
	<a class='btn-empty' href='info.cu?id=${vo.id}'>취소</a>
</div>
</body>
</html>