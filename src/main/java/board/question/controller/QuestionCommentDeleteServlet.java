package board.question.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.QuestionService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/questionCommentDelete")
public class QuestionCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionService questionService = new QuestionService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int commentNo = Integer.parseInt(request.getParameter("commentNo"));
			//2. 비지니스로직 호출
			int result = questionService.deleteBoardComment(commentNo);		
			//3. 리다이렉트
			request.getSession().setAttribute("msg", "댓글 삭제 성공!");
			response.sendRedirect(request.getContextPath() + "/board/lastPropertyView?no=" + boardNo);
			
;		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
