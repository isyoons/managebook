package kr.hkit.managebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.hkit.managebook.config.ConnectionFactory;
import kr.hkit.managebook.jdbc.JdbcUtil;
import kr.hkit.managebook.type.Book;

public class BookDao {
	private static final BookDao instance = new BookDao();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public static BookDao getInstance() {
		return instance;
	}

	private BookDao() {
	}

	public ArrayList<Book> getAllBookList(Connection con) {
		String sql = "select book_code, book_name, book_author, book_made, book_price from book";
		ArrayList<Book> arr = new ArrayList<>();
		Book book = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setBookCode(rs.getString(1));
				book.setBookName(rs.getString(2));
				book.setBookAuthor(rs.getString(3));
				book.setBookMade(rs.getString(4));
				book.setBookPrice(rs.getInt(5));
				arr.add(book);
				book = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return arr;
	}

	public int insertBookData(Connection con, Book book) throws SQLException {
		String sql = "insert into book values (?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		int x = 0;
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, book.getBookCode());
			pstmt.setString(2, book.getBookName());
			pstmt.setString(3, book.getBookAuthor());
			pstmt.setString(4, book.getBookMade());
			pstmt.setInt(5, book.getBookPrice());
			pstmt.setBoolean(6, true);
			pstmt.setInt(7, 0);

			x = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);

		}
		return x;
	}

	public boolean updateBook(Connection con, Book Book) throws SQLException {
		String sql = "update Book set book_name =?, book_author=?, book_made=?, book_price=? where book_code = ?";
		int x = 0;
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, Book.getBookName());
			pstmt.setString(2, Book.getBookAuthor());
			pstmt.setString(3, Book.getBookMade());
			pstmt.setInt(4, Book.getBookPrice());
			pstmt.setString(5, Book.getBookCode());
			x = pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
		if (x == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteBook(Connection con, String code) throws SQLException {
		String sql = "delete from Book where Book_code = ?";
		int x = 0;
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, code);
			x = pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
		if (x == 1) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Book> getAllLendingPossibleBook(Connection con) throws SQLException {
		String sql = "select book_code,book_name,book_author,book_made,book_price, book_lending_count from book "
				+ "where book_lending_possible is true";
		ArrayList<Book> arr = new ArrayList<>();
		Book book = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setBookCode(rs.getString(1));
				book.setBookName(rs.getString(2));
				book.setBookAuthor(rs.getString(3));
				book.setBookMade(rs.getString(4));
				book.setBookPrice(rs.getInt(5));
				book.setBookLendingCount(rs.getInt(6));
				arr.add(book);
				book = null;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return arr;
	}

	public Book getBookFromBookCode(Connection con, String code) throws SQLException {
		String sql = "select * from book where book_code=?";
		Book book = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				book = new Book();
				book.setBookCode(rs.getString(1));
				book.setBookName(rs.getString(2));
				book.setBookAuthor(rs.getString(3));
				book.setBookMade(rs.getString(4));
				book.setBookPrice(rs.getInt(5));
				book.setBookLendingCount(rs.getInt(7));

			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return book;
	}

	public void updateBookLendingPlusOne(Connection con, String code) throws SQLException {
		String sql = "update book set book_lending_count=book_lending_count+1 where book_code=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public void updateRendingPossible(Connection con, String bookCode, boolean trueFalse) throws SQLException {
		String sql = "update book set book_lending_possible=? where book_code=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, trueFalse);
			pstmt.setString(2, bookCode);
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public Book getBookFromBookName(Connection con, String bookName) throws SQLException {
		String sql = "select * from book where book_name like ?";
		Book book = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				book = new Book();
				book.setBookCode(rs.getString(1));
				book.setBookName(rs.getString(2));
				book.setBookAuthor(rs.getString(3));
				book.setBookMade(rs.getString(4));
				book.setBookPrice(rs.getInt(5));
				book.setBookLendingCount(rs.getInt(7));
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return book;

	}

}
