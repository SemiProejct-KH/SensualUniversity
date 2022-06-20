package enrolment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import enrolment.model.dto.Enrol;
import enrolment.model.service.EnrolService;
import member.model.dto.Member;

/**
 * Servlet implementation class enrolmentViewServlet
 */
@WebServlet("/enrol/enrolment")
public class enrolmentViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnrolService enrolService = new EnrolService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Enrol> list = enrolService.EnrolAll();
		System.out.println("EnrolList = " + list);	
		
			
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/enrolment/enrolment.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		try 
		{
			int No = loginMember.getMemberNo();
			String Str = request.getParameter("sno");
			System.out.println(Str);
			
			int result = enrolService.insertEnrol(No, Str);
			
			request.getRequestDispatcher("/WEB-INF/views/enrolment/enrolment.jsp").forward(request, response);
//			response.sendRedirect(request.getContextPath() + "/enrolment/enrolment");
		}  
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
}
