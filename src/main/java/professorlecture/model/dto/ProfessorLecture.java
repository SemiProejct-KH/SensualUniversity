package professorlecture.model.dto;

public class ProfessorLecture {
	
	private String subjectTerm; // 강의년도,학기
	private String memberLevel; // 학생 학년
	private String memberName; // 학생 이름
	private String memberId;
	private String departmentName;
	public ProfessorLecture() {
		super();
	}
	public ProfessorLecture(String subjectTerm, String memberLevel, String memberName, String memberId,
			String departmentName) {
		super();
		this.subjectTerm = subjectTerm;
		this.memberLevel = memberLevel;
		this.memberName = memberName;
		this.memberId = memberId;
		this.departmentName = departmentName;
	}
	public String getSubjectTerm() {
		return subjectTerm;
	}
	public void setSubjectTerm(String subjectTerm) {
		this.subjectTerm = subjectTerm;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
		return "ProfessorLecture [subjectTerm=" + subjectTerm + ", memberLevel=" + memberLevel + ", memberName="
				+ memberName + ", memberId=" + memberId + ", departmentName=" + departmentName + "]";
	}
	
	

}
