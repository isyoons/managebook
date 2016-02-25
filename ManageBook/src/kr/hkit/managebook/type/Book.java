package kr.hkit.managebook.type;

public class Book {
	private String bookCode;
	private String bookName;
	private String bookAuthor;
	private String bookMade;
	private int bookPrice;
	private boolean bookLendingPossible;
	private int bookLendingCount;

	public Book() {
	}

	public Book(String bookCode, String bookName, String bookAuthor, String bookMade, int bookPrice,
			boolean bookLendingPossible, int bookLendingCount) {
		super();
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookMade = bookMade;
		this.bookPrice = bookPrice;
		this.bookLendingPossible = bookLendingPossible;
		this.bookLendingCount = bookLendingCount;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookMade() {
		return bookMade;
	}

	public void setBookMade(String bookMade) {
		this.bookMade = bookMade;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public boolean isBookLendingPossible() {
		return bookLendingPossible;
	}

	public void setBookLendingPossible(boolean bookLendingPossible) {
		this.bookLendingPossible = bookLendingPossible;
	}

	public int getBookLendingCount() {
		return bookLendingCount;
	}

	public void setBookLendingCount(int bookLendingCount) {
		this.bookLendingCount = bookLendingCount;
	}

	@Override
	public String toString() {
		return "Book [bookCode=" + bookCode + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookMade="
				+ bookMade + ", bookPrice=" + bookPrice + ", bookLendingPossible=" + bookLendingPossible
				+ ", bookLendingCount=" + bookLendingCount + "]";
	}
}
