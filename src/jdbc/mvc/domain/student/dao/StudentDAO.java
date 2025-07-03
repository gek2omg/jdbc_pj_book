package jdbc.mvc.domain.student.dao;

import jdbc.mvc.domain.student.dto.StudentDTO;

import java.util.List;

public interface StudentDAO {
    public int insertBook(StudentDTO studentDTO);
    public int updateBook(StudentDTO studentDTO);
    public int deleteBook(int studentId);
    public StudentDTO findById(int studentId);
    public List<StudentDTO> findByName(String name);
    public List<StudentDTO> findAll();
}
