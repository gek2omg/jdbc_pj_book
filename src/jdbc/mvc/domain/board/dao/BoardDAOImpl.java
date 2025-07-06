package jdbc.mvc.domain.board.dao;

import jdbc.mvc.domain.board.dto.BoardDTO;
import jdbc.mvc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * BoardDAO 구현체
 * 데이터베이스와 직접 연동하여 게시물 정보 처리
 */
public class BoardDAOImpl implements BoardDAO {

    private static BoardDAOImpl instance;

    Connection conn = null;         // 오라클 연결
    PreparedStatement pstmt = null; // SQL 문장
    ResultSet rs = null;            // SQL 실행결과(SELECT 절에서만 사용)


    private BoardDAOImpl() {}

    public static BoardDAOImpl getInstance() {
        if (instance == null) {
            instance = new BoardDAOImpl();
        }
        return instance;
    }

    // 1. 게시물 추가
    @Override
    public int insertBoard(BoardDTO boardDTO) {
        String query = """
                        INSERT INTO mvc_board_tbl(boardNo, boardTitle, boardContent, boardId)
                        VALUES ((SELECT NVL(MAX(boardNo) + 1, 1) FROM MVC_BOARD_TBL MBT), ?, ?, ?)
        """;

        int result  = 0;
        System.out.println("BoardServiceImpl - boardInsert()");

        try {
            conn = DBUtil.getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성

            pstmt.setString(1, boardDTO.getBoardTitle());     // 1은 ? 물음표 위치
            pstmt.setString(2, boardDTO.getBoardContent());
            pstmt.setString(3, boardDTO.getBoardId());

            result = pstmt.executeUpdate();  // 입력, 수정, 삭제 등의 SQL 실행 => 1:성공, 0:실패
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // 2. 게시물 수정
    @Override
    public int updateBoard(BoardDTO boardDTO) {
        int result  = 0;

        String query = """
                        UPDATE mvc_board_tbl 
                           SET boardTitle = ?, boardContent = ?, boardId = ? 
                         WHERE boardNo = ?
        """;

        try {
            conn = DBUtil.getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setString(1, boardDTO.getBoardTitle());
            pstmt.setString(2, boardDTO.getBoardContent());
            pstmt.setString(3, boardDTO.getBoardId());
            pstmt.setInt(4, boardDTO.getBoardNo());     // 1은 ?물음표 위치

            result = pstmt.executeUpdate();  // 입력, 수정, 삭제 등의 SQL 실행 => 1:성공, 0:실패
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // 게시물 삭제
    @Override
    public int deleteBoard(int boardNo) {
        int result  = 0;

        String query = """
                        DELETE mvc_board_tbl 
                         WHERE boardNo = ?
        """;

        try {
            conn = DBUtil.getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setInt(1, boardNo);

            result = pstmt.executeUpdate();  // 입력, 수정, 삭제 등의 SQL 실행 => 1:성공, 0:실패
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public BoardDTO findById(int boardNo) {
        BoardDTO board = null;
        String query = """
                        SELECT * FROM mvc_board_tbl 
                         WHERE boardNo = ?
        """;

        try {
            conn = DBUtil.getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setInt(1, boardNo);

            rs = pstmt.executeQuery();
            if(rs.next()) {
                board = new BoardDTO();
                board.setBoardNo(rs.getInt("boardNo"));
                board.setBoardTitle(rs.getString("boardTitle"));
                board.setBoardContent(rs.getString("boardContent"));
                board.setBoardId(rs.getString("boardId"));
                board.setPubRegDate(rs.getDate("boardRegDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return board;
    }

    @Override
    public List<BoardDTO> findByTitle(String title) {
        List<BoardDTO> boardDTOList = new ArrayList<>();

        String query = """
                            SELECT * 
                              FROM mvc_board_tbl 
                             WHERE boardTitle LIKE '%' || ? || '%' 
                             ORDER BY boardRegDate DESC
        """;

        try {
            conn = DBUtil.getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setString(1, title);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                BoardDTO board = new BoardDTO();
                board.setBoardNo(rs.getInt("boardNo"));
                board.setBoardTitle(rs.getString("boardTitle"));
                board.setBoardContent(rs.getString("boardContent"));
                board.setBoardId(rs.getString("boardId"));
                board.setPubRegDate(rs.getDate("boardRegDate"));
                boardDTOList.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return boardDTOList;
    }

    @Override
    public List<BoardDTO> findAll() {
        List<BoardDTO> boardDTOList = new ArrayList<>();

        String query = """
                            SELECT * 
                              FROM mvc_board_tbl 
                             ORDER BY boardNo ASC
        """;

        try {
            conn = DBUtil.getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성

            rs = pstmt.executeQuery();

            while(rs.next()) {
                BoardDTO board = new BoardDTO();
                board.setBoardNo(rs.getInt("boardNo"));
                board.setBoardTitle(rs.getString("boardTitle"));
                board.setBoardContent(rs.getString("boardContent"));
                board.setBoardId(rs.getString("boardId"));
                board.setPubRegDate(rs.getDate("boardRegDate"));
                boardDTOList.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return boardDTOList;
    }
}
