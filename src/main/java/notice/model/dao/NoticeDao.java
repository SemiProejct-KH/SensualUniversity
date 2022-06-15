package notice.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import notice.model.dto.NoticeExt;
import notice.model.exception.NoticeException;
import studentclass.model.dto.StudentClass;

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
