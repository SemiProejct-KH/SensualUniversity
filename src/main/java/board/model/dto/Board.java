package board.model.dto;

import java.sql.Date;

public class Board {
	private int boardNo;
	private String memberId;
	private String boardTitle;
	private String boardContent;
	private Date boardDate;
	private int boardReadCount;
	private String boardCategory;
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int boardNo, String memberId, String boardTitle, String boardContent, Date boardDate,
			int boardReadCount, String boardCategory) {
		super();
		this.boardNo = boardNo;
		this.memberId = memberId;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.boardReadCount = boardReadCount;
		this.boardCategory = boardCategory;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public int getBoardReadCount() {
		return boardReadCount;
	}
	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}
	public String getBoardCategory() {
		return boardCategory;
	}
	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", memberId=" + memberId + ", boardTitle=" + boardTitle + ", boardContent="
				+ boardContent + ", boardDate=" + boardDate + ", boardReadCount=" + boardReadCount + ", boardCategory="
				+ boardCategory + "]";
	}
	
}
