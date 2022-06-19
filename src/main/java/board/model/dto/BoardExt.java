package board.model.dto;

import java.sql.Date;
import java.util.List;

import notice.model.dto.NoticeAttachment;

public class BoardExt extends Board{
	private int boardAttachCount;
	private List<BoardAttachment> boardAttachments;
	private List<BoardComment> comments;
	
	public BoardExt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardExt(int boardNo, String memberId, String boardTitle, String boardContent, Date boardDate,
			int boardReadCount, String boardCategory) {
		super(boardNo, memberId, boardTitle, boardContent, boardDate, boardReadCount, boardCategory);
		// TODO Auto-generated constructor stub
	}
	public BoardExt(int boardAttachCount, List<BoardAttachment> boardAttachments) {
		super();
		this.boardAttachCount = boardAttachCount;
		this.boardAttachments = boardAttachments;
	}
	public int getBoardAttachCount() {
		return boardAttachCount;
	}
	public void setBoardAttachCount(int boardAttachCount) {
		this.boardAttachCount = boardAttachCount;
	}
	public List<BoardAttachment> getBoardAttachments() {
		return boardAttachments;
	}
	public void setBoardAttachments(List<BoardAttachment> boardAttachments) {
		this.boardAttachments = boardAttachments;
	}
	public void setBoardComments(List<BoardComment> comments) {
		this.comments = comments;
	}
	public List<BoardComment> getBoardComments() {
		return comments;
	}
	
	@Override
	public String toString() {
		return "BoardExt [boardAttachCount=" + boardAttachCount + ", boardAttachments=" + boardAttachments
				+ ", comments=" + comments + "]";
	}
			
}
