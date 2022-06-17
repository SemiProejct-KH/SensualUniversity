package notice.model.dto;

public class NoticeAttachment {
	private int noticeAttachmentNo;
	private int noticeNo;
	private String originalFilename;
	private String renameFilename;
	
	public NoticeAttachment() {
		super();
	}
	
	public NoticeAttachment(int noticeAttachmentNo, int noticeNo, String originalFilename,
			String renameFilename) {
		super();
		this.noticeAttachmentNo = noticeAttachmentNo;
		this.noticeNo = noticeNo;
		this.originalFilename = originalFilename;
		this.renameFilename = renameFilename;
	}
	
	public int getNoticeAttachmentNo() {
		return noticeAttachmentNo;
	}
	public void setNoticeAttachmentNo(int noticeAttachmentNo) {
		this.noticeAttachmentNo = noticeAttachmentNo;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
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
		return "NoticeAttachment [noticeAttachmentNo=" + noticeAttachmentNo + ", noticeNo=" + noticeNo
				+ ", OriginalFilename=" + originalFilename
				+ ", nRenameFilename=" + renameFilename + "]";
	}
	
	
}
