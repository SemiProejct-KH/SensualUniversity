package member.model.dto;

import java.sql.Date;
import java.util.List;

public class MemberExt  extends Member{
	private String departmentName;
	private List<Member> members;
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	@Override
	public String toString() {
		return "MemberExt [departmentName=" + departmentName + ", members=" + members + ", toString()="
				+ super.toString() + "]";
	}
	
}
