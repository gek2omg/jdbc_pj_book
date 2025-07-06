package jdbc.mvc.domain.board.dao;

import jdbc.mvc.domain.board.dto.BoardDTO;

import java.util.List;

// DAO - DB 처리(DB 연결, 데이터 CRUD)
public interface BoardDAO {

    // 1. 게시물 추가
    public int insertBoard(BoardDTO boardDTO);

    // 2. 게시물 수정
    public int updateBoard(BoardDTO boardDTO);

    // 3. 게시물 삭제
    public int deleteBoard(int boardId);

    // 4. 게시물 아이디 조회
    public BoardDTO findById(int boardId);

    // 5. 게시물제목 조회
    public List<BoardDTO> findByTitle(String title);

    // 6. 전체목록 조회
    public List<BoardDTO> findAll();

}
