package member;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements MemberService {
	@Autowired @Qualifier("hanul") private SqlSession sql;
//	private SqlSession sql;
//	public MemberDAO(@Qualifier("hanul") SqlSession sql) {
//		this.sql = sql;
//	}
	
	@Override
	public int member_join(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		return sql.selectOne("member.login", map);
	}

	@Override
	public MemberVO member_myInfo(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int member_idCheck(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int member_myInfo_update(MemberVO vo) {
		return sql.update("member.myInfo_update", vo);
	}

	@Override
	public int member_delete(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> member_list() {
		return sql.selectList("member.list");
	}

}
