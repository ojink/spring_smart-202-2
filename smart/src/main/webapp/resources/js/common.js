/**
 * 공통 함수 선언
 */

$(function(){
	if( $('.date').length>0 ){
		//달력ui 속성지정
		$.datepicker.setDefaults({
			dateFormat: 'yy-mm-dd',
			dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
			monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월'
							, '7월', '8월', '9월', '10월', '11월', '12월'],
			showMonthAfterYear: true, //년도 뒤에 월 표시
			changeYear: true,
			changeMonth: true,
		});
	}


	//파일첨부관련처리
	$('#attach-file').change(function(){		
		console.log( this.files[0] );//선택한 파일정보
		var attached = this.files[0];
		//파일을 실제 선택한 경우
		if( attached ){
			$('#file-name').text(attached.name); //선택한 파일명이 보이게
			$('#delete-file').css('display', 'inline'); //선택한 파일삭제할 버튼 보이게
			//미리보기 태그가 있는 경우 선택한 이미지파일을 보이게 처리
			if( $("#preview").length>0 ){
				//실제 선택한 파일이 이미지인 경우만 
				if( isImage(attached.name) ){
					$("#preview").html( '<img class="profile">' );
					var reader = new FileReader();
					reader.onload = function(e){
						$('#preview img').attr('src', e.target.result );
					}
					reader.readAsDataURL( attached );
				}else{
					//프로필이미지처럼 반드시 이미지만 첨부해야 하는 경우는 
					//이미지가 아닌 파일을 선택했다면 쓰레기통이 안보이게 accept="image/*"
					if( $(this).attr('accept')=='image/*' ) {
						$('#delete-file').css('display', 'none');
						$('#attach-file').val('');
					}
					$('#preview').empty(); //이미지가 아니면 미리보기태그만 삭제
				}
			}
			
		}else{
			initAttach();  //파일태그 클릭후 취소한 경우
		}		
	});
	
	$('#delete-file').click(function(){
		initAttach();
	});
	
});

function initAttach(){
	$('#file-name').text('');				//보여졌던 파일명 없애기
	$('#attach-file').val(''); 				//실제 첨부한 파일정보 없애기
	$('#delete-file').css('display', 'none'); 	//삭제버튼 안 보이게
	//$('#preview').html(''); 				//이미지 안 보이게
	$('#preview img').remove();		
}

function isImage( filename ){
	//파일의 확장자로 이미지파일인지 판단 : abc.png, abc.JPG
	var ext = filename.substring( filename.lastIndexOf('.')+1 ).toLowerCase();
	var imgs = [ 'png', 'jpg', 'jpeg', 'bmp', 'gif', 'webp' ];
	if( imgs.indexOf( ext )==-1 ) return false;
	else return true;
}
//필수입력항목 입력여부확인 함수 
function emptyCheck(){
	var ok = true;
	$('.chk').each(function(){
		if( $.trim($(this).val())=='' ){
			var title = $(this).attr('placeholder') 
						?  $(this).attr('placeholder') : $(this).attr('title');
			alert(title + ' 입력하세요');
			$(this).val('');
			$(this).focus();
			ok = false;
			return ok;
		}		
	});
	return ok;
} 