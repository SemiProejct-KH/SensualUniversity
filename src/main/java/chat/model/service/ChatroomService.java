package chat.model.service;

import static common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import chat.model.dao.ChatroomDao;
import chat.model.dto.Register;

public class ChatroomService {
	
	private ChatroomDao chatroomDao = new ChatroomDao();

	public List<Register> findRegister(int no) {
		Connection conn = getConnection();
		List<Register> list = chatroomDao.findRegister(conn, no);
		close(conn);
		return list;
	}



}
