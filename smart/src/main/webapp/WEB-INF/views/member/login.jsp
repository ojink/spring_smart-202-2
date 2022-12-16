<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.naver { background: url("img/naverlogin.png") center no-repeat;  background-size: cover; }
.kakao { background: url("img/kakaologin.png") center no-repeat;  background-size: cover; }
</style>
</head>
<body>
<div class='center'>
	<a href='<c:url value="/"/>'><img src='img/hanul.logo.png'></a>
	<div class='box'>
	<ul>
		<li><input type='text' id='userid' class='chk' placeholder="아이디"></li>	
		<li><input type='password' id='userpw' class='chk' placeholder="비밀번호"></li>
		<li><input type='button' value='로그인' class='login'></li>	
		<li><hr style='margin:20px 0 '></li>
		<li><input type='button' class='naver social' id='naver'></li>
		<li><input type='button' class='kakao social' id='kakao'></li>
	</ul>
	</div>
	<div><a href='find'>비밀번호찾기</a></div>
</div>
<script>
$('.social').click(function(){
	location = $(this).attr('id') + 'Login';
});
$('.login').click(function(){
	login();
});
$('#userpw').keydown(function(e){
	if(e.keyCode==13) login();
})
function login(){
	if( emptyCheck() ){
		$.ajax({
			url: 'smartLogin',
			data: { id:$('#userid').val(), pw:$('#userpw').val() },
			success: function( response ){
				console.log( response )
				if( response ){
					location = '<c:url value="/"/>';
				}else{
					alert('아이디나 비밀번호가 일치하지 않습니다');
				}
			},error: function(req,text){
				alert(text+':'+req.status);
			}
		});
	}
}
</script>
</body>
</html>





