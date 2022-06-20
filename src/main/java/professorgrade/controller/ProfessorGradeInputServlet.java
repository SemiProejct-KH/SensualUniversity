package professorgrade.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import professorgrade.model.service.ProfessorGradeService;

/**
 * Servlet implementation class ProfessorGradeInputServlet
 */
@WebServlet("/professor/grade/gradeinput")
public class ProfessorGradeInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfessorGradeService professorgradeservice = new ProfessorGradeService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
