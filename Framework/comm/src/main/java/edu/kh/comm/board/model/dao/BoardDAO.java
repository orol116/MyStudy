package edu.kh.comm.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.comm.board.model.vo.Board;
import edu.kh.comm.board.model.vo.BoardDetail;
import edu.kh.comm.board.model.vo.BoardImage;
import edu.kh.comm.board.model.vo.BoardType;
import edu.kh.comm.board.model.vo.Pagination;

// @Component : bean ���
@Repository// : DB�� ����ϴ� Ŭ���� ���� + bean ���
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** �Խ��� �ڵ�, �̸� ��ȸ DAO
	 * @return boardTypeList
	 */
	public List<BoardType> selectBoardType() {
		return sqlSession.selectList("boardMapper.selectBoardType");
	}

	/** Ư�� �Խ����� ��ü �Խñ� �� ��ȸ DAO
	 * @param boardCode
	 * @return listCount
	 */
	public int getListCount(int boardCode) {
		return sqlSession.selectOne("boardMapper.getListCount", boardCode);
	}

	/** �Խ��� ��� ��ȸ DAO
	 * @param pagination
	 * @param boardCode
	 * @return boardList
	 */
	public List<Board> selectBoardList(Pagination pagination, int boardCode) {
		
		// RowBounds ��ü (Mybatis���� ����)
		// - ��ü ��ȸ ������� ��� ���� �ǳ� �ٰ�(offset)
		//   �� ���� ��� �ุ(limit) ��ȸ�� ������ ����
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("boardMapper.selectBoardList", boardCode, rowBounds);
	}

	/** �Խñ� �� ��ȸ DAO
	 * @param boardNo
	 * @return detail
	 */
	public BoardDetail selectBoardDetail(int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoardDetail", boardNo);
	}

	/** ��ȸ �� ���� DAO
	 * @param boardNo
	 * @return result
	 */
	public int updateReadCount(int boardNo) {
		return sqlSession.update("boardMapper.updateReadCount", boardNo);
	}

	/** �Խñ� ���� DAO
	 * @param detail
	 * @return boardNo
	 */
	public int insertBoard(BoardDetail detail) {
		
		int result = sqlSession.insert("boardMapper.insertBoard", detail);
		
		if (result > 0) result = detail.getBoardNo();
		
		// �Խñ� ���� ���� ��
		// <selectKey> �±׸� �̿��� ���õ� boardNo ���� ��ȯ�Ѵ�. -> �Խñ� ��ȣ ��� ����
		
		return result;
	}

	/** �Խñ� �̹��� ���� DAO
	 * @param boardImageList
	 * @return result
	 */
	public int insertBoardImageList(List<BoardImage> boardImageList) {
		return sqlSession.insert("boardMapper.insertBoardImageList", boardImageList);
	}

}