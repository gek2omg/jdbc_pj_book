package jdbc.mvc.view;

import jdbc.mvc.controller.BookController;
import jdbc.mvc.dto.BookDTO;

import java.util.Scanner;

public class Menu {

    // 입력을 위한 스캐너
    Scanner scan = new Scanner(System.in);
    BookController bookController = new BookController();

    // 1. 도서관리  2. 게시판  3.와인    4.종료
    public void displayMenu() {
        while(true) {
            System.out.println("*---------------------------------------------*");
            System.out.println("    1. 도서관리    2.게시판    3.와인    4.종료 ");
            System.out.println("*---------------------------------------------*");
            System.out.print("▶ 메뉴선택 : ");
            int menuNo = scan.nextInt();
            scan.nextLine();

            switch(menuNo) {
                case 1:
                    book_menu();
                    break;
                case 2:
                    board_menu();
                    break;
                case 3:
                    wine_menu();
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    System.out.println();
                    return;
                default:
                    System.out.println("메뉴를 다시 선택해주세요.");
            }
        }
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
            int menuNo = scan.nextInt();
            scan.nextLine();

            switch(menuNo) {
                case 1:
                    bookController.bookInsert(bookInput());
                    break;
                case 2:
                    bookController.bookUpdate(bookId(), bookInput());
                    break;
                case 3:
                    bookController.bookDelete(bookId());
                    break;
                case 4:
                    bookController.bookFindById(bookId());
                    break;
                case 5:
                    bookController.bookFindByTitle(bookTitle());
                    break;
                case 6:
                    bookController.bookFindAll();
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

    // 수정, 삭제, 조회
    private int bookId() {
        System.out.print("도서번호 : ");
        int bookid = Integer.parseInt(scan.nextLine());
        return bookid;
    }

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

    /**
     * 2. 게시판 메뉴
     */
    public void board_menu() {
        System.out.println("<<< board_menu >>>");
    }

    /**
     * 와인 메뉴
     */
    public void wine_menu() {
        System.out.println("<<< wineMenu >>>");
    }
}
