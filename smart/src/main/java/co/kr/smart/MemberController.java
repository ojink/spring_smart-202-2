package co.kr.smart;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonService;
import member.MemberService;
import member.MemberVO;

@Controller
public class MemberController {
	@Autowired private MemberService member;
	@Autowired private CommonService common;
//	@Autowired private MemberServiceImpl service;
//	private MemberService member;
//	public MemberController(MemberService member) {
//		this.member = member;
//	}
	
	//로그아웃처리 요청
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//비지니스로직: 세션에 있는 로그인정보를 삭제한다
		session.removeAttribute("loginInfo");
		//응답화면연결
		return "redirect:/";
	}
	
	//비밀번호 변경처리 요청
	@RequestMapping("/changePassword")
	public String changePassword(String userpw, HttpSession session) {
		//비지니스로직-화면에서 입력한 비밀번호로 DB에 변경저장한다.
		//기존의 salt(로그인정보) 를 사용해서 새로입력한 비번을 암호화한다
		MemberVO vo = (MemberVO)session.getAttribute("loginInfo");
		userpw = common.getEncrypt( vo.getSalt(), userpw );
		vo.setUserpw(userpw);
		//DB에 변경저장한다
		member.member_password_update(vo);
		session.setAttribute("loginInfo", vo);
		//응답화면연결
		return "redirect:/";
	}
	
	//비밀번호 변경 화면 요청
	@RequestMapping("/changePW")
	public String changePw(HttpSession session) {
		session.setAttribute("category", "change");
		return "member/change";				
	}
	
	//비밀번호 재발급처리 요청
	@ResponseBody @RequestMapping(value="/reset", produces="text/html; charset=utf-8" )
	public String reset(MemberVO vo) {
		//비지니스로직-화면에서 입력한 아이디/이메일이 일치하는 회원에게 임시 비번을 발급해준다 
		//임시 비번을 생성한 후 회원정보에 변경저장 하고, 임시비번을 회원에게 이메일로 알려준다
		String name = member.member_userid_email(vo);
		if( name==null ) {
			StringBuffer msg = new StringBuffer("<script>");
			msg.append("alert('아이디나 이메일이 맞지 않습니다. \\n확인하세요!'); ");
			msg.append("history.go(-1);");
			msg.append("</script>");
			return msg.toString();
		}
		
		//임시 비번을 생성
		String pw = UUID.randomUUID().toString();//afhlhj324afl_hlahfl235al_hafldka24 
		pw = pw.substring( pw.lastIndexOf("-")+1 ); //hafldka24 
		String salt = common.generateSalt();
		vo.setSalt( salt );
		vo.setUserpw( common.getEncrypt(salt, pw) );
		vo.setName( name );
		
		//회원정보에 변경저장
		StringBuffer msg = new StringBuffer("<script>");
		if( member.member_password_update(vo) == 1 && common.sendPassword(vo, pw) ) {
			msg.append("alert('임시 비밀번호가 발급되었습니다. \\n이메일을 확인하세요'); ");
			msg.append("location='login'; "); //임시비번으로 로그인하도록 로그인화면 연결
		}else {
			msg.append("alert('임시 비밀번호 발급 실패ㅠㅠ'); ");
			msg.append("history.go(-1); ");
		}
		msg.append("</script>");
		
		return msg.toString();
	}
	
	//비밀번호찾기 화면 요청 - 비밀번호재발급(임시비번발급) 화면
	@RequestMapping("/find")
	public String find(HttpSession session) {
		session.setAttribute("category", "find");
		return "default/member/find";
	}
	
	//회원가입화면 요청
	@RequestMapping("/member")
	public String member(HttpSession session) {
		session.setAttribute("category", "join");
		return "member/join";
	}
	
	
	private String NaverClientId = "mqIOjOK1HE2OJbrNFrnA";
	private String NaverClientSecret = "j9CPsKwuik";
	
	//네이버로그인처리 요청
	@RequestMapping("/naverLogin")
	public String naverLogin(HttpSession session, HttpServletRequest request) {
		//3.4.2 네이버 로그인 연동 URL 생성하기
		//https://nid.naver.com/oauth2.0/authorize?response_type=code
		//&client_id=CLIENT_ID
		//&state=STATE_STRING
		//&redirect_uri=CALLBACK_URL
		String state = UUID.randomUUID().toString();
		session.setAttribute("state", state);
		
		StringBuffer url = new StringBuffer(
				"https://nid.naver.com/oauth2.0/authorize?response_type=code");
		url.append("&client_id=").append(NaverClientId);
		url.append("&state=").append(state);
		url.append("&redirect_uri=").append( common.appURL(request) ).append("/navercallback");
		url.append("&auth_type=reprompt"); //동의항목 재동의 요청
		return "redirect:" + url.toString();
	}
	
	//네이버콜백처리
	@RequestMapping("/navercallback")
	public String navercallback(String code, String state, HttpSession session) {
		//3.4.3 네이버 로그인 연동 결과 Callback 정보
		//API 요청 성공시 : http://콜백URL/redirect?code={code값}&state={state값}
		//API 요청 실패시 : http://콜백URL/redirect?state={state값}&error={에러코드값}&error_description={에러메시지}
		String sState = (String)session.getAttribute("state");
		if( code==null || ! state.equals(sState) ) return "redirect:/";
		
		//Callback으로 전달받은 'code' 값을 이용하여 '접근토큰발급API'를 호출
		//https://nid.naver.com/oauth2.0/token?grant_type=authorization_code
		//&client_id=jyvqXeaVOVmV
		//&client_secret=527300A0_COq1_XV33cf
		//&code=EIc5bFrl4RibFls1
		//&state=9kgsGTfH4j7IyAkg  
		StringBuffer url = new StringBuffer(
			"https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
		url.append("&client_id=").append(NaverClientId);
		url.append("&client_secret=").append(NaverClientSecret);
		url.append("&code=").append(code);
		url.append("&state=").append(state);
		String response = common.requestAPI( url.toString() );
		JSONObject json = new JSONObject( response );
		String token = json.getString("access_token");
		String type = json.getString("token_type");
		
		//3.4.5 접근 토큰을 이용하여 프로필 API 호출하기
		//curl  -XGET "https://openapi.naver.com/v1/nid/me" \
	    //-H "Authorization: Bearer AAAAPIuf0L+qfDkMABQ3IJ8heq2mlw71DojBj3oc2Z6OxMQESVSrtR0dbvsiQbPbP1/cxva23n7mQShtfK4pchdk/rc="
		response = common.requestAPI("https://openapi.naver.com/v1/nid/me", type + " " + token);
		json = new JSONObject( response );
		//API호출 결과코드가 정상(00)인 경우 프로필정보에 접근
		if( json.getString("resultcode").equals("00") ) {
			MemberVO vo = new MemberVO();
			vo.setSocial("N");
			
			json = json.getJSONObject("response");
			vo.setUserid( json.getString("id") );
			//nickname, name, email, gender(- F: 여성,- M: 남성,- U: 확인불가), profile_image, mobile
			//vo.setName( json.has("nickname") ? json.getString("nickname") : "");
			vo.setName( jsonValue("nickname", json) );
			if( vo.getName().isEmpty() ) 
				vo.setName( jsonValue("name", json, "...") );
			vo.setEmail( jsonValue("email", json) );
			vo.setGender( jsonValue("gender", json, "M").equals("M") ? "남" : "여" );
			vo.setProfile( jsonValue("profile_image", json) );
			vo.setPhone( jsonValue("mobile", json) );
			
			if( member.member_idCheck(vo.getUserid())==1 ) { //update
				member.member_myInfo_update(vo);
			}else { //insert
				member.member_join(vo);
			}
			//소셜로그인되게 세션에 로그인정보를 담는다
			session.setAttribute("loginInfo", vo);
		}
		
		return "redirect:/";
	}
	
	private String jsonValue(String key, JSONObject json) {
		return json.has(key) ?  json.getString(key) : "";
	}
	private String jsonValue(String key, JSONObject json, String defaultValue) {
		return json.has(key) ?  json.getString(key) : defaultValue;
	}
	
	//로그인처리 요청
	@ResponseBody @RequestMapping("/smartLogin")
	public boolean login(String id, String pw, HttpSession session) {
		//비지니스로직-화면에서 입력한 아이디/비번이 일치하는 회원정보를 DB에서 조회한다
		//해당 아이디의 salt 를 조회해온다
		String salt = member.member_salt(id);
		pw = common.getEncrypt(salt, pw); //DB에서 조회해온 salt 를 사용해 화면에서 입력한 비번을 암호화한다
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		MemberVO vo = member.member_login(map);
		//          화면엣 출력할 수 있도록 세션에 attribute로 담는다
		session.setAttribute("loginInfo", vo);
		return vo==null ? false : true;
	}
	
	
	//로그인화면 요청
	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.setAttribute("category", "login");
		return "default/member/login";
	}
}
