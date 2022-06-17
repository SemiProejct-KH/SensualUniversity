package admin.controller;

import java.io.IOException;
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

/**
 * Servlet implementation class AdminProfessorListServlet
 */
@WebServlet("/admin/professorList")
public class AdminProfessorListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
			
			int totalContents = memberService.getProfessorTotalContents(); // select count(*) from member
			String url = request.getRequestURI();
			String pagebar = PageBar.getPagebar(cPage, numPerPage, totalContents, url);
			System.out.println("pagebar = " + pagebar);
			
			List<MemberExt> list = memberService.professorFind(param);
			System.out.println("list = " + list);
			
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request
				.getRequestDispatcher("/WEB-INF/views/admin/professorList.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}

}
