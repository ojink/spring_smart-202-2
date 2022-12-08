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
<h3>고객목록</h3>
<table class='tb-list w-px600'>
<colgroup>
	<col width='150px'>
	<col width='150px'>
	<col>
</colgroup>
<tr><th>고객명</th>
	<th>전화번호</th>
	<th>이메일</th>
</tr>
<c:forEach items='${list}' var="vo">
<tr><td><a href='info.cu?id=${vo.id}'>${vo.name}</a></td>
	<td>${vo.phone}</td>
	<td>${vo.email}</td>
</tr>
</c:forEach>

</table>


<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>