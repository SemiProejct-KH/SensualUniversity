package chat.model.dto;

public class Register {
	private String subjectName;

	public Register() {
		super();
	}

	public Register(String subjectName) {
		super();
		this.subjectName = subjectName;
	}

	@Override
	public String toString() {
		return "Register [subjectName=" + subjectName + "]";
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
