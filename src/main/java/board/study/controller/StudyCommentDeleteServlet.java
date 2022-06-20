package board.study.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.StudyService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/studyCommentDelete")
public class StudyCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyService studyService = new StudyService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int commentNo = Integer.parseInt(request.getParameter("commentNo"));
			//2. 비지니스로직 호출
			int result = studyService.deleteBoardComment(commentNo);		
			//3. 리다이렉트
			request.getSession().setAttribute("msg", "댓글 삭제 성공!");
			response.sendRedirect(request.getContextPath() + "/board/studyView?no=" + boardNo);
			
;		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
