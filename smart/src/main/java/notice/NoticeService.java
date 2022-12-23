package notice;

import java.util.List;

public interface NoticeService {
	int notice_insert(NoticeVO vo); //공지글 신규저장
	int notice_reply_insert(NoticeVO vo); //공지글 답글신규저장
	List<NoticeVO> notice_list(); //공지록 목록조회
	NoticePageVO notice_list(NoticePageVO page); //페이지처리한 공지록 목록조회
	NoticeVO notice_info(int id);//선택한 공지글 조회	
	int notice_read(int id); //선택한 공지글 조회수변경
	int notice_update(NoticeVO vo); //공지글변경
	int notice_delete(int id); //공지글삭제
}
