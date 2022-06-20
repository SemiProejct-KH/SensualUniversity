package studentrecord.model.dao;
import static common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import studentclass.model.dao.StudentClassDao;
import studentrecord.model.dto.Record;

public class RecordDao {
	
	private Properties prop = new Properties();
	
	public RecordDao() {
		String fileName = StudentClassDao.class.getResource("/sql/student-class.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Record> recordAll(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Record> list = new ArrayList<>();
		Record record = null;
		String sql = prop.getProperty("recordall");
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				record = new Record();
				record.setSubjectLebel(rset.getString("subject_lebel"));
				record.setDepartmentName(rset.getString("department_name"));
				record.setSubjectNo(rset.getString("subject_no"));
				record.setMemberName(rset.getString("member_name"));
				record.setGradeAttend(rset.getInt("grade_attend"));
				record.setGradeMiddle(rset.getInt("grade_middle"));
				record.setGradeFinal(rset.getInt("grade_final"));
				record.setGradAssignment(rset.getInt("grade_assignment"));
				list.add(record);
				
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

	public List<Record> presentlyrecordAll(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Record> list = new ArrayList<>();
		Record record = null;
		String sql = prop.getProperty("presentlyrecordall");
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				record = new Record();
				record.setSubjectLebel(rset.getString("subject_lebel"));
				record.setDepartmentName(rset.getString("department_name"));
				record.setSubjectNo(rset.getString("subject_no"));
				record.setMemberName(rset.getString("member_name"));
				record.setGradeAttend(rset.getInt("grade_attend"));
				record.setGradeMiddle(rset.getInt("grade_middle"));
				record.setGradeFinal(rset.getInt("grade_final"));
				record.setGradAssignment(rset.getInt("grade_assignment"));
				list.add(record);
				
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
