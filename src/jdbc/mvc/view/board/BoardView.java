package jdbc.mvc.view.board;

import jdbc.mvc.domain.board.dto.BoardDTO;
import jdbc.mvc.domain.book.dto.BookDTO;

import java.util.List;

/**
 * 뷰 - 결과화면
 *      Book 에 정보 출력, Book 에러메시지, Book 결과 조회
 */
public class BoardView {

    private static BoardView instance;

    public static BoardView getInstance() {
        if (instance == null) {
            instance = new BoardView();
        }
        return instance;
    }

    // 에러메시지
    public void boardErrorMsg(String msg) {
        switch (msg) {
            case "insert":
                System.out.println("게시물 추가 실패!!");
                break;
            case "update":
                System.out.println("게시물 수정 실패!!");
                break;
            case "delete":
                System.out.println("게시물 삭제 실패!!");
                break;
            case "select":
                System.out.println("게시물 조회 실패!!");
                break;
            default:
                System.out.println("ERROR!!");
        }
    }

    // 게시물목록
    public void boardList(List<BoardDTO> boardDTOList) {
        // 방법1. 향상된 for문 조회
        for (BoardDTO board : boardDTOList) {
            boardSelect(board);
        }
        // 방법
    }

    // 1건
    public void boardSelect(BoardDTO board) {
        System.out.println("게시물 ID : " + board.getBoardNo());
        System.out.println("타이틀 : " + board.getBoardTitle());
        System.out.println("내용 : " + board.getBoardContent());
        System.out.println("등록일 : " + board.getPubRegDate());
        System.out.println("---------------------------------");
    }
}
