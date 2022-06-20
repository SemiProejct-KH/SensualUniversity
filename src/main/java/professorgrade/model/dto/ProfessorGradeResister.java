package professorgrade.model.dto;

public class ProfessorGradeResister {

	private String resisterNo;

	@Override
	public String toString() {
		return "ProfessorGradeResister [resisterNo=" + resisterNo + "]";
	}

	public String getResisterNo() {
		return resisterNo;
	}

	public void setResisterNo(String resisterNo) {
		this.resisterNo = resisterNo;
	}

	public ProfessorGradeResister(String resisterNo) {
		super();
		this.resisterNo = resisterNo;
	}

	public ProfessorGradeResister() {
		super();
	}
	
}
