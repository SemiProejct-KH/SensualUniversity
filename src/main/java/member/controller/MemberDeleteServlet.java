package member.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값 처리
			String memberId = request.getParameter("memberId");
			
			//2. 서비스로직호출
			int result = memberService.deleteMember(memberId);
			
			// 탈퇴후처리 - 세션폐기, 쿠키폐기
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(0); // 응답을 받은 즉시 삭제
			response.addCookie(cookie);
			
			// 모든 세션속성 제거 (session.invalidate() 대신)
			HttpSession session = request.getSession();
			Enumeration<String> names = session.getAttributeNames(); 
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				session.removeAttribute(name);
			}
			
			//3. 리다이렉트 처리
			session.setAttribute("msg", "memberDelete");
			response.sendRedirect(request.getContextPath() + "/");
//			response.sendRedirect(request.getContextPath() + "/member/logout");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
