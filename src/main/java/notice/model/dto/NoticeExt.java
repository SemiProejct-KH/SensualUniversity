package notice.model.dto;

import java.util.List;

public class NoticeExt extends Notice {
    private String memberName;
	private int noticeAttachCount;
	private List<NoticeAttachment> NoticeAttachments;
	
	
	public NoticeExt() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NoticeExt(String memberName, int noticeAttachCount) {
		super();
		this.memberName = memberName;
		this.noticeAttachCount = noticeAttachCount;
	}
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getNoticeAttachCount() {
		return noticeAttachCount;
	}
	public void setNoticeAttachCount(int noticeAttachCount) {
		this.noticeAttachCount = noticeAttachCount;
	}

	
	public List<NoticeAttachment> getNoticeAttachments() {
		return NoticeAttachments;
	}

	public void setNoticeAttachments(List<NoticeAttachment> noticeAttachments) {
		NoticeAttachments = noticeAttachments;
	}

	@Override
	public String toString() {
		return "NoticeExt [memberName=" + memberName + ", noticeAttachCount=" + noticeAttachCount
				+ ", Noticeattachments=" + NoticeAttachments + "]";
	}

}
