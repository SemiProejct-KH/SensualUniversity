package professorgrade.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import professorgrade.model.dao.ProfessorGradeDao;
import professorgrade.model.dto.ProfessorGrade;

public class ProfessorGradeService {
	
	private ProfessorGradeDao professorgradedao = new ProfessorGradeDao();

	public List<ProfessorGrade> classAll(int No) {
		Connection conn = getConnection();
		List<ProfessorGrade> list = professorgradedao.classAll(conn, No);
		close(conn);
		return list;
	}
}
