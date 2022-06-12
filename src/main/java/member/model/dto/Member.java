package member.model.dto;

import java.sql.Date;

public class Member {
	private int memberNo;
	private String departmentNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private Date memberBirth;
	private String memberPhone;
	private String memberEmail;
	private String memberImg;
	private MemberRole memberRole; // enum 'A' 'P' 'S'
	private String memberLevel; // '1' '2' '3' '4'
	private Date enrollDate;
	
	public Member() {}

	public Member(int memberNo, String departmentNo, String memberId, String memberPw, String memberName,
			Date memberBirth, String memberPhone, String memberEmail, String memberImg, MemberRole memberRole,
			String memberLevel, Date enrollDate) {
		super();
		this.memberNo = memberNo;
		this.departmentNo = departmentNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberBirth = memberBirth;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberImg = memberImg;
		this.memberRole = memberRole;
		this.memberLevel = memberLevel;
		this.enrollDate = enrollDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberImg() {
		return memberImg;
	}

	public void setMemberImg(String memberImg) {
		this.memberImg = memberImg;
	}

	public MemberRole getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", departmentNo=" + departmentNo + ", memberId=" + memberId
				+ ", memberPw=" + memberPw + ", memberName=" + memberName + ", memberBirth=" + memberBirth
				+ ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail + ", memberImg=" + memberImg
				+ ", memberRole=" + memberRole + ", memberLevel=" + memberLevel + ", enrollDate=" + enrollDate + "]";
	}
	
}