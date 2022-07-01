package edu.kh.comm.common.interceptor;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.kh.comm.board.model.service.BoardService;
import edu.kh.comm.board.model.vo.BoardType;

public class BoardTypeInterceptor implements HandlerInterceptor {
	
	/* ���ͼ��Ͱ� ��û�� ����ä�� �ñ�
	 * 
	 * 1. preHandle  (��ó��) : ��Ʈ�ѷ� ���� ��
	 * 
	 * 2. postHandle (��ó��) : ��Ʈ�ѷ� ���� �� (��Ʈ�ѷ� ���� ��� ���� ����)
	 * 
	 * 3. afterCompletion (View�� ó�� ��) : ���� �ڿ� ��ȯ(close())�� ����
	 * 
	 * ** ��, �񵿱� ��û(�ڹ� ������ ���� ������ ��û)�� ����ä�� �ʴ´�.
	 */
	
	private Logger logger = LoggerFactory.getLogger(BoardTypeInterceptor.class);
	
	// BoardService ������ ���� �ޱ�(DI)
	@Autowired
	private BoardService boardService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// application scope�� "boardTypeList"�� ���� ���
		// �̸� ��ȸ�ϴ� Service ȣ�� �� ����� ����
		
		// application scope ��ü ������
		ServletContext application = request.getServletContext();
		
		if (application.getAttribute("boardTypeList") == null) {
			List<BoardType> boardTypeList = boardService.selectBoardType();
			
			application.setAttribute("boardTypeList", boardTypeList);
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}