package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.Member;
import member.model.dto.MemberRole;
import member.model.service.MemberService;


@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
			.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp")
			.forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 인코딩 필터처리
			// 2. 사용자입력값 처리
			String memberId = request.getParameter("memberId");
			String memberPw = request.getParameter("password");
			String memberName = request.getParameter("memberName");
			
			String _memberBirth = request.getParameter("memberBirth");
			System.out.println(_memberBirth);
			Date memberBirth = null;
			if(_memberBirth != null && !"".equals(_memberBirth))
				memberBirth = Date.valueOf(_memberBirth);
			
			String memberLevle = request.getParameter("memberLevle");
			String departmentNo = request.getParameter("departmentNo");
			String memberPhone = request.getParameter("memberPhone");
			String memberEmail = request.getParameter("memberEmail");
			
			Member member = new Member(
										0, departmentNo, memberId, memberPw, memberName, 
										memberBirth, memberPhone, memberEmail, null, 
										MemberRole.S, memberLevle, null
									  );
			System.out.println("member@memberEnrollServlet = " + member);
			// 3. 업무로직 (db insert)
			int result = memberService.insertMember(member);
			String msg = "성공적으로 회원가입했습니다.";
			// 4. 리다이렉트
			request
				.getSession()
				.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	

}
