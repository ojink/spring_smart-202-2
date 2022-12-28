package board;

public interface BoardService {
	//CRUD
	int board_insert(BoardVO vo);  //방명록글 신규저장
	BoardPageVO board_list(BoardPageVO page); //방명록글 목록조회(페이지처리)
	BoardVO board_info(int id); //방명록 선택글 조회
	BoardFileVO board_file_info(int id); //방명록에 첨부된 각 파일정보 조회
	int board_read(int id); //방명록 글 조회수 처리
	int board_update(BoardVO vo); //방명록 글 변경저장
	int board_delete(int id); //방명록 삭제
}
