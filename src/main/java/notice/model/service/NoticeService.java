package notice.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import notice.model.dao.NoticeDao;
import notice.model.dto.NoticeAttachment;
import notice.model.dto.NoticeExt;

public class NoticeService {
	
	public static final int NUM_PER_PAGE = 0;
	private NoticeDao noticeDao = new NoticeDao();

	public List<NoticeExt> findAll(Map<String, Object> param) {
		Connection conn = getConnection();
		List<NoticeExt> list = noticeDao.findAll(conn, param);
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
		NoticeExt notice = noticeDao.findByNo(conn, no); // board테이블 조회
		List<NoticeAttachment> attachments = noticeDao.findAttachmentByNoticeNo(conn, no); // attachment 테이블 조회
		notice.setNoticeAttachments(attachments);
		close(conn);
		return notice;
	}

	public NoticeAttachment findAttachmentByNo(int no) {
		Connection conn = getConnection();
		NoticeAttachment attach = noticeDao.findAttachmentByNo(conn, no);
		close(conn);
		return attach;
	}

	public int deleteNotice(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = noticeDao.deleteNotice(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateNotice(NoticeExt notice) {
		int result = 0;
		Connection conn = getConnection();
		try {
			
			// 1. board 수정
			result = noticeDao.updateNotice(conn, notice);
			
			// 2. attachment에 등록
			List<NoticeAttachment> attachments = ((NoticeExt) notice).getNoticeAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(NoticeAttachment attach : attachments) {
					result = noticeDao.insertNoticeAttachment(conn, attach);
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

	public int deleteAttachment(int attachNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = noticeDao.deleteAttachment(conn, attachNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}

	public int getTotalContents() {
		Connection conn = getConnection();
		int totalContents = noticeDao.getTotalContents(conn);
		close(conn);
		return totalContents;

	}

	public List<NoticeExt> findMainList() {
		Connection conn = getConnection();
		List<NoticeExt> noticeList = noticeDao.findMainList(conn);
		close(conn);
		return noticeList;
	}

	public List<NoticeExt> findAll() {
		Connection conn = getConnection();
		List<NoticeExt> list = noticeDao.findAll(conn);
		close(conn);
		return list;
	}

}