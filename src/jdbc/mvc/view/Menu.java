package jdbc.mvc.view;

import jdbc.mvc.util.InputValidate;
import jdbc.mvc.view.book.BookMenu;
import jdbc.mvc.view.student.StudentMenu;

import java.util.Scanner;

/**
 * 작성일 : 2025.6.18<br>
 * 작성자 : 김동관<br>
 * 설명 : 웹화면을 대신하여 콘솔에서 입력받는 프론트엔드 이다.
 * 메뉴선택, 자료 입력등
 */
public class Menu {

    BookMenu bookMenu = BookMenu.getInstance();
    StudentMenu studentMenu = StudentMenu.getInstance();


    // 입력을 위한 스캐너
    Scanner scan = new Scanner(System.in);

    // 1. 도서관리  2. 게시판  3.와인    4.종료
    public void displayMenu() {
        while(true) {
            System.out.println("*------------------------------------------------------*");
            System.out.println("    1. 도서관리    2.게시판    3.학생(수강생)    4.종료 ");
            System.out.println("*------------------------------------------------------*");
            System.out.print("▶ 메뉴선택 : ");

            int menuNo = InputValidate.readMenuInput(scan, 1, 4);

            switch(menuNo) {
                case 1:
                    bookMenu.book_menu();
                    break;
                case 2:
                    break;
                case 3:
                    studentMenu.student_menu();
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
}
