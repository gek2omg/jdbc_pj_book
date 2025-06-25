package jdbc.mvc.dao;

import jdbc.mvc.dto.BookDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BookDAO 구현체
 * 데이터베이스와 직접 연동하여 도서 정보 처리
 */
public class BookDAOImpl implements BookDAO {

    private static BookDAOImpl instance;

    private BookDAOImpl() {}

    public static BookDAOImpl getInstance() {
        if (instance == null) {
            instance = new BookDAOImpl();
        }
        return instance;
    }

    // DB 연결
    String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    String dbID = "scott_05";       // 계정
    String dbPassword = "tiger";    // 비밀번호

    Connection conn = null;         // 오라클 연결
    PreparedStatement pstmt = null; // SQL 문장
    ResultSet rs = null;            // SQL 실행결과(SELECT 절에서만 사용)

    // 데이터베이스 접속
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbID, dbPassword);
    }

    // 1. 도서 추가
    @Override
    public int insertBook(BookDTO bookDTO) {
        System.out.println("BookServiceImpl - bookInsert()");

        int result  = 0;

        String query = "INSERT INTO mvc_book_tbl(bookid, title, author, publisher, price) "
                        + "VALUES ((SELECT NVL(MAX(bookId) + 1, 1) FROM MVC_BOOK_TBL MBT), ?, ?, ?, ?)";

        try {
            conn = getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setString(1, bookDTO.getTitle());     // 1은 ?물음표 위치
            pstmt.setString(2, bookDTO.getAuthor());
            pstmt.setString(3, bookDTO.getPublisher());
            pstmt.setInt(4, bookDTO.getPrice());

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

    // 2. 도서 수정
    @Override
    public int updateBook(BookDTO bookDTO) {
        int result  = 0;

        String query = "UPDATE mvc_book_tbl SET title = ?, author = ?, publisher = ?, price = ? WHERE bookid = ?";

        try {
            conn = getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setString(1, bookDTO.getTitle());     // 1은 ?물음표 위치
            pstmt.setString(2, bookDTO.getAuthor());
            pstmt.setString(3, bookDTO.getPublisher());
            pstmt.setInt(4, bookDTO.getPrice());
            pstmt.setInt(5, bookDTO.getBookId());

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

    // 도서 삭제
    @Override
    public int deleteBook(int bookId) {
        int result  = 0;

        String query = "DELETE mvc_book_tbl WHERE bookid = ?";

        try {
            conn = getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setInt(1, bookId);

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
    public BookDTO selectBookFindById(int bookId) {
        BookDTO book = null;
        String query = "SELECT * FROM mvc_book_tbl WHERE bookid = ?";

        try {
            conn = getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setInt(1, bookId);

            rs = pstmt.executeQuery();
            if(rs.next()) {
                book = new BookDTO();
                book.setBookId(rs.getInt("bookid"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPrice(rs.getInt("price"));
                book.setPubdate(String.valueOf(rs.getDate("pubdate")));
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

        return book;
    }

    @Override
    public List<BookDTO> selectBookFindByTitle(String title) {
        List<BookDTO> bookDTOList = new ArrayList<>();

        String query = "SELECT * FROM mvc_book_tbl WHERE title LIKE '%' || ? || '%' ORDER BY pubdate DESC";

        try {
            conn = getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setString(1, title);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                BookDTO book = new BookDTO();
                book.setBookId(rs.getInt("bookid"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPrice(rs.getInt("price"));
                book.setPubdate(String.valueOf(rs.getDate("pubdate")));
                bookDTOList.add(book);
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

        return bookDTOList;
    }

    @Override
    public List<BookDTO> selectBookFindAll() {
        List<BookDTO> bookDTOList = new ArrayList<>();

        String query = "SELECT * FROM mvc_book_tbl ORDER BY pubdate DESC";

        try {
            conn = getConnection();   // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성

            rs = pstmt.executeQuery();

            while(rs.next()) {
                BookDTO book = new BookDTO();
                book.setBookId(rs.getInt("bookid"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPrice(rs.getInt("price"));
                book.setPubdate(String.valueOf(rs.getDate("pubdate")));
                bookDTOList.add(book);
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

        return bookDTOList;
    }
}
