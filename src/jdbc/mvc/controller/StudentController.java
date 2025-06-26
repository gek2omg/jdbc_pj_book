package jdbc.mvc.controller;

import jdbc.mvc.dto.StudentDTO;

public class StudentController {

    private static StudentController instance;
    StudentController studentController = StudentController.getInstance();

    public static StudentController getInstance() {
        if (instance == null) {
            instance = new StudentController();
        }
        return instance;
    }

    // 학생 저장
    public void save(StudentDTO studentDTO) {

    }

    // 학생 수정
    public void modify(int studentId, StudentDTO studentDTO) {
    }

    // 학생 삭제
    public void remove(int studentId) {}

    // 학생 ID 조회
    public void findById(int studentId) {}

    // 학생 이름 검색
    public void findByName(String name) {}

    // 학생 전체 목록
    public void findAll() {}
}
