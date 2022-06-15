package professorgrade.model.dao;
import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import professorgrade.model.dto.ProfessorGrade;

public class ProfessorGradeDao {

	private Properties prop = new Properties();

	public ProfessorGradeDao() {
		String fileName = ProfessorGradeDao.class.getResource("/sql/professor-grade.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<ProfessorGrade> classAll(Connection conn, int No) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProfessorGrade> list = new ArrayList<>();
		ProfessorGrade professorgrade = null;
		String sql = prop.getProperty("getGrade");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, No);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				professorgrade = new ProfessorGrade();
				professorgrade.setSubjectTerm(rset.getString("subject_term"));
				professorgrade.setSubjectLebel(rset.getString("subject_lebel"));
				professorgrade.setSubjectName(rset.getString("subject_name"));
				professorgrade.setMemberId(rset.getString("subject_member_id"));
				professorgrade.setDepartmentName(rset.getString("subject_department_name"));
				professorgrade.setGrademiddle(rset.getInt("subject_grade_middle"));
				professorgrade.setGradefinal(rset.getInt("subject_grade_final"));
				professorgrade.setGradeassignment(rset.getInt("subject_grade_assignmnet"));
				professorgrade.setGradeattend(rset.getInt("subject_grade_attend"));

				list.add(professorgrade);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
}