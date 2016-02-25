package kr.hkit.managebook.search;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.hkit.managebook.service.SearchMemberService;
import kr.hkit.managebook.type.Member;

public class SearchMemberFrame extends AbsSearchMemberFrame {
	private final static SearchMemberFrame instance = new SearchMemberFrame();
	private String[][] data = null;
	private DefaultTableModel model = null;
	private int delayCount;
	private int lendingCount;
	private int returnCount;
	private final String[] TABLE_NAME = { "도서 코드", "도서명", "대여 일자", "반납 일자", "연체 여부" };
	private String[][] members = null;
	private int members_index=0;
	private int members_size=0;
	
	
	public static SearchMemberFrame getInstance() {
		return instance;
	}

	private SearchMemberFrame() {
		// data = getAllLendingBookInfo();
		getTable().getTableHeader().setReorderingAllowed(false);// 테이블 컬럼의 이동을
																// 방지한다
		setAllLendingBookInfo(data);
		getBtnAnother().setEnabled(false);
	}

	private void setAllLendingBookInfo(String[][] data) {
		model = new DefaultTableModel(data, TABLE_NAME) {
			public boolean isCellEditable(int arg0, int arg1)// 테이블을 직접 수정할수 없게
																// 막아두는 부분이다.
			{
				return false;
			}
		};
		getTable().setModel(model);
	}

	@Override
	protected void btnSearchActionPerformed(ActionEvent e) {
		checkMember();

	}

	@Override
	protected void btnAnother_actionPerformed(ActionEvent e) {
		Member member = new Member();
		member.setMemberCode(members[members_index][0]);
		member.setMemberName(members[members_index][1]);
		member.setMemberTel(members[members_index][2]);
		
		setpMemberTextFieldFromMember(member);
		setAllLendingBookInfo(getAllLendingBookInfo(member.getMemberCode()));
		setCount();
		setCountTextFieldFromCount(delayCount, lendingCount, returnCount);
		members_index = (members_index+1)%members_size;
	}

	protected void btnCloseActionPerformed(ActionEvent e) {
		refresh();
		dispose();

	}

	private void refresh() {
		data = null;
		model = new DefaultTableModel(data, TABLE_NAME);
		getTable().setModel(model);
		clearAllTextField();
		getBtnAnother().setEnabled(false);

	}

	private void clearAllTextField() {
		getpMemberName().getTfValue().setText("");
		getpMemberCode().getTfValue().setText("");
		getpMemberTel().getTfValue().setText("");
		getpBookOverdue().getTfValue().setText("");
		getpBookLending().getTfValue().setText("");
		getpMemberLending().getTfValue().setText("");
	}

	private void setpMemberTextFieldFromMember(Member member) {
		getpMemberCode().getTfValue().setText(member.getMemberCode());
		getpMemberTel().getTfValue().setText(member.getMemberTel());

	}

	private void setCountTextFieldFromCount(int delayCount, int lendingCount, int returnCount) {
		getpBookOverdue().getTfValue().setText(String.valueOf(delayCount));
		getpBookLending().getTfValue().setText(String.valueOf(lendingCount));
		getpMemberLending().getTfValue().setText(String.valueOf(returnCount + lendingCount + delayCount));
	}

	private String[][] getMemberFromMemberName(String memberName) {
		return SearchMemberService.getInstance().getMemberFromMemberName(memberName);
	}

	private String[][] getAllLendingBookInfo(String memberCode) {

		return SearchMemberService.getInstance().getAllLendingBookInfo(memberCode);
	}

	protected void tableMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private void checkMember() {
		String memberName = getpMemberName().getTfValue().getText().toString().trim();
		
		Member member = new Member();
		member.setMemberName(null);
		members = getMemberFromMemberName(memberName);
		members_size = members.length;
		if (members.length == 1) {
			member.setMemberCode(members[0][0]);
			member.setMemberName(members[0][1]);
			member.setMemberTel(members[0][2]);
			member.setMemberLending(Integer.parseInt(members[0][3]));
		} else if (members.length > 1) {
			member.setMemberCode(members[0][0]);
			member.setMemberName(members[0][1]);
			member.setMemberTel(members[0][2]);
			member.setMemberLending(Integer.parseInt(members[0][3]));
			JOptionPane.showMessageDialog(null, "중복된 회원 이름이 " + members.length + "명 있습니다.");
			members_index++;
			getBtnAnother().setEnabled(true);
		}
		if (member.getMemberName() == null) {
			JOptionPane.showMessageDialog(null, "해당하는 회원이름에 대한 결과가 없습니다.");
			refresh();
		} else {
			setpMemberTextFieldFromMember(member);
			setAllLendingBookInfo(getAllLendingBookInfo(member.getMemberCode()));
			setCount();
			setCountTextFieldFromCount(delayCount, lendingCount, returnCount);
		}
	}


	private void setCount() {
		int[] cnt = new int[3];
		cnt = SearchMemberService.getInstance().getCount();
		// System.out.println(cnt[0]+","+cnt[1]+","+cnt[2]);
		delayCount = cnt[0];
		lendingCount = cnt[1];
		returnCount = cnt[2];
	}
}
