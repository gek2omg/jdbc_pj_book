package jdbc.mvc.service;

import jdbc.mvc.dao.BookDAOImpl;
import jdbc.mvc.dto.BookDTO;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public int bookInsert(BookDTO bookDTO) {
        System.out.println("BookServiceImpl - bookInsert()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        int insertCnt = bookDAOImpl.bookInsert(bookDTO);

        return insertCnt;
    }

    @Override
    public int bookUpdate(int bookId, BookDTO bookDTO) {
        System.out.println("BookServiceImpl - bookUpdate()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        bookDTO.setBookId(bookId);
        int updateCnt = bookDAOImpl.bookUpdate(bookDTO);

        return updateCnt;
    }

    @Override
    public int bookDelete(int bookId) {
        System.out.println("BookServiceImpl - bookDelete()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        int deleteCnt = bookDAOImpl.bookDelete(bookId);

        return deleteCnt;
    }

    @Override
    public BookDTO bookFindById(int bookId) {
        System.out.println("BookServiceImpl - bookFindById()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        BookDTO book = bookDAOImpl.bookFindById(bookId);
        return book;
    }

    @Override
    public List<BookDTO> bookFindTitle(String title) {
        System.out.println("BookServiceImpl - bookFindTitle()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        List<BookDTO> book = bookDAOImpl.bookFindByTitle(title);
        return book;
    }

    @Override
    public List<BookDTO> bookFindAll() {
        System.out.println("BookServiceImpl - bookFindAll()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        List<BookDTO> book = bookDAOImpl.bookFindAll();
        return book;
    }
}
