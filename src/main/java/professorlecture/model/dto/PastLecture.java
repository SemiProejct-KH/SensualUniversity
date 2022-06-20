package professorlecture.model.dto;

public class PastLecture {
	private String subjectNo;
	private String pastLecture;
	
	public PastLecture() {
		super();
	}

	public PastLecture(String subjectNo, String pastLecture) {
		super();
		this.subjectNo = subjectNo;
		this.pastLecture = pastLecture;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getPastLecture() {
		return pastLecture;
	}

	public void setPastLecture(String pastLecture) {
		this.pastLecture = pastLecture;
	}

	@Override
	public String toString() {
		return "PastLecture [subjectNo=" + subjectNo + ", pastLecture=" + pastLecture + "]";
	}
	
}
