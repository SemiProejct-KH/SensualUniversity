package admin.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageBar;
import member.model.dto.MemberExt;
import member.model.service.MemberService;

@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * select m.*,d.department_name from su_member m left join su_department d on m.department_no = d.department_no where member_role = 'S' order by enroll_date desc
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// 페이지바
			int numPerPage = MemberService.NUM_PER_PAGE;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
				
			}
			
			// 페이지바
			Map<String, Object> param = new HashMap<>();
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			param.put("start", start);
			param.put("end", end);
			
			int totalContents = memberService.getTotalContents(); // select count(*) from member
			String url = request.getRequestURI();
			String pagebar = PageBar.getPagebar(cPage, numPerPage, totalContents, url);
			System.out.println("pagebar = " + pagebar);
			
			// 학생조회
			List<MemberExt> list = memberService.studentFindAll(param);
			System.out.println("list = " + list);
			
			// 학생조회 view단처리
			request.setAttribute("list", list);
			// 페이지바 view단 처리
			request.setAttribute("pagebar", pagebar);
			request
				.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}

}
