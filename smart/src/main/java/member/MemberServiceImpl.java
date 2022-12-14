package member;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("member")
public class MemberServiceImpl implements MemberService {
	@Autowired private MemberDAO dao;
//	private MemberDAO dao;
//	public MemberServiceImpl(MemberDAO dao) {
//		this.dao = dao;
//	}
	
	
	@Override
	public int member_join(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		return dao.member_login(map);
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
		return dao.member_myInfo_update(vo);
	}

	@Override
	public int member_delete(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> member_list() {
		return dao.member_list();
	}

}
