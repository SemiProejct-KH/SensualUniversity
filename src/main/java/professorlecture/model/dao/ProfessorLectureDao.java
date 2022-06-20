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

import professorlecture.model.dto.PastLecture;
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
	
	public List<PastLecture> Past(Connection conn, int No){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<PastLecture> past = new ArrayList<>();
		PastLecture pastlecture = new PastLecture();
		String sql = prop.getProperty("past");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, No);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				pastlecture = new PastLecture();
				
				pastlecture.setSubjectNo(rset.getString("subject_no"));
				pastlecture.setPastLecture(rset.getString("subject_name"));
				
				past.add(pastlecture);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return past;
	}
	
	public List<PresentLecture> Present(Connection conn, int No){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<PresentLecture> list2 = new ArrayList<>();
		PresentLecture presentlecture = new PresentLecture();
		String sql = prop.getProperty("present");
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
