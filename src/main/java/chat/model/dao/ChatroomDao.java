package chat.model.dao;

import static common.JdbcTemplate.*;
import static common.JdbcTemplate.close;
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

import chat.exception.ChatroomException;
import chat.model.dto.Register;
import common.JdbcTemplate;
import studentclass.model.dto.StudentClass;

public class ChatroomDao {
	private Properties prop = new Properties();
	
	public ChatroomDao() {
		String fileName = ChatroomDao.class.getResource("/sql/chatroom-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Register> findRegister(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Register> registerlist = new ArrayList<>();
		Register register = null;
		String sql = prop.getProperty("findRegister");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				register = new Register();
				register.setSubjectName(rset.getString("subject_name"));
				register.setMemberName(rset.getString("member_name"));
				
				registerlist.add(register);			
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return registerlist;
	}

}
