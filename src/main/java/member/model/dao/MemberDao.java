package member.model.dao;

import static common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.dto.Member;
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
	
	public Member findByMemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findByMemberId");
		Member member = null;
		
		try {
			// 1. pstmt객체 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			// 2. 실행 및 rset처리
			rset = pstmt.executeQuery();
			while(rset.next()) {
				member = handleMemberResultSet(rset);
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

	private Member handleMemberResultSet(ResultSet rset) throws SQLException{
		Member member = new Member();
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
			throw new MemberException("회원가입오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

}
