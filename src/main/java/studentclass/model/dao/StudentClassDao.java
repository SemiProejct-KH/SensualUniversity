package studentclass.model.dao;
import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import studentclass.model.dto.StudentClass;


public class StudentClassDao {
	private Properties prop = new Properties();
	
	public StudentClassDao() {
		String fileName = StudentClassDao.class.getResource("/sql/student-class.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<StudentClass> classAll(Connection conn, int No) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<StudentClass> list = new ArrayList<>();
		StudentClass studentClass = null;
		String sql = prop.getProperty("selectclass");
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, No);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				studentClass = new StudentClass();
				studentClass.setSubjectTerm(rset.getString("subject_term"));
				studentClass.setSubjectLebel(rset.getString("subject_lebel"));
				studentClass.setDepartmentName(rset.getString("department_name"));
				studentClass.setSubjectNo(rset.getString("subject_no"));
				studentClass.setSubjectName(rset.getString("subject_name"));
				studentClass.setMemberName(rset.getString("Member_name"));
				studentClass.setSubjectTime(rset.getString("subject_time"));
				studentClass.setSubjectClassroom(rset.getString("subject_classroom"));
				studentClass.setSubjectCredit(rset.getString("subject_credit"));
				
				list.add(studentClass);			
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
}
