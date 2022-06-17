package member.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Map;

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

	public static final int NUM_PER_PAGE = 9;
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
	public int insertProfessorMember(MemberExt member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.insertProfessorMember(conn, member);
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


		public List<MemberExt> studentFindAll(Map<String, Object> param) {
			Connection conn = getConnection();
			List<MemberExt> list = memberDao.studentFindAll(conn, param);
			close(conn);
			return list;
		}

		public int getTotalContents() {
			Connection conn = getConnection();
			int totalContents = memberDao.getTotalContents(conn);
			close(conn);
			return totalContents;
		}

		public List<MemberExt> professorFind(Map<String, Object> param) {
			Connection conn = getConnection();
			List<MemberExt> list = memberDao.professorFind(conn, param);
			close(conn);
			return list;
		}

		public List<MemberExt> studentFind(Map<String, Object> param) {
			Connection conn = getConnection();
			List<MemberExt> list = memberDao.studentFind(conn, param);
			close(conn);
			return list;
		}


		public Member findPassword(String memberId, String memberName, Date memberBirth) {
			Connection conn = getConnection();
			Member member = memberDao.findPassword(conn, memberId, memberName, memberBirth);
			close(conn);
			return member;
		}

		public int getProfessorTotalContents() {
			Connection conn = getConnection();
			int professorTotalContents = memberDao.getProfessorTotalContents(conn);
			close(conn);
			return professorTotalContents;
		}

		public int getStudentTotalContents() {
			Connection conn = getConnection();
			int studentTotalContents = memberDao.getStudentTotalContents(conn);
			close(conn);
			return studentTotalContents;
		}

		public List<MemberExt> findByMember(Map<String, String> param) {
			Connection conn = getConnection();
			List<MemberExt> list = memberDao.findByMember(conn, param);
			close(conn);
			return list;
		}

		public List<MemberExt> findByProfessor(Map<String, String> param) {
			Connection conn = getConnection();
			List<MemberExt> list = memberDao.findByProfessor(conn, param);
			close(conn);
			return list;
		}

		public List<MemberExt> findByStudent(Map<String, String> param) {
			Connection conn = getConnection();
			List<MemberExt> list = memberDao.findByStudent(conn, param);
			close(conn);
			return list;
		}



}
