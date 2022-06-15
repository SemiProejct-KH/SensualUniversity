package studentclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dto.Member;
import studentclass.model.dto.PresentlyStudentClass;
import studentclass.model.service.PresentlyStudentClassService;

/**
 * Servlet implementation class PresentlyStudentClass
 */
@WebServlet("/class/presentlystudentclass")
public class PresentlyStudentClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PresentlyStudentClassService presentlyStudentClassService = new PresentlyStudentClassService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		try 
		{
			int No = loginMember.getMemberNo();
			List<PresentlyStudentClass> list = presentlyStudentClassService.nowClassAll(No);
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/student/presentlystudentclass.jsp").forward(request, response); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}

}
