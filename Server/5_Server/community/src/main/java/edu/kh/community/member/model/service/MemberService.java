package edu.kh.community.member.model.service;

import static edu.kh.community.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.community.member.model.dao.MemberDAO;
import edu.kh.community.member.model.vo.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	/** 로그인 서비스
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Member member) throws Exception {

		// Connection 얻어오기
		Connection conn = getConnection();
		
		// DAO 수행
		Member loginMember = dao.login(conn, member);
		
		// Connection 반환
		close(conn);
		
		// 결과 반환
		return loginMember;
	}

	/** 회원가입 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception {
		
		// 1) 커넥션 얻어오기
		Connection conn = getConnection(); // DBCP에서 얻어옴
		
		// 2) DAO 메서드 호출 후 결과 반환 받기
		int result = dao.signUp(conn, member);
		
		// 3) 트랜잭션 처리
		//    result가 0인 경우 -> DAO return 구문을 잘못 작성한 것이다.
		if (result > 0) commit(conn);
		else			rollback(conn);
		
		// 4) conn 반환(DBCP로 돌려주기)
		close(conn);
		
		return result;
	}

	/** 회원 정보 수정 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member member) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, member);
		
		if (result > 0) commit(conn);
		else   			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 회원 비밀번호 수정 Service
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updateMemberPw(String currentPw, String newPw, int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateMemberPw(conn, currentPw, newPw, memberNo);
		
		if (result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);	
		
		return result;
	}

	/** 회원 탈퇴 Service
	 * @param memberNo
	 * @param memberPw
	 * @return result
	 * @throws Exception
	 */
	public int secession(int memberNo, String memberPw) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.secession(conn, memberNo, memberPw);
		
		if (result > 0) commit(conn);
		else 			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 이메일 중복 검사 Service
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public int emailDupCheck(String memberEmail) throws Exception {

		Connection conn = getConnection(); // DBCP에서 만들어준 커넥션 얻어오기
		
		int result = dao.emailDupCheck(conn, memberEmail);
		
		close(conn);
		
		return result;
	}


	/** 닉네임 중복 검사 Service
	 * @param memberNickname
	 * @return result
	 * @throws Exception
	 */
	public int nicknameDupCheck(String memberNickname) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.nicknameDupCheck(conn, memberNickname);
		
		close(conn);
		
		return result;
	}

	/** 회원 정보 조회 Service
	 * @param memberEmail
	 * @return member
	 * @throws Exception
	 */
	public Member selectOne(String memberEmail) throws Exception {

		Connection conn = getConnection();
		
		Member member = dao.selectOne(conn, memberEmail);
		
		close(conn);
		
		return member;
	}

	/** 회원 목록 조회 Service
	 * @return list
	 * @throws Exception
	 */
	public List<Member> selectAll() throws Exception{

		Connection conn = getConnection();
		
		List<Member> list = dao.selectAll(conn);
		
		close(conn);
		
		return list;
	}

}
