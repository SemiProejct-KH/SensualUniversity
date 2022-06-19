package board.model.dto;

import java.sql.Date;

public class BoardComment {

	private int commentNo;
	private String memberId;
	private String content;
	private int boardNo;
	private Date regDate;
	
	public BoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardComment(int commentNo, String memberId, String content, int boardNo, Date regDate) {
		super();
		this.commentNo = commentNo;
		this.memberId = memberId;
		this.content = content;
		this.boardNo = boardNo;
		this.regDate = regDate;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "BoardComment [commentNo=" + commentNo + ", memberId=" + memberId + ", content=" + content + ", boardNo="
				+ boardNo + ", regDate=" + regDate + "]";
	}
	
}
