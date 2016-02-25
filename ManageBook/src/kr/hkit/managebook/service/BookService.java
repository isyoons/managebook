package kr.hkit.managebook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import kr.hkit.managebook.config.Config;
import kr.hkit.managebook.config.ConnectionFactory;
import kr.hkit.managebook.dao.BookDao;
import kr.hkit.managebook.type.Book;

public class BookService {
	private static final BookService instance = new BookService();
	private Connection con;

	private BookService() {
		con = ConnectionFactory.getConnection();
	}

	public static BookService getInstance() {
		return instance;
	}

	public boolean insertBookData(Book book) {
		int x = 0;
		try {
			x = BookDao.getInstance().insertBookData(con, book);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {// 1062 Duplicate Primary key
				JOptionPane.showMessageDialog(null, "중복된 도서코드를 입력하셨습니다.");
			} else {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		if (x == 1)
			return true;
		else
			return false;
	}

	public void updateBookData(Book book) {
		try {
			if (!BookDao.getInstance().updateBook(con, book)) {
				JOptionPane.showMessageDialog(null, "책 정보 갱신에 실패하였습니다.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deleteBookData(String code) {
		try {
			BookDao.getInstance().deleteBook(con, code);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
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

	public String[][] getAllBookList() {
		String[][] data = null;
		ArrayList<Book> arr = null;

		try {
			arr = BookDao.getInstance().getAllBookList(con);
			data = new String[arr.size()][5];
			for (int i = 0; i < arr.size(); i++) {
				data[i][0] = arr.get(i).getBookCode();
				data[i][1] = arr.get(i).getBookName();
				data[i][2] = arr.get(i).getBookAuthor();
				data[i][3] = arr.get(i).getBookMade();
				data[i][4] = Config.DECIMALF.format(arr.get(i).getBookPrice());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "테이블 가져오기 에러");
			data = null;
		}
		return data;
	}
}
