package edu.kh.comm.member.model.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.comm.common.Util;
import edu.kh.comm.member.model.dao.MyPageDAO;
import edu.kh.comm.member.model.vo.Member;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	// 회원 정보 수정 Service
	@Override
	public int updateInfo(Map<String, Object> paramMap) {
		return dao.updateInfo(paramMap);
	}

	// 비밀번호 변경 Service
	@Override
	public int updatePw(Map<String, Object> pwMap) {
		
		String nowPw = dao.selectCurrentPw((int)pwMap.get("memberNo"));

		if (bcrypt.matches((String)pwMap.get("currentPw"), nowPw)) {
			pwMap.put("newPw", bcrypt.encode((String)pwMap.get("newPw")));
			return dao.updatePw(pwMap);
		}
		
		return 0;
	}

	// 회원 탈퇴 Service
	@Override
	public int secession(Member loginMember) {

		String nowPw = dao.selectCurrentPw(loginMember.getMemberNo());

		if (bcrypt.matches(loginMember.getMemberPw(), nowPw)) {
			return dao.secession(loginMember.getMemberNo());
		}
		
		return 0;
	}

	// 프로필 이미지 수정 서비스 구현
	@Override
	public int updateProfile(Map<String, Object> map) throws IllegalStateException, IOException {
		
		// 자주쓰는 값 변수에 저장
		MultipartFile uploadImage = (MultipartFile)map.get("uploadImage");
		String delete = (String)map.get("delete"); // "0" 변경 / "1" 삭제
		
		// 프로필 이미지 삭제여부를 확인해서
		// 삭제가 아닌 경우(== 새 이미지로 변경) -> 업로드된 파일명을 변경
		// 삭제된 경우 -> NULL 값을 준비
		String renameImage = null; // 변경된 파일명 저장
		
		if (delete.equals("0")) { // 이미지가 변경된 경우
			// 파일명 변경
			// upload.getOriginalFilename() : 원본 파일명
			renameImage = Util.fileRename(uploadImage.getOriginalFilename());
			
			map.put("profileImage", map.get("webPath") + renameImage); // 파일명 변경
		} else {
			map.put("profileImage", null); // null 값 준비
		}
		
		// DAO를 호출해서 프로필 이미지 수정
		int result = dao.updateProfile(map);
		
		// DB 수정 성공 시 메모리에 임시 저장되어있는 파일을 서버에 저장
		if (result > 0 && map.get("profileImage") != null) {
			uploadImage.transferTo(new File(map.get("folderPath") + renameImage));
			
			// transferTo() : 해당 파일을 지정된 경로 + 이름으로 저장
		}

		return result;
	}
	
	

	
	
	
	
}
