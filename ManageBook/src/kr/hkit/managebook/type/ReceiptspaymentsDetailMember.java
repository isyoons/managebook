package kr.hkit.managebook.type;

import java.sql.Date;

public class ReceiptspaymentsDetailMember {
	private String memberCode;
	private String memberName;
	private Date lendingDate;
	private Date returnDate;
	private String overDue;

	public ReceiptspaymentsDetailMember() {
	}

	public ReceiptspaymentsDetailMember(String memberCode, String memberName, Date lendingDate, Date returnDate,
			String overDue) {
		this.memberCode = memberCode;
		this.memberName = memberName;
		this.lendingDate = lendingDate;
		this.returnDate = returnDate;
		this.overDue = overDue;
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

	public Date getLendingDate() {
		return lendingDate;
	}

	public void setLendingDate(Date lendingDate) {
		this.lendingDate = lendingDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getOverDue() {
		return overDue;
	}

	public void setOverDue(String overDue) {
		this.overDue = overDue;
	}

}
