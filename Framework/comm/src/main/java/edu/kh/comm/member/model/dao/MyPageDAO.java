package edu.kh.comm.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyPageDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	// �Ķ���Ͱ� Map�� ��� -> Mapper���� ��� �� #{key}�� �ۼ��ϸ�
	// key�� �����Ǵ� value�� ����� �ȴ�.
	// ex #{updateTel} => '01099999999'
	
	/** ȸ�� ���� ���� DAO
	 * @param paramMap
	 * @return result
	 */
	public int updateInfo(Map<String, Object> paramMap) {
		return sqlSession.update("myPageMapper.updateInfo", paramMap);
	}

	/** ���� ��й�ȣ ��ȸ DAO
	 * @param pwMap
	 * @return currentPw
	 */
	public String selectCurrentPw(int memberNo) {
		return sqlSession.selectOne("myPageMapper.selectCurrentPw", memberNo);
	}

	/** ��й�ȣ ���� DAO
	 * @param pwMap 
	 * @return result
	 */
	public int updatePw(Map<String, Object> pwMap) {
		return sqlSession.update("myPageMapper.updatePw", pwMap);
	}

	/** ȸ�� Ż�� DAO
	 * @param scsMap
	 * @return result
	 */
	public int secession(int memberNo) {
		return sqlSession.update("myPageMapper.secession", memberNo);
	}

	/** ������ �̹��� ����
	 * @param map
	 * @return result
	 */
	public int updateProfile(Map<String, Object> map) {
		return sqlSession.update("myPageMapper.updateProfile", map);
	}

}