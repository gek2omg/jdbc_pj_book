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
    public int createStudent(StudentDTO studentDTO) {
        return studentDAO.insertBook(studentDTO);
    }

    @Override
    public int updateStudent(int studentId, StudentDTO studentDTO) {
        studentDTO.setStudentId(studentId);
        return studentDAO.updateBook(studentDTO);
    }

    @Override
    public int removeStudent(int studentId) {
        return studentDAO.deleteBook(studentId);
    }

    @Override
    public StudentDTO getStudentById(int studentId) {
        return studentDAO.findById(studentId);
    }

    @Override
    public List<StudentDTO> getStudentByName(String name) {
        return studentDAO.findByName(name);
    }

    @Override
    public List<StudentDTO> getStudentAll() {
        return studentDAO.findAll();
    }
}
