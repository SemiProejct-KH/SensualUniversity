package professorgrade.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import professorgrade.model.dto.ProfessorGrade;
import professorgrade.model.service.ProfessorGradeService;

/**
 * Servlet implementation class ProfessorGradeServlet
 */
@WebServlet("/professor/grade/professorlist")
public class ProfessorGradeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfessorGradeService professorgradeservice = new ProfessorGradeService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String subjectNo = request.getParameter("subjectNo");
			System.out.println("subjectNo = " + subjectNo);
			
			List<ProfessorGrade> list = professorgradeservice.classAll(subjectNo);
			System.out.println("두번째 list = " + list);

			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(list, response.getWriter());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
