package studentclass.model.dao;

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

import studentclass.model.dto.PresentlyStudentClass;

public class PresentlyStudentClassDao {
	private Properties prop = new Properties();
	
	public PresentlyStudentClassDao() {
		String fileName = StudentClassDao.class.getResource("/sql/student-class.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<PresentlyStudentClass> nowClassAll(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<PresentlyStudentClass> list = new ArrayList<>();
		PresentlyStudentClass presentlyStudentClass = null;
		String sql = prop.getProperty("nowselectclass");
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				presentlyStudentClass = new PresentlyStudentClass();
				presentlyStudentClass.setSubjectTerm(rset.getString("subject_term"));
				presentlyStudentClass.setSubjectLebel(rset.getString("subject_lebel"));
				presentlyStudentClass.setDepartmentName(rset.getString("department_name"));
				presentlyStudentClass.setSubjectNo(rset.getString("subject_no"));
				presentlyStudentClass.setSubjectName(rset.getString("subject_name"));
				presentlyStudentClass.setMemberName(rset.getString("Member_name"));
				presentlyStudentClass.setSubjectTime(rset.getString("subject_time"));
				presentlyStudentClass.setSubjectClassroom(rset.getString("subject_classroom"));
				presentlyStudentClass.setSubjectCredit(rset.getString("subject_credit"));
				
				list.add(presentlyStudentClass);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int deleteNowEnrol(Connection conn, int no, String str) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteEnol");
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}
	
}
