package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.MemberExt;
import member.model.service.MemberService;

@WebServlet("/admin/studentList")
public class AdminStudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * select m.*,d.department_name from su_member m left join su_department d on m.department_no = d.department_no where member_role = 'S' order by enroll_date desc;
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<MemberExt> list = memberService.studentFindAll();
			System.out.println("list = " + list);
			
			request.setAttribute("list", list);
			request
				.getRequestDispatcher("/WEB-INF/views/admin/studentList.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}

}
