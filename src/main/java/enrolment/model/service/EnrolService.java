package enrolment.model.service;
import static common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;

import enrolment.model.dao.EnrolDao;
import enrolment.model.dto.Enrol;

public class EnrolService {
	
	private EnrolDao enrolDao = new EnrolDao();

	public List<Enrol> EnrolAll() {
		Connection conn = getConnection();
		List<Enrol> list = enrolDao.EnrolAll(conn);
		close(conn);
		return list;
	}
	

}
