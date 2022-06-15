package professorlecture.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import professorlecture.model.dao.ProfessorLectureDao;
import professorlecture.model.dto.ProfessorLecture;

public class ProfessorLectureService {

	private ProfessorLectureDao professorlecturedao = new ProfessorLectureDao();

	public List<ProfessorLecture> classAll(int No) {
		Connection conn = getConnection();
		List<ProfessorLecture> list = professorlecturedao.classAll(conn, No);
		close(conn);
		return list;

	}
}