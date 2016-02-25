package kr.hkit.managebook.type;

public class Member {

	private String memberCode;
	private String memberName;
	private String memberTel;
	private int memberLending;

	public Member() {
	}

	public Member(String memberCode, String memberName, String memberTel, int memberLending) {
		super();
		this.memberCode = memberCode;
		this.memberName = memberName;
		this.memberTel = memberTel;
		this.memberLending = memberLending;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberTel() {
		return memberTel;
	}

	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}

	public int getMemberLending() {
		return memberLending;
	}

	public void setMemberLending(int memberLending) {
		this.memberLending = memberLending;
	}

	@Override
	public String toString() {
		return "Member [memberCode=" + memberCode + ", memberName=" + memberName + ", memberTel=" + memberTel
				+ ", memberLending=" + memberLending + "]";
	}

}
