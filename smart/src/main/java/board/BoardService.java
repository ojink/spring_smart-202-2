package board;

import java.util.List;

public interface BoardService {
	//CRUD
	int board_insert(BoardVO vo);  //방명록글 신규저장
	BoardPageVO board_list(BoardPageVO page); //방명록글 목록조회(페이지처리)
	BoardVO board_info(int id); //방명록 선택글 조회
	BoardFileVO board_file_info(int id); //방명록에 첨부된 각 파일정보 조회
	int board_read(int id); //방명록 글 조회수 처리
	int board_update(BoardVO vo); //방명록 글 변경저장
	int board_delete(int id); //방명록 삭제
	int board_file_delete( String removed );  //첨부파일목록 삭제
	
	int board_comment_insert( BoardCommentVO vo ); //댓글신규저장
	List<BoardCommentVO> board_comment_list(int board_id); //댓글목록조회
	int board_comment_update(BoardCommentVO vo); //댓글변경저장
	int board_comment_delete(int id);//댓글삭제
}
