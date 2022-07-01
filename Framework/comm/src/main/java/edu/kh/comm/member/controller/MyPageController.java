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
@SessionAttributes({"loginMember"}) // session scope���� loginMember�� ����
public class MyPageController {
	
	@Autowired
	private MyPageService service;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	// ȸ�� ���� ��ȸ
	@GetMapping("/info")
	public String info() {
		return "member/myPage-info";
	}
	
	// ��й�ȣ ����
	@GetMapping("/changePw")
	public String changePw() {
		return "member/myPage-changePw";
	}
	
	// ȸ��Ż��
	@GetMapping("/secession")
	public String secession() {
		return "member/myPage-secession";
	}
	
	// ������ ����
	@GetMapping("/profile")
	public String profile() {
		return "member/myPage-profile";
	}
	
	
	// ȸ�� ���� ����
	@PostMapping("/info")
	public String updateInfo(@ModelAttribute("loginMember") Member loginMember
							, @RequestParam Map<String, Object> paramMap // ��û �� ���޵� �Ķ���͸� �������� �ʰ� ��� Map�� ��Ƽ� ���´�.
							, String[] updateAddress
							, RedirectAttributes ra) {
		
		// �ʿ��� ��
		// - �г���
		// - ��ȭ��ȣ
		// - �ּ� (String[]�� ���� String.join()�� �̿��� ���ڿ��� ����)
		// - ȸ�� ��ȣ (Session -> �α����� ȸ�� ������ ���ؼ� ����)
		//   -> @SessionAttributes, @ModelAttribute �ʿ�
		
		// @SessionAttributes
		// 1) Model�� ���� �������� key�� @SessionAttributes�� �ۼ��� key�� ������
		//    Model�� ���õ� �����͸� request -> session scope�� �̵���Ų��.
		
		// 2) ������ session scope�� �̵���Ų ���� ������ ����
		//    @ModelAttribute("loginMember") Member loginMember
		//					  [session key]
		//    --> @SessionAttributes�� ���� session scope�� ��ϵ� ���� ����
		//		  �����ʿ� �ۼ��� Member loginMember ������ ����
		//  	  ��, Ŭ���� ���� @SessionAttributes({"loginMember"})�� �ۼ��Ǿ� �־�� �����ϴ�.
		
		
		
		// *** �Ű������� �̿��ؼ� session, �Ķ���� �����͸� ���ÿ� ���� �� ������ ***
		
		// session���� ��ü�� ������ ���� �Ű������� �ۼ��� ���¿���
		// -> @ModelAttribute("loginMember") Member loginMember
		
		// �Ķ������ name �Ӽ� ���� �Ű������� �ۼ���  ��ü�� �ʵ�� ��ġ�ϸ�
		// -> name="memberNickname"
		
		// session���� ���� ��ü�� �ʵ忡 ����Ⱑ �ȴ�!!
		
		// [�ذ� ���] �Ķ������ name �Ӽ��� �����ؼ� ������ ������ �ذ�ȴ�!!
		// (�ʵ���� ���ļ� ������ �߻��ϴϱ� ��ġ�� �ʰ� �ϸ� �ȴ�.)
		
		
		// �Ķ���͸� ������ paramMap�� ȸ����ȣ, �ּҸ� �߰����ֱ�
		String memberAddress = String.join(",,", updateAddress); // �ּ� �迭 -> ���ڿ��� ��ȯ
		
		// �ּҰ� ���Է� �Ǿ��� ��
		if (memberAddress.equals(",,,,")) memberAddress = null;
		
		paramMap.put("memberNo", loginMember.getMemberNo());		
		paramMap.put("memberAddress", memberAddress);
		
		// ȸ�� ���� ���� ���� ȣ��
		int result = service.updateInfo(paramMap);
		
		String message = null;
		
		if (result > 0) {
			message = "ȸ�� ������ �����Ǿ����ϴ�.";
			
			// DB - Session�� ȸ�� ���� ����ȭ (���� ���� Ȱ��)
			loginMember.setMemberNickname((String)paramMap.get("updateNickname"));
			loginMember.setMemberTel((String)paramMap.get("updateTel"));
			loginMember.setMemberAddress((String)paramMap.get("memberAddress"));
			
		} else {
			message = "ȸ�� ���� ���� ����";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:info";
	}
	
	// ȸ�� ��й�ȣ ����
	@PostMapping("/changePw")
	public String changePw(@ModelAttribute("loginMember") Member loginMember
						 , @RequestParam Map<String, Object> pwMap
						 , RedirectAttributes ra) {
		
		pwMap.put("memberNo", loginMember.getMemberNo());
		
		int result = service.updatePw(pwMap);
		
		String message = null;
		
		if (result > 0) {
			message = "��й�ȣ�� ����Ǿ����ϴ�.";	
		} else {
			message = "���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:changePw";
	}
	
	// ȸ�� Ż��
	@PostMapping("/secession")
	public String secession(@ModelAttribute("loginMember") Member loginMember
						  , RedirectAttributes ra
						  , HttpServletRequest req
						  , HttpServletResponse resp
						  , SessionStatus status) {

		int result = service.secession(loginMember);
		
		String message = null;
		
		if (result > 0) {
			message = "ȸ�� Ż�� �Ϸ�Ǿ����ϴ�.";
			ra.addFlashAttribute("message", message);
			
			status.setComplete();
			
			Cookie cookie = new Cookie("saveId", "");
			cookie.setMaxAge(0);
			cookie.setPath(req.getContextPath());
			resp.addCookie(cookie);
			
			return "redirect:/";
		} else {
			message = "���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
			ra.addFlashAttribute("message", message);
			return "redirect:secession";
		}		
	}
	
	// ������ ����
	@PostMapping("/profile")
	public String updateProfile(@ModelAttribute("loginMember") Member loginMember
							  , @RequestParam("uploadImage") MultipartFile uploadImage // ���ε� ����
							  , @RequestParam Map<String, Object> map
							  , HttpServletRequest req // ���� ���� ���
							  , RedirectAttributes ra) throws IllegalStateException, IOException {
		
		// ��� �ۼ��ϱ�
		
		// 1) �� ���� ��� (/comm/resources/images/memberProfile)
		String webPath = "/resources/images/memberProfile/";
		
		// 2) ���� ���� ���� ���
		// E:\download\eclipse-workspace\7_Framework\comm\src\main\webapp\resources\images\memberProfile
		String folderPath = req.getSession().getServletContext().getRealPath(webPath);
		
		// map�� ���, �̹���, delete ���
		map.put("webPath", webPath);
		map.put("folderPath", folderPath);
		map.put("uploadImage", uploadImage);
		map.put("memberNo", loginMember.getMemberNo());
		
		int result = service.updateProfile(map);
		
		String message = null;
		
		if (result > 0) {
			message = "������ �̹����� ����Ǿ����ϴ�.";
			
			// DB - ���� ����ȭ
			
			// ���� ȣǮ �� ���޵� map�� ������ �ּҸ� �����Ѱ��̴�. (���� ����)
			// -> ���񽺿��� �߰��� "profileImage"�� ���� map�� �״�� �����ִ�.
			loginMember.setProfileImage((String)map.get("profileImage"));
			
		} else {
			message = "������ �̹��� ���� ����";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:profile";
	}
	
	
}