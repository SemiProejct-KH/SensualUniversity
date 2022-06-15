package enrolment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enrolment.model.dto.Enrol;
import enrolment.model.service.EnrolService;

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
		request.getRequestDispatcher("/WEB-INF/views//enrolment/enrolment.jsp").forward(request, response);
	}

}
