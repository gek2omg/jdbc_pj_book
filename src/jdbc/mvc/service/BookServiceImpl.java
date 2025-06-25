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

//        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        int insertCnt = bookDAO.insertBook(bookDTO);

        return insertCnt;
    }

    @Override
    public int modifyBook(int bookId, BookDTO bookDTO) {
        System.out.println("BookServiceImpl - modifyBook()");

//        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        bookDTO.setBookId(bookId);
        int updateCnt = bookDAO.updateBook(bookDTO);

        return updateCnt;
    }

    @Override
    public int removeBook(int bookId) {
        System.out.println("BookServiceImpl - removeBook()");

//        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        int deleteCnt = bookDAO.deleteBook(bookId);

        return deleteCnt;
    }

    @Override
    public BookDTO findBookById(int bookId) {
        System.out.println("BookServiceImpl - findBookById()");

//        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        BookDTO book = bookDAO.selectBookFindById(bookId);
        return book;
    }

    @Override
    public List<BookDTO> findBookByTitle(String title) {
        System.out.println("BookServiceImpl - findBookByTitle()");

//        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        List<BookDTO> book = bookDAO.selectBookFindByTitle(title);
        return book;
    }

    @Override
    public List<BookDTO> findBookAll() {
        System.out.println("BookServiceImpl - findBookAll()");

//        BookDAOImpl bookDAOImpl = BookDAOImpl.getInstance();
        List<BookDTO> book = bookDAO.selectBookFindAll();
        return book;
    }
}
