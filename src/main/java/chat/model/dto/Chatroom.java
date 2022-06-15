package chat.model.dto;

public class Chatroom {
	private int chatroomNo;
	private int memberNo;
	public Chatroom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Chatroom(int chatroomNo, int memberNo) {
		super();
		this.chatroomNo = chatroomNo;
		this.memberNo = memberNo;
	}
	@Override
	public String toString() {
		return "Chatroom [chatroomNo=" + chatroomNo + ", memberNo=" + memberNo + "]";
	}
	public int getChatroomNo() {
		return chatroomNo;
	}
	public void setChatroomNo(int chatroomNo) {
		this.chatroomNo = chatroomNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
}
