package jdbc.mvc.service;

import jdbc.mvc.dto.BookDTO;

import java.util.List;

public interface BookService {

    // 1.도서 추가
    public int saveBook(BookDTO bookDTO);

    // 2.도서 수정
    public int modifyBook(int bookId, BookDTO bookDTO);

    // 3.도서 삭제
    public int removeBook(int bookId);

    // 4.도서아디 조회
    public BookDTO findById(int bookId);

    // 5.도서제목 조회
    public List<BookDTO> findByTitle(String title);

    // 6.전체목록 조회
    public List<BookDTO> findAll();
}
