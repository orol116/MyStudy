package edu.kh.comm.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // ��Ʈ�ѷ����� ���� + bean ��� (������Ʈ ��ĵ �� bean���� ������)
public class MainController {
	
	@RequestMapping("/main")
	public String mainForward() {
		
		// index.jsp�� forward�� ó���ϴ� mainForward()����
		// �ٽ� �ѹ� common/main.jsp�� forward
		return "common/main";
	}

}