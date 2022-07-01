package edu.kh.comm.board.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.comm.board.model.vo.BoardDetail;
import edu.kh.comm.board.model.vo.BoardType;

public interface BoardService {

	/** �Խ��� �ڵ�, �̸� ��ȸ Service
	 * @return boardTypeList
	 */
	List<BoardType> selectBoardType();

	/** �Խñ� ��� ��ȸ Service
	 * @param cp
	 * @param boardCode
	 * @return map
	 */
	Map<String, Object> selectBoardList(int cp, int boardCode);

	/** �Խñ� �� ��ȸ Service
	 * @param boardNo
	 * @return detail
	 */
	BoardDetail selectBoardDetail(int boardNo);

	/** ��ȸ �� ���� Service
	 * @param boardNo
	 * @return result
	 */
	int updateReadCount(int boardNo);

	/** �Խñ� ���� + �̹��� ���� Service
	 * @param detail
	 * @param imageList
	 * @param webPath
	 * @param folderPath
	 * @return boardNo
	 */
	int insertBoard(BoardDetail detail, List<MultipartFile> imageList, String webPath, String folderPath) throws IOException;

}