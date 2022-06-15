package chat.model.service;

import static common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;

import chat.model.dao.ChatroomDao;
import chat.model.dto.Register;

public class ChatroomService {
	
	private ChatroomDao chatroomDao = new ChatroomDao();

	public List<Register> findRegister() {
		Connection conn = getConnection();
		List<Register> registerlist = chatroomDao.findRegister(conn);
		close(conn);
		return registerlist;
	}

}
