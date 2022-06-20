package board.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import board.model.dao.StudyDao;
import board.model.dto.BoardAttachment;
import board.model.dto.BoardComment;
import board.model.dto.BoardExt;

public class StudyService {

	private StudyDao studyDao = new StudyDao();
	
	public int getTotalContents() {
		Connection conn = getConnection();
		int totalContents = studyDao.getTotalContents(conn);
		close(conn);
		return totalContents;

	}
	public List<BoardExt> findAll(Map<String, Object> param) {
		Connection conn = getConnection();
		List<BoardExt> list = studyDao.findAll(conn, param);
		close(conn);
		return list;
	}
	
	public BoardExt findByNo(int no) {
		Connection conn = getConnection();
		BoardExt board = studyDao.findByNo(conn, no); // board테이블 조회
		List<BoardAttachment> attachments = studyDao.findAttachmentByBoardNo(conn, no); // attachment 테이블 조회
		List<BoardComment> comments = studyDao.findBoardCommentByBoardNo(conn, no);
		
		board.setBoardAttachments(attachments);
		board.setBoardComments(comments);
		close(conn);
		return board;
	}
	public int updateReadCount(int no) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = studyDao.updateReadCount(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	public int insertBoard(BoardExt board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			
			result = studyDao.insertBoard(conn, board); 
			
			int no = studyDao.findCurrentBoardNo(conn); 
			
			// 3. boardAttachment에 등록
			List<BoardAttachment> boardAttachments = ((BoardExt) board).getBoardAttachments();
			if(boardAttachments != null && !boardAttachments.isEmpty()) {
				for(BoardAttachment boardAttach : boardAttachments) {
					boardAttach.setBoardNo(no);
					result = studyDao.insertBoardAttachment(conn, boardAttach);
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
	
	public int updateBoard(BoardExt board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			
			// 1. board 수정
			result = studyDao.updateBoard(conn, board);
			
			// 2. attachment에 등록
			List<BoardAttachment> attachments = ((BoardExt) board).getBoardAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(BoardAttachment attach : attachments) {
					result = studyDao.insertBoardAttachment(conn, attach);
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
	public BoardAttachment findAttachmentByNo(int no) {
		Connection conn = getConnection();
		BoardAttachment attach = studyDao.findAttachmentByNo(conn, no);
		close(conn);
		return attach;
	}
	
	public int deleteAttachment(int attachNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = studyDao.deleteAttachment(conn, attachNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}
	public int deleteBoard(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = studyDao.deleteBoard(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}
	public int insertBoardComment(BoardComment bc) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = studyDao.insertBoardComment(conn, bc);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return 0;
	}
	public int deleteBoardComment(int commentNo) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = studyDao.deleteBoardComment(conn, commentNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

}
