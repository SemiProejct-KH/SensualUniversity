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
	
	public List<ProfessorLecture> Lecture(Connection conn, int No) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProfessorLecture> list = new ArrayList<>();
		ProfessorLecture professorlecture = null;
		String sql = prop.getProperty("getLecture");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, No);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				professorlecture = new ProfessorLecture();
				
				professorlecture.setSubjectTerm(rset.getString("subject_term"));
				professorlecture.setSubjectLebel(rset.getString("subject_lebel"));
				professorlecture.setSubjectTime(rset.getString("subject_time"));
				professorlecture.setSubjectClassroom(rset.getString("subject_classroom"));
				professorlecture.setMemberName(rset.getString("member_name"));
				professorlecture.setMemberNo(rset.getString("member_no"));
				professorlecture.setMemberLevel(rset.getString("member_level"));
				professorlecture.setDepartmentNo(rset.getString("department_no"));
				professorlecture.setMemberEmail(rset.getString("member_email"));
				professorlecture.setMemberPhone(rset.getString("member_phone"));
				
				list.add(professorlecture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
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
	
}
