package notice.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import notice.model.dto.NoticeAttachment;
import notice.model.dto.NoticeExt;
import notice.model.exception.NoticeException;

public class NoticeDao {
	private Properties prop = new Properties();
	
	public NoticeDao() {
		// 빌드패스 sql/noticec-query.properties 파일내용 불러오기
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
		notice.setMemberNo(rset.getInt("member_no"));
		notice.setNoticeTitle(rset.getString("notice_title"));
		notice.setNoticeContent(rset.getString("notice_content"));
		notice.setNoticeDate(rset.getDate("notice_date"));				
		notice.setNoticeReadCount(rset.getInt("notice_read_count"));				
		return notice;
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
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setMemberName(rset.getString("member_name"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setNoticeReadCount(rset.getInt("notice_read_count"));
				
				list.add(notice);	
			}
		} catch (Exception e) {
			throw new NoticeException("공지사항 전체게시글 조회 오류,", e);
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
			
			pstmt.setInt(1, notice.getMemberNo());
			pstmt.setString(2, notice.getNoticeTitle());
			pstmt.setString(3, notice.getNoticeContent());
			
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
			pstmt.setString(2, noticeAttach.getNoticeAttachmentOriginalFilename());
			pstmt.setString(3, noticeAttach.getNoticeAttachmentRenameFilename());
		
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
			throw new NoticeException("공지사항글 한건 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return notice;
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
			throw new NoticeException("공지사항글번호에 의한 첨부파일조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return attachments;
	}

	private NoticeAttachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		NoticeAttachment attach = new NoticeAttachment();
		attach.setNoticeAttachmentNo(rset.getInt("notice_attahment_no"));
		attach.setNoticeNo(rset.getInt("notice_no"));
		attach.setNoticeAttachmentOriginalFilename(rset.getString("notice_attachment_original_filename"));
		attach.setNoticeAttachmentRenameFilename(rset.getString("notice_attachment_renamed_filename"));
		return attach;
	}

//	public int getTotalContents(Connection conn) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		int totalContents = 0;
//		String sql = prop.getProperty("getTotalContents");
//		 
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rset = pstmt.executeQuery();
//			while(rset.next()) {
//				totalContents = rset.getInt(1); // 컬럼 인덱스 사용 (db는 1-based)
//			}
//		} catch(Exception e) {
//			throw new NoticeException("전체게시글 조회 오류!", e);
//		} finally {
//			close(rset);
//			close(pstmt);
//		}
//		return totalContents;
//	}

}
