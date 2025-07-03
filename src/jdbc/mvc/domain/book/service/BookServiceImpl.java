package jdbc.mvc.domain.book.service;

import jdbc.mvc.domain.book.dao.BookDAO;
import jdbc.mvc.domain.book.dao.BookDAOImpl;
import jdbc.mvc.domain.book.dto.BookDTO;

import java.util.List;

public class BookServiceImpl implements BookService {
    private static BookServiceImpl instance;
    private final BookDAO bookDAO;

    public BookServiceImpl() {
        this.bookDAO = BookDAOImpl.getInstance();
    }

    public static BookServiceImpl getInstance() {
        if (instance == null) {
            instance = new BookServiceImpl();
        }
        return instance;
    }

    @Override
    public int createBook(BookDTO bookDTO) {
        System.out.println("BookServiceImpl - bookInsert()");

        int result = bookDAO.insertBook(bookDTO);

        return result;
    }

    @Override
    public int updateBook(int bookId, BookDTO bookDTO) {
        System.out.println("BookServiceImpl - modifyBook()");

        bookDTO.setBookId(bookId);
        int result = bookDAO.updateBook(bookDTO);

        return result;
    }

    @Override
    public int removeBook(int bookId) {
        System.out.println("BookServiceImpl - removeBook()");

        int deleteCnt = bookDAO.deleteBook(bookId);

        return deleteCnt;
    }

    @Override
    public BookDTO getBookById(int bookId) {
        System.out.println("BookServiceImpl - findBookById()");

        BookDTO book = bookDAO.findById(bookId);
        return book;
    }

    @Override
    public List<BookDTO> getBookByTitle(String title) {
        System.out.println("BookServiceImpl - findBookByTitle()");

        List<BookDTO> book = bookDAO.findByTitle(title);
        return book;
    }

    @Override
    public List<BookDTO> getBookAll() {
        System.out.println("BookServiceImpl - findBookAll()");

        List<BookDTO> books = bookDAO.findAll();
        return books;
    }
}
