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
    BookService bookService = new BookServiceImpl();
    BookView bookView = new BookView();

    // 1.도서 추가
    public void bookInsert(BookDTO bookDTO) {
        System.out.println("<<< bookInsert() >>>");
        System.out.println(bookDTO);

        int insertCnt = bookService.saveBook(bookDTO);

        if (insertCnt == 1) {
            System.out.println("도서 정보 추가 성공!!" + insertCnt);
        } else {
            bookView.bookErrorMsg("insert");
        }
    }

    // 2.도서 수정
    public void bookUpdate(int bookId, BookDTO bookDTO) {
        System.out.println("<<< bookUpdate() >>>");
        System.out.println(bookDTO);

        int updateCnt = bookService.modifyBook(bookId, bookDTO);

        if (updateCnt == 1) {
            System.out.println("도서 정보 수정 성공!!" + updateCnt);
        } else {
            bookView.bookErrorMsg("update");
        }
    }

    // 3.도서 삭제
    public void bookDelete(int bookId) {
        System.out.println("<<< bookDelete() >>>");

        int deleteCnt = bookService.removeBook(bookId);
        if (deleteCnt == 1) {
            System.out.println("도서 정보 삭제 성공!!" + deleteCnt);
        } else {
            bookView.bookErrorMsg("delete");
        }
    }

    // 4.도서아디 조회
    public void bookFindById(int bookId) {
        System.out.println("<<< bookFindId() >>>");
        BookDTO book = bookService.findById(bookId);
        if(book != null) {
            System.out.println("번호\t제목\t저자\t출판사\t출판일");
            System.out.print(book.getBookId()+"\t");
            System.out.print(book.getTitle()+"\t");
            System.out.print(book.getAuthor()+"\t");
            System.out.print(book.getPublisher()+"\t");
            System.out.print(book.getPubdate()+"\t");
            System.out.println();
        } else {
            bookView.bookErrorMsg("select");
        }
    }

    // 5.도서제목 조회
    public void bookFindByTitle(String title) {
        System.out.println("<<< bookFindByTitle() >>>");
        List<BookDTO> bookDTOList = bookService.findByTitle(title);
        if (bookDTOList != null) {
            System.out.println("도서 정보 제목 조회 성공!!");
            for (BookDTO bookDTO : bookDTOList) {
                System.out.println("도서:!!" + bookDTO);
            }
        } else {
            bookView.bookErrorMsg("select");
        }

    }

    // 6.전체목록 조회
    public void bookFindAll() {
        System.out.println("<<< bookFindAll() >>>");
        List<BookDTO> bookDTOList = bookService.findAll();
        if (bookDTOList != null) {
            System.out.println("도서 정보 전체 조회 성공!!");
            for (BookDTO bookDTO : bookDTOList) {
                System.out.println("도서!!" + bookDTO);
            }
        } else {
            bookView.bookErrorMsg("select");
        }
    }

}
