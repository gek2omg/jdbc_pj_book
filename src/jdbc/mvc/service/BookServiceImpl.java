package jdbc.mvc.service;

import jdbc.mvc.dao.BookDAOImpl;
import jdbc.mvc.dto.BookDTO;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public int saveBook(BookDTO bookDTO) {
        System.out.println("BookServiceImpl - bookInsert()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        int insertCnt = bookDAOImpl.insertBook(bookDTO);

        return insertCnt;
    }

    @Override
    public int modifyBook(int bookId, BookDTO bookDTO) {
        System.out.println("BookServiceImpl - modifyBook()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        bookDTO.setBookId(bookId);
        int updateCnt = bookDAOImpl.updateBook(bookDTO);

        return updateCnt;
    }

    @Override
    public int removeBook(int bookId) {
        System.out.println("BookServiceImpl - removeBook()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        int deleteCnt = bookDAOImpl.deleteBook(bookId);

        return deleteCnt;
    }

    @Override
    public BookDTO findBookById(int bookId) {
        System.out.println("BookServiceImpl - findBookById()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        BookDTO book = bookDAOImpl.selectBookFindById(bookId);
        return book;
    }

    @Override
    public List<BookDTO> findBookByTitle(String title) {
        System.out.println("BookServiceImpl - findBookByTitle()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        List<BookDTO> book = bookDAOImpl.selectBookFindByTitle(title);
        return book;
    }

    @Override
    public List<BookDTO> findBookAll() {
        System.out.println("BookServiceImpl - findBookAll()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        List<BookDTO> book = bookDAOImpl.selectBookFindAll();
        return book;
    }
}
