package notice.model.service;

import static common.JdbcTemplate.*;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import notice.model.dao.NoticeDao;
import notice.model.dto.NoticeAttachment;
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

	public int insertNotice(NoticeExt notice) {
		int result = 0;
		Connection conn = getConnection();
		try {
			
			// 1. notice에 등록
			result = noticeDao.insertNotice(conn, notice); // pk no값 결정 - seq_notice_no.nextval
			
			// 2. notice pk 가져오기
			int no = noticeDao.findCurrentNoticeNo(conn); // seq_notice_no.currval
			System.out.println("방금 등록된 notice.no = " + no);
			
			// 3. noticeAttachment에 등록
			List<NoticeAttachment> noticeAttachments = ((NoticeExt) notice).getNoticeAttachments();
			if(noticeAttachments != null && !noticeAttachments.isEmpty()) {
				for(NoticeAttachment noticeAttach : noticeAttachments) {
					noticeAttach.setNoticeNo(no);
					result = noticeDao.insertNoticeAttachment(conn, noticeAttach);
				}
			}
			commit(conn);
		} catch(Exception e) {			
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	public int updateReadCount(int no) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = noticeDao.updateReadCount(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public NoticeExt findByNo(int no) {
		Connection conn = getConnection();
		NoticeExt notice = noticeDao.findByNo(conn, no); // notice테이블 조회
		List<NoticeAttachment> attachments = noticeDao.findAttachmentByNoticeNo(conn, no); // attachment 테이블 조회
//		List<NoticeComment> comments = noticeDao.findBoardCommentByBoardNo(conn, no); // board_comment 테이블 조회
		notice.setNoticeAttachments(attachments);
//		board.setBoardComments(comments);
		close(conn);
		return notice;
	}

//	public int getTotalContents() {
//		Connection conn = getConnection();
//		int totalContents = noticeDao.getTotalContents(conn);
//		close(conn);
//		return totalContents;
//	}

}
