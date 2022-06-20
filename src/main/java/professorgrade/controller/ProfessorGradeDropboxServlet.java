package professorgrade.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dto.Member;
import professorgrade.model.dto.ProfessorGradeDropbox;
import professorgrade.model.service.ProfessorGradeService;

/**
 * Servlet implementation class ProfessorGradeDropboxServlet
 */
@WebServlet("/professor/professorgrade")
public class ProfessorGradeDropboxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfessorGradeService professorgradeservice = new ProfessorGradeService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		try {
		int No = loginMember.getMemberNo();
		
		List<ProfessorGradeDropbox> GradeDropbox = professorgradeservice.GradeDropboxlist(No);
		System.out.println(No);
		System.out.println("GradeDropbox = "+ GradeDropbox);
		
		request.setAttribute("GradeDropbox", GradeDropbox);
		request.getRequestDispatcher("/WEB-INF/views/professor/professorgrade.jsp").forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
