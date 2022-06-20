package notice.model.dao;

import static common.JdbcTemplate.close;

import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import notice.model.dto.NoticeAttachment;
import notice.model.dto.NoticeExt;
import notice.model.exception.NoticeException;

public class NoticeDao {
	private Properties prop = new Properties();
	
	public NoticeDao() {
		// 빌드패스 sql/notice-query.properties 파일내용 불러오기
		// 참조할 파일명 변수에 담기 = url을 String으로 반환하기위한 .getPath()
		String fileName = NoticeDao.class.getResource("/sql/notice-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private NoticeExt handleNoticeResultSet(ResultSet rset) throws SQLException {
		NoticeExt notice = new NoticeExt();
		notice.setNoticeNo(rset.getInt("notice_no"));
		notice.setMemberId(rset.getString("member_id"));
		notice.setNoticeTitle(rset.getString("notice_title"));
		notice.setNoticeContent(rset.getString("notice_content"));
		notice.setNoticeDate(rset.getDate("notice_date"));				
		notice.setNoticeReadCount(rset.getInt("notice_read_count"));				
		return notice;
	}

	public List<NoticeExt> findAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<NoticeExt> list = new ArrayList<>();
		NoticeExt notice = new NoticeExt();
		String sql = prop.getProperty("findAll"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				notice = new NoticeExt();
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setMemberId(rset.getString("member_id"));
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setNoticeReadCount(rset.getInt("notice_read_count"));
				
				list.add(notice);	
			}
		} catch (Exception e) {
			throw new NoticeException("공지사항 전체목록 조회 오류,", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertNotice(Connection conn, NoticeExt notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, notice.getMemberId());
			pstmt.setString(2, notice.getNoticeTitle());
			pstmt.setString(3, notice.getNoticeContent());
			pstmt.setInt(4, notice.getNoticeReadCount());
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			throw new NoticeException("공지사항 등록 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int findCurrentNoticeNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int no = 0;
		String sql = prop.getProperty("findCurrentNoticeNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next())
				no = rset.getInt(1);
		} catch (SQLException e) {
			throw new NoticeException("공지사항번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return no;
	}

	public int insertNoticeAttachment(Connection conn, NoticeAttachment noticeAttach) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNoticeAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeAttach.getNoticeNo());
			pstmt.setString(2, noticeAttach.getOriginalFilename());
			pstmt.setString(3, noticeAttach.getRenameFilename());
		
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			throw new NoticeException("첨부파일 등록 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateReadCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new NoticeException("조회수 증가처리 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}


	public NoticeAttachment findAttachmentByNo(Connection conn, int attachNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		NoticeAttachment attach = null;
		String sql = prop.getProperty("findAttachmentByNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attachNo);
			rset = pstmt.executeQuery();
			if(rset.next()) 
				attach = handleAttachmentResultSet(rset);
				
		} catch (SQLException e) {
			throw new NoticeException("첨부파일 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	private NoticeAttachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		NoticeAttachment noticeAttach = new NoticeAttachment();
		noticeAttach.setNoticeAttachmentNo(rset.getInt("notice_attachment_no"));
		noticeAttach.setNoticeNo(rset.getInt("notice_no"));
		noticeAttach.setOriginalFilename(rset.getString("notice_attachment_original_filename"));
		noticeAttach.setRenameFilename(rset.getString("notice_attachment_rename_filename"));
		return noticeAttach;
	}

	public NoticeExt findByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		NoticeExt notice = null;
		String sql = prop.getProperty("findByNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				notice = handleNoticeResultSet(rset);
			}
		} catch (SQLException e) {
			throw new NoticeException("게시글 한건 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}

	public int updateNotice(Connection conn, NoticeExt notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setInt(3, notice.getNoticeNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new NoticeException("공지사항글 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<NoticeAttachment> findAttachmentByNoticeNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<NoticeAttachment> attachments = new ArrayList<>();
		String sql = prop.getProperty("findAttachmentByNoticeNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				NoticeAttachment attach = handleAttachmentResultSet(rset);
				attachments.add(attach);
			}
		} catch (SQLException e) {
			throw new NoticeException("게시글번호에 의한 첨부파일조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return attachments;
	}

	public int deleteNotice(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteNotice"); 
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new NoticeException("공지사항 삭제 오류", e);
		} finally {
			close(pstmt);
		}

		
		return result;
	}

	public int deleteAttachment(Connection conn, int attachNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteAttachment"); 
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, attachNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new NoticeException("첨부파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("getTotalContents");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalContents = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new NoticeException("총게시물수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public List<NoticeExt> findMainList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<NoticeExt> noticeList = new ArrayList<>();
		NoticeExt notice = new NoticeExt();
		String sql = prop.getProperty("findMainList"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				//notice = handleNoticeResultSet(rset);
				notice = new NoticeExt();
				notice.setNoticeNo(rset.getInt("notice_no"));
				
				noticeList.add(notice);	
				System.out.println("noticeDao = " + notice);
			}
		} catch (Exception e) {
			throw new NoticeException("메인페이지 공지사항 조회 오류,", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return noticeList;
	}

	public List<NoticeExt> findAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<NoticeExt> list = new ArrayList<>();
		NoticeExt notice = new NoticeExt();
		String sql = prop.getProperty("findAll"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				notice = new NoticeExt();
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setMemberId(rset.getString("member_id"));
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setNoticeReadCount(rset.getInt("notice_read_count"));
				
				list.add(notice);	
			}
		} catch (Exception e) {
			throw new NoticeException("공지사항 전체목록 조회 오류,", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
}
