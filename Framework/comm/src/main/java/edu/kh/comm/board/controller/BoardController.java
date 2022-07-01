package edu.kh.comm.board.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.comm.board.model.service.BoardService;
import edu.kh.comm.board.model.vo.BoardDetail;
import edu.kh.comm.member.model.vo.Member;

@Controller
@RequestMapping("/board")
@SessionAttributes({"loginMember"})
public class BoardController {
	
	@Autowired
	private BoardService service;

	// �Խñ� ��� ��ȸ
	
	// @PathVariable("value") : URL ��ο� ���ԵǾ� �ִ� ���� ������ ����� �� �ְ� �ϴ� ����
	// -> �ڵ����� request scope�� ��ϵȴ�. -> jsp���� ${value}�� EL �ۼ������ϴ�.
	
	// PathVariable : ��û �ڿ��� �ĺ��ϴ� ��쿡 ����Ѵ�.
	
	// QueryString  : ����, �˻� ���� ���͸� �ɼǿ� ����Ѵ�.
	
	@GetMapping("/list/{boardCode}")
	public String boardList(@PathVariable("boardCode") int boardCode
						  , @RequestParam(value="cp", required=false, defaultValue = "1") int cp
						  , Model model) {
		
		// �Խñ� ��� ��ȸ ���� ȣ��
		// 1) �Խ��� �̸� ��ȸ -> ���ͼ��� application�� �÷��� boardTypeList �� �� ������?
		// 2) ���������̼� ��ü ����(listCount)
		// 3) �Խñ� ��� ��ȸ
		Map<String, Object> map = service.selectBoardList(cp, boardCode);
		
		model.addAttribute("map", map);
		
		return "board/boardList";
	}
	
	// �Խñ� ����ȸ
	@GetMapping("/detail/{boardCode}/{boardNo}")
	public String boardDetail(@PathVariable("boardCode") int boardCode
							, @PathVariable("boardNo") int boardNo
							, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp
							, Model model
							, HttpSession session
							, HttpServletRequest req, HttpServletResponse resp) {
		
		// �Խñ� ����ȸ ���� ȣ��
		BoardDetail detail = service.selectBoardDetail(boardNo);
		
		// ��Ű�� �̿��� ��ȸ�� �ߺ� ���� ���� �ڵ�
		// int memberNo = loginMember.getMemberNo();
		
		// @ModelAttribute("loginMember") Member loginMember (���Ұ�)
		// ��? @ModelAttribute�� ������ required �Ӽ��� ��� ������ �ʼ�!
		//     -> ���ǿ� loginMember�� ������ ���� �߻�
		
		// �ذ��� : HttpSession�� �̿�
		
		
		if (detail != null) { // �� ��ȸ ���� ��
			
			int memberNo = 0;
			Member loginMember = (Member)session.getAttribute("loginMember");
			
			if (loginMember != null) {
				memberNo = loginMember.getMemberNo();				
			}
		
			// �۾��̿� ���� Ŭ���̾�Ʈ�� ���� ���� ��� -> ��ȸ �� ����
			if (detail.getMemberNo() != memberNo) {
				
				Cookie cookie = null; // ������ �����ϴ� ��Ű�� �����ϴ� ����
				Cookie[] cArr = req.getCookies(); // ��Ű ������
				
				if (cArr != null && cArr.length > 0) { // ���� ��Ű�� ���� ���
					for (Cookie c : cArr) { // ���� ��Ű �� �̸�(key)�� "readBoardNo"�� ������ ������
						if (c.getName().equals("readBoardNo")) {
							cookie = c;
						}
					}
				}
				
				int result = 0;
				
				if (cookie == null) { // ������ "readBoardNo" �̸��� ��Ű�� ���� ���
					cookie = new Cookie("readBoardNo", boardNo + "");
					
					// ��ȸ �� ���� ���� ȣ��
					result = service.updateReadCount(boardNo); 
				} else {
					// ������ "readBoardNo" �̸��� ��Ű�� ���� ���
					// -> ��Ű�� ����� �� ���ʿ� ���� ��ȸ�� �Խñ� ��ȣ�� �߰�
					//    ��, ���� ��Ű���� �ߺ��Ǵ� ��ȣ�� ������Ѵ�.
					String[] temp = cookie.getValue().split("/"); // ���� value
					
					// "readBoardNo" / "1/2/3/4/5/8/10/100"
					
					List<String> list = Arrays.asList(temp); // �迭 -> List ��
					
					// String.indexOf("���ڿ�") 
					// - String���� "���ڿ�"�� ��ġ�ϴ� �κ��� ���� �ε����� ��ȯ
					// - ��ġ�ϴ� �κ��� ������ -1 ��ȯ
					
					// List.indexOf(Object) 
					// - List���� Obejct�� ��ġ�ϴ� �κ��� �ε����� ��ȯ
					// - ��ġ�ϴ� �κ��� ������ -1 ��ȯ
					if (list.indexOf(boardNo + "") == -1) { // ���� ���� ���� �۹�ȣ�� ���ٸ� �߰�
						cookie.setValue(cookie.getValue() + "/" + boardNo);
						result = service.updateReadCount(boardNo); 
					}
				}
				
				if (result > 0) {
					detail.setReadCount(detail.getReadCount()); // �̹� ��ȸ�� ������ DB�� ����ȭ
					
					// ��ȸ ���� ���� �� �Խñ� ��ȣ�� ����
					//cookie = new Cookie("boardNo-" + boardNo, boardNo + "");
					
					
					cookie.setPath(req.getContextPath());
					cookie.setMaxAge(60 * 60 * 1); // 1�ð�
					resp.addCookie(cookie);
				}
			}
		}
		
		model.addAttribute("detail", detail);
		return "board/boardDetail";
	}
	
	// �Խñ� �ۼ� ȭ�� ��ȯ
	// @RequestMapping(value = "/write/{boardCode}", method = RequestMethod.GET)
	@GetMapping(value = "/write/{boardCode}")
	public String boardWriteForm(@PathVariable("boardCode") int boardCode
							   , String mode) {
		
		if (mode.equals("update")) {
			// �Խñ� ����ȸ ���� ȣ��
			
		}
		
		return "board/boardWriteForm";
	}
	
	// �Խñ� �ۼ�(����/����)
	@PostMapping("/write/{boardCode}") 
	public String boardWrite(BoardDetail detail // boardTitle, boardContent
			    		   , @RequestParam(value = "images", required = false) List<MultipartFile> imageList // ���ε� ����(�̹���) ����Ʈ
			    		   , @PathVariable("boardCode") int boardCode
			    		   , String mode
			    		   , @ModelAttribute("loginMember") Member loginMember
			    		   , HttpServletRequest req
			    		   , RedirectAttributes ra) throws IOException {
		
		// 1) �α����� ȸ�� ��ȣ ������
		detail.setMemberNo(loginMember.getMemberNo());
		
		// 2) �̹��� ���� ��� (webPath, folderPath)
		String webPath = "/resources/images/board/";
		String folderPath = req.getSession().getServletContext().getRealPath(webPath);
		
		if (mode.equals("insert")) { // ����
			
			// �Խñ� �κ� ����(����, ����, ȸ����ȣ, �Խ��� �ڵ�)
			// -> ���Ե� �Խñ��� ��ȣ(boardNo) ��ȯ 
			//    ��? -> ������ ������ ����ȸ�� �����̷�Ʈ �ϱ� ���ؼ�
			
			// �Խñۿ� ���Ե� �̹��� ���� ����(0 ~ 5��, �Խñ� ��ȣ �ʿ�!)
			// -> ���� ���Ϸ� ��ȯ�ؼ� ������ ����(transfer())
			
			// �� ���� insert �� �� ���̶� �����ϸ� ��ü rollback (Ʈ����� ó��)
			
			
			/* @PathVariable() �� ���� boardCode�� �ڵ����� Ŀ�ǵ� ��ü BoardDetail�� ���õȴ�.
			 * 
			 * input type="file" �±��� name�� ���ٸ� List<MultipartFile>�� �������.
			 * 
			 * List<MultipartFile>�� ���ε�� ������ ��� name�� ���� input�±׸� ��� ���´�!
			 * -> ���ε� Ȯ�� �� List ��Ҹ� �ϳ��� ���� ���ϸ��� �ִ��� �Ǵ� ũ�Ⱑ 0�� �ƴ����� �̿��ؼ� Ȯ��
			 */
			
			int boardNo = service.insertBoard(detail, imageList, webPath, folderPath);
			
			String path = null;
			String message = null;
			
			if (boardNo > 0) {
				// /board/write/1
				// /board/detail/1/1500
				
				path = "../detail/" + boardCode + "/" + boardNo;
				message = "�Խñ��� ��ϵǾ����ϴ�";
			} else {
				path = req.getHeader("referer");
				message = "�Խñ� ���� ����";
			}
			
			ra.addFlashAttribute("message", message);
			
			return "redirect:" + path;
			
		} else { // ����
			
		}
		
		return null;
	}
}