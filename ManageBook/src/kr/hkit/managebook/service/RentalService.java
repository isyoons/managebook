package kr.hkit.managebook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import kr.hkit.managebook.config.Config;
import kr.hkit.managebook.config.ConnectionFactory;
import kr.hkit.managebook.dao.BookDao;
import kr.hkit.managebook.dao.MemberDao;
import kr.hkit.managebook.dao.ReceiptsPaymentsDao;
import kr.hkit.managebook.type.Book;
import kr.hkit.managebook.type.Member;

public class RentalService {
	private static final RentalService instance = new RentalService();
	private Connection con;

	private RentalService() {
		con = ConnectionFactory.getConnection();
	}

	public static RentalService getInstance() {
		return instance;
	}

	public String[][] getAllLendingPossibleBook() {
		String[][] data = null;
		try {
			ArrayList<Book> arr = BookDao.getInstance().getAllLendingPossibleBook(con);
			data = new String[arr.size()][6];
			for (int i = 0; i < arr.size(); i++) {
				data[i][0] = arr.get(i).getBookCode();
				data[i][1] = arr.get(i).getBookName();
				data[i][2] = arr.get(i).getBookAuthor();
				data[i][3] = arr.get(i).getBookMade();
				data[i][4] = Config.DECIMALF.format(arr.get(i).getBookPrice());
				data[i][5] = Integer.toString(arr.get(i).getBookLendingCount());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return data;
	}

	public Member getMemberFromMemberCode(String text) {
		Member member = null;
		try {
			member = MemberDao.getInstance().getMemberFromMemberCode(con, text);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return member;
	}

	public Book getBookFromBookCode(String code) {
		Book book = null;
		try {
			book = BookDao.getInstance().getBookFromBookCode(con, code);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return book;
	}

	public void updateMemberLendingPlusOne(String code) {
		try {
			MemberDao.getInstance().updateMemberLendingPlusOne(con, code);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
	}

	public void updateBookLendingPlusOne(String code) {
		try {
			BookDao.getInstance().updateBookLendingPlusOne(con, code);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public void addReceiptsPayments(String memberCode, String bookCode, Date lendingDate) {
		try {
			ReceiptsPaymentsDao.getInstance().addReceiptsPayments(con, memberCode, bookCode, lendingDate);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public void updateRendingPossible(String bookCode, boolean trueFalse) {
		try {
			BookDao.getInstance().updateRendingPossible(con, bookCode, trueFalse);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
