package kr.hkit.managebook.basic;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.hkit.managebook.service.BookService;
import kr.hkit.managebook.type.Book;

public class ManageBookFrame extends AbsManageBookFrame {
	private final static ManageBookFrame instance = new ManageBookFrame();
	private String[][] data = null;
	private DefaultTableModel model = null;
	private String[] TABLE_NAME = { "도서코드", "도서명", "저자", "출판사", "가격" };

	public static ManageBookFrame getInstance() {
		return instance;
	}

	private ManageBookFrame() {
		setTitle("도서관리");
		data = BookService.getInstance().getAllBookList();
		getTable().getTableHeader().setReorderingAllowed(false);
		refreshTable(data);

		initialSetting();

	}

	// ------- 버튼 동작
	@Override
	protected void btnAddActionPerformed(ActionEvent e) {
		setTextFieldEditable(true);
		getBtnSave().setEnabled(false);
		getBtnEdit().setEnabled(false);
		getBtnDelete().setEnabled(false);
		clear();
	}

	@Override
	protected void btnSaveActionPerformed(ActionEvent e) {
		String code = getpBookCode().getTfValue().getText().trim();
		String name = getpBookName().getTfValue().getText().trim();
		String author = getpBookAuthor().getTfValue().getText().trim();
		String made = getpBookMade().getTfValue().getText().trim();
		String price_str = getpBookPrice().getTfValue().getText().trim();
		int price = 0;

		if (!isValidCode(code)) {
			JOptionPane.showMessageDialog(null, "코드의 형식이 맞지않습니다. 코드:4글자 첫글자:영문자,나머지:영문자 혹은 숫자");
		} else if (!isValidPrice(price_str)) {
			JOptionPane.showMessageDialog(null, "가격은 숫자로 입력해주세요.");
		} else {
			// 모든 조건이 충족했을때.
			price = Integer.parseInt(price_str);
			Book tmpBook = new Book(code, name, author, made, price, true, 0);
			if (insertBookData(tmpBook)) {

				data = BookService.getInstance().getAllBookList();
				refreshTable(data);
				JOptionPane.showMessageDialog(null, "도서 입력에 성공 했습니다.");
				activateBookCode(false);
				getBtnEdit().setEnabled(true);
				getBtnDelete().setEnabled(true);
				getBtnSave().setEnabled(false);
			}
		}

	}

	@Override
	protected void btnEditActionPerformed(ActionEvent e) {

		Book tmpBook = getBookFromTextField();
		BookService.getInstance().updateBookData(tmpBook);
		data = BookService.getInstance().getAllBookList();
		JOptionPane.showMessageDialog(null, "도서 수정에 성공 했습니다.");
		refreshTable(data);
	}

	@Override
	protected void btnDeleteActionPerformed(ActionEvent e) {

		String code = getpBookCode().getTfValue().getText().trim();
		int ok = JOptionPane.showConfirmDialog(null, "정말 삭제 하시겠습니까?", "삭제", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (ok == 0) {
			deleteBookData(code);
			getBtnEdit().setEnabled(false);
			getBtnDelete().setEnabled(false);
			data = BookService.getInstance().getAllBookList();
			refreshTable(data);
			initialSetting();
		}

	}

	private void deleteBookData(String code) {
		BookService.getInstance().deleteBookData(code);

	}

	@Override
	protected void btnCloseActionPerformed(ActionEvent e) {
		dispose();
	}

	@Override
	protected void tableMouseClicked(MouseEvent arg0) {
		int selected_row = getTable().getSelectedRow();
		String code = getTable().getModel().getValueAt(selected_row, 0).toString();
		System.out.println(code);
		Book book = getBookFromBookCode(code);
		System.out.println(book);
		if (book != null) {
			setBookTextFieldFromBook(book);
			getBtnEdit().setEnabled(true);
			getBtnDelete().setEnabled(true);
			getBtnSave().setEnabled(false);
			setTextFieldEditable(true);
			activateBookCode(false);

		}

	}

	@Override
	protected void pBookTfValueKeyReleased(KeyEvent arg0) {
		boolean isCodeTyped = true;
		boolean isNameTyped = true;
		boolean isAuthorTyped = true;
		boolean isMadeTyped = true;
		boolean isPriceTyped = true;
		isCodeTyped = ((getpBookCode().getTfValue().getText().toString().trim()).equals(""))
				|| (getpBookCode().getTfValue().getText() == null);

		isNameTyped = ((getpBookName().getTfValue().getText().toString().trim()).equals(""))
				|| (getpBookName().getTfValue().getText() == null);
		isAuthorTyped = ((getpBookAuthor().getTfValue().getText().toString().trim()).equals(""))
				|| (getpBookAuthor().getTfValue().getText() == null);
		isMadeTyped = ((getpBookMade().getTfValue().getText().toString().trim()).equals(""))
				|| (getpBookMade().getTfValue().getText() == null);
		isPriceTyped = ((getpBookPrice().getTfValue().getText().toString().trim()).equals(""))
				|| (getpBookPrice().getTfValue().getText() == null);

		if (!isCodeTyped && !isNameTyped && !isAuthorTyped && !isMadeTyped && !isPriceTyped) {
			if (getpBookCode().getTfValue().isEditable()) {
				getBtnSave().setEnabled(true);
			} else {
				getBtnEdit().setEnabled(true);
			}
		} else {
			getBtnSave().setEnabled(false);
			getBtnEdit().setEnabled(false);
		}
	}

	// ----------------------------------------- 메소드 구현
	private void setBookTextFieldFromBook(Book book) {
		getpBookCode().getTfValue().setText(book.getBookCode());
		getpBookName().getTfValue().setText(book.getBookName());
		getpBookAuthor().getTfValue().setText(book.getBookAuthor());
		getpBookMade().getTfValue().setText(book.getBookMade());
		getpBookPrice().getTfValue().setText(Integer.toString(book.getBookPrice()));
	}

	private Book getBookFromBookCode(String code) {
		Book book = null;
		book = BookService.getInstance().getBookFromBookCode(code);

		return book;
	}

	public void clear() {
		getpBookCode().getTfValue().setText("");
		getpBookName().getTfValue().setText("");
		getpBookAuthor().getTfValue().setText("");
		getpBookMade().getTfValue().setText("");
		getpBookPrice().getTfValue().setText("");
	}

	public void setTextFieldEditable(boolean trueFalse) {
		getpBookCode().getTfValue().setEditable(trueFalse);
		getpBookName().getTfValue().setEditable(trueFalse);
		getpBookAuthor().getTfValue().setEditable(trueFalse);
		getpBookMade().getTfValue().setEditable(trueFalse);
		getpBookPrice().getTfValue().setEditable(trueFalse);
	}

	public void activateBookCode(boolean trueFalse) {
		getpBookCode().getTfValue().setEditable(trueFalse);
	}

	private void refreshTable(String[][] data) {
		model = new DefaultTableModel(data, TABLE_NAME) {
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		getTable().setModel(model);
	}

	private void setAllBtnDisable() {
		getBtnAdd().setEnabled(false);
		getBtnSave().setEnabled(false);
		getBtnEdit().setEnabled(false);
		getBtnDelete().setEnabled(false);
		getBtnClose().setEnabled(false);
	}

	private Book getBookFromTextField() {
		Book tmpBook = null;
		try {
			String code = getpBookCode().getTfValue().getText().trim();
			String name = getpBookName().getTfValue().getText().trim();
			String author = getpBookAuthor().getTfValue().getText().trim();
			String made = getpBookMade().getTfValue().getText().trim();
			int price = Integer.parseInt(getpBookPrice().getTfValue().getText().trim());
			tmpBook = new Book(code, name, author, made, price, true, 0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return tmpBook;
	}

	public void initialSetting() {
		// 버튼 텍스트필드 활성화 내용.. 초기화
		setAllBtnDisable();
		getBtnAdd().setEnabled(true);
		getBtnClose().setEnabled(true);
		setTextFieldEditable(false);
		clear();

	}

	private boolean insertBookData(Book tmpBook) {

		return BookService.getInstance().insertBookData(tmpBook);
	}

	private boolean isValidPrice(String price_str) {
		try {
			Integer.parseInt(price_str);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	private boolean isValidCode(String code) {
		String nameRegExpr = "^[a-zA-Z][a-zA-Z0-9]{3}$";
		return Pattern.matches(nameRegExpr, code);
	}

}
