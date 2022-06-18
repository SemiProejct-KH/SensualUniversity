package member.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import member.model.dto.Member;
import member.model.dto.MemberExt;
import member.model.dto.MemberRole;
import member.model.exception.MemberException;

public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
		// 빌드패스 sql/member-query.properties 파일내용 불러오기
		// 참조할 파일명 변수에 담기 = url을 String으로 반환하기위한 .getPath()
		String fileName = MemberDao.class.getResource("/sql/member-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public MemberExt findByMemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findByMemberId");
		MemberExt member = null;
		
		try {
			// 1. pstmt객체 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			// 2. 실행 및 rset처리
			rset = pstmt.executeQuery();
			while(rset.next()) {
				member = handleMemberResultSet(rset);
				member.setDepartmentName(rset.getString("department_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3. 자원반납(rset, pstmt)
			close(rset);
			close(pstmt);
		}
		return member;
	}

	private MemberExt handleMemberResultSet(ResultSet rset) throws SQLException{
		MemberExt member = new MemberExt();
		member.setMemberNo(rset.getInt("member_no"));
		member.setDepartmentNo(rset.getString("department_no"));
		member.setMemberId(rset.getString("member_id"));
		member.setMemberPw(rset.getString("member_pw"));
		member.setMemberName(rset.getString("member_name"));
		// "U" -> MemberRole.U, "A" -> MemberRole.A
		member.setMemberBirth(rset.getDate("member_birth"));
		member.setMemberPhone(rset.getString("member_phone"));
		member.setMemberEmail(rset.getString("member_email"));
		member.setMemberImg(rset.getString("member_img"));
		member.setMemberRole(MemberRole.valueOf(rset.getString("member_role")));
		member.setMemberLevel(rset.getString("member_level"));
		member.setEnrollDate(rset.getDate("enroll_date"));
		return member;
	}

	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getDepartmentNo());
			pstmt.setString(2, member.getMemberId());
			pstmt.setString(3, member.getMemberPw());
			pstmt.setString(4, member.getMemberName());
			pstmt.setDate(5, member.getMemberBirth());
			pstmt.setString(6, member.getMemberPhone());
			pstmt.setString(7, member.getMemberEmail());
			pstmt.setString(8, member.getMemberImg());
			pstmt.setString(9, member.getMemberRole().toString()); // "S" "A" "P"
			pstmt.setString(10, member.getMemberLevel());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("학생 회원가입오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	

	public int insertProfessorMember(Connection conn, MemberExt member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getDepartmentNo());
			pstmt.setString(2, member.getMemberId());
			pstmt.setString(3, member.getMemberPw());
			pstmt.setString(4, member.getMemberName());
			pstmt.setDate(5, member.getMemberBirth());
			pstmt.setString(6, member.getMemberPhone());
			pstmt.setString(7, member.getMemberEmail());
			pstmt.setString(8, member.getMemberImg());
			pstmt.setString(9, member.getMemberRole().toString()); // "S" "A" "P"
			pstmt.setString(10, member.getMemberLevel());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("교수 회원가입오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection conn, Member member) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("updateMember");
			try {
				// 1. pstmt객체 생성 & 미완성쿼리 값대입
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getMemberName());
				pstmt.setDate(2, member.getMemberBirth());
				pstmt.setString(3, member.getMemberEmail());
				pstmt.setString(4, member.getMemberPhone());
				pstmt.setString(5, member.getMemberLevel());
				pstmt.setString(6, member.getDepartmentNo());
				pstmt.setString(7, member.getMemberId());
				// 2. 실행
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				throw new MemberException("회원정보수정 오류", e);
			} finally {
				// 3. 자원반납 - pstmt
				close(pstmt);
			}
			return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember"); 

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원탈퇴 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updatePassword(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePassword");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new MemberException("비밀번호 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<MemberExt> studentFindAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MemberExt> list = new ArrayList<>();
		String sql = prop.getProperty("studentFindAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MemberExt member = handleMemberResultSet(rset);
				member.setDepartmentName(rset.getString("department_name"));
				list.add(member);
			}
		} catch (Exception e) {
			throw new MemberException("회원목록조회 오류!", e);
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}

	public int getTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("getTotalContents");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next())
				totalContents = rset.getInt(1); 
		} catch (Exception e) {
			throw new MemberException("전체회원수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public List<MemberExt> professorFind(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MemberExt> list = new ArrayList<>();
		String sql = prop.getProperty("professorFind");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MemberExt member = handleMemberResultSet(rset);
				member.setDepartmentName(rset.getString("department_name"));
				list.add(member);
			}
		} catch (Exception e) {
			throw new MemberException("교수목록조회 오류!", e);
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}

	public List<MemberExt> studentFind(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MemberExt> list = new ArrayList<>();
		String sql = prop.getProperty("studentFind");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MemberExt member = handleMemberResultSet(rset);
				member.setDepartmentName(rset.getString("department_name"));
				list.add(member);
			}
		} catch (Exception e) {
			throw new MemberException("학생목록조회 오류!", e);
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}


	public Member findPassword(Connection conn, String memberId, String memberName, Date memberBirth) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String sql = prop.getProperty("findPassword");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberName);
			pstmt.setDate(3, memberBirth);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				member = handleMemberResultSet(rset);
			}
			
		} catch (Exception e) {
			throw new MemberException("비밀번호찾기 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public int getProfessorTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int professorTotalContents = 0;
		String sql = prop.getProperty("getProfessorTotalContents");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next())
				professorTotalContents = rset.getInt(1); 
		} catch (Exception e) {
			throw new MemberException("교수 회원 수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return professorTotalContents;
	}

	public int getStudentTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int studentTotalContents = 0;
		String sql = prop.getProperty("getStudentTotalContents");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next())
				studentTotalContents = rset.getInt(1); 
		} catch (Exception e) {
			throw new MemberException("학생 회원 수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return studentTotalContents;
	}

	public List<MemberExt> findByMember(Connection conn, Map<String, String> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MemberExt> list = new ArrayList<>();
		String sql = prop.getProperty("findByMember");
		sql = sql.replace("#", param.get("searchType"));
		System.out.println("sql = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + param.get("searchKeyword") + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MemberExt member = handleMemberResultSet(rset);
				member.setDepartmentName(rset.getString("department_name"));
				list.add(member);
			}
		} catch (Exception e) {
			throw new MemberException("관리자 회원목록 검색 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<MemberExt> findByProfessor(Connection conn, Map<String, String> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MemberExt> list = new ArrayList<>();
		String sql = prop.getProperty("findByProfessor");
		sql = sql.replace("#", param.get("searchType"));
		System.out.println("sql = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + param.get("searchKeyword") + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MemberExt member = handleMemberResultSet(rset);
				member.setDepartmentName(rset.getString("department_name"));
				list.add(member);
			}
		} catch (Exception e) {
			throw new MemberException("관리자 교수목록 검색 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<MemberExt> findByStudent(Connection conn, Map<String, String> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MemberExt> list = new ArrayList<>();
		String sql = prop.getProperty("findByStudent");
		sql = sql.replace("#", param.get("searchType"));
		System.out.println("sql = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + param.get("searchKeyword") + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MemberExt member = handleMemberResultSet(rset);
				member.setDepartmentName(rset.getString("department_name"));
				list.add(member);
			}
		} catch (Exception e) {
			throw new MemberException("관리자 회원목록 검색 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


}
