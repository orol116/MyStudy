package edu.kh.comm.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.comm.member.model.service.MyPageService;
import edu.kh.comm.member.model.vo.Member;

@Controller
@RequestMapping("/member/myPage")
@SessionAttributes({"loginMember"}) // session scope에서 loginMember를 얻어옴
public class MyPageController {
	
	@Autowired
	private MyPageService service;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	// 회원 정보 조회
	@GetMapping("/info")
	public String info() {
		return "member/myPage-info";
	}
	
	// 비밀번호 변경
	@GetMapping("/changePw")
	public String changePw() {
		return "member/myPage-changePw";
	}
	
	// 회원탈퇴
	@GetMapping("/secession")
	public String secession() {
		return "member/myPage-secession";
	}
	
	// 프로필 변경
	@GetMapping("/profile")
	public String profile() {
		return "member/myPage-profile";
	}
	
	
	// 회원 정보 수정
	@PostMapping("/info")
	public String updateInfo(@ModelAttribute("loginMember") Member loginMember
							, @RequestParam Map<String, Object> paramMap // 요청 시 전달된 파라미터를 구분하지 않고 모두 Map에 담아서 얻어온다.
							, String[] updateAddress
							, RedirectAttributes ra) {
		
		// 필요한 값
		// - 닉네임
		// - 전화번호
		// - 주소 (String[]로 얻어와 String.join()을 이용해 문자열로 변경)
		// - 회원 번호 (Session -> 로그인한 회원 정보를 통해서 얻어옴)
		//   -> @SessionAttributes, @ModelAttribute 필요
		
		// @SessionAttributes
		// 1) Model에 세팅 데이터의 key와 @SessionAttributes에 작성된 key가 같으면
		//    Model에 세팅된 데이터를 request -> session scope로 이동시킨다.
		
		// 2) 기존에 session scope로 이동시킨 값을 얻어오는 역할
		//    @ModelAttribute("loginMember") Member loginMember
		//					  [session key]
		//    --> @SessionAttributes를 통해 session scope에 등록된 값을 얻어와
		//		  오른쪽에 작성된 Member loginMember 변수에 대입
		//  	  단, 클래스 위에 @SessionAttributes({"loginMember"})가 작성되어 있어야 가능하다.
		
		
		
		// *** 매개변수를 이용해서 session, 파라미터 데이터를 동시에 얻어올 때 문제점 ***
		
		// session에서 객체를 얻어오기 위해 매개변수에 작성한 상태에서
		// -> @ModelAttribute("loginMember") Member loginMember
		
		// 파라미터의 name 속성 값이 매개변수에 작성된  객체의 필드와 일치하면
		// -> name="memberNickname"
		
		// session에서 얻어온 객체의 필드에 덮어쓰기가 된다!!
		
		// [해결 방법] 파라미터의 name 속성을 변경해서 얻어오면 문제가 해결된다!!
		// (필드명이 겹쳐서 문제가 발생하니까 겹치지 않게 하면 된다.)
		
		
		// 파라미터를 저장한 paramMap에 회원번호, 주소를 추가해주기
		String memberAddress = String.join(",,", updateAddress); // 주소 배열 -> 문자열로 변환
		
		// 주소가 미입력 되었을 때
		if (memberAddress.equals(",,,,")) memberAddress = null;
		
		paramMap.put("memberNo", loginMember.getMemberNo());		
		paramMap.put("memberAddress", memberAddress);
		
		// 회원 정보 수정 서비스 호출
		int result = service.updateInfo(paramMap);
		
		String message = null;
		
		if (result > 0) {
			message = "회원 정보가 수정되었습니다.";
			
			// DB - Session의 회원 정보 동기화 (얉은 복사 활용)
			loginMember.setMemberNickname((String)paramMap.get("updateNickname"));
			loginMember.setMemberTel((String)paramMap.get("updateTel"));
			loginMember.setMemberAddress((String)paramMap.get("memberAddress"));
			
		} else {
			message = "회원 정보 수정 실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:info";
	}
	
	// 회원 비밀번호 변경
	@PostMapping("/changePw")
	public String changePw(@ModelAttribute("loginMember") Member loginMember
						 , @RequestParam Map<String, Object> pwMap
						 , RedirectAttributes ra) {
		
		pwMap.put("memberNo", loginMember.getMemberNo());
		
		int result = service.updatePw(pwMap);
		
		String message = null;
		
		if (result > 0) {
			message = "비밀번호가 변경되었습니다.";	
		} else {
			message = "현재 비밀번호가 일치하지 않습니다.";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:changePw";
	}
	
	// 회원 탈퇴
	@PostMapping("/secession")
	public String secession(@ModelAttribute("loginMember") Member loginMember
						  , RedirectAttributes ra
						  , HttpServletRequest req
						  , HttpServletResponse resp
						  , SessionStatus status) {

		int result = service.secession(loginMember);
		
		String message = null;
		
		if (result > 0) {
			message = "회원 탈퇴가 완료되었습니다.";
			ra.addFlashAttribute("message", message);
			
			status.setComplete();
			
			Cookie cookie = new Cookie("saveId", "");
			cookie.setMaxAge(0);
			cookie.setPath(req.getContextPath());
			resp.addCookie(cookie);
			
			return "redirect:/";
		} else {
			message = "현재 비밀번호가 일치하지 않습니다.";
			ra.addFlashAttribute("message", message);
			return "redirect:secession";
		}		
	}
	
	// 프로필 수정
	@PostMapping("/profile")
	public String updateProfile(@ModelAttribute("loginMember") Member loginMember
							  , @RequestParam("uploadImage") MultipartFile uploadImage // 업로드 파일
							  , @RequestParam Map<String, Object> map
							  , HttpServletRequest req // 파일 저장 경로
							  , RedirectAttributes ra) throws IllegalStateException, IOException {
		
		// 경로 작성하기
		
		// 1) 웹 접근 경로 (/comm/resources/images/memberProfile)
		String webPath = "/resources/images/memberProfile/";
		
		// 2) 서버 저장 폴더 경로
		// E:\download\eclipse-workspace\7_Framework\comm\src\main\webapp\resources\images\memberProfile
		String folderPath = req.getSession().getServletContext().getRealPath(webPath);
		
		// map에 경로, 이미지, delete 담기
		map.put("webPath", webPath);
		map.put("folderPath", folderPath);
		map.put("uploadImage", uploadImage);
		map.put("memberNo", loginMember.getMemberNo());
		
		int result = service.updateProfile(map);
		
		String message = null;
		
		if (result > 0) {
			message = "프로필 이미지가 변경되었습니다.";
			
			// DB - 세션 동기화
			
			// 서비스 호풀 시 전달된 map은 실제로 주소만 전달한것이다. (얕은 복사)
			// -> 서비스에서 추가된 "profileImage"는 원본 map에 그대로 남아있다.
			loginMember.setProfileImage((String)map.get("profileImage"));
			
		} else {
			message = "프로필 이미지 변경 실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:profile";
	}
	
	
}
