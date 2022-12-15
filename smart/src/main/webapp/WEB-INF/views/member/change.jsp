<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class='box'>
<form method='post' action='reset'>
<ul>
	<li><input type='password' name='userpw' class='chk' placeholder="새비밀번호">
		<div class='valid text-left'>비밀번호를 입력하세요</div>
	</li>	
	<li><input type='password' name='userpw_ck' class='chk' placeholder="새비밀번호 확인">
		<div class='valid text-left'>비밀번호를 다시 입력하세요</div>
	</li>
	<li><input type='reset' value='다시입력'></li>
	<li><input type='button' value='비밀번호 변경' class='password'></li>	
</ul>
</form>
</div>
<script src='js/member.js?<%=new java.util.Date()%>'></script>
<script>
$('[type=password]').keyup(function(){
	var status = member.tag_status( $(this) );
	$(this).siblings('div').text( status.desc )
		.removeClass('valid invalid').addClass( status.code );
	
});

</script>
</body>
</html>