/**
 *  회원정보 관련 입력의 유효성 확인 함수들
 */

var member = {
	//태그에 입력한 값의 상태 확인
	tag_status: function( tag ){
		var name = tag.attr('name');
		if( name=='userpw' ) 		return this.userpw_status( tag.val() );
		else if( name=='userpw_ck') return this.userpw_ck_status( tag.val() );
		else if( name=='userid' )	return this.userid_status( tag.val() );
		else if( name=='email' )	return this.email_status( tag.val() );
	},
	
	//이메일 입력상태 확인
	email_status: function( email ){
		var reg = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
		if( email=='' )			return this.common.empty;
		else if( email.match(this.space) )	return this.common.space;
		else if( reg.test(email) )			return this.email.valid;
		else 								return this.email.invalid;
	},
	
	//이메일 상태값
	email: {
		valid: { code:'valid', desc:'사용가능한 이메일' },
		invalid: { code:'invalid', desc:'이메일형식이 맞지 않습니다' },
	},
	
	//아이디 입력상태 확인: 영문소문자,숫자만
	userid_status: function( userid ){
		var reg = /[^a-z0-9]/g;
		if( userid=='' )						return this.common.empty;
		else if( userid.match( this.space ) ) 	return this.common.space;
		else if( reg.test(userid) )				return this.userid.invalid; 
		else if( userid.length<5 )				return this.common.min;
		else if( userid.length>10 )				return this.common.max;
		else 									return this.userid.valid;
	},
	
	//아이디 상태값
	userid: {
		valid: { code:'valid', desc:'아이디중복확인 하세요' },
		usable: { code:'valid', desc:'사용가능한 아이디' },
		unUsable: { code:'invalid', desc:'이미 사용중인 아이디' },
		invalid: { code:'invalid', desc:'영문소문자,숫자만 입력' },
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
		invalid: { code:'invalid', desc:'영문 대/소문자,숫자만 입력' },
		valid: { code:'valid', desc:'사용가능한 비밀번호' },
		equal: { code:'valid', desc:'비밀번호가 일치합니다' },
		notEqual: { code:'invalid', desc:'비밀번호가 불일치' },
		lack: { code:'invalid', desc:'영문 대/소문자,숫자를 모두 포함해야함' },
	},
	
	space: /\s/g,
	
	//비밀번호의 입력상태 확인: 영문 대/소문자, 숫자를 모두 포함하게
	userpw_status: function( pw ){
		var reg = /[^a-zA-Z0-9]/g, upper= /[A-Z]/g, lower=/[a-z]/g, digit=/[0-9]/g;
		
		if( pw=='' )  					return this.common.empty;
		else if( pw.match(this.space) )	return this.common.space;
		else if( reg.test(pw) )			return this.userpw.invalid;
		else if( pw.length < 5 )		return this.common.min;
		else if( pw.length > 10 )		return this.common.max;
		else if( !upper.test(pw) || !lower.test(pw) || !digit.test(pw) )
										return this.userpw.lack;
		else							return this.userpw.valid;		
	},
	//비밀번호 확인의 입력상태 확인
	userpw_ck_status: function( pw_ck ){
		if( pw_ck=='' )  							return this.common.empty;
		else if( pw_ck==$('[name=userpw]').val() )	return this.userpw.equal;
		else 										return this.userpw.notEqual;
	},
	
	
} 