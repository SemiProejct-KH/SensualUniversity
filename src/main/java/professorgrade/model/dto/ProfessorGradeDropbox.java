package professorgrade.model.dto;

public class ProfessorGradeDropbox {
	
	private String subjectNo;
	private String presentLecture;
	
	public ProfessorGradeDropbox() {
		super();
	}

	public ProfessorGradeDropbox(String subjectNo, String presentLecture) {
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
		return "ProfessorGradeDropbox [subjectNo=" + subjectNo + ", presentLecture=" + presentLecture + "]";
	}

	
	
}
