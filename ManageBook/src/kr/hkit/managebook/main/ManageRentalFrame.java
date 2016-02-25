package kr.hkit.managebook.main;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.hkit.managebook.service.RentalService;
import kr.hkit.managebook.type.Book;
import kr.hkit.managebook.type.Member;

public class ManageRentalFrame extends AbsManageRentalFrame {
	private static final ManageRentalFrame instance = new ManageRentalFrame();
	private String[][] data = null;
	private DefaultTableModel model = null;
	private final String[] TABLE_NAME = { "도서 코드", "도서명", "저자", "출판사", "가격", "총대여 횟수" };

	public static ManageRentalFrame getInstance() {

		return instance;

	}

	private ManageRentalFrame() {

		getTable().getTableHeader().setReorderingAllowed(false);
		initSetting();

	}

	@Override
	protected void btnSearchActionPerformed(ActionEvent arg0) {
		Member member = getMemberFromMemberCode(getpMemberCode().getTfValue().getText());
		if (member.getMemberCode() == null) {
			JOptionPane.showMessageDialog(null, "해당하는 회원코드를 찾을수 없습니다.");

		} else {
			setMemberTextFieldFromMember(member);
			if ((getpBookCode().getTfValue().getText().trim() == null)
					|| (getpBookCode().getTfValue().getText().trim().equals(""))) {
				activateLendingButton(false);
			} else {
				activateLendingButton(true);
			}
		}

	}

	@Override
	public void tableMouseClicked(MouseEvent arg0) {

		int selected_row = getTable().getSelectedRow();
		String code = getTable().getModel().getValueAt(selected_row, 0).toString();
		Book book = getBookFromBookCode(code);
		setBookTextFieldFromBook(book);
		getpMemberCode().getTfValue().setFocusable(true);

		String name = getpMemberName().getTfValue().getText().trim();
		if (!name.equals("") && name != null) {
			activateLendingButton(true);
		}
	}

	@Override
	protected void btnCloseActionPerformed(ActionEvent arg0) {
		dispose();

	}

	@Override
	protected void btnRentalActionPerformed(ActionEvent arg0) {
		updateMemberLendingPlusOne(getpMemberCode().getTfValue().getText());
		updateBookLendingPlusOne(getpBookCode().getTfValue().getText());
		addReceiptsPayments(getpMemberCode().getTfValue().getText(), getpBookCode().getTfValue().getText(), new Date());
		updateRendingPossible(getpBookCode().getTfValue().getText(), false);
		clearBookTextField();
		refreshTable(getAllLendingPossibleBook());
		activateLendingButton(false);
	}

	private void updateRendingPossible(String bookCode, boolean trueFalse) {
		RentalService.getInstance().updateRendingPossible(bookCode, trueFalse);
	}

	private void addReceiptsPayments(String memberCode, String bookCode, Date lendingDate) {
		RentalService.getInstance().addReceiptsPayments(memberCode, bookCode, lendingDate);

	}

	// ----------구현--------------------------------------------
	private void activateLendingButton(boolean b) {
		getBtnRental().setEnabled(b);

	}

	private void refreshTable(String[][] data) {

		model = new DefaultTableModel(data, TABLE_NAME) {

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}

		};
		getTable().setModel(model);
	}

	public String[][] getAllLendingPossibleBook() {
		return RentalService.getInstance().getAllLendingPossibleBook();
	}

	private void disableTextField() {
		getpBookCode().getTfValue().setEditable(false);
		getpBookAuthor().getTfValue().setEditable(false);
		getpBookLendingCount().getTfValue().setEditable(false);
		getpBookMade().getTfValue().setEditable(false);
		getpBookName().getTfValue().setEditable(false);
		getpBookPrice().getTfValue().setEditable(false);
		getpMemberTel().getTfValue().setEditable(false);
		getpMemberName().getTfValue().setEditable(false);

	}

	private void setMemberTextFieldFromMember(Member member) {
		getpMemberName().getTfValue().setText(member.getMemberName());
		getpMemberTel().getTfValue().setText(member.getMemberTel());

	}

	private Member getMemberFromMemberCode(String text) {
		return RentalService.getInstance().getMemberFromMemberCode(text);
	}

	private void setBookTextFieldFromBook(Book book) {
		getpBookCode().getTfValue().setText(book.getBookCode());
		getpBookName().getTfValue().setText(book.getBookName());
		getpBookAuthor().getTfValue().setText(book.getBookAuthor());
		getpBookMade().getTfValue().setText(book.getBookMade());
		getpBookPrice().getTfValue().setText(book.getBookPrice() + "");
		getpBookLendingCount().getTfValue().setText(book.getBookLendingCount() + "");
	}

	private Book getBookFromBookCode(String code) {

		return RentalService.getInstance().getBookFromBookCode(code);
	}

	private void updateMemberLendingPlusOne(String code) {
		RentalService.getInstance().updateMemberLendingPlusOne(code);

	}

	private void updateBookLendingPlusOne(String code) {
		RentalService.getInstance().updateBookLendingPlusOne(code);

	}

	public void clearBookTextField() {
		getpBookAuthor().getTfValue().setText(null);
		getpBookCode().getTfValue().setText(null);
		getpBookLendingCount().getTfValue().setText(null);
		getpBookMade().getTfValue().setText(null);
		getpBookName().getTfValue().setText(null);
		getpBookPrice().getTfValue().setText(null);
	}

	public void clearMemberTextField() {
		getpMemberCode().getTfValue().setText(null);
		getpMemberName().getTfValue().setText(null);
		getpMemberTel().getTfValue().setText(null);
		getpBookMade().getTfValue().setText(null);

	}

	public void initSetting() {
		data = getAllLendingPossibleBook();
		refreshTable(data);

		activateLendingButton(false);
		disableTextField();
		clearBookTextField();
		clearMemberTextField();
	}
}
