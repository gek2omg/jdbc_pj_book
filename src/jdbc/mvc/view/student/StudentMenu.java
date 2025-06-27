package jdbc.mvc.view.student;

import jdbc.mvc.domain.student.controller.StudentController;
import jdbc.mvc.domain.student.dto.StudentDTO;
import jdbc.mvc.util.InputValidate;

import java.util.Scanner;

/**
 * 학생 메뉴
 */
public class StudentMenu {

    private static StudentMenu instance;
    StudentController  studentController = StudentController.getInstance();

    // 입력을 위한 스캐너
    Scanner scan = new Scanner(System.in);

    public static StudentMenu getInstance() {
        if (instance == null) {
            instance = new StudentMenu();
        }
        return instance;
    }

    public void student_menu() {
        System.out.println("<<< studentMenu >>>");

        while(true) {
            System.out.println("*------------------------------------------------------------------------------------------------------*");
            System.out.println("    1. 추가    2.수정    3.삭제    4.학생 ID 조회    5.학생 이름 조회    6.전체목록 조회    7.종료    ");
            System.out.println("*------------------------------------------------------------------------------------------------------*");
            System.out.print("▶ 메뉴선택 : ");

            int menuNo = InputValidate.readMenuInput(scan, 1, 7);

            switch(menuNo) {
                case 1:
                    studentController.save(studentInput());
                    break;
                case 2:
                    int studentId = studentId();
                    studentController.findById(studentId);
                    studentController.modify(studentId, studentInput());
                    studentController.findById(studentId);
                    break;
                case 3:
                    studentController.remove(studentId());
                    break;
                case 4:
                    studentController.findById(studentId());
                    break;
                case 5:
                    studentController.findByName(studentName());
                    break;
                case 6:
                    studentController.findAll();
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

    public int studentId() {
        int studentId = 0;
        while (true) {
            try {
                System.out.print("학생번호 : ");
                studentId = Integer.parseInt(scan.nextLine()); // 예외 발생 가능 지점
                break; // 숫자 입력 성공 시 반복 종료
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
        return studentId;
    }

    public String studentName() {
        System.out.print("이름 : ");
        String name = scan.nextLine();
        return name;
    }

    public StudentDTO studentInput() {
        StudentDTO studentDTO = new StudentDTO();

        System.out.print("이름 : ");
        String name = scan.nextLine();
        studentDTO.setName(name);

        System.out.print("생년월일(6자리) : ");
        String birthday = scan.nextLine();
        studentDTO.setBirthday(birthday);

        System.out.print("전화번호 : ");
        String phone = scan.nextLine();
        studentDTO.setPhone(phone);

        return studentDTO;
    }
}
