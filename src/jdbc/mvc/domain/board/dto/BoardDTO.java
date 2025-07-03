package jdbc.mvc.domain.board.dto;

import java.sql.Date;

public class BoardDTO {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardId;
    private Date pubRegDate;

    public BoardDTO() {
        super();
    }

    public BoardDTO(int boardNo, String boardTitle, String boardContent, String boardId, Date pubRegDate) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardId = boardId;
        this.pubRegDate = pubRegDate;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public Date getPubRegDate() {
        return pubRegDate;
    }

    public void setPubRegDate(Date pubRegDate) {
        this.pubRegDate = pubRegDate;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "boardNo=" + boardNo +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                ", boardId='" + boardId + '\'' +
                ", pubRegDate=" + pubRegDate +
                '}';
    }
}
