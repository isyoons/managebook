package kr.hkit.managebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import kr.hkit.managebook.jdbc.JdbcUtil;
import kr.hkit.managebook.type.Receiptspayments;
import kr.hkit.managebook.type.ReceiptspaymentsDetailMember;

public class ReceiptsPaymentsDao {
	private static final ReceiptsPaymentsDao instance = new ReceiptsPaymentsDao();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public static ReceiptsPaymentsDao getInstance() {
		return instance;
	}

	private ReceiptsPaymentsDao() {
	}

	public int selectCnt(Connection con) throws SQLException {
		String sql = "select count(*) from return_view";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new SQLException("존재하지 않습니다.");
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public String[][] selectReturnViewByAll(Connection con) throws SQLException {
		String sql = "select * from return_view";
		int rowCnt = selectCnt(con);
		String[][] datas = new String[rowCnt][7];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				datas[i][0] = rs.getString(1);
				datas[i][1] = rs.getString(2);
				datas[i][2] = rs.getString(3);
				datas[i][3] = rs.getString(4);
				datas[i][4] = sdf.format(rs.getTimestamp(5));
				i++;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return datas;
	}

	public String[] selectReturnManageViewByAll(Connection con, String bookCode) throws SQLException {
		String sql = "select * from return_manage_view where 도서코드=?";
		String[] datas = new String[10];
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				datas[0] = rs.getString(1);
				datas[1] = rs.getString(2);
				datas[2] = rs.getString(3);
				datas[3] = rs.getString(4);
				datas[4] = rs.getString(5);
				datas[5] = rs.getString(6);
				datas[6] = rs.getString(7);
				datas[7] = rs.getString(8);
				datas[8] = rs.getString(9);
				datas[9] = rs.getString(10);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return datas;
	}

	public int updateReturnDate(Connection con, String returnDate, String bookCode) throws SQLException {
		String sql = "update receiptspayments set return_date=? where book_code=? and return_date is null";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, returnDate);
			pstmt.setString(2, bookCode);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public int updateBookLendingPossible(Connection con, String bookCode, Boolean b) throws SQLException {
		String sql = "update book set book_lending_possible=? where book_code=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, b);
			pstmt.setString(2, bookCode);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void addReceiptsPayments(Connection con, String memberCode, String bookCode, Date lendingDate)
			throws SQLException {
		String sql = "insert into receiptspayments values(?, ? ,?,null)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberCode);
			pstmt.setString(2, bookCode);
			pstmt.setTimestamp(3, new Timestamp(lendingDate.getTime()));
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public ArrayList<ReceiptspaymentsDetailMember> getAllOverdueLendingMemberFromBook(Connection con, String bookCode)
			throws SQLException {
		ArrayList<ReceiptspaymentsDetailMember> list = new ArrayList<>();
		String sql = "select m.member_code, m.member_name, r.lending_date , "
				+ "r.return_date ,	(case when r.return_date is not null then 'N' when DATEDIFF(ifnull(r.return_date,curdate()),r.lending_date )"
				+ ">=3 then 'Y' else 'N' end ) from book b, receiptspayments r, "
				+ "member m where m.member_code = r.member_code and " + "r.book_code = b.book_code and b.book_code = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookCode);
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			while (rs.next()) {
				list.add(new ReceiptspaymentsDetailMember(rs.getString(1), rs.getString(2), rs.getDate(3),
						rs.getDate(4), rs.getString(5)));
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return list;
	}

	public ArrayList<Receiptspayments> getAllLendingBookInfo(Connection con, String code) throws SQLException {
		String sql = "select * from search_book_view where 멤버코드=?";
		String OverDueCheck = "N";
		Calendar today = Calendar.getInstance();
		ArrayList<Receiptspayments> arr = new ArrayList<>();
		Receiptspayments receipt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				receipt = new Receiptspayments();
				receipt.setBookCode(rs.getString(1));
				receipt.setBookName(rs.getString(2));
				receipt.setLendingDate(rs.getDate(3));
				receipt.setReturnDate(rs.getDate(4));
				if(receipt.getReturnDate()==null)
				{
					if(Math.floor(today.getTimeInMillis()/(24*3600*1000))>Math.floor(receipt.getLendingDate().getTime()/(24*3600*1000))+3) //오늘날의 총일수와 빌린날총일수+3비교...;;;
					{
						OverDueCheck="Y";
					}else
					{
						OverDueCheck="N";
					}
					
				}
				else
				{
					OverDueCheck="N";
				}
				receipt.setOverDue(OverDueCheck);
				arr.add(receipt);
				receipt = null;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	
		return arr;
	}
}
