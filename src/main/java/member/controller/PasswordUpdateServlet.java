package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PasswordEncrypt;
import member.model.dto.Member;
import member.model.service.MemberService;

@WebServlet("/member/passwordUpdate")
public class PasswordUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/passwordUpdate.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자입력값 처리
			String memberId = request.getParameter("memberId");
			String oldPassword = PasswordEncrypt.encrypt(request.getParameter("oldPassword"), memberId);
			String newPassword = PasswordEncrypt.encrypt(request.getParameter("newPassword"), memberId);
			
			System.out.println("memberId = " + memberId);
			System.out.println("oldPassword = " + oldPassword);
			System.out.println("newPassword = " + newPassword);
			
			// 2. 업무로직 - 기존비밀번호가 일치하는 경우에만 비밀번호를 수정
			Member member = memberService.findByMemberId(memberId);
			String msg = "";
			String location = request.getContextPath();
			if(member != null && oldPassword.equals(member.getMemberPw())) {
				// 비밀번호 수정
				Member updateMember = new Member();
				updateMember.setMemberId(memberId);
				updateMember.setMemberPw(newPassword);
				int result = memberService.updatePassword(updateMember);
				msg = "passwordUpdateSuccess";
				location += "/member/memberMyPage";
			}
			else {
				msg = "passwordUpdateFail";
				location += "/member/passwordUpdate";
			}
			
			// 3. 리다이렉트처리
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(location);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
