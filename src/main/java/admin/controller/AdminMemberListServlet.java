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

/**
 * select * from su_member order by enroll_date desc
 */
@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			List<MemberExt> list = memberService.findAll();
//			System.out.println("list = " + list);
			
			request
				.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}

}
