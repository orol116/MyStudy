package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/myPage/secession")
public class MyPageSecessionServlet extends HttpServlet{
	
	// 회원 탈퇴 페이지로 전환
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/member/myPage-secession.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memberPw = req.getParameter("memberPw");
		
		HttpSession session = req.getSession();
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		int memberNo = loginMember.getMemberNo();
		
		try {
			
			MemberService service = new MemberService();
			
			int result = service.secession(memberNo, memberPw);
			
			String path = null;
			if (result > 0) {
				
				// 로그아웃 방법 1
//				path = req.getContextPath() + "/member/logout"; 로그아웃 요청으로 리다이렉트
				
				// 로그아웃 방법 2
				session.invalidate(); // 세션 무효화
				// -> 세션을 무효화 해버려서 메세지 전달이 되지 않는 문제가 발생
				
				// [해결방법]
				// 새로운 세션을 얻어와서 메세지 세팅
				
				session = req.getSession(); // 무효화 후 새로 생성된 세션 얻어오기
				session.setAttribute("message", "탈퇴 되었습니다.");
				path = req.getContextPath();
				
				Cookie c = new Cookie("saveId", ""); // 쿠키 생성
				c.setMaxAge(0);						 // 쿠키 수명
				c.setPath(req.getContextPath());     // 쿠키 적용 경로
				resp.addCookie(c);					 // 쿠키 클라이언트에 전송
				
			} else {
				session.setAttribute("message", "비밀번호가 일치하지 않습니다.");
				path = "secession";
			}
			
			resp.sendRedirect(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
