package professorlecture.model.dto;

public class PresentLecture {
	
	private String subjectNo;
	private String presentLecture;
	public PresentLecture() {
		super();
	}
	public PresentLecture(String subjectNo, String presentLecture) {
		super();
		this.subjectNo = subjectNo;
		this.presentLecture = presentLecture;
	}
	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}
	public String getPresentLecture() {
		return presentLecture;
	}
	public void setPresentLecture(String presentLecture) {
		this.presentLecture = presentLecture;
	}
	@Override
	public String toString() {
		return "PresentLecture [subjectNo=" + subjectNo + ", presentLecture=" + presentLecture + "]";
	}

	
}
