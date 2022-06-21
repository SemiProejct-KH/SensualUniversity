package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dto.Member;

/**
 * 로그인후 사용가능한 페이지 추가해주세요.
 */
@WebFilter({ 
	"/member/memberMyPage", 
	"/member/memberUpdate", 
	"/member/memberDelete",
	"/member/passwordUpdate",
	"/board/questionList",
	"/board/studyList",
	"/board/lastPropertyList",
	"/board/questionView",
	"/board/questionEnroll",
	"/board/questionDelete",
	"/chat/chatroom",
	"/notice/noticeList",
	"/notice/noticeEnroll",
	"/notce/noticeUpdate",
	"/notice/noticeDelete",
	"/class/presentlystudentclass",
	"/class/studentclass",
	"/student/presentlystudentrecord",
	"/student/studentrecord",
	"/enrol/enrolment"
	
})
public class LoginFilter implements Filter {

    public LoginFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request; 
		HttpServletResponse httpRes = (HttpServletResponse) response; 
		
		// 로그인여부 검사
		HttpSession session = httpReq.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if(loginMember == null) {
			session.setAttribute("msg", "로그인 후 사용가능합니다.");
			httpRes.sendRedirect(httpReq.getContextPath() + "/");
			return; // 조기리턴
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}
}