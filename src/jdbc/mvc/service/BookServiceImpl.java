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
        System.out.println("BookServiceImpl - bookUpdate()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        bookDTO.setBookId(bookId);
        int updateCnt = bookDAOImpl.updateBook(bookDTO);

        return updateCnt;
    }

    @Override
    public int removeBook(int bookId) {
        System.out.println("BookServiceImpl - bookDelete()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        int deleteCnt = bookDAOImpl.deleteBook(bookId);

        return deleteCnt;
    }

    @Override
    public BookDTO findById(int bookId) {
        System.out.println("BookServiceImpl - bookFindById()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        BookDTO book = bookDAOImpl.condFindById(bookId);
        return book;
    }

    @Override
    public List<BookDTO> findByTitle(String title) {
        System.out.println("BookServiceImpl - bookFindTitle()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        List<BookDTO> book = bookDAOImpl.condFindByTitle(title);
        return book;
    }

    @Override
    public List<BookDTO> findAll() {
        System.out.println("BookServiceImpl - bookFindAll()");

        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        List<BookDTO> book = bookDAOImpl.condFindAll();
        return book;
    }
}
