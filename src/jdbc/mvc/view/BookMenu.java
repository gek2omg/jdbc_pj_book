package jdbc.mvc.view;

import jdbc.mvc.controller.BookController;
import jdbc.mvc.dto.BookDTO;
import jdbc.mvc.util.InputValidate;

import java.util.Scanner;

/**
 * 도서관리 메뉴
 */
public class BookMenu {
    private static BookMenu instance;
    BookController bookController = BookController.getInstance();

    // 입력을 위한 스캐너
    Scanner scan = new Scanner(System.in);

    public static BookMenu getInstance() {
        if (instance == null) {
            instance = new BookMenu();
        }
        return instance;
    }

    /**
     * 1. 도서관리 메뉴
     */
    public void book_menu() {
        System.out.println("<<< bookMenu >>>");

        while(true) {
            System.out.println("*------------------------------------------------------------------------------------------------------*");
            System.out.println("    1. 추가    2.수정    3.삭제    4.도서아이디 조회    5.도서 제목 조회    6.전체목록 조회    7.종료    ");
            System.out.println("*------------------------------------------------------------------------------------------------------*");
            System.out.print("▶ 메뉴선택 : ");

            int menuNo = InputValidate.readMenuInput(scan, 1, 7);

            switch(menuNo) {
                case 1:
                    bookController.saveBook(bookInput());
                    break;
                case 2:
                    int bookId = bookId();
                    bookController.findBookById(bookId);
                    bookController.modifyBook(bookId, bookInput());
                    bookController.findBookById(bookId);
                    break;
                case 3:
                    bookController.removeBook(bookId());
                    break;
                case 4:
                    bookController.findBookById(bookId());
                    break;
                case 5:
                    bookController.findBookByTitle(bookTitle());
                    break;
                case 6:
                    bookController.findBookAll();
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

    // 도서번호 입력 : 수정, 삭제, 조회
    private int bookId() {
        int bookid = 0;
        while (true) {
            try {
                System.out.print("도서번호 : ");
                bookid = Integer.parseInt(scan.nextLine()); // 예외 발생 가능 지점
                break; // 숫자 입력 성공 시 반복 종료
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
        return bookid;
    }

    // 도서 제목 입력 : 조서 제목 검색시 사용
    private String bookTitle() {
        System.out.print("도서제목 : ");
        String title = scan.nextLine();
        return title;
    }


    // 1-1. book 추가, 수정
    public BookDTO bookInput() {
        // 콘솔에서 입력받은 값 -> setter로 BookDTO 멤버변수에 전달
        // BookDTO 생성
        BookDTO bookDTO = new BookDTO();
//        int id = 1;
//        bookDTO.setBookId(id);

        System.out.print("도서명 : ");
        String title = scan.nextLine();
        bookDTO.setTitle(title);

        System.out.print("저자 : ");
        String author = scan.nextLine();
        bookDTO.setAuthor(author);

        System.out.print("출판사 : ");
        String publisher = scan.nextLine();
        bookDTO.setPublisher(publisher);

        System.out.print("가격 : ");
        int price = scan.nextInt();
        scan.nextLine();
        bookDTO.setPrice(price);

//        System.out.print("출판일 : ");
//        String pubdate = scan.nextLine();
//        bookDTO.setPubdate(pubdate);

        return bookDTO;
    }
}
