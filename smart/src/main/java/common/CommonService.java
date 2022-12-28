package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import member.MemberVO;

@Service
public class CommonService {
	
	//물리적인 첨부파일 삭제 
	public void fileDelete(String filepath, HttpServletRequest request) {
		if( filepath != null ) {
			//DB:  http://localhost/smart/upload/myinfo/2022/12/20/afdlj_abc.png
			//실제: d://app/smart/upload/myinfo/2022/12/20/afdlj_abc.png 	
			filepath = filepath.replace( appURL(request)
										, "d://app" + request.getContextPath() );			
			File file = new File( filepath );
			if( file.exists() ) file.delete();
		}
	}
	
	//첨부파일 다운로드
	public boolean fileDownload(String filename, String filepath
							, HttpServletRequest request
							, HttpServletResponse response) throws Exception {
		
	//DB:  http://localhost/smart/upload/myinfo/2022/12/20/afdlj_abc.png
	//실제: d://app/smart/upload/myinfo/2022/12/20/afdlj_abc.png 	
		filepath = filepath.replace( appURL(request), "d://app/" + request.getContextPath() );
		File file = new File( filepath );
		if( !file.exists() ) return false;
		
		String mime = request.getSession().getServletContext().getMimeType(filename);
		response.setContentType(mime);
		//첨부파일을 다운로드하는 것임을 지정
		//한글파일명 깨지지 않게 인코딩
		response.setHeader( "content-disposition" 
				, "attachment; filename=" + URLEncoder.encode(filename, "utf-8") );
		
		//IO : byte(InputStream/OutputStream)  character(Reader/Writer)
		//FileIO : FileInputStream/FileOutputStream    FileReader/FileWriter
		ServletOutputStream out = response.getOutputStream();
		//파일정보를 읽어들여 저장해주는 처리  
		FileCopyUtils.copy( new FileInputStream(file), out );
		out.flush();
		
		return true;
	}
	
	//첨부파일 업로드
	public String fileUpload(String category, MultipartFile file, HttpServletRequest request) {
		//업로드할 물리적 위치
		//D:\Study_Spring\.metadata\.....\smart\resources
		//String path = request.getSession().getServletContext().getRealPath("resources");
		//프로젝트내의 물리적영역이 아니라 고정적인 물리영역에 저장하도록 한다
		String path = "d://app" + request.getContextPath(); // d://app/smart
		// /upload/myinfo/2022/12/20
		String upload = "/upload/" + category 
					  + new SimpleDateFormat("/yyyy/MM/dd").format( new Date() );
		// D:\Study_Spring\.metadata\.....\smart\resources/upload/myinfo/2022/12/20		
		path +=  upload;
		//해당 폴더가 없으면 폴더를 만든다
		File folder = new File( path );
		if( ! folder.exists() ) folder.mkdirs();
		
		//해당 폴더에 첨부한 파일을 저장한다		
		String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		
		try {
			file.transferTo( new File(path, filename) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		//  http://localhost/smart/upload/myinfo/2022/12/20/afdlj_abc.png		
		return appURL(request) + upload + "/" + filename;		
	}
	
	
	public String requestAPI( String apiURL, String property ) {
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", property);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			apiURL = res.toString();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return apiURL;
	}
	
	public String requestAPI( String apiURL ) {
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			apiURL = res.toString();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return apiURL;
	}

	
	
	// 요청 url의 contextpath
	public String appURL(HttpServletRequest request) {
		return request.getRequestURL().toString().replace(request.getServletPath(), "");
	}

	// 솔트 생성 메소드
	public String generateSalt() {
		SecureRandom secure = new SecureRandom(); // 암호화 랜덤값 생성 객체
		byte bytes[] = new byte[24];
		secure.nextBytes(bytes);
		// 1byte = 8bit = 2^8 : -256~255
		// byte값을 16진수로 변환 :
		StringBuffer salt = new StringBuffer();
		for (byte b : bytes) {
			salt.append(String.format("%02x", b)); // %o
		}
		return salt.toString();
	}

	// 솔트를 사용해 비밀번호를 암호화하는 메소드
	public String getEncrypt(String salt, String pw) {
		pw += salt;

		// 암호화 해시 함수: 암호화방식 지정- SHA-256
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(pw.getBytes());
			byte[] bytes = md.digest();
			// 16진수로 변환

			StringBuffer data = new StringBuffer();
			for (byte b : bytes) {
				data.append(String.format("%02x", b));
			}
			pw = data.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return pw;
	}

	// 임시 비밀번호 이메일 전송처리
	public boolean sendPassword(MemberVO vo, String pw) {
		boolean send = true;
		HtmlEmail mail = new HtmlEmail();
		mail.setCharset("utf-8");
		mail.setDebug(true); // 콘솔에서 메일전송되어지는 과정 확인용

		mail.setHostName("smtp.naver.com"); // 이메일서비스서버지정
		mail.setAuthentication("itstudydev", "Itstudy10102"); // 관리자 이메일주소, 비번
//		mail.setAuthentication("it-study", "It-Study1!"); // 관리자 이메일주소, 비번
		mail.setSSLOnConnect(true); // 로그인버튼 클릭

		try {
			mail.setFrom("itstudydev@naver.com", "스마트 웹&앱 관리자"); // 메일 전송자이메일
			mail.addTo(vo.getEmail(), vo.getName()); // 메일 수신자 지정

			mail.setSubject("스마트 웹&앱 로그인 임시 비밀번호 확인"); // 이메일 제목

			StringBuffer msg = new StringBuffer();
			msg.append("<html>");
			msg.append("<body>");
			msg.append("<h3>[").append(vo.getName()).append("] 임시 비밀번호</h3>");
			msg.append("<div>임시 비밀번호가 발급되었습니다</div>");
			msg.append("<div>아래 비밀번호로 로그인하신후 비밀번호를 변경하세요</div>");
			msg.append("<div>임시 비밀번호: <strong>").append(pw).append("</strong></div>");
			msg.append("</body>");
			msg.append("</html>");
			mail.setHtmlMsg(msg.toString()); // 이메일 내용

			mail.send(); // 보내기버튼 클릭

		} catch (Exception e) {
			send = false;
		}

		return send;
	}
}
