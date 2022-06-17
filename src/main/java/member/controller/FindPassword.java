package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class FindPassword
 */
@WebServlet("/member/findPw")
public class FindPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
		.getRequestDispatcher("/WEB-INF/views/member/findPw.jsp")
		.forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩 필터처리
		// 2. 사용자입력값 처리
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		String _memberBirth = request.getParameter("memberBirth");
		Date memberBirth = null;
		if(_memberBirth != null && !"".equals(_memberBirth))
			memberBirth = Date.valueOf(_memberBirth);
	
		
		Member member = memberService.findPassword(memberId,memberName,memberBirth);
		System.out.println("member@FindPasswordServlet = " + member);
		
		String msg = "";
		String location = request.getContextPath();
		HttpSession session = request.getSession();
		if(member != null) {
			session.setAttribute("member", member);
			msg = "새로 사용할 비밀번호를 입력해주세요.";
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/member/findPwResult.jsp");
			reqDispatcher.forward(request, response);
			
		} else {
			msg = "조회된 회원이 없습니다.";
		}
		request.getSession().setAttribute("msg", msg);
	}

}
