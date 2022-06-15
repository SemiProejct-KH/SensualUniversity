package chat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chat.model.service.ChatroomService;
import member.model.dto.Member;

/**
 * Servlet implementation class ChatroomMakerServlet
 */
@WebServlet("/chat/chatroom/chatroommaker")
public class ChatroomMakerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChatroomService chatroomService = new ChatroomService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		try {
//			int No = loginMember.getMemberNo();
			
			int chatroomNo = Integer.parseInt(request.getParameter("chatroomNo"));
			String[] _memberNo = request.getParameterValues("memberNo");
			String memberNo = null;
			if(_memberNo != null) {
				memberNo = String.join(",", memberNo);
			}
			
			//db에 방만 저장 후 chatroom 리다이렉트
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
