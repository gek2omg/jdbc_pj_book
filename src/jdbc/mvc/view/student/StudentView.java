package jdbc.mvc.view.student;

import jdbc.mvc.domain.student.dto.StudentDTO;

import java.util.List;

public class StudentView {

    private static StudentView instance;

    public static StudentView getInstance() {
        if (instance == null) {
            instance = new StudentView();
        }
        return instance;
    }

    public void studentSelect(StudentDTO studentDTO) {
        System.out.println("학생 ID : " + studentDTO.getStudentId());
        System.out.println("이름 : " + studentDTO.getName());
        System.out.println("생년월일 : " + studentDTO.getBirthday());
        System.out.println("연락처 : " + studentDTO.getPhone());
        System.out.println("---------------------------------------");
    }

    public void studentList(List<StudentDTO> studentDTOs) {
        for (StudentDTO studentDTO : studentDTOs) {
            studentSelect(studentDTO);
        }
    }
}
