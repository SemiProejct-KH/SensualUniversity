package professorlecture.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dto.Member;
import professorlecture.model.dto.PresentLecture;
import professorlecture.model.service.ProfessorLectureService;

/**
 * Servlet implementation class ProfessorLectureServlet
 */
@WebServlet("/professor/professorlecture")
public class ProfessorLectureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfessorLectureService professorlectureservice = new ProfessorLectureService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Member loginMember = (Member) session.getAttribute("loginMember");
		try {
			int No = loginMember.getMemberNo();
			
			// 현재 강의목록 조회 
			List<PresentLecture> list2 = professorlectureservice.Present(No);
			System.out.println(No);
			System.out.println("list2 = " + list2);

			request.setAttribute("list2", list2);
			request.getRequestDispatcher("/WEB-INF/views/professor/professorlecture.jsp").forward(request, response);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}

}