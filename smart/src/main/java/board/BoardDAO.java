package board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO implements BoardService {
	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	@Override
	public int board_insert(BoardVO vo) {
		return sql.insert("board.insert", vo);
	}

	@Override
	public BoardPageVO board_list(BoardPageVO page) {
		page.setTotalList( sql.selectOne("board.total") );
		page.setList( sql.selectList("board.list", page) );
		return page;
	}

	@Override
	public BoardVO board_info(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int board_read(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int board_update(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int board_delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
