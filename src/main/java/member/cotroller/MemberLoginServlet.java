package member.cotroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.Member;
import member.model.service.MemberService;


@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩처리 필터로 처리함
		
		// 2. 사용자입력값 처리
		String memberId = request.getParameter("input_id");
		String password = request.getParameter("input_pw");
//		System.out.println("memberId@MemberLoginServlet =" + memberId);
//		System.out.println("password@MemberLoginServlet =" + password);
		
		// 3. 업무로직
		Member member = memberService.findByMemberId(memberId);
//		System.out.println("member@MemberLoginServlet = " + member);
		if(member != null && password.equals(member.getMemberPw())) {
			// 로그인 성공
			request.setAttribute("loginMember", member);
		} else {
			// 로그인 실패
		}
		
		
		// 4. 응답처리 : 리다이렉트
		request
		.getRequestDispatcher("/WEB-INF/views/sample/sample.jsp")
		.forward(request, response);
		
	}

}
