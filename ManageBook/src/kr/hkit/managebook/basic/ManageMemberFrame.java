package kr.hkit.managebook.basic;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.hkit.managebook.service.MemberService;
import kr.hkit.managebook.type.Member;

public class ManageMemberFrame extends AbsManageMemberFrame {
	private final static ManageMemberFrame instance = new ManageMemberFrame();
	private String[][] data = null;
	private DefaultTableModel model = null;
	private final String[] TABLE_NAME = { "회원 코드", "성명", "전화번호" };

	public static ManageMemberFrame getInstance() {
		return instance;
	}

	private ManageMemberFrame() {
		setTitle("회원 관리");
		data = getAllMemberList();
		getTable().getTableHeader().setReorderingAllowed(false);
		refreshTable(data);
		initSetting();

	}

	@Override
	protected void btnAddActionPerformed(ActionEvent e) {
		clear();
		activateMemberTextField(true);
		getBtnDelete().setEnabled(false);
		getBtnEdit().setEnabled(false);

	}

	@Override
	protected void btnSaveActionPerformed(ActionEvent e) {
		String code = getpMemberCode().getTfValue().getText().trim();
		String name = getpMemberName().getTfValue().getText().trim();
		String tel = getpMemberTel().getTfValue().getText().trim();

		if (!isValidCode(code)) {
			JOptionPane.showMessageDialog(null, "코드의 형식이 맞지않습니다.코드:4글자 첫글자:영문자,나머지:영문자 혹은 숫자");
		} else if (!isValidTel(tel)) {
			JOptionPane.showMessageDialog(null, "전화번호의 형식이 맞지 않습니다.XX(X)-XXX(X)-XXXX");
		} else if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 제대로 입력하세요");
		} else {
			// 모든 조건을 통과했을때

			Member member = new Member(code, name, tel, 0);
			if (addMember(member)) {
				getBtnEdit().setEnabled(true);
				getBtnDelete().setEnabled(true);
				activateMemberCodeTextField(false);
				getBtnSave().setEnabled(false);
				// 테이블 갱신
				data = getAllMemberList();
				refreshTable(data);
				JOptionPane.showMessageDialog(null, "회원 입력에 성공했습니다.");

			}
		}
	}

	@Override
	protected void btnEditActionPerformed(ActionEvent e) {

		String code = getpMemberCode().getTfValue().getText().trim();
		String name = getpMemberName().getTfValue().getText().trim();
		String tel = getpMemberTel().getTfValue().getText().trim();
		if (!isValidTel(tel)) {
			JOptionPane.showMessageDialog(null, "전화번호의 형식이 맞지 않습니다.(XX(X)-XXX(X)-XXXX");
		} else if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 제대로 입력하세요");
		} else {
			Member member = new Member(code, name, tel, 0);
			if (updateMember(member)) {
				data = getAllMemberList();
				refreshTable(data);
				JOptionPane.showMessageDialog(null, "회원 수정에 성공했습니다.");
			}
		}

	}

	@Override
	protected void btnDeleteActionPerformed(ActionEvent e) {
		String code = getpMemberCode().getTfValue().getText().trim();
		int ok = JOptionPane.showConfirmDialog(null, "정말 삭제 하시겠습니까?", "삭제", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (ok == 0) {
			if (deleteMember(code)) {
				data = getAllMemberList();
				refreshTable(data);
				JOptionPane.showMessageDialog(null, "회원 삭제에 성공했습니다.");
				clear();
				getBtnEdit().setEnabled(false);
				getBtnDelete().setEnabled(false);
			}
		}
		initSetting();
	}

	@Override
	protected void btnCloseActionPerformed(ActionEvent e) {
		dispose();

	}

	@Override
	protected void tableMouseClicked(MouseEvent e) {
		int selected_row = getTable().getSelectedRow();
		String code = getTable().getModel().getValueAt(selected_row, 0).toString();
		String name = getTable().getModel().getValueAt(selected_row, 1).toString();
		String tel = getTable().getModel().getValueAt(selected_row, 2).toString();

		getpMemberCode().getTfValue().setText(code);
		getpMemberName().getTfValue().setText(name);
		getpMemberTel().getTfValue().setText(tel);

		activateMemberTextField(true);
		activateMemberCodeTextField(false);
		getBtnEdit().setEnabled(true);
		getBtnDelete().setEnabled(true);

	}

	@Override
	protected void pMemberTfValueKeyReleased(KeyEvent arg0) {
		boolean isNameTyped = true;
		boolean isCodeTyped = true;
		boolean isTelTyped = true;
		isCodeTyped = ((getpMemberCode().getTfValue().getText().toString().trim()).equals(""))
				|| (getpMemberCode().getTfValue().getText() == null);

		isNameTyped = ((getpMemberName().getTfValue().getText().toString().trim()).equals(""))
				|| (getpMemberName().getTfValue().getText() == null);
		isTelTyped = ((getpMemberTel().getTfValue().getText().toString().trim()).equals(""))
				|| (getpMemberTel().getTfValue().getText() == null);
		if (!isNameTyped && !isCodeTyped && !isTelTyped) {

			if (!getpMemberCode().getTfValue().isEditable()) {// member코드가 비활성화
																// 상태일때만 수정가능하므로
				getBtnEdit().setEnabled(true);
			} else {
				getBtnSave().setEnabled(true);
			}

		} else {
			getBtnSave().setEnabled(false);
			getBtnEdit().setEnabled(false);
		}

	}

	// ----------------구현
	private void refreshTable(String[][] data) {
		model = new DefaultTableModel(data, TABLE_NAME) {

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}

		};
		getTable().setModel(model);

	}

	private String[][] getAllMemberList() {

		return MemberService.getInstance().getAllMemberList();
	}

	private void activateMemberAllButton(boolean b) {
		getBtnAdd().setEnabled(b);
		getBtnClose().setEnabled(b);
		getBtnEdit().setEnabled(b);
		getBtnDelete().setEnabled(b);
		getBtnSave().setEnabled(b);
	}

	private void activateMemberTextField(boolean b) {
		getpMemberCode().getTfValue().setEditable(b);
		getpMemberName().getTfValue().setEditable(b);
		getpMemberTel().getTfValue().setEditable(b);

	}

	private void clear() {
		getpMemberCode().getTfValue().setText(null);
		getpMemberName().getTfValue().setText(null);
		getpMemberTel().getTfValue().setText(null);
	}

	private boolean isValidCode(String data) {
		String nameRegExpr = "^[a-zA-Z][a-zA-Z0-9]{3}$";
		return Pattern.matches(nameRegExpr, data);
	}

	private boolean isValidTel(String data) {
		String telRegExpr = "^[0]\\d{1,2}-\\d{3,4}-\\d{4}$";
		return Pattern.matches(telRegExpr, data);
	}

	private void activateMemberCodeTextField(boolean b) {
		getpMemberCode().getTfValue().setEditable(b);

	}

	private boolean addMember(Member member) {
		return MemberService.getInstance().addMember(member);

	}

	private boolean updateMember(Member member) {

		return MemberService.getInstance().updateMember(member);
	}

	private boolean deleteMember(String code) {
		return MemberService.getInstance().deleteMember(code);
	}

	public void initSetting() {
		activateMemberTextField(false);
		activateMemberAllButton(false);
		getBtnAdd().setEnabled(true);
		getBtnClose().setEnabled(true);
		clear();

	}
}
