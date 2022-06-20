package mainPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.dto.NoticeExt;
import notice.model.service.NoticeService;

/**
 * Servlet implementation class MainPageServlet
 */
@WebServlet("/main/mainPage")
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NoticeService noticeService = new NoticeService();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<NoticeExt> list = noticeService.findMainList();
			System.out.println("mainList" + list);
			
			request.setAttribute("list", list);
			// 주석추가
			request.getRequestDispatcher("/WEB-INF/views/main/mainPage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
}
