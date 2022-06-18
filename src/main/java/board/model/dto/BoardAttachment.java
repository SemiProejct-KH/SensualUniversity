package board.model.dto;

public class BoardAttachment {
	private int boardAttachmentNo;
	private int boardNo;
	private String originalFilename;
	private String renameFilename;
	public BoardAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardAttachment(int boardAttachmentNo, int boardNo, String originalFilename, String renameFilename) {
		super();
		this.boardAttachmentNo = boardAttachmentNo;
		this.boardNo = boardNo;
		this.originalFilename = originalFilename;
		this.renameFilename = renameFilename;
	}
	public int getBoardAttachmentNo() {
		return boardAttachmentNo;
	}
	public void setBoardAttachmentNo(int boardAttachmentNo) {
		this.boardAttachmentNo = boardAttachmentNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getRenameFilename() {
		return renameFilename;
	}
	public void setRenameFilename(String renameFilename) {
		this.renameFilename = renameFilename;
	}
	@Override
	public String toString() {
		return "BoardAttachment [boardAttachmentNo=" + boardAttachmentNo + ", boardNo=" + boardNo
				+ ", originalFilename=" + originalFilename + ", renameFilename=" + renameFilename + "]";
	}
	
	
}
