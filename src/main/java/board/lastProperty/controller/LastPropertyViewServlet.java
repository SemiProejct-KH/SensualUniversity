package board.lastProperty.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.dto.BoardExt;
import board.model.service.LastPropertyService;

/**
 * Servlet implementation class LastPropertyViewServlet
 */
@WebServlet("/board/lastPropertyView")
public class LastPropertyViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LastPropertyService lastPropertyService = new LastPropertyService();
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.사용자입력값처리
			int no = Integer.parseInt(request.getParameter("no"));
			
			boolean hasRead = false;
			String lastPropertyCookieVal = "";
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {				
				for(Cookie cookie : cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					if("lastPropertyCookie".equals(name)) {
						lastPropertyCookieVal = value; // 기존쿠키값
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
				int result = lastPropertyService.updateReadCount(no);
				System.out.println("RESULT" + result);
				// 쿠키 새로 전송 (boardCookie 없는 경우 | 요청된 boardCookie에 현재 no가 없는 경우)
				Cookie cookie = new Cookie("lastPropertyCookie", lastPropertyCookieVal + "|" + no + "|");
				cookie.setPath(request.getContextPath() + "/board/lastPropertyView"); // 쿠키를 사용할 경로
				cookie.setMaxAge(365 * 24 * 60 * 60); // 1년
				response.addCookie(cookie); // 응답헤더에 Set-Cookie로 전송
				System.out.println("> lastProperty가 새로 생성되었음.");
			}
			
			// 게시글 조회
			BoardExt board = lastPropertyService.findByNo(no);
			System.out.println(board);
			
			// XSS공격대비(Cross-site Scripting공격) 변환
			board.setBoardTitle(board.getBoardTitle().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			board.setBoardContent(board.getBoardContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			
			// board#content 개행처리
			board.setBoardContent(board.getBoardContent().replaceAll("\n", ""));
			
			System.out.println("board = " + board);
			
			// 3.view단 위임
			request.setAttribute("board", board);
			request.getRequestDispatcher("/WEB-INF/views/board/lastProperty/lastPropertyView.jsp")
				.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		

	}

}
