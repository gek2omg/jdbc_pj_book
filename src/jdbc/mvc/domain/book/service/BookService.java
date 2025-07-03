package jdbc.mvc.domain.book.service;

import jdbc.mvc.domain.book.dto.BookDTO;

import java.util.List;

public interface BookService {

    // 1.도서 추가
    public int createBook(BookDTO bookDTO);

    // 2.도서 수정
    public int updateBook(int bookId, BookDTO bookDTO);

    // 3.도서 삭제
    public int removeBook(int bookId);

    // 4.도서아디 조회
    public BookDTO getBookById(int bookId);

    // 5.도서제목 조회
    public List<BookDTO> getBookByTitle(String title);

    // 6.전체목록 조회
    public List<BookDTO> getBookAll();
}
