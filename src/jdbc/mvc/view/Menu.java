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

    BookMenu bookMenu = BookMenu.getInstance();


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
                    bookMenu.book_menu();
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
