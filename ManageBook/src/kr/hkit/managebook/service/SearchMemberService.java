package kr.hkit.managebook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import kr.hkit.managebook.config.ConnectionFactory;
import kr.hkit.managebook.dao.MemberDao;
import kr.hkit.managebook.dao.ReceiptsPaymentsDao;
import kr.hkit.managebook.type.Member;
import kr.hkit.managebook.type.Receiptspayments;

public class SearchMemberService {
	private int delayCount=0;
	private int lendingCount=0;
	private int returnCount=0;
	private static final SearchMemberService instance = new SearchMemberService();
	private Connection con;

	private SearchMemberService() {
		con = ConnectionFactory.getConnection();
	}

	public static SearchMemberService getInstance() {
		return instance;
	}

	public String[][] getMemberFromMemberName(String memberName) {
		String[][] data = null;
		ArrayList<Member> arr;

		try {
			arr = MemberDao.getInstance().getMemberFromMemberName(con, memberName);
			data = new String[arr.size()][4];
			for(int i = 0; i<arr.size();i++)
			{
				data[i][0]=arr.get(i).getMemberCode();
				data[i][1]=arr.get(i).getMemberName();
				data[i][2]=arr.get(i).getMemberTel();
				data[i][3]=Integer.toString(arr.get(i).getMemberLending());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return data;

	}

	public String[][] getAllLendingBookInfo(String memberCode) {
		String[][] data = null;
		ArrayList<Receiptspayments> arr;
		countReset();
		try {
			arr = ReceiptsPaymentsDao.getInstance().getAllLendingBookInfo(con, memberCode);
			data = new String[arr.size()][5];
			for (int i = 0; i < arr.size(); i++) {
				data[i][0] = arr.get(i).getBookCode();
				data[i][1] = arr.get(i).getBookName();
				data[i][2] = arr.get(i).getLendingDate().toString();
				if (arr.get(i).getReturnDate() != null) {
					data[i][3] = arr.get(i).getReturnDate().toString();
					returnCount++;
				} else {
					data[i][3] = "-";
				}
				data[i][4] = arr.get(i).getOverDue().toString();
				if(data[i][4].equals("Y"))
				{
					delayCount++;
				}
				else if(data[i][4].equals("N")&&data[i][3].equals("-"))
				{
					lendingCount++;
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return data;
	}

	
	private void countReset()
	{
		delayCount=0;
		lendingCount=0;
		returnCount=0;
	}
	
	public int[] getCount()
	{	

		int []cnt={delayCount,lendingCount,returnCount};
		return cnt;
		
	}

}
