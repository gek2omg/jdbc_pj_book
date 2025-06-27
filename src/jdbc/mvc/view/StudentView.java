package jdbc.mvc.view;

import jdbc.mvc.dto.StudentDTO;

public class StudentView {

    private static StudentView instance;

    public static StudentView getInstance() {
        if (instance == null) {
            instance = new StudentView();
        }
        return instance;
    }

    public void studentSelect(StudentDTO studentDTO) {
        System.out.println("<UNK> ID : " + studentDTO.getStudentId());
        System.out.println("<UNK> : " + studentDTO.getName());
        System.out.println("<UNK> : " + studentDTO.getBirthday());
        System.out.println("<UNK> : " + studentDTO.getPhone());
    }
}
