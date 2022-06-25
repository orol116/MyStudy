package edu.kh.comm.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyPageDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	// 파라미터가 Map인 경우 -> Mapper에서 사용 시 #{key}를 작성하면
	// key에 대응되는 value가 출력이 된다.
	// ex #{updateTel} => '01099999999'
	
	/** 회원 정보 수정 DAO
	 * @param paramMap
	 * @return result
	 */
	public int updateInfo(Map<String, Object> paramMap) {
		return sqlSession.update("myPageMapper.updateInfo", paramMap);
	}

	/** 현재 비밀번호 조회 DAO
	 * @param pwMap
	 * @return currentPw
	 */
	public String selectCurrentPw(int memberNo) {
		return sqlSession.selectOne("myPageMapper.selectCurrentPw", memberNo);
	}

	/** 비밀번호 변경 DAO
	 * @param pwMap 
	 * @return result
	 */
	public int updatePw(Map<String, Object> pwMap) {
		return sqlSession.update("myPageMapper.updatePw", pwMap);
	}

	/** 회원 탈퇴 DAO
	 * @param scsMap
	 * @return result
	 */
	public int secession(int memberNo) {
		return sqlSession.update("myPageMapper.secession", memberNo);
	}

	/** 프로필 이미지 수정
	 * @param map
	 * @return result
	 */
	public int updateProfile(Map<String, Object> map) {
		return sqlSession.update("myPageMapper.updateProfile", map);
	}

}
