package studentclass.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import studentclass.model.dao.PresentlyStudentClassDao;
import studentclass.model.dto.PresentlyStudentClass;

public class PresentlyStudentClassService {
	private PresentlyStudentClassDao presentlyStudentClassDao = new PresentlyStudentClassDao();

	public List<PresentlyStudentClass> nowClassAll(int no) {
		Connection conn = getConnection();
		List<PresentlyStudentClass> list = presentlyStudentClassDao.nowClassAll(conn, no);
		close(conn);
		return list;
	}
}
