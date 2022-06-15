package notice.model.dto;

public class NoticeAttachment {
	private int noticeAttachmentNo;
	private int noticeNo;
	private String noticeAttachmentOriginalFilename;
	private String noticeAttachmentRenameFilename;
	
	public NoticeAttachment() {
		super();
	}
	
	public NoticeAttachment(int noticeAttachmentNo, int noticeNo, String noticeAttachmentOriginalFilename,
			String noticeAttachmentRenameFilename) {
		super();
		this.noticeAttachmentNo = noticeAttachmentNo;
		this.noticeNo = noticeNo;
		this.noticeAttachmentOriginalFilename = noticeAttachmentOriginalFilename;
		this.noticeAttachmentRenameFilename = noticeAttachmentRenameFilename;
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
	public String getNoticeAttachmentOriginalFilename() {
		return noticeAttachmentOriginalFilename;
	}
	public void setNoticeAttachmentOriginalFilename(String noticeAttachmentOriginalFilename) {
		this.noticeAttachmentOriginalFilename = noticeAttachmentOriginalFilename;
	}
	public String getNoticeAttachmentRenameFilename() {
		return noticeAttachmentRenameFilename;
	}
	public void setNoticeAttachmentRenameFilename(String noticeAttachmentRenameFilename) {
		this.noticeAttachmentRenameFilename = noticeAttachmentRenameFilename;
	}
	
	@Override
	public String toString() {
		return "NoticeAttachment [noticeAttachmentNo=" + noticeAttachmentNo + ", noticeNo=" + noticeNo
				+ ", noticeAttachmentOriginalFilename=" + noticeAttachmentOriginalFilename
				+ ", noticeAttachmentRenameFilename=" + noticeAttachmentRenameFilename + "]";
	}
	
	
}
