package edu.kh.jdbc.member.model.dao;

import edu.kh.jdbc.member.model.vo.Member;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplate.close;

// DAO (Data Access Object) : 데이터가 저장되어있는 DB, 파일등에 접근하는 객체
//                            -> DB 접근할 수 있다 == SQL을 수행하고 결과를 반환 받을 수 있다.

// Java에서 DB에 접근하고 결과를 반환 받기 위한 프로그래밍 API를 제공함
// == JDBC(Connection, Statement, PreparedStatement, ResultSet)

public class MemberDAO {

    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private Properties prop = null;
    // Map이면서 K, V가 모두 String, 외부 XML 파일 입출력 특화

    // MemberDAO 기본 생성자
    public MemberDAO() {
        // MemberDAO 객체 생성 시
        // member-sql.xml 파일의 내용을 읽어와
        // Properties 객체 생성

        try {
            prop = new Properties();
            prop.loadFromXML(new FileInputStream("member-sql.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 아이디 중복 검사 DAO 메서드
     * @param conn
     * @param memberId
     * @return result
     * @throws Exception
     */
    public int duplicateCheck(Connection conn, String memberId) throws Exception {
        // throws : 호출한 메서드로 예외를 던짐

        // 1) 결과 저장용 변수 선언
        int result = 0;

        try {
            // 2) SQL 작성
            String sql = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_ID = ? AND SECESSION_FL = 'N'";

            // 3) PreparedStatement 객체 생성(Connection, SQL 필요)
            pstmt = conn.prepareStatement(sql);

            // 4) 위치 홀더 '?'에 알맞은 값 세팅
            pstmt.setString(1, memberId);

            // 5) SQL 수행 후 결과 반환 받기
            rs = pstmt.executeQuery(); // SELECT 수행 결과 ResultSet을 반환 받음

            // 6) 조회 결과를 한 행씩 접근하여 원하는 컬럼 값 얻어오기
            //    -> 아이디 중복 검사 SELECT 결과는 0 또는 1이라는 1행 결과가 무조건 나옴
            //    -> while문 보다 if문을 사용하는게 효율적
            if (rs.next()) {
                result = rs.getInt(1); // 1은 컬럼 순서
            }

        } finally { // try - finally 구문 (catch는 throws에 의해서 생략)
            // 7) 사용한 JDBC 자원 반환(Connection은 제외)
            close(rs);
            close(pstmt);
        }

        return result;
    }

    /**
     * 회원 가입 DAO
     * @param conn
     * @param sighUpMember
     * @return result
     * @throws Exception
     */
    public int signUp(Connection conn, Member sighUpMember) throws Exception {

        int result = 0; // 결과 저장용 변수

        try {
            // 1) SQL 작성(Properties에 저장된 SQL 얻어오기)
            String sql = prop.getProperty("signUp");

            // 2) PreparedStatement 객체 생성(Connection, SQL 필요)
            pstmt = conn.prepareStatement(sql);

            // 3) 위치 홀더 '?'에 알맞은 값 세팅
            pstmt.setString(1, sighUpMember.getMemberId());
            pstmt.setString(2, sighUpMember.getMemberPw());
            pstmt.setString(3, sighUpMember.getMemberName());
            pstmt.setString(4, sighUpMember.getMemberGender() + "");
            // getMemberGender()의 반환형은 char
            // setString()의 매개변수는 String
            // -> 자료형 불일치로 오류 발생
            // --> char 자료형 + ""(빈 문자열) == 문자열

            // 4) SQL(INSERT) 수행 후 결과 반환 받기
            result = pstmt.executeUpdate(); // 성공한 행의 개수 반환

        } finally {
            // 5) 사용한 JDBC 자원 반환(Connection 제외)
            close(pstmt);
        }

        return result;
    }

    /**
     * 로그인 DAO
     * @param conn
     * @param mem
     * @return loginMember
     * @throws Exception
     */
    public Member login(Connection conn, Member mem) throws Exception {

        // 결과 저장용 변수 선언
        Member loginMember = null;

        try {
            // 1)  SQL 작성(Properties에서 얻어오기)
            String sql = prop.getProperty("login");

            // 2) PreparedStatement 생성
            pstmt = conn.prepareStatement(sql);

            // 3) 위치 홀더 '?'에 알맞은 값 세팅
            pstmt.setString(1, mem.getMemberId());
            pstmt.setString(2, mem.getMemberPw());

            // 4) SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기 (rs 변수 사용)
            rs = pstmt.executeQuery();

            // 5) if 또는 while문을 이용해서 rs에 한 행씩 접근하여 원하는 값 얻어오기
            //    --> 결과 행이 많아야 1행 -> if문 사용
            if (rs.next()) {
                int memberNo = rs.getInt("MEMBER_NO");
                String memberId = rs.getString("MEMBER_ID");
                String memberName = rs.getString("MEMBER_NM");
                char memberGender = rs.getString("MEMBER_GENDER").charAt(0);
                Date enrollDate = rs.getDate("ENROLL_DATE");

                // 6) 얻어온 컬럼 값을 이용해 Member 객체를 생성하여 loginMember 변수에 저장
                loginMember = new Member();
                loginMember.setMemberNo(memberNo);
                loginMember.setMemberId(memberId);
                loginMember.setMemberName(memberName);
                loginMember.setMemberGender(memberGender);
                loginMember.setEnrollDate(enrollDate);
            }

        } finally {
            // 7) 사용한 JDBC 객체 자원 반환(Connection 제외)
            close(rs);
            close(pstmt);
        }

        // 8) DAO 수행 결과 반환
        return loginMember;
    }

    /**
     * 가입된 회원 목록 조회 DAO
     * @param conn
     * @return memberList
     * @throws Exception
     */
    public List<Member> selectAll(Connection conn) throws Exception {

        // 결과 저장용 변수
        List<Member> memberList = new ArrayList<Member>();

        try {
            // 1) SQL 작성
            String sql = prop.getProperty("selectAll");

            // 2) Statement 객체 생성
            stmt = conn.createStatement();

            // 3) SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기
            rs = stmt.executeQuery(sql);

            // 4) ResultSet을 한 행씩 접근(rs.next())하여 조회된 컬럼 값을 얻어와
            //    Member 객체에 저장
            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("MEMBER_ID"));
                member.setMemberName(rs.getString("MEMBER_NM"));
                member.setEnrollDate(rs.getDate("ENROLL_DATE"));

                // 5) 컬럼 값이 저장된 Member 객체를 List에 추가
                memberList.add(member);
            }

        } finally {
            // 6) 사용한 JDBC 자원 반환(Connection 제외)
            close(rs);
            close(stmt);
        }

        // 7) 결과 반환
        return memberList;
    }

    /**
     * 내 정보 수정 DAO
     * @param conn
     * @param updateMember
     * @return result
     * @throws Exception
     */
    public int updateMyInfo(Connection conn, Member updateMember) throws Exception {

        int result = 0;

        try {
            String sql = prop.getProperty("updateMyInfo");

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, updateMember.getMemberName());
            pstmt.setString(2, updateMember.getMemberGender() + "");
            pstmt.setInt(3, updateMember.getMemberNo());

            result = pstmt.executeUpdate();

        } finally {
            close(pstmt);
        }

        return result;
    }

    /**
     * 비밀번호 변경 DAO
     *
     * @param conn
     * @param memberNo
     * @param newPw1
     * @param currentPw
     * @return result
     * @throws Exception
     */
    public int updatePw(Connection conn, int memberNo, String newPw1, String currentPw) throws Exception{

        int result = 0;

        try {

            String sql = prop.getProperty("updatePw");

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newPw1);
            pstmt.setInt(2, memberNo);
            pstmt.setString(3, currentPw);

            result = pstmt.executeUpdate();

        } finally {
            close(pstmt);
        }

        return result;

    }

    /**
     * 회원 탈퇴 DAO
     * @param conn
     * @param memberNo
     * @param currentPw
     * @return result
     * @throws Exception
     */
    public int secession(Connection conn, int memberNo, String currentPw) throws Exception {

        int result = 0;

        try {

            String sql = prop.getProperty("secession");

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentPw);
            pstmt.setInt(2, memberNo);

            result = pstmt.executeUpdate();


        } finally {
            close(pstmt);
        }

        return result;
    }
}
