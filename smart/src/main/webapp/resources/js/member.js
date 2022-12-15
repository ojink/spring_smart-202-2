/**
 *  회원정보 관련 입력의 유효성 확인 함수들
 */

var member = {
	//태그에 입력한 값의 상태 확인
	tag_status: function( tag ){
		var name = tag.attr('name');
		if( name=='userpw' ) 		return this.userpw_status( tag.val() );
		else if( name=='userpw_ck') return this.userpw_ck_status( tag.val() );
	},
	
	//공통사용 상태값
	common: {
		empty: { code:'invalid', desc:'입력하세요' },
		space: { code:'invalid', desc:'공백없이 입력하세요' },
		max: { code:'invalid', desc:'10자이내로 입력하세요' },
		min: { code:'invalid', desc:'5자이상 입력하세요' },
	},
	
	//비밀번호관련 상태값
	userpw: {
		valid: { code:'valid', desc:'사용가능한 비밀번호' },
		equal: { code:'valid', desc:'비밀번호가 일치합니다' },
		notEqual: { code:'invalid', desc:'비밀번호가 불일치' },
	},
	
	space: /\s/g,
	
	//비밀번호의 입력상태 확인
	userpw_status: function( pw ){
		if( pw=='' )  					return this.common.empty;
		else if( pw.match(this.space) )	return this.common.space;
		else if( pw.length < 5 )		return this.common.min;
		else if( pw.length > 10 )		return this.common.max;
		else							return this.userpw.valid;
	},
	//비밀번호 확인의 입력상태 확인
	userpw_ck_status: function( pw_ck ){
		if( pw_ck=='' )  							return this.common.empty;
		else if( pw_ck==$('[name=userpw]').val() )	return this.userpw.equal;
		else 										return this.userpw.notEqual;
	},
	
	
} 