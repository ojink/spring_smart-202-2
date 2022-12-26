<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지글 수정</h3>
<form method='post' action='update.no' enctype='multipart/form-data'>
<input type='hidden' name='id' value='${vo.id}'>
<table class='w-px1200'>
<tr><th class='w-px140'>제목</th>
	<td><input type='text' name='title' class='full chk' title='제목' 
				value="${fn: escapeXml(vo.title)}"></td>
</tr>
<tr><th>내용</th>
	<td><textarea name='content' class='full chk' title="내용">${vo.content}</textarea></td>
</tr>
<tr><th>첨부파일</th>
	<td class='text-left'>
		<div class='align'>
		<label>
			<input type='file' name='file' id='attach-file'>
			<a><i class="font-b fa-solid fa-file-arrow-up"></i></a>
		</label>
		<span id='file-name'>${vo.filename}</span>
		<span id='preview'></span>
		<a id='delete-file' style='display:${empty vo.filename? "none" : "inline"}'><i class="font-r fa-regular fa-trash-can"></i></a>
		</div>
	</td>
</tr>
</table>
<input type='hidden' name='filename'>
<input type='hidden' name='curPage' value='${page.curPage}'>
<input type='hidden' name='search' value='${page.search }'>
<input type='hidden' name='keyword' value='${page.keyword }'>
</form>
<div class='btnSet'>
	<a class='btn-fill save'>저장</a>
	<a class='btn-empty' href='info.no?id=${vo.id}&curPage=${page.curPage}&search=${page.search}&keyword=${page.keyword}'>취소</a>
</div>
<script>
if( isImage('${vo.filename}') ) $('#preview').html( '<img src="${vo.filepath}">' );
$('.save').click(function(){
	if( emptyCheck() )  {
		$('[name=filename]').val( $('#file-name').text() );
		$('form').submit();
	}
});
</script>
</body>
</html>




