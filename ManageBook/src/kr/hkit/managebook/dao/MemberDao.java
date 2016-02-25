package kr.hkit.managebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.hkit.managebook.jdbc.JdbcUtil;
import kr.hkit.managebook.type.Member;

public class MemberDao {
	private static final MemberDao instance = new MemberDao();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private MemberDao() {
		// TODO Auto-generated constructor stub
	}

	public static MemberDao getInstance() {
		return instance;
	}

	public Member getMemberFromMemberCode(Connection con, String text) throws SQLException {
		String sql = "select * from member where member_code=?";
		Member member = new Member();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, text);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member.setMemberCode(rs.getString(1));
				member.setMemberName(rs.getString(2));
				member.setMemberTel(rs.getString(3));
				member.setMemberLending(rs.getInt(4));
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return member;
	}

	public void updateMemberLendingPlusOne(Connection con, String code) throws SQLException {
		String sql = "update member set member_lending=member_lending+1 where member_code=?";
		System.out.println(code);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public ArrayList getMemberFromMemberName(Connection con, String text) throws SQLException {
		String sql = "select * from member where member_name=?";
		ArrayList<Member> memberList = new ArrayList();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, text);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = new Member();
				member.setMemberCode(rs.getString(1));
				member.setMemberName(rs.getString(2));
				member.setMemberTel(rs.getString(3));
				member.setMemberLending(rs.getInt(4));
				memberList.add(member);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return memberList;
	}

	public ArrayList getAllMemberList(Connection con) throws SQLException {
		String sql = "select * from member";
		ArrayList<Member> memberList = new ArrayList();

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = new Member();
				member.setMemberCode(rs.getString(1));
				member.setMemberName(rs.getString(2));
				member.setMemberTel(rs.getString(3));
				member.setMemberTel(rs.getString(3));
				memberList.add(member);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return memberList;

	}

	public boolean addMember(Connection con, Member member) throws SQLException {
		String sql = "insert into member values(?,?,?,?)";
		int x = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMemberCode());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getMemberTel());
			pstmt.setInt(4, member.getMemberLending());
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

	public boolean updateMember(Connection con, Member member) throws SQLException {
		String sql = "update member set member_name =?, member_tel=? where member_code = ?";
		int x = 0;
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getMemberTel());
			pstmt.setString(3, member.getMemberCode());

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

	public boolean deleteMember(Connection con, String code) throws SQLException {
		String sql = "delete from member where member_code = ?";
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
}
