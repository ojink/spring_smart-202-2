/**
 * 공통 함수 선언
 */

//필수입력항목 입력여부확인 함수 
function emptyCheck(){
	var ok = true;
	$('.chk').each(function(){
		if( $.trim($(this).val())=='' ){
			var title = $(this).attr('placeholder');
			alert(title + ' 입력하세요');
			$(this).val('');
			$(this).focus();
			ok = false;
			return ok;
		}		
	});
	return ok;
} 