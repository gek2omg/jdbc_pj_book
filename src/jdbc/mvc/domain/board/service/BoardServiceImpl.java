package jdbc.mvc.domain.board.service;

import jdbc.mvc.domain.board.dao.BoardDAO;
import jdbc.mvc.domain.board.dao.BoardDAOImpl;
import jdbc.mvc.domain.board.dto.BoardDTO;
import jdbc.mvc.domain.book.service.BookServiceImpl;

import java.util.List;

public class BoardServiceImpl implements BoardService {

    private static BoardServiceImpl instance;
    private static BoardDAO boardDAO;

    private BoardServiceImpl() {
        this.boardDAO = BoardDAOImpl.getInstance();
    }

    public static BoardServiceImpl getInstance() {
        if (instance == null) {
            instance = new BoardServiceImpl();
        }
        return instance;
    }

    @Override
    public int createBoard(BoardDTO boardDTO) {
        return boardDAO.insertBoard(boardDTO);
    }

    @Override
    public int updateBoard(int boardNo, BoardDTO boardDTO) {
        boardDTO.setBoardNo(boardNo);
        return boardDAO.updateBoard(boardDTO);
    }

    @Override
    public int removeBoard(int boardNo) {
        return boardDAO.deleteBoard(boardNo);
    }

    @Override
    public BoardDTO getBoardById(int boardNo) {
        return boardDAO.findById(boardNo);
    }

    @Override
    public List<BoardDTO> getBoardByTitle(String title) {
        return boardDAO.findByTitle(title);
    }

    @Override
    public List<BoardDTO> getBoardAll() {
        return boardDAO.findAll();
    }
}
