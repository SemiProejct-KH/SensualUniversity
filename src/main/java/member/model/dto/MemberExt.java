package member.model.dto;

public class MemberExt  extends Member{
	private String departmentName;


	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "MemberExt [departmentName=" + departmentName + ", toString()=" + super.toString() + "]";
	}
	
}
