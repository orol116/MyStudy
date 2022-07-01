package edu.kh.comm.member.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.comm.member.model.dao.MemberDAO;
import edu.kh.comm.member.model.vo.Member;

@Service // ����Ͻ� ����(������ ����, DB ���� ��)�� ó���ϴ� Ŭ�������� ���� + bean ���
public class MemberServiceImpl implements MemberService {

	@Autowired // bean���� ��ϵ� ��ü �� ���� Ÿ���� ������ ������ ����(DI)
	private MemberDAO dao;
	
	// ��ȣȭ�� ���� bcryp ��ü ������ ���� (DI)
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	
	/* * Connection�� Service���� ���Դ� ���� *
	 * - Service�� �޼��� �ϳ��� ��û�� ó���ϴ� ���� ����
	 *   -> �ش� ������ ���� �� Ʈ������� �ѹ��� ó���ϱ� ���ؼ�
	 *      ��¿ �� ���� Connection�� Service���� ���� �� �ۿ� ������.
	 */

	// �α��� Service ����
	@Override
	public Member login(Member inputMember) {
		
		// ���� ���� ��й�ȣ�� ��ȣȭ
		// DB���� ��ȸ�� ��й�ȣ�� ��(DB���� �� X)
		
		// ��? Bcrypt ��ȣȭ�� �����ϱ� ���ؼ� ��¿ �� ���� Service���� ���ؾ��Ѵ�.
		
		// sha ��� ��ȣȭ
		// ex) A ȸ�� / ��й�ȣ 1234 -> ��ȣȭ : abcd
		// ex) B ȸ�� / ��й�ȣ 1234 -> ��ȣȭ : abcd (��ȣȭ �� ����� ������ ���� <��ŷ ���� ����> )
		
		// Bcrypt ��ȣȭ : ��ȣȭ ��� ���� salt�� �߰��Ͽ� ������ ���·� ��ȣȭ�� �����Ѵ�.
		// ex) A ȸ�� / ��й�ȣ 1234 -> ��ȣȭ : abcd
		// ex) B ȸ�� / ��й�ȣ 1234 -> ��ȣȭ : @azd
		
		// * �Ź� ��ȣȭ�Ǵ� ��й�ȣ�� �޶����� DB���� ���� �񱳰� �Ұ����ϴ�.
		//   ��� Brypt ��ȣȭ�� �����ϴ� ��ü��
		//   �̸� ���ϴ� ���(�޼���)�� ������ �־� �̸� Ȱ���ϸ� �ȴ�.
		
		// ** Bcrypt ��ȣȭ�� ����ϱ� ���� �̸� �����ϴ� Spring-security ��� �߰� **
		
						// ���� ��й�ȣ					// ��ȣȭ�� ��й�ȣ
		logger.debug(inputMember.getMemberPw() + " / " + bcrypt.encode(inputMember.getMemberPw()));
//		logger.debug(inputMember.getMemberPw() + " / " + bcrypt.encode(inputMember.getMemberPw()));
//		logger.debug(inputMember.getMemberPw() + " / " + bcrypt.encode(inputMember.getMemberPw()));
//		logger.debug(inputMember.getMemberPw() + " / " + bcrypt.encode(inputMember.getMemberPw()));
//		logger.debug(inputMember.getMemberPw() + " / " + bcrypt.encode(inputMember.getMemberPw()));
		
		Member loginMember = dao.login(inputMember);
		
		// loginMember == null : ��ġ�ϴ� �̸����� ����
		
		if (loginMember != null) { // ��ġ�ϴ� �̸����� ���� ȸ�� ������ ���� ���
			
			// �Էµ� ��й�ȣ(��), ��ȸ�� ��й�ȣ(��ȣȭ) ��
			// bcrypt.matches(��, ��ȣȭ) ������ �ۼ��ؾ���
			if (bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) { // ��й�ȣ�� ��ġ�� ���
				
				loginMember.setMemberPw(null); // �� �������� ��й�ȣ �����
				
				// => ��й�ȣ�� ���ǿ� �ø��� �ȵȴ�!!!!!
				// => ���߿�  loginMember�� ���ǿ� �ö󰡱� ������ null�� ����ֱ�
				
			} else { // ��й�ȣ�� ��ġ���� ���� ���
				loginMember = null; // �α����� ������ ���·� �ٲ��ش�.
			}
		}
		
		return loginMember;
		
		// Connection�� �����ų� / ��ȯ�ϰų�
		// Ʈ����� ó���� �ϴ� ������ ���� �ʾƵ�
		// Spring���� ��� �ϱ� ������
	}

	// �̸��� �ߺ� �˻� Service
	@Override
	public int emailDupCheck(String memberEmail) {
		return dao.emailDupCheck(memberEmail);
	}

	// �г��� �ߺ� �˻� Service
	@Override
	public int nicknameDupCheck(String memberNickname) {
		return dao.nicknameDupCheck(memberNickname);
	}
	
	// ȸ�� ���� Service
	@Override
	public int signUp(Member inputMember) {

		// ��й�ȣ ��ȣȭ(bcrypt)
		String encPw = bcrypt.encode(inputMember.getMemberPw());
		
		// ��ȣȭ�� ��й�ȣ�� �ٽ� ����
		inputMember.setMemberPw(encPw);
		
		// DAO ȣ��
		int result = dao.signUp(inputMember);
		
		// Ʈ����� ���� ó���� �ϴ� ����
		// -> 1���� ���񽺿��� n���� dao�� ȣ���� �� �ִ�.
		// -> dao �ϳ��� ���� �߻� �� ��ü rollback
		
		return result;
	}

	// ȸ�� 1�� ���� ��ȸ Service
	@Override
	public Member selectOne(String memberEmail) {
		return dao.selectOne(memberEmail);
	}

	// ȸ�� ��� ��ȸ Service
	@Override
	public List<Member> selectAll() {
		return dao.selectAll();
	}
	
	
}