package studentrecord.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dto.Member;
import studentrecord.model.dto.Record;
import studentrecord.model.service.RecordService;

/**
 * Servlet implementation class StudentRecordServlet
 */
@WebServlet("/student/studentrecord")
public class StudentRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecordService recordService = new RecordService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		int No = loginMember.getMemberNo();
		
		List<Record> list = recordService.recordAll(No);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/student/studentrecord.jsp").forward(request, response);
	}

}
