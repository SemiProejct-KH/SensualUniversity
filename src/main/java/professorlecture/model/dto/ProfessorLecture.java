package professorlecture.model.dto;

public class ProfessorLecture {
	
	private String subjectTerm; // 년도,학기
	private String subjectLevel; // 학년
	private String subjectName; // 이름
	private String memberId; // 아이디
	private String departmentName; // 학과
	
	public ProfessorLecture() {
		super();
	}

	public ProfessorLecture(String subjectTerm, String subjectLevel, String subjectName, String memberId,
			String departmentName) {
		super();
		this.subjectTerm = subjectTerm;
		this.subjectLevel = subjectLevel;
		this.subjectName = subjectName;
		this.memberId = memberId;
		this.departmentName = departmentName;
	}

	public String getSubjectTerm() {
		return subjectTerm;
	}

	public void setSubjectTerm(String subjectTerm) {
		this.subjectTerm = subjectTerm;
	}

	public String getSubjectLevel() {
		return subjectLevel;
	}

	public void setSubjectLevel(String subjectLevel) {
		this.subjectLevel = subjectLevel;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "ProfessorLecture [subjectTerm=" + subjectTerm + ", subjectLevel=" + subjectLevel + ", subjectName="
				+ subjectName + ", memberId=" + memberId + ", departmentName=" + departmentName + "]";
	}

}
