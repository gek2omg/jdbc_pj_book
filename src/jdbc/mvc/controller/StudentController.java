package jdbc.mvc.controller;

import jdbc.mvc.dto.StudentDTO;
import jdbc.mvc.service.BookServiceImpl;
import jdbc.mvc.service.StudentService;
import jdbc.mvc.service.StudentServiceImpl;
import jdbc.mvc.view.StudentView;

public class StudentController {

    private static StudentController instance;
    private final StudentService studentService;

    StudentView studentView = StudentView.getInstance();

    public StudentController() {
        studentService = StudentServiceImpl.getInstance();
    }

    public static StudentController getInstance() {
        if (instance == null) {
            instance = new StudentController();
        }
        return instance;
    }

    // 학생 저장
    public void save(StudentDTO studentDTO) {
        System.out.println("save");
        int result = studentService.save(studentDTO);

        if(result == 1) {

        } else {

        }
    }

    // 학생 수정
    public void modify(int studentId, StudentDTO studentDTO) {
        int result = studentService.modify(studentId, studentDTO);

        if(result == 1) {

        } else {

        }
    }

    // 학생 삭제
    public void remove(int studentId) {
        int result = studentService.remove(studentId);

        if(result == 1) {

        } else {

        }
    }

    // 학생 ID 조회
    public void findById(int studentId) {
        StudentDTO studentDTO = studentService.findById(studentId);
        studentView.studentSelect(studentDTO);
    }

    // 학생 이름 검색
    public void findByName(String name) {}

    // 학생 전체 목록
    public void findAll() {}
}
