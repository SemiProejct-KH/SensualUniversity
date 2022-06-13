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
import studentclass.model.dto.StudentClass;
import studentclass.model.service.StudentClassService;

/**
 * Servlet implementation class StudentClassServlet
 */
@WebServlet("/class/studentclass")
public class StudentClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentClassService studentClassService = new StudentClassService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		try 
		{
			int No = loginMember.getMemberNo();
			List<StudentClass> list = studentClassService.classAll(No);
			System.out.println(No);
			System.out.println("list = " + list);
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/student/studentclass.jsp").forward(request, response);
		}  
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}



}
