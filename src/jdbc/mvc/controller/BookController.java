package jdbc.mvc.controller;

import jdbc.mvc.dto.BookDTO;
import jdbc.mvc.service.BookService;
import jdbc.mvc.service.BookServiceImpl;
import jdbc.mvc.view.BookView;

import java.util.List;

/**
 * 작성일 : 2025.6.18<br>
 * 작성자 : 김동관<br>
 * 설명 : 도서 콘트롤러 - 고객의 요구사항(비즈니스 로직)에 대한 흐름제어를 담당한다.
 * Book 정보에 대한 추가,수정,삭제,도서아이디 조회, 전체목록 조회
 * BookController -> BookServiceImpl(BookService) -> BookDAOImple(BookDAO)
 */
public class BookController {
    private static BookController instance;
    private final BookService bookService;

    BookView bookView = BookView.getInstance();

    private BookController() {
        bookService = BookServiceImpl.getInstance();
    }

    public static BookController getInstance() {
        if (instance == null) {
            instance = new BookController();
        }
        return instance;
    }

    // 1.도서 추가
    public void saveBook(BookDTO bookDTO) {
        System.out.println("<<< saveBook() >>>");
        System.out.println(bookDTO);

        int insertCnt = bookService.saveBook(bookDTO);

        if (insertCnt == 1) {
            System.out.println("도서 정보 추가 성공!!" + insertCnt);
        } else {
            bookView.bookErrorMsg("insert");
        }
    }

    // 2.도서 수정
    public void modifyBook(int bookId, BookDTO bookDTO) {
        System.out.println("<<< modifyBook() >>>");

        int result = bookService.modifyBook(bookId, bookDTO);

        if (result == 1) {
            System.out.println("도서 정보 수정 성공!!" + result);
        } else {
            bookView.bookErrorMsg("update");
        }
    }

    // 3.도서 삭제
    public void removeBook(int bookId) {
        System.out.println("<<< removeBook() >>>");

        int result = bookService.removeBook(bookId);
        if (result == 1) {
            System.out.println("도서 정보 삭제 성공!!" + result);
            System.out.println("---------------------------------");
        } else {
            bookView.bookErrorMsg("delete");
        }
    }

    // 4.도서 아이디 조회
    public void findBookById(int bookId) {
        System.out.println("<<< findBookById() >>>");
        BookDTO book = bookService.findBookById(bookId);
        if(book != null) {
            System.out.println("도서 ID 조회 성공");
            System.out.println("---------------------------------");
            bookView.bookSelect(book);
        } else {
            bookView.bookErrorMsg("select");
        }
    }

    // 5.도서제목 조회
    public void findBookByTitle(String title) {
        System.out.println("<<< findBookByTitle() >>>");
        List<BookDTO> bookDTOList = bookService.findBookByTitle(title);
        if (bookDTOList != null) {
            System.out.println("도서 정보 제목 조회 성공!!");
            System.out.println("---------------------------------");
            bookView.bookList(bookDTOList);
        } else {
            bookView.bookErrorMsg("select");
        }

    }

    // 6.전체목록 조회
    public void findBookAll() {
        System.out.println("<<< findBookAll() >>>");
        List<BookDTO> bookDTOList = bookService.findBookAll();
        if (bookDTOList != null) {
            System.out.println("도서 정보 전체 조회 성공!!");
            System.out.println("---------------------------------");
            bookView.bookList(bookDTOList);
        } else {
            bookView.bookErrorMsg("select");
        }
    }

}
