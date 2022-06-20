package professorlecture.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import professorlecture.model.dao.ProfessorLectureDao;
import professorlecture.model.dto.PastLecture;
import professorlecture.model.dto.PresentLecture;
import professorlecture.model.dto.ProfessorLecture;

public class ProfessorLectureService {

	private ProfessorLectureDao professorlecturedao = new ProfessorLectureDao();
	
	public List<PastLecture> Past(int No){
		Connection conn = getConnection();
		List<PastLecture> past = professorlecturedao.Past(conn, No);
		close(conn);
		return past;
	}
	
	public List<PresentLecture> Present(int No) {
		Connection conn = getConnection();
		List<PresentLecture> list2 = professorlecturedao.Present(conn, No);
		close(conn);
		return list2;
	}

	public List<ProfessorLecture> findStudentByProfessorAndSubject(String subjectNo) {
		Connection conn = getConnection();
		List<ProfessorLecture> list = professorlecturedao.findStudentByProfessorAndSubject(conn, subjectNo);
		close(conn);
		return list;
	}
	
}
