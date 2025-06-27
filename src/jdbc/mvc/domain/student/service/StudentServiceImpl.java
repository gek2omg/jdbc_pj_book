package jdbc.mvc.domain.student.service;

import jdbc.mvc.domain.student.dao.StudentDAO;
import jdbc.mvc.domain.student.dao.StudentDAOImpl;
import jdbc.mvc.domain.student.dto.StudentDTO;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private static StudentServiceImpl instance;
    private StudentDAO studentDAO;

    public StudentServiceImpl() {
        studentDAO = StudentDAOImpl.getInstance();
    }

    public static StudentServiceImpl getInstance() {
        if (instance == null) {
            instance = new StudentServiceImpl();
        }
        return instance;
    }

    @Override
    public int save(StudentDTO studentDTO) {
        return studentDAO.insert(studentDTO);
    }

    @Override
    public int modify(int studentId, StudentDTO studentDTO) {
        studentDTO.setStudentId(studentId);
        return studentDAO.update(studentDTO);
    }

    @Override
    public int remove(int studentId) {
        return studentDAO.delete(studentId);
    }

    @Override
    public StudentDTO findById(int studentId) {
        return studentDAO.selectFindById(studentId);
    }

    @Override
    public List<StudentDTO> findByName(String name) {
        return studentDAO.selectFindByName(name);
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentDAO.selectFindByAll();
    }
}
