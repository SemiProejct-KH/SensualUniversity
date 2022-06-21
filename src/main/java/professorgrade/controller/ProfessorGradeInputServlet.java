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
@WebServlet("/professor/grade/gradeInput")
public class ProfessorGradeInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfessorGradeService professorgradeservice = new ProfessorGradeService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String registerno = request.getParameter("resisterTempNo");
		System.out.println("마지막 resisterNo = " + registerno);
		int grademiddle = Integer.parseInt(request.getParameter("gradeMiddle"));
		System.out.println("마지막 gradeMiddle = " + grademiddle);
		int gradefinal = Integer.parseInt(request.getParameter("gradeFinal"));
		System.out.println("마지막 gradeFinal = " + gradefinal);
		int gradeassignment = Integer.parseInt(request.getParameter("gradeAssignment"));
		System.out.println("마지막 gradeAssignment = " + gradeassignment);
		int gradeattend = Integer.parseInt(request.getParameter("gradeAttend"));
		System.out.println("마지막 gradeAttend = " + gradeattend);
		
		int result = professorgradeservice.GradeInput(registerno, grademiddle, gradefinal, gradeassignment, gradeattend);
		
		request.getRequestDispatcher("/WEB-INF/views/professor/professorgrade.jsp");
		
		} catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		
	}

}
