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
		String fileName = ProfessorLectureDao.class.getResource("/sql/professor-query.properties").getPath();
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
				professorlecture.setSubjectName(rset.getString("subject_name"));
				professorlecture.setMemberId(rset.getString("subject_member_id"));
				professorlecture.setDepartmentName(rset.getString("subject_department_name"));
				
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
}
