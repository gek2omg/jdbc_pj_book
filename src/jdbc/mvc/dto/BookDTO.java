package jdbc.mvc.dto;

// DTO(데이터 전송 객체 : Data Transfer Object) => DB 테이블 : 멤버변수 : 화면 input 박스
public class BookDTO {

    // 멤버변수
    private int bookId;         // 도서번호(PK)
    private String title;       // 도서명
    private String author;      // 저자
    private String publisher;   // 출판사
    private int price;          // 가격
    private String pubdate;     // 출판일

    // 생성자
    public BookDTO() {
        super();
    }

    public BookDTO(int bookId, String title, String author, String publisher, int price, String pubdate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.pubdate = pubdate;
    }

    // 멤버메서드
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", pubdate='" + pubdate + '\'' +
                '}';
    }
}
