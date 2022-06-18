package studentrecord.model.dto;

public class Record {
	
	private String subjectLebel;
	private String departmentName;
	private String subjectNo;
	private String memberName;
	private int gradeAttend;
	private int gradeMiddle;
	private int gradeFinal;
	private int gradAssignment;
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Record(String subjectLebel, String departmentName, String subjectNo, String memberName, int gradeAttend,
			int gradeMiddle, int gradeFinal, int gradAssignment) {
		super();
		this.subjectLebel = subjectLebel;
		this.departmentName = departmentName;
		this.subjectNo = subjectNo;
		this.memberName = memberName;
		this.gradeAttend = gradeAttend;
		this.gradeMiddle = gradeMiddle;
		this.gradeFinal = gradeFinal;
		this.gradAssignment = gradAssignment;
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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getGradeAttend() {
		return gradeAttend;
	}
	public void setGradeAttend(int gradeAttend) {
		this.gradeAttend = gradeAttend;
	}
	public int getGradeMiddle() {
		return gradeMiddle;
	}
	public void setGradeMiddle(int gradeMiddle) {
		this.gradeMiddle = gradeMiddle;
	}
	public int getGradeFinal() {
		return gradeFinal;
	}
	public void setGradeFinal(int gradeFinal) {
		this.gradeFinal = gradeFinal;
	}
	public int getGradAssignment() {
		return gradAssignment;
	}
	public void setGradAssignment(int gradAssignment) {
		this.gradAssignment = gradAssignment;
	}
	@Override
	public String toString() {
		return "record [subjectLebel=" + subjectLebel + ", departmentName=" + departmentName + ", subjectNo="
				+ subjectNo + ", memberName=" + memberName + ", gradeAttend=" + gradeAttend + ", gradeMiddle="
				+ gradeMiddle + ", gradeFinal=" + gradeFinal + ", gradAssignment=" + gradAssignment + "]";
	}
	
	
}
