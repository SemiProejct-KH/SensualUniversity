package enrolment.model.dao;
import static common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import enrolment.model.dto.Enrol;
import studentclass.model.dao.StudentClassDao;

public class EnrolDao {
	private Properties prop = new Properties();
	
	public EnrolDao() {
		String fileName = StudentClassDao.class.getResource("/sql/student-class.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Enrol> EnrolAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Enrol> list = new ArrayList<>();
		Enrol enrol = null;
		String sql = prop.getProperty("enrolclass");
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				enrol = new Enrol();
				enrol.setSubjectTerm(rset.getString("subject_term"));
				enrol.setSubjectLebel(rset.getString("subject_lebel"));
				enrol.setDepartmentName(rset.getString("department_name"));
				enrol.setSubjectNo(rset.getString("subject_no"));
				enrol.setSubjectName(rset.getString("subject_name"));
				enrol.setMemberName(rset.getString("Member_name"));
				enrol.setSubjectTime(rset.getString("subject_time"));
				enrol.setSubjectClassroom(rset.getString("subject_classroom"));
				enrol.setSubjectCredit(rset.getString("subject_credit"));
				
				list.add(enrol);
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
