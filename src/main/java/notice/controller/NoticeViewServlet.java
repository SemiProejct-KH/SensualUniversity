package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeViewServlet
 */
@WebServlet("/notice/noticeView")
public class NoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getRequestDispatcher("/WEB-INF/views/board/notice/noticeView.jsp")
				.forward(request, response);			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
}
