package jdbc.mvc.dao;

import jdbc.mvc.dto.BookDTO;

import java.util.List;

// DAO - DB 처리(DB 연결, 데이터 CRUD)
public interface BookDAO {

    // 1. 도서 추가
    public int bookInsert(BookDTO bookDTO);

    // 2. 도서 수정
    public int bookUpdate(BookDTO bookDTO);

    // 3. 도서 삭제
    public int bookDelete(int bookId);

    // 4. 도서 아이디 조회
    public BookDTO bookFindById(int bookId);

    // 5. 도서제목 조회
    public List<BookDTO> bookFindByTitle(String title);

    // 6. 전체목록 조회
    public List<BookDTO> bookFindAll();

}
