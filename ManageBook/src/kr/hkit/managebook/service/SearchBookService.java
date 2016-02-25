package kr.hkit.managebook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import kr.hkit.managebook.config.ConnectionFactory;
import kr.hkit.managebook.dao.BookDao;
import kr.hkit.managebook.dao.ReceiptsPaymentsDao;
import kr.hkit.managebook.type.Book;
import kr.hkit.managebook.type.ReceiptspaymentsDetailMember;

public class SearchBookService {
	private static final SearchBookService instance = new SearchBookService();
	private Connection con;
	private int allLendingCount = 0;

	public static SearchBookService getInstance() {
		return instance;
	}

	private SearchBookService() {
		con = ConnectionFactory.getConnection();
	}

	public Book getBookFromBookCode(String bookCode) {
		Book book = null;
		try {
			book = BookDao.getInstance().getBookFromBookCode(con, bookCode);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return book;
	}

	public Book getBookFromBookName(String bookName) {
		Book book = null;
		try {
			book = BookDao.getInstance().getBookFromBookName(con, bookName);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return book;
	}

	public String[][] getAllOverdueLendingMemberFromBook(String bookCode) {
		ArrayList<ReceiptspaymentsDetailMember> arr = null;
		String[][] data = null;
		allLendingCount = 0;
		try {
			arr = ReceiptsPaymentsDao.getInstance().getAllOverdueLendingMemberFromBook(con, bookCode);
			if (arr.size() > 0) {
				allLendingCount = arr.size();
				data = new String[arr.size()][5];
				for (int i = 0; i < arr.size(); i++) {
					data[i][0] = arr.get(i).getMemberCode();
					data[i][1] = arr.get(i).getMemberName();
					data[i][2] = arr.get(i).getLendingDate().toString();
					if (arr.get(i).getReturnDate() != null) {
						data[i][3] = arr.get(i).getReturnDate().toString();
					} else {
						data[i][3] = "-";
					}
					data[i][4] = arr.get(i).getOverDue();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public int getAllLendingCount() {
		return allLendingCount;
	}

}
