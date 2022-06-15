package enrolment.model.service;
import static common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;

import enrolment.model.dao.EnrolDao;
import enrolment.model.dto.Enrol;
import member.model.dto.Member;

public class EnrolService {
	
	private EnrolDao enrolDao = new EnrolDao();

	public List<Enrol> EnrolAll() {
		Connection conn = getConnection();
		List<Enrol> list = enrolDao.EnrolAll(conn);
		close(conn);
		return list;
	}

	public int insertEnrol(int no, String str) {
		int result = 0;
		Connection conn = getConnection();
		try
		{
			result = enrolDao.insertEnrol(conn, no, str);
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
