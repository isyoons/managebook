package kr.hkit.managebook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import kr.hkit.managebook.config.ConnectionFactory;
import kr.hkit.managebook.dao.MemberDao;
import kr.hkit.managebook.type.Member;

public class MemberService {
	private static final MemberService instance = new MemberService();
	private Connection con;

	public static MemberService getInstance() {
		return instance;
	}

	private MemberService() {
		con = ConnectionFactory.getConnection();
	}

	public String[][] getAllMemberList() {
		String[][] data = null;

		try {
			ArrayList<Member> arr = null;
			arr = MemberDao.getInstance().getAllMemberList(con);

			data = new String[arr.size()][3];
			for (int i = 0; i < arr.size(); i++) {
				data[i][0] = arr.get(i).getMemberCode();
				data[i][1] = arr.get(i).getMemberName();
				data[i][2] = arr.get(i).getMemberTel();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return data;
	}

	public boolean addMember(Member member) {
		boolean success = false;
		try {
			success = MemberDao.getInstance().addMember(con, member);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {// 1062 Duplicate Primary key
				JOptionPane.showMessageDialog(null, "중복된 멤버코드를 입력하셨습니다.");
			} else {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

		}
		return success;
	}

	public boolean updateMember(Member member) {
		boolean success = false;
		try {
			success = MemberDao.getInstance().updateMember(con, member);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return success;
	}

	public boolean deleteMember(String code) {
		boolean success = false;
		try {
			success = MemberDao.getInstance().deleteMember(con, code);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return success;
	}

}
