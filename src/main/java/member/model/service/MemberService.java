package member.model.service;

import static common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;

import member.model.dao.MemberDao;
import member.model.dto.Member;
import member.model.dto.MemberExt;

/**
 * 1. connection생성
 * 2. dao요청(conn같이보냄)
 * 3. dml 경우 transaction처리
 * 4. connection반환
 * 5. controller로 값 반환
 */
public class MemberService {

	private MemberDao memberDao = new MemberDao();
	
	public MemberExt findByMemberId(String memberId) {
		Connection conn = getConnection();
		MemberExt member = memberDao.findByMemberId(conn, memberId);
		close(conn);
		return member;
	}

	public int insertMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.insertMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller에게 통보용!
		} finally {
			close(conn);			
		}
		return result;
	}

	public int updateMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updateMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller에게 통보용!
		} finally {
			close(conn);			
		}
		return result;
	}

		public int deleteMember(String memberId) {
		int result = 0;
		// 1. Connection객체 생성
		Connection conn = getConnection();
		try {
			// 2. dao 요청
			result = memberDao.deleteMember(conn, memberId);
			// 3. 트랜잭션 처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller 통보용
		} finally {
			// 4. Connection객체 반환
			close(conn);
		}
		return result;
	}

		public int updatePassword(Member member) {
			int result = 0;
			Connection conn = getConnection();
			try {
				result = memberDao.updatePassword(conn, member);
				commit(conn);
			} catch (Exception e) {
				rollback(conn);
				throw e;
			} finally {
				close(conn);
			}
			return result;
		}


		public List<MemberExt> studentFindAll() {
			Connection conn = getConnection();
			List<MemberExt> list = memberDao.studentFindAll(conn);
			close(conn);
			return list;
		}

}
