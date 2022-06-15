package chat.model.dto;

public class Register {
	private String subjectName;
	private String memberName;
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Register(String subjectName, String memberName) {
		super();
		this.subjectName = subjectName;
		this.memberName = memberName;
	}
	@Override
	public String toString() {
		return "Register [subjectName=" + subjectName + ", memberName=" + memberName + "]";
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
}
