package notice.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import member.model.dto.MemberExt;
import notice.model.dao.NoticeDao;
import notice.model.dto.NoticeExt;

public class NoticeService {
	
	public static final int NUM_PER_PAGE = 0;
	private NoticeDao noticeDao = new NoticeDao();

	public List<NoticeExt> findAll(int memberNo) {
		Connection conn = getConnection();
		List<NoticeExt> list = noticeDao.findAll(conn);
		close(conn);
		return list;
	}
	
//	public int getTotalContents() {
//		Connection conn = getConnection();
//		int totalContents = noticeDao.getTotalContents(conn);
//		close(conn);
//		return totalContents;
//	}

}
