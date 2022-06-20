package professorlecture.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import professorlecture.model.dto.ProfessorLecture;
import professorlecture.model.service.ProfessorLectureService;

/**
 * Servlet implementation class ProfessorListServlet
 */
@WebServlet("/professor/lecture/professorlist")
public class ProfessorListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfessorLectureService professorlectureservice = new ProfessorLectureService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자입력값
			String subjectNo = request.getParameter("subjectNo");
			System.out.println("subjectNo = " + subjectNo);
			
			// 2. 업무로직 : 해당교수/해당강의 수강생 목록 조회
			List<ProfessorLecture> list = professorlectureservice.findStudentByProfessorAndSubject(subjectNo);
			System.out.println("list = " + list);
			
			// 3. 응답처리
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(list, response.getWriter());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
}
