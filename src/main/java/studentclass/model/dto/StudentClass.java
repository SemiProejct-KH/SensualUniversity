package studentclass.model.dto;

public class StudentClass {
	private String subjectTerm;
    private String subjectLebel;
    private String departmentName;
    private String subjectNo;
    private String subjectName;
    private String memberName;
    private String subjectTime;
    private String subjectClassroom;
    private String subjectCredit;
	public StudentClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentClass(String subjectTerm, String subjectLebel, String departmentName, String subjectNo,
			String subjectName, String memberName, String subjectTime, String subjectClassroom, String subjectCredit) {
		super();
		this.subjectTerm = subjectTerm;
		this.subjectLebel = subjectLebel;
		this.departmentName = departmentName;
		this.subjectNo = subjectNo;
		this.subjectName = subjectName;
		this.memberName = memberName;
		this.subjectTime = subjectTime;
		this.subjectClassroom = subjectClassroom;
		this.subjectCredit = subjectCredit;
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
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
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
	public String getSubjectCredit() {
		return subjectCredit;
	}
	public void setSubjectCredit(String subjectCredit) {
		this.subjectCredit = subjectCredit;
	}
	@Override
	public String toString() {
		return "StudentClass [subjectTerm=" + subjectTerm + ", subjectLebel=" + subjectLebel + ", departmentName="
				+ departmentName + ", subjectNo=" + subjectNo + ", subjectName=" + subjectName + ", memberName="
				+ memberName + ", subjectTime=" + subjectTime + ", subjectClassroom=" + subjectClassroom
				+ ", subjectCredit=" + subjectCredit + "]";
	}
    
    

}
