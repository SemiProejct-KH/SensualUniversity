package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.PasswordEncrypt;
import member.model.dto.Member;
import member.model.service.MemberService;


@WebServlet("/member/findPwResult")
public class FindPasswordResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자입력값 처리
			String memberId = request.getParameter("memberId");
			String newPassword = PasswordEncrypt.encrypt(request.getParameter("newPassword"), memberId);
			
			System.out.println("memberId = " + memberId);
			System.out.println("newPassword = " + newPassword);
			
			Member member = memberService.findByMemberId(memberId);
			String msg = "";
			String location = request.getContextPath();
			if(member != null) {
				Member updateMember = new Member();
				updateMember.setMemberId(memberId);
				updateMember.setMemberPw(newPassword);
				int result = memberService.updatePassword(updateMember);
				msg = "findPwRusultSuccess";
				location += "/";
				HttpSession session = request.getSession(false);
				if(session != null)
					session.invalidate();
			}
			else {
				msg = "findPwRusultFail";
				location += "/member/findPwResult";
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


