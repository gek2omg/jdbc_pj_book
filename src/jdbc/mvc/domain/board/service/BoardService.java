package jdbc.mvc.domain.board.service;

import jdbc.mvc.domain.board.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    // 1.도서 추가
    public int createBoard(BoardDTO boardDTO);

    // 2.도서 수정
    public int updateBoard(int BoardId, BoardDTO boardDTO);

    // 3.도서 삭제
    public int removeBoard(int BoardId);

    // 4.도서아디 조회
    public BoardDTO getBoardById(int BoardId);

    // 5.도서제목 조회
    public List<BoardDTO> getBoardByTitle(String title);

    // 6.전체목록 조회
    public List<BoardDTO> getBoardAll();
}
