package kh.edu.updown.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import kh.edu.updown.model.vo.Member;

public class LoginDAO {

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	
	/** 점수 업데이트 DAO
	 * @param loginMember
	 * @param count
	 * @return result
	 */
	public int updateScore(Member loginMember, int count) {
		int result = 0;
		
		try {
			// oracle jdbc driver 메모리 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 커넥션 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.22:9000:xe", "updown", "updown1234");
			conn.setAutoCommit(false); // 자동 커밋 비활성화
			
			// 특정 아이디를 가진 회원의
			// 최고 점수가 0 또는 count보다 높을 경우
			
			// 최고 점수 0인 경우 : 아직 게임을 진행하지 않아 기록이 없음 == 최초 등록
			// 최고 점수가 count 보다 높은 경우 : 이전 기록이 현재 기록 보다 높음 == 현재 기록이 더 적다 == 기록 갱신
			String sql = "UPDATE MEMBER SET HIGH_SCORE = ? "
					   + "WHERE MEMBER_ID = ? "
					   + "AND (HIGH_SCORE = 0 OR HIGH_SCORE > ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, loginMember.getMemberId());
			pstmt.setInt(3, count);
			
			// SQL 수행
			result = pstmt.executeUpdate();
			
			// 트랜잭션 제어 처리
			if(result > 0)  conn.commit();
			else			conn.rollback();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result; 
	}



	/** 전체 회원 조회(최고 점수 내림 차순) DAO
	 * @return members
	 */
	public List<Member> selectAll() {
		List<Member> members = new ArrayList<Member>();

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.22:9000:xe", "updown", "updown1234");

			String sql = "SELECT MEMBER_NAME, HIGH_SCORE FROM MEMBER ORDER BY HIGH_SCORE";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = new Member();

				member.setMemberName(rs.getString("MEMBER_NAME"));
				member.setHighScore(rs.getInt("HIGH_SCORE"));

				members.add(member);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return members; 
	}



	/** 비밀번호 변경 DAO
	 * @param memberId
	 * @param currentPw
	 * @param newPw
	 * @return result
	 */
	public int updatePw(String memberId, String currentPw, String newPw) {
		
		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.22:9000:xe", "updown", "updown1234");

			String sql = "SELECT MEMBER_PW FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberId);
			pstmt.setString(2, currentPw);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				sql = "UPDATE MEMBER SET MEMBER_PW = ? WHERE MEMBER_ID = ?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, newPw);
				pstmt.setString(2, memberId);

				rs = pstmt.executeQuery();

				result = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result; 
	}

}
