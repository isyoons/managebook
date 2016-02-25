package kr.hkit.managebook.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.hkit.managebook.config.ConnectionFactory;
import kr.hkit.managebook.dao.ReceiptsPaymentsDao;
import kr.hkit.managebook.jdbc.JdbcUtil;

public class ReturnService
{
	private static final ReturnService instance = new ReturnService();
	public static final String[] COL_NAMES =
	{ "도서코드", "도서명", "회원코드", "회원명", "대여일" };

	public static ReturnService getInstance()
	{
		return instance;
	}

	public ReturnService()
	{
	}

	public DefaultTableModel getLendingBookList()
	{
		DefaultTableModel model = null;
		String[][] datas = null;
		Connection con = null;
		try
		{
			con = ConnectionFactory.getConnection();
			datas = ReceiptsPaymentsDao.getInstance().selectReturnViewByAll(con);
			model = new DefaultTableModel(datas, COL_NAMES)
			{
				public boolean isCellEditable(int i, int j)
				{
					return false;
				}
			};
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtil.close(con);
		}
		return model;
	}

	public String[] getReturnDataFromTableData(String bookCode)
	{
		Connection con = null;
		String[] datas = null;
		try
		{
			con = ConnectionFactory.getConnection();
			datas = ReceiptsPaymentsDao.getInstance().selectReturnManageViewByAll(con, bookCode);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			JdbcUtil.close(con);
		}
		return datas;
	}

	public void updateReturnDate(String returnDate, String bookCode)
	{
		Connection con = null;
		try
		{
			con = ConnectionFactory.getConnection();
			ReceiptsPaymentsDao dao = ReceiptsPaymentsDao.getInstance();
			dao.updateReturnDate(con, returnDate, bookCode);
		} catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "반납 실패");
			e.printStackTrace();
		} finally
		{
			JdbcUtil.close(con);
		}
	}

	public void updateBookLendingPossible(String bookCode, boolean b)
	{
		Connection con = null;
		try
		{
			con = ConnectionFactory.getConnection();
			ReceiptsPaymentsDao dao = ReceiptsPaymentsDao.getInstance();
			int result = dao.updateBookLendingPossible(con, bookCode, b);
			if (result > 0)
				JOptionPane.showMessageDialog(null, "반납 성공");
		} catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "반납 실패");
			e.printStackTrace();
		} finally
		{
			JdbcUtil.close(con);
		}
	}
}
