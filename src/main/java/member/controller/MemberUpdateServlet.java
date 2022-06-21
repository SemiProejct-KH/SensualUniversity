package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.Member;
import member.model.dto.MemberExt;
import member.model.dto.MemberRole;
import member.model.service.MemberService;

@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 인코딩 필터
			// 2. 사용자입력값처리
//			int memberNo = Integer.parseInt(request.getParameter("memberNo"));
			String memberId = request.getParameter("memberId");
			String memberName = request.getParameter("memberName");
			String _memberBirth = request.getParameter("memberBirth");
			System.out.println(_memberBirth);
			
			Date memberBirth = null;
			if(_memberBirth != null && !"".equals(_memberBirth))
				memberBirth = Date.valueOf(_memberBirth);
			
			String memberEmail = request.getParameter("memberEmail");
			String memberPhone = request.getParameter("memberPhone");
			String departmentNo = request.getParameter("departmentNo");
			String departmentName = request.getParameter("departmentName");
			switch(departmentName) {
				case "컴퓨터소프트웨어학과" : departmentNo = "D1"; break;
				case "정보통신공학과" : departmentNo = "D2"; break;
				case "전자공학과" : departmentNo = "D3"; break;
				case "생활체육과" : departmentNo = "D4"; break;
				case "경영학과" : departmentNo = "D5"; break;
			}
			String memberLevel = request.getParameter("memberLevel");
			
//			Member member = new Member(
//										0, departmentNo, memberId, null, 
//										memberName, memberBirth, memberPhone, memberEmail, 
//										null, null, memberLevle, null
//									  );
			
			MemberExt member = new MemberExt();
			member.setMemberId(memberId);
//			member.setMemberPw(null);
			member.setMemberName(memberName);
			member.setMemberBirth(memberBirth);
			member.setMemberLevel(memberLevel);
			member.setDepartmentNo(departmentNo);
			member.setMemberPhone(memberPhone);
			member.setMemberEmail(memberEmail);
//			member.setMemberRole(null);
//			member.setMemberImg(null);
			System.out.println("member@memberEnrollServlet = " + member);
			System.out.println("member@memberUpdateServlet = " + member);
									
			// 3. 업무로직
			int result = memberService.updateMember(member);
//			String msg = "회원정보 수정!";
			
			// 4. redirect
			request.getSession().setAttribute("msg", "memberUpdate");
			response.sendRedirect(request.getContextPath() + "/member/memberMyPage");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
