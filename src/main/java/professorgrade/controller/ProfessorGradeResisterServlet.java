package professorgrade.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import professorgrade.model.dto.ProfessorGradeResister;
import professorgrade.model.service.ProfessorGradeService;

/**
 * Servlet implementation class ProfessorGradeResisterServlet
 */
@WebServlet("/professor/grade/resisterNo")
public class ProfessorGradeResisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfessorGradeService professorgradeservice = new ProfessorGradeService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			String subjectNo = request.getParameter("subjectNo");
			System.out.println("서블릿 subjectNo = " + subjectNo);
			String memberId = request.getParameter("memberId");
			System.out.println("서블릿 memberId = " + memberId);

			List<ProfessorGradeResister> Resister = professorgradeservice.GradeResister(subjectNo, memberId);
			System.out.println("세번째 list = " + Resister);

			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(Resister, response.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
