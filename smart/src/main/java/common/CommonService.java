package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

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
	
}
