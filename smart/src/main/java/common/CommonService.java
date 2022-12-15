package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import member.MemberVO;

@Service
public class CommonService {
	//솔트 생성 메소드
	public String generateSalt() {
		SecureRandom secure = new SecureRandom(); //암호화 랜덤값 생성 객체
		byte bytes[] = new byte[24];
		secure.nextBytes(bytes);
		//1byte = 8bit = 2^8 : -256~255
		//byte값을 16진수로 변환 : 
		StringBuffer salt = new StringBuffer();
		for(byte b : bytes) {
			salt.append( String.format("%02x", b) ); //%o			
		}
		return salt.toString();
	}
	
	//솔트를 사용해 비밀번호를 암호화하는 메소드
	public String getEncrypt(String salt, String pw) {
		pw += salt;
		
		//암호화 해시 함수: 암호화방식 지정- SHA-256
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update( pw.getBytes() );
			byte[] bytes = md.digest();
			//16진수로 변환
			
			StringBuffer data = new StringBuffer();
			for(byte b : bytes ) {
				data.append( String.format("%02x", b));
			}
			pw = data.toString();			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return pw;
	}
	
	//임시 비밀번호 이메일 전송처리
	public boolean sendPassword(MemberVO vo, String pw) {
		boolean send = true;
		HtmlEmail mail = new HtmlEmail();
		mail.setCharset("utf-8");
		mail.setDebug(true); //콘솔에서 메일전송되어지는 과정 확인용
		
		mail.setHostName("smtp.naver.com"); //이메일서비스서버지정
		mail.setAuthentication("it-study", "It-Study1!"); //관리자 이메일주소, 비번
		mail.setSSLOnConnect(true); //로그인버튼 클릭
		
		try {
		mail.setFrom("it-study@naver.com", "스마트 웹&앱 관리자"); //메일 전송자이메일
		mail.addTo( vo.getEmail(), vo.getName() ); //메일 수신자 지정
		
		mail.setSubject("스마트 웹&앱 로그인 임시 비밀번호 확인"); //이메일 제목
		
		StringBuffer msg = new StringBuffer();
		msg.append("<html>");
		msg.append("<body>");
		msg.append("<h3>[").append(vo.getName()).append("] 임시 비밀번호</h3>");
		msg.append("<div>임시 비밀번호가 발급되었습니다</div>");
		msg.append("<div>아래 비밀번호로 로그인하신후 비밀번호를 변경하세요</div>");
		msg.append("<div>임시 비밀번호: <strong>").append( pw ).append("</strong></div>");
		msg.append("</body>");
		msg.append("</html>");
		mail.setHtmlMsg( msg.toString() ); //이메일 내용
		
		mail.send(); //보내기버튼 클릭
		
		}catch(Exception e) {
			send = false;
		}
		
		return send;
	}
}
