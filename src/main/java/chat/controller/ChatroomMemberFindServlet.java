package chat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatroomMemberFindServlet
 */
@WebServlet("/chatroom/memberFinder")
public class ChatroomMemberFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subjectSearch = request.getParameter("subjectSearch");
		String memberSearchType = request.getParameter("memberSearchType");
	}

}
