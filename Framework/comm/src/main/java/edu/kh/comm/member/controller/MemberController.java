package edu.kh.comm.member.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import edu.kh.comm.member.model.service.MemberService;
import edu.kh.comm.member.model.service.MemberServiceImpl;
import edu.kh.comm.member.model.vo.Member;

// POJO ��� �����ӿ�ũ : �ܺ� ���̺귯�� ��� X

// class : ��ü�� ��������� ���赵
// 		   -> ��ü�� ������ �Ǿ�� ��� ������ �����ϴ�.
// -> IOC(������ ����, ��ü �����ֱ⸦ �������� ����)�� �̿��Ͽ� ��ü�� �����Ѵ�.
//    ** �̶�, �������� ������ ��ü�� [bean] �̶�� �Ѵ�. **

// bean ��� == "�������� ��ü�� ���� ������ �־��" �� �ǹ�
//@Component // �ش� Ŭ������ bean���� ����϶�� ���α׷����� �˷��ִ� �ּ�(Annotation)

@Controller // ������ bean�� Controller���� ���� + bean ���
@RequestMapping("/member") // localhost:8080/comm/member ������ ��û�� ó���ϴ� ��Ʈ�ѷ�
@SessionAttributes({"loginMember", "message"}) // Model�� �߰��� ���� key�� ������̼ǿ� �ۼ��� ���� ������
									// �ش� ���� session scope�� �̵���Ű�� ������ �Ѵ�.
public class MemberController {
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired // bean���� ��ϵ� ��ü �� Ÿ���� ���ų�, ��� ������ bean�� �������ִ� ����
	private MemberService service; // -> ������ ����(DI, Dependency Injection)
	
	
	// Controller : ��û/������ �����ϴ� ������ �ϴ� Ŭ����
	
	/* @RequestMapping : Ŭ���̾�Ʈ ��û(url)�� �´� Ŭ���� or �޼��带 ��������ִ� ������̼�
	 * 
	 * [��ġ�� ���� �ؼ�]
	 * - Ŭ���� ���� : ���� �ּ�(����Ʈ ��Ʈ�ѷ� ���� ����)�� ����
	 * - �޼��� ���� : ���� �ּ� �� ������ �ּ�
	 * 
	 * ��, Ŭ���������� @RequestMapping�� �������� �ʴ´ٸ�
	 *     �޼��� ������ �ؼ��� ���Ѵ�.
	 *     => �޼��� ���� : �ܵ� ��û ó�� �ּ�
	 *     
	 * [�ۼ����� ���� �ؼ�]
	 * 1) @RequestMapping("url")
	 *    -> ��û ���(GET/POST) ���� ���� url�� ��ġ�ϴ� ��û ó��
	 * 
	 * 2) @RequestMapping(value = "url", method = RequestMethod.GET | POST)
	 *    -> ��û ��Ŀ� ���� ��û ó��
	 *    
	 * ** �޼��� �������� GET/POST ����� �����Ͽ� ������ ��� **
	 * 
	 * GetMapping("url") / @PostMapping("url") ����ϴ� ���� �Ϲ����̴�.
	 * (�޼��� ���������� �ۼ��� �����ϴ�.)
	 */
	
	
	// Argument Resolver ��� �Ű������� �����ϰ� ó�����ִ� �ذ�簡 �������� ����Ǿ� �ִ�!
	
	// ������ ���� ���� �ּ�
	// https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments
	
	// �α���
	/*
	// ��û �� �Ķ���͸� ������ ��� 1
	// -> HttpServletRequest �̿�
	@RequestMapping("/login")
	public String login(HttpServletRequest req) {
		logger.info("�α��� ��û��");
		
		String inputEmail = req.getParameter("inputEmail");
		String inputPw = req.getParameter("inputPw");
		
		logger.debug("inputEmail : " + inputEmail); 
		logger.debug("inputPw : " + inputPw); 
		
		return "redirect:/";
	}
	*/
	
	// ��û �� �Ķ���͸� ������ ��� 2
	// => @RequestParam ������̼� ���
	
	// @RequestParam("name�Ӽ���") �ڷ��� ������
	// - Ŭ���̾�Ʈ ��û �� ���� ���޵� �Ķ���͸� ������ ����
	// -> � �Ķ���͸� ������ ���������� "name�Ӽ���"�� �̿��� �����Ѵ�.
	
	// �Ű����� ���� �� ������ Ÿ�� �Ľ��� �����Ӱ� ������ �� �ִ�.
	// ex) String -> int�� ��ȯ
	
	// [�Ӽ�]
	// value : input �±��� name �Ӽ� �� (default == �Ӽ��� �ϳ��� ���� ���� ����� �⺻ ��)
	//		   @RequestParam("inputEmail") == @RequestParam(value="inputEmail")
	
	// required : �Էµ� name �Ӽ� ���� �ʼ������� �Ķ���Ϳ� ���ԵǾ�� �Ǵ����� ����
	//			  required=true / required=false (�⺻�� true)
	
	// 400 - �߸��� ��û(Bad Request) : �Ķ���Ͱ� �������� �ʾ� ��û�� �߸���.
	// required = false�� �� �Ķ���Ͱ� ������ null�̴�.
	
	// defaultValue : required�� false�� ���¿��� �Ķ���Ͱ� �������� ���� ����� ���� ����
	
	// ** @RequestParam�� ���������� �Ķ���͸� ������ ��� **
	// -> name �Ӽ� ����
	//    �Ķ���͸� ������ ���� �̸��� �����ϰ� �ۼ��Ѵ�!!
	
	
//	@RequestMapping("/login")
//	public String login(/*@RequestParam("inputEmail")*/ int inputEmail,
//						/*@RequestParam("inputPw")*/ String inputPw,
//						@RequestParam(value="inputName", required = false, defaultValue = "ȫ�浿") String name) {
//		
//		logger.debug("email : " + inputEmail);
//		logger.debug("pw    : " + inputPw);
//		logger.debug("name  : " + name);
//		
//		// email�� ���ڸ� �Է� �޴´ٰ� ����
//		logger.debug(inputEmail + 100 + "");
//		
//		return "redirect:/";
//	}
	
	// ��û �� �Ķ���͸� ������ ��� 3
	// -> @ModelAttribute ������̼� ���
	
	// [@ModelAttribute�� �Ű������� �ۼ��ϴ� ���]
	
	// @ModelAttribute VOŸ�� ������ 
	// -> �Ķ���� �� name �Ӽ� ���� VO�� �ʵ�� ��ġ�ϸ� 
	//    �ش� VO ��ü�� �ʵ忡 ���� ����
	
	// *** @ModelAttribute�� �̿��ؼ� ��ü�� ���� ���� ��� ��쿡 ���� ���ǻ��� ***
	// �ݵ�� �ʿ��� ����!
	// - VO �⺻ ������
	// - VO �ʵ忡 ���� Setter
	
	// Getter�� JSP - EL ��� �� �ݵ�� �ʿ��ϴ�!!
	
	// �Ʒ� 2���� ������̼��� ���� �ǹ��̴�.
	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	@PostMapping("/login")
	public String login(/*@ModelAttribute*/ Member inputMember
						, Model model
						, RedirectAttributes ra
						, HttpServletResponse resp
						, HttpServletRequest req
						, @RequestParam(value = "saveId", required = false) String saveId) {
		
		// @ModelAttribute ���� ����
		// -> Ŀ�ǵ� ��ü (@ModelAttribute�� ������ ���¿��� �Ķ���Ͱ� �ʵ忡 ���õ� ��ü)
		logger.info("�α��� ��� �����");
		
		// ���̵�, ��й�ȣ�� ��ġ�ϴ� ȸ�� ������ ��ȸ�ϴ� Service ȣ�� �� ��� ��ȯ �ޱ�
		Member loginMember = service.login(inputMember);
		
		/* Model : �����͸� �� ����(K:V) ���·� ��� �����ϴ� �뵵�� ��ü
		 * -> request, session�� ��ü�ϴ� ��ü
		 * 
		 * - �⺻ scope : request
		 * - session scope�� ��ȯ�ϰ� ���� ���
		 *   Ŭ���� ������ @SessionAttributes�� �ۼ��ϸ� �ȴ�.
		 */
		
		// @SessionAttributes ���ۼ� -> request scope		
		if (loginMember != null) { // �α��� ���� ��
			model.addAttribute("loginMember", loginMember); // == req.setAttribute("loginMember", loginMember);
			
			// �α��� ���� �� ������ ��Ű ����
			// ��, ���̵� ���� üũ ���ο� ���� ��Ű�� ���� �ð��� ����
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			if (saveId != null) { // ���̵� ������ üũ�Ǿ��� ��
				cookie.setMaxAge(60 * 60 * 24 * 365); // �� ������ ���� (1��)
			} else { // üũ���� �ʾ��� ��
				cookie.setMaxAge(0); // 0�� -> �������ڸ��� ����� == ��Ű ����
			}
			
			// ��Ű�� ����� ����(���) ����
			cookie.setPath(req.getContextPath());
			
			// ��Ű�� ���� �� Ŭ���̾�Ʈ���� ����
			resp.addCookie(cookie);
			
		} else {
			ra.addFlashAttribute("message", "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			// -> redirect�� ��� Session scope�� �̵� ��
			//    redirect�� �Ϸ�Ǹ� �ٽ� Request scope�� �̵��Ѵ�.
			
			// redirect �ÿ��� request scope�� ���õ� �����Ͱ� ������ �� �ֵ��� �ϴ� �����
			// Spring���� �������ش�.
			// -> RedirectAttributes ��ü (��Ʈ�ѷ� �Ű������� �ۼ��ϸ� ��� �����ϴ�.)
		}

		return "redirect:/";
	}
	
	// �α׾ƿ�
	@GetMapping("/logout")
	public String logout(HttpSession session, SessionStatus status) {
		
		// �α׾ƿ� == ������ ���ִ� ��
		
		// * @SessionAttributes�� �̿��ؼ� session scope�� ��ġ�� �����ʹ�
		//   SessionStatus ��� ���� ��ü�� �̿��ؾ߸� ���� �� �ִ�.
		logger.info("�α׾ƿ� �����");
		
		// session.invalidate(); // ���� ���� ��ȿȭ ������δ� �ȵȴ�!
		status.setComplete(); // ������ �� ���� �Ϸ�� -> ����
		
		return "redirect:/";
	}
	
	// ȸ�� ���� ȭ�� ��ȯ
	@GetMapping("/signUp")
	public String signUp() {
		return "member/signUp";
	}
	
	// �̸��� �ߺ� �˻�
	@ResponseBody // ajax ���� �� ����Ѵ�!
	@GetMapping("/emailDupCheck")
	public int emailDupCheck(/* @RequestParam("memberEmail") */ String memberEmail) {
		// ��Ʈ�ѷ����� ��ȯ�Ǵ� ���� forward �Ǵ� redirect�� ���� ����� ��찡 �Ϲ����̴�.
		// -> ��ȯ�Ǵ� ���� ��η� �νĵȴ�.
		
		// -> �̸� �ذ��ϱ� ����(�ν��� ���������ִ�) ������̼� @ResponseBody�� �����Ѵ�.
		
		// @ResponseBody : ��ȯ�Ǵ� ���� ������ ����(body)�� �߰��Ͽ�
		// 				   ���� ��û �ּҷ� ���ư���.
		// -> ��Ʈ�ѷ����� ��ȯ�Ǵ� ���� ��ΰ� �ƴ� "�� ��ü"�� �νĵȴ�.
		return service.emailDupCheck(memberEmail);
	}
	
	// �г��� �ߺ� �˻�
	@ResponseBody
	@GetMapping("/nicknameDupCheck") 
	public int nicknameDupCheck(String memberNickname) {
		return service.nicknameDupCheck(memberNickname);
	}
	
	// ȸ�� ����
	@PostMapping("/signUp")
	public String signUp(Member inputMember
						, String[] memberAddress
						, RedirectAttributes ra) {
					
		// Ŀ�ǵ� ��ü�� �̿��ؼ� �Էµ� ȸ�� ������ �� �޾ƿ´�.
		// ��, ���� name�� ���� �ּҰ� �ϳ��� ���ڿ��� ���ļ� ���õǾ��ִ�.
		// -> ���θ� �ּҿ� " , " ��ȣ�� ���ԵǴ� ��찡 �־� �̸� �����ڷ� ����� �� ����.
		
		// String[] memberAddress 
		// -> name�� memberAddress�� �Ķ������ ���� ��� �迭�� ��Ƽ� ��ȯ�Ѵ�.
		
		inputMember.setMemberAddress(String.join(",,", memberAddress)); 
		// String.join("������", �迭)
		// �迭�� �ϳ��� ���ڿ��� ��ġ�� �޼���
		// �߰��� ���� �����ڸ� ������ �� �ִ�.
		
		if (inputMember.getMemberAddress().equals(",,,,")) { // �ּҰ� �Էµ��� ���� ���
			inputMember.setMemberAddress(null);
		}
		
		// ȸ�� ���� ���� ȣ��
		int result = service.signUp(inputMember);
		
		String message = null;
		String path = null;
		
		if (result > 0) { // ȸ�� ���� ����
			message = "ȸ�� ���� ����";
			path = "redirect:/"; // ����������
		} else { // ȸ�� ���� ����
			message = "ȸ�� ���� ����";
			path = "redirect:/signUp"; // ȸ�� ���� ������
		}
		
		ra.addFlashAttribute("message", message);
		
		return path;
	}
	
	
	// ȸ�� 1�� ���� ��ȸ(ajax)
	@ResponseBody // ��ȯ�Ǵ� ���� forward/redirect ��ΰ� �ƴ� �� ��ü���� �ν�
	@PostMapping("/selectOne")
	public String selectOne(String memberEmail) {
		
		Member member = service.selectOne(memberEmail);
		
		// JSON : �ڹٽ�ũ��Ʈ ��ü ǥ������� �ۼ��� ���ڿ�(String)
		
		// GSON ���̺귯�� : JSON�� ���� �ٷ�� ���� Google���� �����ϴ� ���̺귯��
		
		// Gson().toJson(object) : ��ü�� JSON ���·� ��ȯ
		return new Gson().toJson(member);
	}
	
	
	// ȸ�� ��� ��ȸ(ajax)
	@ResponseBody
	@GetMapping("/selectAll")
	public String selectAll() {
		
		List<Member> memberList = service.selectAll();
		
		return new Gson().toJson(memberList);
	}
	
	
	/* Spring ���� ó�� ��� (3����, �ߺ� ��� ����)
	 * 
	 * 1 ���� : �޼��� ���� ����ó���ϱ� (try - catch / throws)
	 * 
	 * 2 ���� : �ϳ��� ��Ʈ�ѷ����� �߻��ϴ� ���ܸ� ��Ƽ� ó���ϱ� 
	 * 			-> @ExceptionHandler (�޼��忡 �ۼ�)
	 * 
	 * 3 ���� : ����(�� ���ø����̼�)���� �߻��ϴ� ���ܸ� ��Ƽ� ó��
	 * 			-> @ControllerAdvice (���� ���� ����ó���� Ŭ������ �ۼ�)
	 */
	
	// ȸ�� ��Ʈ�ѷ����� �߻��ϴ� ��� ���ܸ� ��Ƽ� ó���ϱ�
//	@ExceptionHandler(Exception.class)
//	public String exceptionHandler(Exception e, Model model) {
//		e.printStackTrace();
//		
//		model.addAttribute("errorMessage", "���� �̿� �� ������ �߻��߽��ϴ�.");
//		model.addAttribute("e", e);
//		
//		return "common/error";
//	}
}