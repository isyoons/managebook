package kr.hkit.managebook.type;

import java.sql.Date;

public class Receiptspayments {
	private String memberCode;
	private String bookName;
	private String bookCode;
	private Date lendingDate;
	private Date returnDate;
	private String overDue;
	

	/**
	 * @param memberCode
	 * @param bookName
	 * @param bookCode
	 * @param lendingDate
	 * @param returnDate
	 * @param overDue
	 */
	public Receiptspayments(String memberCode, String bookName, String bookCode, Date lendingDate, Date returnDate,
			String overDue) {
		super();
		this.memberCode = memberCode;
		this.bookName = bookName;
		this.bookCode = bookCode;
		this.lendingDate = lendingDate;
		this.returnDate = returnDate;
		this.overDue = overDue;
	}

	


	public Receiptspayments() {
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
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

	@Override
	public String toString() {
		return String.format(
				"Receiptspayments [memberCode=%s, bookName=%s, bookCode=%s, lendingDate=%s, returnDate=%s, overDue=%s]",
				memberCode, bookName, bookCode, lendingDate, returnDate, overDue);
	}

}
