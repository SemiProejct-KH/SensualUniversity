package professorgrade.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import professorgrade.model.dao.ProfessorGradeDao;
import professorgrade.model.dto.ProfessorGrade;
import professorgrade.model.dto.ProfessorGradeDropbox;
import professorgrade.model.dto.ProfessorGradeInput;
import professorgrade.model.dto.ProfessorGradeResister;

public class ProfessorGradeService {

	private ProfessorGradeDao professorgradedao = new ProfessorGradeDao();
	
	public int GradeInput(String registerno, int grademiddle, int gradefinal, int gradeassignment, int gradeattend) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = professorgradedao.GradeInput(conn, registerno, grademiddle, gradefinal, gradeassignment, gradeattend);
			commit(conn);			
		} catch(Exception e)
		{
			rollback(conn);
			throw e;
		}
		finally
		{
			close(conn);
		}
		return result;
	}
	
	public List<ProfessorGradeResister> GradeResister(String subjectNo, String memberId){
		Connection conn = getConnection();
		List<ProfessorGradeResister> Resister = professorgradedao.GradeResister(conn, subjectNo, memberId);
		close(conn);
		return Resister;
	}

	public List<ProfessorGradeDropbox> GradeDropboxlist(int No) {
		Connection conn = getConnection();
		List<ProfessorGradeDropbox> GradeDropbox = professorgradedao.GradeDropboxlist(conn, No);
		close(conn);
		return GradeDropbox;
	}
	
	public List<ProfessorGrade> classAll(String subjectNo) {
		Connection conn = getConnection();
		List<ProfessorGrade> list = professorgradedao.classAll(conn, subjectNo);
		close(conn);
		return list;
	}
}
