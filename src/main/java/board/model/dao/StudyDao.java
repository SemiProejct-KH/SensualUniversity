package board.model.dao;

import static common.JdbcTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import board.exception.BoardException;
import board.model.dto.BoardAttachment;
import board.model.dto.BoardComment;
import board.model.dto.BoardExt;
import notice.model.exception.NoticeException;

public class StudyDao {
	public int getTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = "select count(*) from su_board where board_category = 'S'";
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalContents = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new BoardException("총게시물수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	private BoardExt handleBoardResultSet(ResultSet rset) throws SQLException {
		BoardExt board = new BoardExt();
		board.setBoardNo(rset.getInt("board_no"));
		board.setMemberId(rset.getString("member_id"));
		board.setBoardTitle(rset.getString("board_title"));
		board.setBoardContent(rset.getString("board_content"));
		board.setBoardDate(rset.getDate("board_date"));				
		board.setBoardReadCount(rset.getInt("board_read_count"));	
		board.setBoardCategory(rset.getString("board_category"));	
		return board;
	}

	public List<BoardExt> findAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BoardExt> list = new ArrayList<>();
		BoardExt board = new BoardExt();
		String sql = "select * from(select row_number() over(order by board_no desc) rnum, b.board_no, b.member_id, b.board_title, b.board_date, b.board_read_count, b.board_category from su_board b) b where (rnum between ? and ?) and b.board_category = 'S'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				board = new BoardExt();	
				board.setBoardNo(rset.getInt("board_no"));
				board.setMemberId(rset.getString("member_id"));
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardDate(rset.getDate("board_date"));				
				board.setBoardReadCount(rset.getInt("board_read_count"));	
				board.setBoardCategory(rset.getString("board_category"));	
				
				list.add(board);	
			}
		} catch (Exception e) {
			throw new BoardException("게시글 전체목록 조회 오류,", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int updateReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update su_board set board_read_count = board_read_count + 1 where board_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new BoardException("조회수 증가처리 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<BoardAttachment> findAttachmentByBoardNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BoardAttachment> attachments = new ArrayList<>();
		String sql = "select * from su_board_attachment where board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BoardAttachment attach = handleAttachmentResultSet(rset);
				attachments.add(attach);
				System.out.println("attach" + attach);
			}
		} catch (SQLException e) {
			throw new BoardException("게시글번호에 의한 첨부파일조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return attachments;
	}

	private BoardAttachment handleAttachmentResultSet(ResultSet rset) throws SQLException{
		BoardAttachment boardAttach = new BoardAttachment();
		boardAttach.setBoardAttachmentNo(rset.getInt("board_attachment_no"));
		boardAttach.setBoardNo(rset.getInt("board_no"));
		boardAttach.setOriginalFilename(rset.getString("board_attachment_original_filename"));
		boardAttach.setRenameFilename(rset.getString("board_attachment_rename_filename"));
		return boardAttach;
	}

	public BoardExt findByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BoardExt board = null;
		String sql = "select * from su_board where board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				board = handleBoardResultSet(rset);
			}
		} catch (SQLException e) {
			throw new BoardException("게시글 한건 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return board;
	}

	public int insertBoard(Connection conn, BoardExt board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into su_board (board_no, member_id, board_title, board_content, board_read_count, board_category) values (seq_notice_no.nextVal, ?, ?, ?, ?, 'S')";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getMemberId());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, board.getBoardReadCount());
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			throw new BoardException("게시글 등록 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int findCurrentBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int no = 0;
		String sql = "select seq_notice_no.currval from dual";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next())
				no = rset.getInt(1);
		} catch (SQLException e) {
			throw new BoardException("게시글번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return no;
	}

	public int insertBoardAttachment(Connection conn, BoardAttachment boardAttach) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into su_board_attachment (board_attachment_no, board_no, board_attachment_original_filename, board_attachment_rename_filename) values (seq_attachment_no.nextval, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardAttach.getBoardNo());
			pstmt.setString(2, boardAttach.getOriginalFilename());
			pstmt.setString(3, boardAttach.getRenameFilename());
		
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			throw new BoardException("첨부파일 등록 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateBoard(Connection conn, BoardExt board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update su_board set board_title = ?, board_content = ? where board_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getBoardNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new BoardException("게시글 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public BoardAttachment findAttachmentByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BoardAttachment attach = null;
		String sql = "select * from su_board_attachment where board_attachment_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			if(rset.next()) 
				attach = handleAttachmentResultSet(rset);
				
		} catch (SQLException e) {
			throw new BoardException("첨부파일 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	public int deleteAttachment(Connection conn, int attachNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from su_board_attachment where board_attachment_no = ?"; 
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, attachNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("첨부파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteBoard(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from su_board where board_no = ?"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new NoticeException("게시글 삭제 오류", e);
		} finally {
			close(pstmt);
		}

		
		return result;
	}

	public int insertBoardComment(Connection conn, BoardComment bc) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into board_comment values (seq_board_comment_no.nextval, ?, ?, ?, default)"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bc.getMemberId());
			pstmt.setString(2, bc.getContent());
			pstmt.setInt(3, bc.getBoardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("댓글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<BoardComment> findBoardCommentByBoardNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BoardComment> comments = new ArrayList<>();
		String sql = "select * from board_comment where board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BoardComment bc = new BoardComment();
				bc.setCommentNo(rset.getInt("comment_no"));
				bc.setMemberId(rset.getString("member_id"));
				bc.setContent(rset.getString("content"));
				bc.setBoardNo(rset.getInt("board_no"));
				bc.setRegDate(rset.getDate("reg_date"));
				comments.add(bc);
			}
			
		} catch (SQLException e) {
			throw new BoardException("댓글 목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return comments;
	}

	public int deleteBoardComment(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "delete from board_comment where comment_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			throw new BoardException("댓글 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
}
