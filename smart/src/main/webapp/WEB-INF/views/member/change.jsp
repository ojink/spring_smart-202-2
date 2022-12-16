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
<form method='post' action='changePassword'>
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
	if( $(this).attr('name') == 'userpw' ){
		var equal = $(this).val()==$('[name=userpw_ck]').val();
		$('[name=userpw_ck]').siblings('div')
				.removeClass('valid invalid')
				.addClass( equal ? 'valid' : 'invalid' )
				.text(equal ? '비밀번호가 일치' : '비밀번호가 불일치');
	}
	var status = member.tag_status( $(this) );
	$(this).siblings('div').text( status.desc )
		.removeClass('valid invalid').addClass( status.code );
});
$('.password').click(function(){
	if( change() ) $('form').submit();
});

//입력태그값이 유효한지 확인한 후 submit
function change(){
	var ok = true;
	$('[type=password]').each(function(){
// 		태그정보로 판단
		var _div = $(this).siblings('div');
		if( _div.hasClass('invalid') ){
			alert( '비밀변경 불가!\n' + _div.text() );
			$(this).focus();
			ok = false;
			return ok;
		}
		
// 		함수호출 결과로 판단
// 		var status = member.tag_status( $(this) );
// 		if( status.code=='invalid' ){
// 			alert( '비밀변경 불가!\n' + status.desc );
// 			$(this).focus();
// 			ok = false;
// 			return ok;
// 		}
	});
	
	return ok;
}
</script>
</body>
</html>