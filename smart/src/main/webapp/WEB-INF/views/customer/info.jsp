<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
	<a class='btn-fill remove'>정보삭제</a>
<!-- 	<a class='btn-fill' onclick="fn_delete()">정보삭제</a> -->
	<a class='btn-fill' href='list.cu'>고객목록</a>
</div>
<script>
$(document).on('click', '.remove',  function(){
	if( confirm('[${vo.name}] 정말 삭제?') ){ 
		location='delete.cu?id=${vo.id}'; 
	}
});

// $('.remove').on('click', function(){
// 	if( confirm('[${vo.name}] 정말 삭제?') ){ 
// 		location='delete.cu?id=${vo.id}'; 
// 	}
// });

// $('.remove').click(function(){
// 	if( confirm('[${vo.name}] 정말 삭제?') ){ 
// 		location='delete.cu?id=${vo.id}'; 
// 	}
// });

function fn_delete(){
	if( confirm('[${vo.name}] 정말 삭제?') ){ 
		location='delete.cu?id=${vo.id}'; 
	}
}
</script>
</body>
</html>