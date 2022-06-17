package member.model.dto;

import java.sql.Date;

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

	public MemberExt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberExt(int memberNo, String departmentNo, String memberId, String memberPw, String memberName,
			Date memberBirth, String memberPhone, String memberEmail, String memberImg, MemberRole memberRole,
			String memberLevel, Date enrollDate) {
		super(memberNo, departmentNo, memberId, memberPw, memberName, memberBirth, memberPhone, memberEmail, memberImg,
				memberRole, memberLevel, enrollDate);
		// TODO Auto-generated constructor stub
	}
	
}
