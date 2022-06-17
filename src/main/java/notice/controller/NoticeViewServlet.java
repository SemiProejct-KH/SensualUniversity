package notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.dto.NoticeExt;
import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeViewServlet
 */
@WebServlet("/notice/noticeView")
public class NoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeService noticeService = new NoticeService();
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.사용자입력값처리
			int no = Integer.parseInt(request.getParameter("no"));
			
			// 쿠키처리
			boolean hasRead = false;
			String noticeCookieVal = "";
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {				
				for(Cookie cookie : cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					if("noticeCookie".equals(name)) {
						noticeCookieVal = value; // 기존쿠키값
						if(value.contains("|" + no + "|")) {
							hasRead = true;
						}
						break;
					}
				}
			}
			
			
			// 2.업무로직
			
			// 조회수증가
			if(!hasRead) {
				int result = noticeService.updateReadCount(no);
				// 쿠키 새로 전송 (boardCookie 없는 경우 | 요청된 boardCookie에 현재 no가 없는 경우)
				Cookie cookie = new Cookie("noticeCookie", noticeCookieVal + "|" + no + "|");
				cookie.setPath(request.getContextPath() + "/notice/noticeView"); // 쿠키를 사용할 경로
				cookie.setMaxAge(365 * 24 * 60 * 60); // 1년
				response.addCookie(cookie); // 응답헤더에 Set-Cookie로 전송
				System.out.println("> noticeCookie가 새로 생성되었음.");
			}
			
			// 게시글 조회
			NoticeExt notice = noticeService.findByNo(no);
			
			// XSS공격대비(Cross-site Scripting공격) 변환
			notice.setNoticeTitle(notice.getNoticeTitle().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			notice.setNoticeContent(notice.getNoticeContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			
			// board#content 개행처리
			notice.setNoticeContent(notice.getNoticeContent().replaceAll("\n", "<br/>"));
			
			System.out.println("notice= = " + notice);
			
			// 3.view단 위임
			request.setAttribute("notice", notice);
			request.getRequestDispatcher("/WEB-INF/views/board/notice/noticeView.jsp")
				.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}

}
