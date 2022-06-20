package professorlecture.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import professorlecture.model.dto.PresentLecture;
import professorlecture.model.dto.ProfessorLecture;

public class ProfessorLectureDao {
	
	private Properties prop = new Properties();
	
	public ProfessorLectureDao() {
		String fileName = ProfessorLectureDao.class.getResource("/sql/professor-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public List<ProfessorLecture> Lecture(Connection conn, int No) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		List<ProfessorLecture> list = new ArrayList<>();
//		ProfessorLecture professorlecture = null;
//		String sql = prop.getProperty("getLecture");
//		System.out.println("sql = " + sql);
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, No);
//			rset = pstmt.executeQuery();
//			
//			while (rset.next()) {
//				professorlecture = new ProfessorLecture();
//				
//				professorlecture.setSubjectNo(rset.getString("subject_no"));
//				professorlecture.setSubjectTerm(rset.getString("subject_term"));
//				professorlecture.setSubjectLebel(rset.getString("subject_lebel"));
//				professorlecture.setSubjectTime(rset.getString("subject_time"));
//				professorlecture.setSubjectClassroom(rset.getString("subject_classroom"));
//				professorlecture.setMemberName(rset.getString("member_name"));
//				professorlecture.setMemberNo(rset.getString("member_no"));
//				professorlecture.setMemberLevel(rset.getString("member_level"));
//				professorlecture.setDepartmentNo(rset.getString("department_no"));
//				professorlecture.setMemberEmail(rset.getString("member_email"));
//				professorlecture.setMemberPhone(rset.getString("member_phone"));
//				
//				list.add(professorlecture);
//				System.out.println("Dao = " + list);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rset);
//			close(pstmt);
//		}
//		return list;
//	}
	
	public List<PresentLecture> Present(Connection conn, int No){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<PresentLecture> list2 = new ArrayList<>();
		PresentLecture presentlecture = new PresentLecture();
		String sql = prop.getProperty("present");
		// select s.subject_name from su_subject s where s.member_no = ? and s.subject_term = '2022년도 1학기'
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, No);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				presentlecture = new PresentLecture();
				
				presentlecture.setSubjectNo(rset.getString("subject_no"));
				presentlecture.setPresentLecture(rset.getString("subject_name"));
				
				list2.add(presentlecture);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list2;
	}

	public List<ProfessorLecture> findStudentByProfessorAndSubject(Connection conn, String subjectNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProfessorLecture> list = new ArrayList<>();
		ProfessorLecture professorlecture = null;
		String sql = prop.getProperty("getLecture");
		try {
//			pstmt.setInt(1, memberNo);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subjectNo);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				professorlecture = new ProfessorLecture();
				
				professorlecture.setSubjectTerm(rset.getString("subject_term"));
				professorlecture.setMemberLevel(rset.getString("member_level"));
				professorlecture.setMemberName(rset.getString("member_name"));
				professorlecture.setMemberId(rset.getString("member_id"));
				professorlecture.setDepartmentName(rset.getString("department_name"));
				
				list.add(professorlecture);
				System.out.println("Dao = " + list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
}
