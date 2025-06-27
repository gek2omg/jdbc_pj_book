package jdbc.mvc.dao;

import jdbc.mvc.dto.BookDTO;
import jdbc.mvc.dto.StudentDTO;
import jdbc.mvc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private static StudentDAOImpl instance;

    Connection conn = null;         // 오라클 연결
    PreparedStatement pstmt = null; // SQL 문장
    ResultSet rs = null;            // SQL 실행결과(SELECT 절에서만 사용)

    public StudentDAOImpl() {
    }

    public static StudentDAOImpl getInstance() {
        if (instance == null) {
            instance = new StudentDAOImpl();
        }
        return instance;
    }

    @Override
    public int insert(StudentDTO studentDTO) {
        System.out.println("insert");
        String query = """
                        INSERT INTO mvc_student_tbl(studentId, name, birthday, phone)
                        VALUES ((SELECT NVL(MAX(studentId) + 1, 1) FROM MVC_STUDENT_TBL), ?, ?, ?)
        """;

        int result  = 0;

        try {
            conn = DBUtil.getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성

            pstmt.setString(1, studentDTO.getName());     // 1은 ? 물음표 위치
            pstmt.setString(2, studentDTO.getBirthday());
            pstmt.setString(3, studentDTO.getPhone());

            result = pstmt.executeUpdate();  // 입력, 수정, 삭제 등의 SQL 실행 => 1:성공, 0:실패
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public int update(StudentDTO studentDTO) {
        int result  = 0;

        String query = """
                        UPDATE mvc_student_tbl 
                           SET name = ?, birthday = ?, phone = ?
                         WHERE studentId = ?
        """;

        try {
            conn = DBUtil.getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setString(1, studentDTO.getName());     // 1은 ?물음표 위치
            pstmt.setString(2, studentDTO.getBirthday());
            pstmt.setString(3, studentDTO.getPhone());
            pstmt.setInt(4, studentDTO.getStudentId());

            result = pstmt.executeUpdate();  // 입력, 수정, 삭제 등의 SQL 실행 => 1:성공, 0:실패
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public int delete(int studentId) {
        int result  = 0;

        String query = """
                        DELETE mvc_student_tbl 
                         WHERE studentId = ?
        """;

        try {
            conn = DBUtil.getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setInt(1, studentId);

            result = pstmt.executeUpdate();  // 입력, 수정, 삭제 등의 SQL 실행 => 1:성공, 0:실패
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public StudentDTO selectFindById(int studentId) {
        StudentDTO studentDTO = null;
        String query = """
                        SELECT * FROM mvc_student_tbl 
                         WHERE studentId = ?
        """;

        try {
            conn = DBUtil.getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setInt(1, studentId);

            rs = pstmt.executeQuery();
            if(rs.next()) {
//                studentDTO = new BookDTO();
                studentDTO.setStudentId(rs.getInt("studentId"));
                studentDTO.setName(rs.getString("name"));
                studentDTO.setBirthday(rs.getString("birthday"));
                studentDTO.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return studentDTO;
    }

    @Override
    public List<StudentDTO> selectFindByName(String name) {
        List<StudentDTO> studentDTOList = new ArrayList<>();

        String query = """
                            SELECT * 
                              FROM mvc_student_tbl 
                             WHERE name LIKE '%' || ? || '%' 
                             ORDER BY studentId DESC
        """;

        try {
            conn = DBUtil.getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setStudentId(rs.getInt("studentId"));
                studentDTO.setName(rs.getString("name"));
                studentDTO.setBirthday(rs.getString("birthday"));
                studentDTO.setPhone(rs.getString("phone"));
                studentDTOList.add(studentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return studentDTOList;
    }

    @Override
    public List<StudentDTO> selectFindByAll() {
        List<StudentDTO> studentDTOList = new ArrayList<>();

        String query = """
                            SELECT * 
                              FROM mvc_student_tbl 
                             ORDER BY studentId DESC
        """;

        try {
            conn = DBUtil.getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성

            rs = pstmt.executeQuery();

            if(rs.next()) {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setStudentId(rs.getInt("studentId"));
                studentDTO.setName(rs.getString("name"));
                studentDTO.setBirthday(rs.getString("birthday"));
                studentDTO.setPhone(rs.getString("phone"));
                studentDTOList.add(studentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return studentDTOList;
    }
}
