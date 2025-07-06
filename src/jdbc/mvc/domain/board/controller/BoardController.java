package jdbc.mvc.domain.board.controller;

import jdbc.mvc.domain.board.dto.BoardDTO;
import jdbc.mvc.domain.board.service.BoardService;
import jdbc.mvc.domain.board.service.BoardServiceImpl;
import jdbc.mvc.view.board.BoardView;

import java.util.List;

/**
 * 작성일 : 2025.6.18<br>
 * 작성자 : 김동관<br>
 * 설명 : 게시물 콘트롤러 - 고객의 요구사항(비즈니스 로직)에 대한 흐름제어를 담당한다.
 * Board 정보에 대한 추가,수정,삭제,게시물아이디 조회, 전체목록 조회
 * BoardController -> BoardServiceImpl(BoardService) -> BoardDAOImple(BoardDAO)
 */
public class BoardController {
    private static BoardController instance;
    private final BoardService boardService;

    BoardView boardView = BoardView.getInstance();

    private BoardController() {
        boardService = BoardServiceImpl.getInstance();
    }

    public static BoardController getInstance() {
        if (instance == null) {
            instance = new BoardController();
        }
        return instance;
    }

    // 1.게시물 추가
    public void createBoard(BoardDTO BoardDTO) {
        System.out.println("<<< saveBoard() >>>");
        System.out.println(BoardDTO);

        int insertCnt = boardService.createBoard(BoardDTO);

        if (insertCnt == 1) {
            System.out.println("게시물 정보 추가 성공!!" + insertCnt);
        } else {
            boardView.boardErrorMsg("insert");
        }
    }

    // 2.게시물 수정
    public void updateBoard(int BoardId, BoardDTO BoardDTO) {
        System.out.println("<<< modifyBoard() >>>");

        int result = boardService.updateBoard(BoardId, BoardDTO);

        if (result == 1) {
            System.out.println("게시물 정보 수정 성공!!" + result);
        } else {
            boardView.boardErrorMsg("update");
        }
    }

    // 3.게시물 삭제
    public void removeBoard(int BoardId) {
        System.out.println("<<< removeBoard() >>>");

        int result = boardService.removeBoard(BoardId);
        if (result == 1) {
            System.out.println("게시물 정보 삭제 성공!!" + result);
            System.out.println("---------------------------------");
        } else {
            boardView.boardErrorMsg("delete");
        }
    }

    // 4.게시물 아이디 조회
    public void getBoardById(int BoardId) {
        System.out.println("<<< findBoardById() >>>");
        BoardDTO Board = boardService.getBoardById(BoardId);
        if(Board != null) {
            System.out.println("게시물 ID 조회 성공");
            System.out.println("---------------------------------");
            boardView.boardSelect(Board);
        } else {
            boardView.boardErrorMsg("select");
        }
    }

    // 5.게시물제목 조회
    public void getBoardByTitle(String title) {
        System.out.println("<<< findBoardByTitle() >>>");
        List<BoardDTO> BoardDTOList = boardService.getBoardByTitle(title);
        if (BoardDTOList != null) {
            System.out.println("게시물 정보 제목 조회 성공!!");
            System.out.println("---------------------------------");
            boardView.boardList(BoardDTOList);
        } else {
            boardView.boardErrorMsg("select");
        }

    }

    // 6.전체목록 조회
    public void getAllBoard() {
        System.out.println("<<< findBoardAll() >>>");
        List<BoardDTO> BoardDTOList = boardService.getBoardAll();
        if (BoardDTOList != null) {
            System.out.println("게시물 정보 전체 조회 성공!!");
            System.out.println("---------------------------------");
            boardView.boardList(BoardDTOList);
        } else {
            boardView.boardErrorMsg("select");
        }
    }

}
