package enrolment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dto.Member;
import studentclass.model.service.PresentlyStudentClassService;

/**
 * Servlet implementation class DeleteEnrolServlet
 */
@WebServlet("/delete/deleteEnrol")
public class DeleteEnrolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PresentlyStudentClassService presentlyStudentClassService = new PresentlyStudentClassService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		try 
		{
			int No = loginMember.getMemberNo();
			String Str = request.getParameter("sno");
			System.out.println(Str);
			
			int result = presentlyStudentClassService.deleteNowEnrol(No, Str);
			
			request.getRequestDispatcher("/WEB-INF/views/student/presentlystudentclass.jsp").forward(request, response);
//			response.sendRedirect(request.getContextPath() + "/class/presentlystudentclass");
		}  
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}


}
