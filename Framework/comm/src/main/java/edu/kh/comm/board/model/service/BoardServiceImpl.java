package edu.kh.comm.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.comm.board.model.dao.BoardDAO;
import edu.kh.comm.board.model.exception.InsertFailException;
import edu.kh.comm.board.model.vo.Board;
import edu.kh.comm.board.model.vo.BoardDetail;
import edu.kh.comm.board.model.vo.BoardImage;
import edu.kh.comm.board.model.vo.BoardType;
import edu.kh.comm.board.model.vo.Pagination;
import edu.kh.comm.common.Util;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;

	// �Խ��� �ڵ�, �̸� ��ȸ Service
	@Override
	public List<BoardType> selectBoardType() {
		return dao.selectBoardType();
	}

	// �Խñ� ��� ��ȸ Service
	@Override
	public Map<String, Object> selectBoardList(int cp, int boardCode) {
		
		// 1) �Խ��� �̸� ��ȸ -> ���ͼ��� application�� �÷��� boardTypeList �� �� ������?
		
		// 2) ���������̼� ��ü ����(listCount)
		int listCount = dao.getListCount(boardCode);
		Pagination pagination = new Pagination(cp, listCount);
		
		// 3) �Խñ� ��� ��ȸ
		List<Board> boardList = dao.selectBoardList(pagination, boardCode);
		
		// map����� ���
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		return map;
	}

	// �Խñ� �� ��ȸ Service
	@Override
	public BoardDetail selectBoardDetail(int boardNo) {
		return dao.selectBoardDetail(boardNo);
	}

	// ��ȸ �� ���� Service
	@Override
	public int updateReadCount(int boardNo) {
		return dao.updateReadCount(boardNo);
	}

	// �Խñ� ���� + �̹��� ���� Service
	
	// Spring���� Ʈ����� ó���ϴ� ���
	
	// * AOP(���� ���� ���α׷���)�� �̿��ؼ� DAO -> Service �Ǵ� Service �ڵ� ���� �������� 
	//   ���ܰ� �߻��ϸ� rollback�� ����
	
	// ��� 1) <tx:advice> XML�� �̿��� ��� -> ������ �����Ͽ� ��ġ�ϴ� �޼��� ȣ�� �� �ڵ����� Ʈ������� �����Ѵ�.
	
	// ��� 2) @Transactional ������ Ʈ����� ó�� ���
	//         -> RuntimeException (Unchecked Exception) ó���� �⺻������ ���´�.
	
	// checked Exception   : ���� ó���� �ʼ� (transferTo())    -> SQL ���� ����, ���� ���ε� ���� ����
	// Unchecked Exception : ���� ó���� ���� (int a = 10 / 0;)
	
	// rollbackFor : rollback�� �����ϱ� ���� ������ ������ �ۼ��Ѵ�.
	
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public int insertBoard(BoardDetail detail, List<MultipartFile> imageList, String webPath, String folderPath) throws IOException {
		
		// 1. �Խñ� ����
		
		// 1) XSS ���� ó�� + ���๮�� ó��
		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		detail.setBoardContent(Util.XSSHandling(detail.getBoardContent()));
		detail.setBoardContent(Util.newLineHandling(detail.getBoardContent()));
		
		// 2) �Խñ� ���� DAO ȣ�� �� �Խñ� ��ȣ ��ȯ �ޱ�
		
		/* �Խñ� ��ȣ�� ���� ���� �����ߴ� ����
		 * 
		 * 1. ���� ��� ��ȯ �� ��Ʈ�ѷ����� ����ȸ�� �����̷�Ʈ�ϱ� ����
		 * 2. ���� ������ �ð��� ������ 2ȸ �̻� ����� ��� ������ ��ȣ��
		 *    �ǵ��� �޸� ������ �����ؼ� ���Ŀ� �ۼ��� �̹��� ���� �ڵ忡 ������ ��ġ�°� �����ϱ� ���ؼ�
		 */
		
		int boardNo = dao.insertBoard(detail);
		
		if (boardNo > 0) {
			// �̹��� ����
			
			// imageList      : ���� ������ ����ִ� ����Ʈ
			// boardImageList : DB�� ������ �̹��� ������ ����ִ� ����Ʈ
			// reNameList     : ����� ���ϸ��� ����ִ� ����Ʈ 

			List<BoardImage> boardImageList = new ArrayList<BoardImage>();
			List<String> reNameList = new ArrayList<String>();
			
			// imageList�� ����ִ� ���� ���� �� ���� ���ε�� ���ϸ� �з��ϴ� �۾�
			for (int i = 0; i < imageList.size(); i++) {
				if (imageList.get(i).getSize() > 0) { // i��° ��ҿ� ���ε�� �̹����� ���� ���
					
					// ����� ���ϸ� ����
					String reName = Util.fileRename(imageList.get(i).getOriginalFilename());
					reNameList.add(reName);
					
					// BoardImage ��ü�� �����Ͽ� �� ���� �� boardImageList�� �߰�
					BoardImage img = new BoardImage();
					img.setBoardNo(boardNo); // �Խñ� ��ȣ
					img.setImageLevel(i); // �̹��� ����(���� ����)
					img.setImageOriginal(imageList.get(i).getOriginalFilename()); // ���� ���ϸ�
					img.setImageReName(webPath + reName); // �� ���� ��� + ����� ���ϸ�
					
					boardImageList.add(img);
				}
			}
			
			// �з� �۾� ���� �� boardImageList�� ������� ���� ��� == ������ ���ε尡 �� ���
			if (!boardImageList.isEmpty()) {
				int result = dao.insertBoardImageList(boardImageList);
				
				// result == ���� ������ ���� ����
				if (result == boardImageList.size()) { // ���Ե� ���� ������ ���ε� �̹��� ���� ���� ���
					
					// ������ �̹��� ����
					for (int i = 0; i < boardImageList.size(); i++) {
						
						int index = boardImageList.get(i).getImageLevel();
						imageList.get(index).transferTo(new File(folderPath + reNameList.get(i)));
					}
				} else { // �̹��� ���� ���� ��
					
					// ������ ���ܸ� �߻����� rollback�� �����ϰ� ��
					// -> ����ڰ� ������ ����
					throw new InsertFailException();
				}
			}
			
		}
		
		return boardNo;
	}
	
	
	
	

}