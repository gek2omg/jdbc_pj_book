package jdbc.mvc.view.board;

import jdbc.mvc.domain.board.controller.BoardController;
import jdbc.mvc.domain.board.dto.BoardDTO;
import jdbc.mvc.util.InputValidate;

import java.util.Scanner;

/**
 * 게시물관리 메뉴
 */
public class BoardMenu {
    private static BoardMenu instance;
    BoardController boardController = BoardController.getInstance();

    // 입력을 위한 스캐너
    Scanner scan = new Scanner(System.in);

    public static BoardMenu getInstance() {
        if (instance == null) {
            instance = new BoardMenu();
        }
        return instance;
    }

    /**
     * 1. 게시물관리 메뉴
     */
    public void board_menu() {
        System.out.println("<<< boardMenu >>>");

        while(true) {
            System.out.println("*------------------------------------------------------------------------------------------------------*");
            System.out.println("    1. 추가    2.수정    3.삭제    4.게시물 아이디 조회    5.게시물 제목 조회    6.전체목록 조회    7.종료    ");
            System.out.println("*------------------------------------------------------------------------------------------------------*");
            System.out.print("▶ 메뉴선택 : ");

            int menuNo = InputValidate.readMenuInput(scan, 1, 7);

            switch(menuNo) {
                case 1:
                    boardController.createBoard(boardInput());
                    break;
                case 2:
                    int boardId = boardId();
                    boardController.getBoardById(boardId);
                    boardController.updateBoard(boardId, boardInput());
                    boardController.getBoardById(boardId);
                    break;
                case 3:
                    boardController.removeBoard(boardId());
                    break;
                case 4:
                    boardController.getBoardById(boardId());
                    break;
                case 5:
                    boardController.getBoardByTitle(boardTitle());
                    break;
                case 6:
                    boardController.getAllBoard();
                    break;
                case 7:
                    System.out.println("프로그램을 종료합니다.");
                    System.out.println();
                    return;
                default:
                    System.out.println("메뉴를 다시 선택해주세요.");
            }
        }
    }

    // 게시물번호 입력 : 수정, 삭제, 조회
    private int boardId() {
        int boardid = 0;
        while (true) {
            try {
                System.out.print("게시물번호 : ");
                boardid = Integer.parseInt(scan.nextLine()); // 예외 발생 가능 지점
                break; // 숫자 입력 성공 시 반복 종료
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
        return boardid;
    }

    // 게시물 제목 입력 : 조서 제목 검색시 사용
    private String boardTitle() {
        System.out.print("게시물제목 : ");
        String title = scan.nextLine();
        return title;
    }


    // 1-1. board 추가, 수정
    public BoardDTO boardInput() {
        // 콘솔에서 입력받은 값 -> setter로 BoardDTO 멤버변수에 전달
        // BoardDTO 생성
        BoardDTO boardDTO = new BoardDTO();
//        int id = 1;
//        boardDTO.setBoardId(id);

        System.out.print("게시물명 : ");
        String title = scan.nextLine();
        boardDTO.setBoardTitle(title);

        System.out.print("게시물내용 : ");
        String content = scan.nextLine();
        boardDTO.setBoardContent(content);

        System.out.print("작성자 : ");
        String author = scan.nextLine();
        boardDTO.setBoardId(author);

        return boardDTO;
    }
}
