package jdbc.mvc.service;

import jdbc.mvc.dao.BookDAO;
import jdbc.mvc.dao.BookDAOImpl;
import jdbc.mvc.dto.BookDTO;

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
    public int saveBook(BookDTO bookDTO) {
        System.out.println("BookServiceImpl - bookInsert()");

        int result = bookDAO.insertBook(bookDTO);

        return result;
    }

    @Override
    public int modifyBook(int bookId, BookDTO bookDTO) {
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
    public BookDTO findBookById(int bookId) {
        System.out.println("BookServiceImpl - findBookById()");

        BookDTO book = bookDAO.selectBookFindById(bookId);
        return book;
    }

    @Override
    public List<BookDTO> findBookByTitle(String title) {
        System.out.println("BookServiceImpl - findBookByTitle()");

        List<BookDTO> book = bookDAO.selectBookFindByTitle(title);
        return book;
    }

    @Override
    public List<BookDTO> findBookAll() {
        System.out.println("BookServiceImpl - findBookAll()");

        List<BookDTO> books = bookDAO.selectBookFindAll();
        return books;
    }
}
