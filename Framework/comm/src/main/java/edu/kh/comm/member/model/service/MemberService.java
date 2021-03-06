package edu.kh.comm.member.model.service;

import java.util.List;

import edu.kh.comm.member.model.vo.Member;

/* Service Interface를 사용하는 이유
 * 
 * 1. 프로젝트에 규칙성을 부여하기 위해서
 * 2. Spring AOP을 위해서 필요하다
 * 3. 클래스 간의 결합도를 약화시키기 위해서 => 유지보수성 향상 (객체 지향적 프로그래밍)
 */
public interface MemberService {
	
	// [인터페이스의 특징]
	// 모든 메서드가 추상 메서드이다.	(묵시적으로 public abstract)
	// 모든 필드는 상수이다.			(묵시적으로 public static final)

	/** 로그인 Service
	 * @param inputMember
	 * @return loginMember
	 */
	Member login(Member inputMember);
	

	/** 이메일 중복 검사 Service
	 * @param memberEmail
	 * @return result
	 */
	int emailDupCheck(String memberEmail);

	
	/** 닉네임 중복 검사 Service
	 * @param memberNickname
	 * @return result
	 */
	int nicknameDupCheck(String memberNickname);


	/** 회원 가입 Service
	 * @param inputMember
	 * @return result
	 */
	int signUp(Member inputMember);


	/** 회원 1명 정보 조회 Service
	 * @param memberEmail
	 * @return member
	 */
	Member selectOne(String memberEmail);


	/** 회원 목록 조회 Service
	 * @return memberList
	 */
	List<Member> selectAll();
	
}
