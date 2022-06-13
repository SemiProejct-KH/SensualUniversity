package studentclass.model.service;
import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import studentclass.model.dao.StudentClassDao;
import studentclass.model.dto.StudentClass;


public class StudentClassService {
	
	private StudentClassDao studentClassDao = new StudentClassDao();

	public List<StudentClass> classAll(int No) {
		Connection conn = getConnection();
		List<StudentClass> list = studentClassDao.classAll(conn, No);
		close(conn);
		return list;
	}

}
