package board.lastProperty.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.dto.BoardComment;
import board.model.service.LastPropertyService;

/**
 * Servlet implementation class LastPropertyCommentServlet
 */
@WebServlet("/board/lastPropertyComment")
public class LastPropertyCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LastPropertyService lastPropertyService = new LastPropertyService();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자입력값 처리
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String memberId = request.getParameter("memberId");
			String content = request.getParameter("content");
			
			BoardComment bc = 
					new BoardComment(0,  memberId, content, boardNo, null);
			System.out.println("boardComment = " + bc);
			
			// 2. 업무로직
			int result = lastPropertyService.insertBoardComment(bc);
			
			// 3. 리다이렉트
			response.sendRedirect(request.getContextPath() + "/board/lastPropertyView?no=" + boardNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
