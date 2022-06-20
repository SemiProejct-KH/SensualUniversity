package professorgrade.model.dto;

public class ProfessorGradeInput {
	
	private String resisterNo;
	private int gradeMiddle;
	private int gradeFinal;
	private int gradeAssignment;
	private int gradeAttend;
	
	public ProfessorGradeInput() {
		super();
	}
	public ProfessorGradeInput(String resisterNo, int gradeMiddle, int gradeFinal, int gradeAssignment,
			int gradeAttend) {
		super();
		this.resisterNo = resisterNo;
		this.gradeMiddle = gradeMiddle;
		this.gradeFinal = gradeFinal;
		this.gradeAssignment = gradeAssignment;
		this.gradeAttend = gradeAttend;
	}
	public String getResisterNo() {
		return resisterNo;
	}
	public void setResisterNo(String resisterNo) {
		this.resisterNo = resisterNo;
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
	public int getGradeAssignment() {
		return gradeAssignment;
	}
	public void setGradeAssignment(int gradeAssignment) {
		this.gradeAssignment = gradeAssignment;
	}
	public int getGradeAttend() {
		return gradeAttend;
	}
	public void setGradeAttend(int gradeAttend) {
		this.gradeAttend = gradeAttend;
	}
	@Override
	public String toString() {
		return "ProfessorGradeInput [resisterNo=" + resisterNo + ", gradeMiddle=" + gradeMiddle + ", gradeFinal="
				+ gradeFinal + ", gradeAssignment=" + gradeAssignment + ", gradeAttend=" + gradeAttend + "]";
	}

	
	
}
