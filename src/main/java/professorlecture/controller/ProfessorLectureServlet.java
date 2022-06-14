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
import professorlecture.model.dto.ProfessorLecture;
import professorlecture.model.service.ProfessorLectureService;

/**
 * Servlet implementation class ProfessorLectureServlet
 */
@WebServlet("/lecture/professorlecture")
public class ProfessorLectureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfessorLectureService professorlectureservice = new ProfessorLectureService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Member loginMember = (Member) session.getAttribute("loginMember");
		try {
			int No = loginMember.getMemberNo();
			List<ProfessorLecture> list = professorlectureservice.classAll(No);
			System.out.println(No);
			System.out.println("list = " + list);

			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/professor/professorlecture.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
