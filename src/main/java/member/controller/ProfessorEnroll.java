package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PasswordEncrypt;
import member.model.dto.MemberExt;
import member.model.dto.MemberRole;
import member.model.service.MemberService;

/**
 * Servlet implementation class ProfessorEnroll
 */
@WebServlet("/member/professorEnroll")
public class ProfessorEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
			.getRequestDispatcher("/WEB-INF/views/member/professorEnroll.jsp")
			.forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 인코딩 필터처리
			// 2. 사용자입력값 처리
			String memberId = request.getParameter("memberId");
			String memberPw = PasswordEncrypt.encrypt(request.getParameter("password"), memberId);
			String memberName = request.getParameter("memberName");
			
			String _memberBirth = request.getParameter("memberBirth");
			System.out.println(_memberBirth);
			Date memberBirth = null;
			if(_memberBirth != null && !"".equals(_memberBirth))
				memberBirth = Date.valueOf(_memberBirth);
			
//			String memberLevel = request.getParameter("memberLevel"); '0'
			String memberPhone = request.getParameter("memberPhone");
			String memberEmail = request.getParameter("memberEmail");
			
			String departmentName = request.getParameter("departmentName");
//			System.out.println(departmentName);
			String departmentNo = null;
			switch(departmentName) {
				case "컴퓨터소프트웨어학과" : departmentNo = "D1"; break;
				case "정보통신공학과" : departmentNo = "D2"; break;
				case "전자공학과" : departmentNo = "D3"; break;
				case "생활체육과" : departmentNo = "D4"; break;
				case "경영학과" : departmentNo = "D5"; break;
			}
			MemberExt member = new MemberExt();
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			member.setMemberName(memberName);
			member.setMemberBirth(memberBirth);
			member.setMemberLevel("0");
			member.setDepartmentNo(departmentNo);
			member.setMemberPhone(memberPhone);
			member.setMemberEmail(memberEmail);
			member.setMemberRole(MemberRole.P);
			member.setMemberImg(null);
			System.out.println("member@professorEnrollServlet = " + member);
										
			// 3. 업무로직 (db insert)
			int result = memberService.insertProfessorMember(member);
			String msg = "memberEnroll";
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
