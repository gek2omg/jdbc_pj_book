package jdbc.mvc.domain.student.service;

import jdbc.mvc.domain.student.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    public int createStudent(StudentDTO studentDTO);
    public int updateStudent(int studentId, StudentDTO studentDTO);
    public int removeStudent(int studentId);
    public StudentDTO getStudentById(int studentId);
    public List<StudentDTO> getStudentByName(String name);
    public List<StudentDTO> getStudentAll();
}
