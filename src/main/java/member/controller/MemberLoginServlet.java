package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.PasswordEncrypt;
import member.model.dto.MemberExt;
import member.model.service.MemberService;


@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩처리 필터로 처리함
		
		// 2. 사용자입력값 처리
		String memberId = request.getParameter("memberId");
		String password = PasswordEncrypt.encrypt(request.getParameter("password"), memberId);
		String saveId = request.getParameter("saveId"); 
//		System.out.println("memberId@MemberLoginServlet =" + memberId);
//		System.out.println("password@MemberLoginServlet =" + password);
		
		// 3. 업무로직
		MemberExt member = memberService.findByMemberId(memberId);
		System.out.println("member@MemberLoginServlet = " + member);
		
		HttpSession session = request.getSession();
		if(member != null && password.equals(member.getMemberPw())) {
			session.setAttribute("loginMember", member);
			
			// saveId 쿠키 처리
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath()); // /semi로 시작하는 경로에 이 쿠키를 사용함.
			if(saveId != null) {
				cookie.setMaxAge(7 * 24 * 60 * 60); // 초단위 일주일후 폐기
			}
			else {
				cookie.setMaxAge(0); // 0 즉시삭제
			}
			response.addCookie(cookie); // 응답객체 쿠키추가. Set-Cookie 헤더에 작성	
			// 4. 성공시 응답처리 
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/sample/sample.jsp");
			reqDispatcher.forward(request, response);
		}
		else {
			session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			// 4. 실패시 응답처리 
			String Referer = request.getHeader("Referer"); 
			response.sendRedirect(Referer);
		}
		
		
	}

}
