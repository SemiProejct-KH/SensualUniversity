package notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.MemberExt;
import notice.model.dto.NoticeExt;
import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeListServlet
 */
// 확인 주석 //
@WebServlet("/notice/noticeList")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeService noticeService = new NoticeService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberExt memberExt = new MemberExt();
		
		try {

			int memberNo = memberExt.getMemberNo();
			List<NoticeExt> list =	noticeService.findAll(memberNo);
			System.out.println(memberNo);
			System.out.println("list = " + list);
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/board/notice/noticeList.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
