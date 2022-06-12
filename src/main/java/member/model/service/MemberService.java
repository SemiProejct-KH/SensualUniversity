package member.model.service;

import static common.JdbcTemplate.*;
import java.sql.Connection;

import member.model.dao.MemberDao;
import member.model.dto.Member;

/**
 * 1. connection생성
 * 2. dao요청(conn같이보냄)
 * 3. dml 경우 transaction처리
 * 4. connection반환
 * 5. controller로 값 반환
 */
public class MemberService {

	private MemberDao memberDao = new MemberDao();
	
	public Member findByMemberId(String memberId) {
		Connection conn = getConnection();
		Member member = memberDao.findByMemberId(conn, memberId);
		close(conn);
		return member;
	}

}
