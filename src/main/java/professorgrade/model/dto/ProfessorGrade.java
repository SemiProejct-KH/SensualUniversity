package professorgrade.model.dto;

public class ProfessorGrade {
	
	private String subjectTerm; // 년도,학기
	private String subjectLebel; // 학년
	private String subjectName; // 이름
	private String memberNo; // 아이디
	private String departmentName; // 학과
	private int grademiddle; // 중간
	private int gradefinal; // 기말
	private int gradeassignment; // 과제
	private int gradeattend; // 출석
	
	public ProfessorGrade() {
		super();
	}

	public ProfessorGrade(String subjectTerm, String subjectLebel, String subjectName, String memberNo,
			String departmentName, int grademiddle, int gradefinal, int gradeassignment, int gradeattend) {
		super();
		this.subjectTerm = subjectTerm;
		this.subjectLebel = subjectLebel;
		this.subjectName = subjectName;
		this.memberNo = memberNo;
		this.departmentName = departmentName;
		this.grademiddle = grademiddle;
		this.gradefinal = gradefinal;
		this.gradeassignment = gradeassignment;
		this.gradeattend = gradeattend;
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getGrademiddle() {
		return grademiddle;
	}

	public void setGrademiddle(int grademiddle) {
		this.grademiddle = grademiddle;
	}

	public int getGradefinal() {
		return gradefinal;
	}

	public void setGradefinal(int gradefinal) {
		this.gradefinal = gradefinal;
	}

	public int getGradeassignment() {
		return gradeassignment;
	}

	public void setGradeassignment(int gradeassignment) {
		this.gradeassignment = gradeassignment;
	}

	public int getGradeattend() {
		return gradeattend;
	}

	public void setGradeattend(int gradeattend) {
		this.gradeattend = gradeattend;
	}

	@Override
	public String toString() {
		return "ProfessorGrade [subjectTerm=" + subjectTerm + ", subjectLebel=" + subjectLebel + ", subjectName="
				+ subjectName + ", memberNo=" + memberNo + ", departmentName=" + departmentName + ", grademiddle="
				+ grademiddle + ", gradefinal=" + gradefinal + ", gradeassignment=" + gradeassignment + ", gradeattend="
				+ gradeattend + "]";
	}

}
