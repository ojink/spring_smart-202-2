package notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO implements NoticeService {
	@Qualifier("hanul") @Autowired private SqlSession sql;

	@Override
	public int notice_insert(NoticeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<NoticeVO> notice_list() {
		return sql.selectList("notice.list");
	}

	@Override
	public NoticeVO notice_info(int id) {
		return sql.selectOne("notice.info", id);
	}

	@Override
	public int notice_read(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int notice_update(NoticeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int notice_delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
