package jdbc.mvc.view;

import jdbc.mvc.controller.BookController;
import jdbc.mvc.dto.BookDTO;

import java.util.Scanner;

/**
 * 작성일 : 2025.6.18<br>
 * 작성자 : 김동관<br>
 * 설명 : 도서 메뉴는 화면에서 사용자가 입력하거나 마우스를 클릭하는 대용하는 파일이다.
 * 메뉴선택, 자료 입력등
 */
public class Menu {

    BookController bookController = BookController.getInstance();

    // 입력을 위한 스캐너
    Scanner scan = new Scanner(System.in);

    // 1. 도서관리  2. 게시판  3.와인    4.종료
    public void displayMenu() {
        while(true) {
            System.out.println("*---------------------------------------------*");
            System.out.println("    1. 도서관리    2.게시판    3.와인    4.종료 ");
            System.out.println("*---------------------------------------------*");
            System.out.print("▶ 메뉴선택 : ");

            int menuNo = 0;

            while(true) {
                try {
                    String input = scan.nextLine();
                    menuNo = Integer.parseInt(input);
                    if(menuNo < 1 || menuNo > 4) {
                        System.out.println("1부터 4 사이 숫자를 입력해주세요.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("숫자만 입력해주세요.");
                }
            }

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

            int menuNo = 0;

            while(true) {
                try {
                    String input = scan.nextLine(); // 문자열로 받기
                    menuNo = Integer.parseInt(input); // 직접 파싱
                    if(menuNo < 1 || menuNo > 7) {
                        System.out.println("1부터 7 사이의 숫자를 입력해주세요.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException  e) {
                    System.out.println("숫자만 입력해주세요.");
                }
            }

            switch(menuNo) {
                case 1:
                    bookController.saveBook(bookInput());
                    break;
                case 2:
                    bookController.modifyBook(bookId(), bookInput());
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
