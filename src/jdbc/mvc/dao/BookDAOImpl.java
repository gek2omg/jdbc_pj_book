package jdbc.mvc.dao;

import jdbc.mvc.dto.BookDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    // 싱글톤
    private static BookDAOImpl instance = new BookDAOImpl();

    private BookDAOImpl() {}

    public static BookDAOImpl getInstance() {
        return instance;
    }

    // DB 연결
    String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    String dbID = "scott_05";       // 계정
    String dbPassword = "tiger";    // 비밀번호

    Connection conn = null;         // 오라클 연결
    PreparedStatement pstmt = null; // SQL 문장
    ResultSet rs = null;            // SQL 실행결과(SELECT 절에서만 사용)

    // 1. 도서 추가
    @Override
    public int bookInsert(BookDTO bookDTO) {
        System.out.println("BookServiceImpl - bookInsert()");

        int insertCnt  = 0;

        String query = "INSERT INTO mvc_book_tbl(bookid, title, author, publisher, price) "
                        + "VALUES ((SELECT NVL(MAX(bookId) + 1, 1) FROM MVC_BOOK_TBL MBT), ?, ?, ?, ?)";

        try {
            conn = DriverManager.getConnection(dbUrl, dbID, dbPassword);    // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setString(1, bookDTO.getTitle());     // 1은 ?물음표 위치
            pstmt.setString(2, bookDTO.getAuthor());
            pstmt.setString(3, bookDTO.getPublisher());
            pstmt.setInt(4, bookDTO.getPrice());

            insertCnt = pstmt.executeUpdate();  // 입력, 수정, 삭제 등의 SQL 실행 => 1:성공, 0:실패
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

        return insertCnt;
    }

    // 2. 도서 수정
    @Override
    public int bookUpdate(BookDTO bookDTO) {
        int updateCnt  = 0;

        String query = "UPDATE mvc_book_tbl SET title = ?, author = ?, publisher = ?, price = ? WHERE bookid = ?";

        try {
            conn = DriverManager.getConnection(dbUrl, dbID, dbPassword);    // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setString(1, bookDTO.getTitle());     // 1은 ?물음표 위치
            pstmt.setString(2, bookDTO.getAuthor());
            pstmt.setString(3, bookDTO.getPublisher());
            pstmt.setInt(4, bookDTO.getPrice());
            pstmt.setInt(5, bookDTO.getBookId());

            updateCnt = pstmt.executeUpdate();  // 입력, 수정, 삭제 등의 SQL 실행 => 1:성공, 0:실패
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

        return updateCnt;
    }

    // 도서 삭제
    @Override
    public int bookDelete(int bookId) {
        int deleteCnt  = 0;

        String query = "DELETE mvc_book_tbl WHERE bookid = ?";

        try {
            conn = DriverManager.getConnection(dbUrl, dbID, dbPassword);    // 오라클 연결
            pstmt = conn.prepareStatement(query);   // SQL 작성
            pstmt.setInt(1, bookId);

            deleteCnt = pstmt.executeUpdate();  // 입력, 수정, 삭제 등의 SQL 실행 => 1:성공, 0:실패
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

        return deleteCnt;
    }

    @Override
    public BookDTO bookFindById(int bookId) {
        BookDTO book = null;
        String query = "SELECT * FROM mvc_book_tbl WHERE bookid = ?";

        try {
            conn = DriverManager.getConnection(dbUrl, dbID, dbPassword);    // 오라클 연결
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
    public List<BookDTO> bookFindByTitle(String title) {
        List<BookDTO> bookDTOList = new ArrayList<>();

        String query = "SELECT * FROM mvc_book_tbl WHERE title LIKE '%' || ? || '%' ORDER BY pubdate DESC";

        try {
            conn = DriverManager.getConnection(dbUrl, dbID, dbPassword);    // 오라클 연결
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
    public List<BookDTO> bookFindAll() {
        List<BookDTO> bookDTOList = new ArrayList<>();

        String query = "SELECT * FROM mvc_book_tbl ORDER BY pubdate DESC";

        try {
            conn = DriverManager.getConnection(dbUrl, dbID, dbPassword);    // 오라클 연결
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
