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

	public int deleteNowEnrol(int no, String str) {
		int result = 0;
		Connection conn = getConnection();
		try
		{
			result = presentlyStudentClassDao.deleteNowEnrol(conn, no, str);
			commit(conn);
		}
		catch(Exception e)
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
}
