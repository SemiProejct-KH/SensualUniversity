package professorlecture.model.dto;

public class ProfessorLecture {
	
	private String subjectTerm; // 강의년도,학기
	private String subjectLebel; // 강의학년
	private String subjectTime; // 강의시간
	private String subjectClassroom; // 강의실
	private String memberName; // 학생 이름
	private String memberNo; // 학생 아이디
	private String memberLevel; // 학생 학년
	private String departmentNo; // 학생 학과
	private String memberEmail; // 학생 이메일
	private String memberPhone; // 학생 전화번호
	
	public ProfessorLecture() {
		super();
	}

	public ProfessorLecture(String subjectTerm, String subjectLebel, String subjectTime, String subjectClassroom,
			String memberName, String memberNo, String memberLevel, String departmentNo, String memberEmail,
			String memberPhone) {
		super();
		this.subjectTerm = subjectTerm;
		this.subjectLebel = subjectLebel;
		this.subjectTime = subjectTime;
		this.subjectClassroom = subjectClassroom;
		this.memberName = memberName;
		this.memberNo = memberNo;
		this.memberLevel = memberLevel;
		this.departmentNo = departmentNo;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
	}

	public String getSubjectTerm() {
		return subjectTerm;
	}

	public void setSubjectTerm(String subjectTerm) {
		this.subjectTerm = subjectTerm;
	}

	public String getSubjectLebel() {
		return subjectLebel;
	}

	public void setSubjectLebel(String subjectLebel) {
		this.subjectLebel = subjectLebel;
	}

	public String getSubjectTime() {
		return subjectTime;
	}

	public void setSubjectTime(String subjectTime) {
		this.subjectTime = subjectTime;
	}

	public String getSubjectClassroom() {
		return subjectClassroom;
	}

	public void setSubjectClassroom(String subjectClassroom) {
		this.subjectClassroom = subjectClassroom;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	@Override
	public String toString() {
		return "ProfessorLecture [subjectTerm=" + subjectTerm + ", subjectLebel=" + subjectLebel + ", subjectTime="
				+ subjectTime + ", subjectClassroom=" + subjectClassroom + ", memberName=" + memberName + ", memberNo="
				+ memberNo + ", memberLevel=" + memberLevel + ", departmentNo=" + departmentNo + ", memberEmail="
				+ memberEmail + ", memberPhone=" + memberPhone + "]";
	}
	
}
