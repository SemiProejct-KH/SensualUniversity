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

import professorlecture.model.dto.ProfessorLecture;

public class ProfessorLectureDao {
	
	private Properties prop = new Properties();
	
	public ProfessorLectureDao() {
		String fileName = ProfessorLectureDao.class.getResource("/sql/professor-lecture.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<ProfessorLecture> classAll(Connection conn, int No) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProfessorLecture> list = new ArrayList<>();
		ProfessorLecture professorgrade = null;
		String sql = prop.getProperty("getLecture");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, No);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				professorgrade = new ProfessorLecture();
				professorgrade.setSubjectTerm(rset.getString("subject_term"));
				professorgrade.setSubjectLebel(rset.getString("subject_lebel"));
				professorgrade.setSubjectName(rset.getString("subject_name"));
				professorgrade.setMemberId(rset.getString("subject_member_id"));
				professorgrade.setDepartmentName(rset.getString("subject_department_name"));
				
				list.add(professorgrade);
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
