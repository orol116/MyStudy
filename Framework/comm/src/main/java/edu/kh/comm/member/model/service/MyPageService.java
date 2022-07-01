package edu.kh.comm.member.model.service;

import java.io.IOException;
import java.util.Map;

import edu.kh.comm.member.model.vo.Member;

public interface MyPageService {

	/** ȸ�� ���� ���� ����
	 * @param paramMap
	 * @return result
	 */
	int updateInfo(Map<String, Object> paramMap);

	/** ��й�ȣ ���� Service
	 * @param pMap
	 * @return result
	 */
	int updatePw(Map<String, Object> pwMap);

	/** ȸ�� Ż�� Service
	 * @param scsMap
	 * @return result
	 */
	int secession(Member loginMember);

	/** ������ ���� Service
	 * @param map
	 * @return result
	 */
	int updateProfile(Map<String, Object> map) throws IllegalStateException, IOException;

}