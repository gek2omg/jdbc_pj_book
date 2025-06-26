package jdbc.mvc.view;

import jdbc.mvc.dto.BookDTO;

import java.util.List;

/**
 * 뷰 - 결과화면
 *      Book 에 정보 출력, Book 에러메시지, Book 결과 조회
 */
public class BookView {

    // 에러메시지
    public void bookErrorMsg(String msg) {
        switch (msg) {
            case "insert":
                System.out.println("도서 추가 실패!!");
                break;
            case "update":
                System.out.println("도서 수정 실패!!");
                break;
            case "delete":
                System.out.println("도서 삭제 실패!!");
                break;
            case "select":
                System.out.println("도서 조회 실패!!");
                break;
            default:
                System.out.println("ERROR!!");
        }
    }

    // 도서목록
    public void bookList(List<BookDTO> bookDTOList) {
        // 방법1. 향상된 for문 조회
        for (BookDTO bookDTO : bookDTOList) {
            bookSelect(bookDTO);
        }
        // 방법
    }

    // 1건
    public void bookSelect(BookDTO book) {
        System.out.println("도서 ID : " + book.getBookId());
        System.out.println("타이틀 : " + book.getTitle());
        System.out.println("저자 : " + book.getAuthor());
        System.out.println("출판사 : " + book.getPublisher());
        System.out.println("가격 : " + book.getPrice());
        System.out.println("출판일 : " + book.getPubdate());
        System.out.println("---------------------------------");
    }
}
