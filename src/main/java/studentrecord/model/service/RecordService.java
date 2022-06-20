package studentrecord.model.service;
import static common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;

import studentrecord.model.dao.RecordDao;
import studentrecord.model.dto.Record;

public class RecordService {
	private RecordDao recordDao = new RecordDao();

	public List<Record> recordAll(int no) {
		Connection conn = getConnection();
		List<Record> list = recordDao.recordAll(conn, no);
		close(conn);
		return list;
	}

	public List<Record> presentlyrecordAll(int no) {
		Connection conn = getConnection();
		List<Record> list = recordDao.presentlyrecordAll(conn, no);
		close(conn);
		return list;
	}
}
