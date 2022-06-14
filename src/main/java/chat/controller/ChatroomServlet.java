package chat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chat.model.dto.Register;
import chat.model.service.ChatroomService;
import member.model.dto.Member;

/**
 * Servlet implementation class ChatroomServlet
 */

/**
한 학생이 듣고 있는 강의 출력 (honggd)
select
    s.subject_name
from
    su_subject s join su_register r
        on s.subject_no = r.subject_no
            join su_member m
                on s.member_no = m.member_no
where
    r.member_no = ?;
    
한 학생이 듣고 있는 강의의 회원 출력 (알고리즘 과목)
select
    m.member_name
from
    su_register r join su_member m
		on r.member_no = m.member_no
where
    r.subject_no = ?;
*/

@WebServlet("/chat/chatroom")
public class ChatroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChatroomService chatroomService = new ChatroomService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		try {
			int No = loginMember.getMemberNo();
			List<Register> list = chatroomService.findRegister(No);
			System.out.println(No);
			System.out.println("list = " + list);
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/chat/chatroom.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
