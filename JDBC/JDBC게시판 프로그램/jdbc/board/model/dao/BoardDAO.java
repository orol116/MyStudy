package edu.kh.jdbc.board.model.dao;

import edu.kh.jdbc.board.model.vo.Board;
import edu.kh.jdbc.board.model.vo.Reply;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplate.close;

public class BoardDAO {

    // JDBC 객체 참조용 변수 선언
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    // SQL 내용을 저장한 Properties 객체 참조 변수 선언
    private Properties prop;

    // 기본 생성자(board-sql.xml 파일 읽어오기(Properties)
    public BoardDAO() {
        try {
            prop = new Properties();

            // XML 파일 읽어오기
            prop.loadFromXML(new FileInputStream("board-sql.xml"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 게시글 목록 조회 DAO
     *
     * @param conn
     * @return boardList
     * @throws Exception
     */
    public List<Board> selectAll(Connection conn) throws Exception {

        List<Board> boardList = new ArrayList<>();

        try {
            // 1) sql 작성
            String sql = prop.getProperty("selectAll");

            // 2) Statement 생성
            Statement stmt = conn.createStatement();

            // 3) sql 수행(SELECT)후 결과 반환 받기(ResultSet)
            rs = stmt.executeQuery(sql);

            // 4) ResultSet을 한 행씩 모두(while) 접근(rs.next)
            while (rs.next()) {

                // 5) 현재 행에서 컬럼명을 이용해서 컬럼 값 얻어오기
                int boardNo = rs.getInt("BOARD_NO");
                String boardTitle = rs.getString("BOARD_TITLE");
                Date createDate = rs.getDate("CREATE_DATE");
                int readCount = rs.getInt("READ_COUNT");
                String memberName = rs.getString("MEMBER_NM");
                int replyCount = rs.getInt("REPLY_COUNT");

                // 6) Board 객체를 생성하여 컬럼 값 담기
                Board board = new Board(boardNo, boardTitle, createDate, readCount, memberName, replyCount);

                // 7) Board 객체를 boardList에 추가
                boardList.add(board);
            }

        } finally {
            // 8) JDBC 자원 반환(Connection) 제외
            close(rs);
            close(stmt);
        }

        return boardList;
    }

    /**
     * 게시글 상세 조회 DAO
     *
     * @param conn
     * @param boardNo
     * @return board
     * @throws Exception
     */
    public Board selectOne(Connection conn, int boardNo) throws Exception {

        Board board = null; // 결과 저장용 변수

        try {
            // 1) SQL 작성
            String sql = prop.getProperty("selectOne");

            // 2) PreparedStatement 생성
            pstmt = conn.prepareStatement(sql);

            // 3) 위치 홀더 '?'에 알맞은 값 세팅
            pstmt.setInt(1, boardNo);

            // 4) SQL 수행(SELECT) 후 결과 반환 받기(ResultSet)
            rs = pstmt.executeQuery();

            // 5) 조회된 한 행(if)이 있을 경우 조회된 컬럼 값 얻어오기
            if(rs.next()) {
                //int boardNo 		= rs.getInt("BOARD_NO");
                // --> 입력 받은 boardNo와 조회된 BOARD_NO는 같으므로
                // 굳이 DB 조회 결과에서 얻어오지 않아도 된다.

                String boardTitle 	= rs.getString("BOARD_TITLE");
                Date createDate		= rs.getDate("CREATE_DATE");
                int readCount		= rs.getInt("READ_COUNT");
                String memberName	= rs.getString("MEMBER_NM");

                String boardContent = rs.getString("BOARD_CONTENT");
                int memberNo		= rs.getInt("MEMBER_NO");


                // 6) Board 객체를 생성하여 컬럼 값 세팅
                board = new Board();

                board.setBoardNo(boardNo); // 매개변수를 세팅
                board.setBoardTitle(boardTitle);
                board.setBoardContent(boardContent);
                board.setCreateDate(createDate);
                board.setMemberName(memberName);
                board.setReadCount(readCount);
                board.setMemberNo(memberNo);

            }

        } finally {
            // 7) 사용한 JDBC 자원 반환
            close(rs);
            close(pstmt);
        }

        // 결과 반환
        return board;
    }



    /** 특정 게시글 댓글 목록 조회 DAO
     * @param conn
     * @param boardNo
     * @return replyList
     * @throws Exception
     */
    public List<Reply> selectReplyList(Connection conn, int boardNo) throws Exception{

        List<Reply> replyList = new ArrayList<Reply>(); // 결과 저장용 변수

        try {
            // 1) SQL 작성
            String sql = prop.getProperty("selectReplyList");

            // 2) PreparedStatement 생성
            pstmt = conn.prepareStatement(sql);

            // 3) 위치 홀더에 알맞은 값 대입
            pstmt.setInt(1, boardNo);

            // 4) SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기
            rs = pstmt.executeQuery();

            // 5) 조회된 결과를 한 행씩 접근( while(rs.next() )
            //    -> 각 행별로 컬럼 값 얻어오기
            while(rs.next()) {
                int replyNo 		= rs.getInt("REPLY_NO");
                String replyContent = rs.getString("REPLY_CONTENT");
                Date createDate		= rs.getDate("CREATE_DATE");
                int memberNo 		= rs.getInt("MEMBER_NO");
                String memberName	= rs.getString("MEMBER_NM");
                // boardNo는 매개변수 사용


                // 6) Reply 객체를 생성하여 컬럼 값 담기
                Reply reply = new Reply();

                reply.setReplyNo(replyNo);
                reply.setReplyContent(replyContent);
                reply.setCreateDate(createDate);
                reply.setMemberNo(memberNo);
                reply.setMemberName(memberName);
                reply.setBoardNo(boardNo);

                // 7) replyList에 Reply객체 추가
                replyList.add(reply);

            }

        } finally {
            // 8) JDBC 객체 자원 반환(Connection 제외)
            close(rs);
            close(pstmt);
        }

        // 결과 반환
        return replyList;
    }


    /**
     * 게시글 조회수 증가 DAO
     *
     * @param conn
     * @param boardNo
     * @return result
     * @throws Exception
     */
    public int increaseReadCount(Connection conn, int boardNo) throws Exception {
        int result = 0;

        try {

            String sql = prop.getProperty("increaseReadCount");

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);

            result = pstmt.executeUpdate();

        } finally {
            close(pstmt);
        }

        return result;
    }

    public int deleteBoard(Connection conn, int boardNo) throws Exception {

        int result = 0;

        try {

            String sql = prop.getProperty("deleteBoard");

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);

            result = pstmt.executeUpdate();

        } finally {
            close(pstmt);
        }

        return result;
    }

    public int updateBoard(Connection conn, Board board) throws Exception {

        int result = 0;

        try {

            String sql = prop.getProperty("updateBoard");

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, board.getBoardTitle());
            pstmt.setString(2, board.getBoardContent());
            pstmt.setInt(3, board.getBoardNo());

            result = pstmt.executeUpdate();

        } finally {
            close(pstmt);
        }

        return result;

    }

    public int insertReply(Connection conn, Reply reply) throws Exception {

        int result = 0;

        try {

            String sql = prop.getProperty("insertReply");

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, reply.getReplyContent());
            pstmt.setInt(2, reply.getMemberNo());
            pstmt.setInt(3, reply.getBoardNo());

            result = pstmt.executeUpdate();

        } finally {
            close(pstmt);
        }

        return result;
    }

    public int updateReply(Connection conn, Reply reply) throws Exception {

        int result = 0;

        try {

            String sql = prop.getProperty("updateReply");

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, reply.getReplyContent());
            pstmt.setInt(2, reply.getReplyNo());

            result = pstmt.executeUpdate();

        } finally {
            close(pstmt);
        }

        return result;
    }

    public int deleteReply(Connection conn, int inputNo) throws Exception {

        int result = 0;

        try {

            String sql = prop.getProperty("deleteReply");

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, inputNo);

            result = pstmt.executeUpdate();

        } finally {
            close(pstmt);
        }

        return result;
    }
}
