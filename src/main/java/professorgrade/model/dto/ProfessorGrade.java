package professorgrade.model.dto;

public class ProfessorGrade {
	
	private String subjectNo; // 강의 이름
	private String subjectTerm; // 강의년도,학기
	private String memberLevel; // 학생학년
	private String memberName; // 학생이름
	private String memberNo; // 학생아이디
	private String departmentNo; // 학생학과
//	private int grademiddle; // 중간
//	private int gradefinal; // 기말
//	private int gradeassignment; // 과제
//	private int gradeattend; // 출석
	
	public ProfessorGrade() {
		super();
	}
public ProfessorGrade(String subjectNo, String subjectTerm, String memberLevel, String memberName, String memberNo,
		String departmentNo) {
	super();
	this.subjectNo = subjectNo;
	this.subjectTerm = subjectTerm;
	this.memberLevel = memberLevel;
	this.memberName = memberName;
	this.memberNo = memberNo;
	this.departmentNo = departmentNo;
}
public String getSubjectNo() {
	return subjectNo;
}
public void setSubjectNo(String subjectNo) {
	this.subjectNo = subjectNo;
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
public String getMemberNo() {
	return memberNo;
}
public void setMemberNo(String memberNo) {
	this.memberNo = memberNo;
}
public String getDepartmentNo() {
	return departmentNo;
}
public void setDepartmentNo(String departmentNo) {
	this.departmentNo = departmentNo;
}
@Override
public String toString() {
	return "ProfessorGrade [subjectNo=" + subjectNo + ", subjectTerm=" + subjectTerm + ", memberLevel=" + memberLevel
			+ ", memberName=" + memberName + ", memberNo=" + memberNo + ", departmentNo=" + departmentNo + "]";
}

}
