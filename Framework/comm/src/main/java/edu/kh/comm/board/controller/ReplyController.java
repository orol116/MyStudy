package edu.kh.comm.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import edu.kh.comm.board.model.service.ReplyService;
import edu.kh.comm.board.model.vo.Reply;

/* Rest(Representational State Transfer)
 * - �ڿ��� �̸����� ����(Representational, �ڿ��� ǥ��)�Ͽ� 
 *   �ڿ��� ����(State)�� �ְ� �޴� ��(Transfer)�̴�. 
 *   
 *   => Ư���� �̸�(�ּ�)�� ��û�� ���� ������ ����
 *   
 * 
 * RestController : ��û�� ���� ������ ��� ������(��) ��ü�� ��Ʈ�ѷ�
 * -> @Controller + ResponseBody
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	// ��� ��� ��ȸ
	@GetMapping("/selectReplyList")
	public String selectReplyList(int boardNo) {
		List<Reply> rList = service.selectReplyList(boardNo);
		return new Gson().toJson(rList);
	}
	
	// ��� ����
	@PostMapping("/insert")
	public int insertReply(Reply reply) {
		return service.insertReply(reply);
	}
	
	// ��� ����
	@GetMapping("/delete")
	public int deleteReply(int replyNo) {
		return service.deleteReply(replyNo);
	}
	
	// ��� ����
	@PostMapping("/update")
	public int updateReply(Reply reply) {
		return service.updateReply(reply);
	}
}