package jdbc.mvc.domain.student.service;

import jdbc.mvc.domain.student.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    public int save(StudentDTO studentDTO);
    public int modify(int studentId, StudentDTO studentDTO);
    public int remove(int studentId);
    public StudentDTO findById(int studentId);
    public List<StudentDTO> findByName(String name);
    public List<StudentDTO> findAll();
}
