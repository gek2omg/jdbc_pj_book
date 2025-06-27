package jdbc.mvc.domain.student.dao;

import jdbc.mvc.domain.student.dto.StudentDTO;

import java.util.List;

public interface StudentDAO {
    public int insert(StudentDTO studentDTO);
    public int update(StudentDTO studentDTO);
    public int delete(int studentId);
    public StudentDTO selectFindById(int studentId);
    public List<StudentDTO> selectFindByName(String name);
    public List<StudentDTO> selectFindByAll();
}
