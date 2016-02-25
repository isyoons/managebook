package kr.hkit.managebook.search;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.hkit.managebook.config.Config;
import kr.hkit.managebook.service.SearchBookService;
import kr.hkit.managebook.type.Book;

public class SearchBookFrame extends AbsSearchBookFrame {
	private final static SearchBookFrame instance = new SearchBookFrame();
	private final String[] TABLE_NAME = { "회원코드", "성명", "대여일", "반납일", "연체여부" };
	private DefaultTableModel model = null;
	private String[][] data = null;

	public static SearchBookFrame getInstance() {
		return instance;
	}

	private SearchBookFrame() {
		model = new DefaultTableModel(data, TABLE_NAME) {

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}

		};
		getTable().setModel(model);
		getTable().getTableHeader().setReorderingAllowed(false);
		disableTextField();
		getLblSumLendingValue().setText(" 회");
	}

	@Override
	protected void btnSearchActionPerformed(ActionEvent e) {
		String bookCodeOrName = getTfbookCode().getText().toString().trim();
		Book book = null;
		if (getRdbtnbookCode().isSelected()) {
			book = getBookFromBookCode(bookCodeOrName);
		} else if (getRdbtnbookName().isSelected()) {
			book = getBookFromBookName(bookCodeOrName);
		}
		if (book == null) {
			JOptionPane.showMessageDialog(null, "해당하는 도서에 대한 결과가 없습니다.");
		} else {
			setBookTextFieldFromBook(book);
			data = getAllOverdueLendingMemberFromBook(book.getBookCode());
			model = new DefaultTableModel(data, TABLE_NAME){

				@Override
				public boolean isCellEditable(int arg0, int arg1) {
					return false;
				}

			};
			getTable().setModel(model);
			int sumLending = getSumLending();
			getLblSumLendingValue().setText("  "+sumLending + " 회");
		}
	}

	private int getSumLending() {

		return SearchBookService.getInstance().getAllLendingCount();

	}

	@Override
	protected void btnCloseActionPerformed(ActionEvent e) {
		dispose();

	}

	@Override
	protected void tableMouseClicked(MouseEvent e) {
		// Do nothing;;;

	}

	// 0------------구현----------------
	private void disableTextField() {
		getPbookAuthor().getTfValue().setEditable(false);
		getPbookCode().getTfValue().setEditable(false);
		getPbookMade().getTfValue().setEditable(false);
		getPbookName().getTfValue().setEditable(false);
		getPbookPrice().getTfValue().setEditable(false);

	}

	private Book getBookFromBookCode(String bookCode) {
		return SearchBookService.getInstance().getBookFromBookCode(bookCode);
	}

	private Book getBookFromBookName(String bookName) {
		return SearchBookService.getInstance().getBookFromBookName(bookName);

	}

	private void setBookTextFieldFromBook(Book book) {
		getPbookAuthor().getTfValue().setText(book.getBookAuthor());
		getPbookCode().getTfValue().setText(book.getBookCode());
		getPbookMade().getTfValue().setText(book.getBookMade());
		getPbookName().getTfValue().setText(book.getBookName());
		getPbookPrice().getTfValue().setText(Config.DECIMALF.format(book.getBookPrice()));
	}

	private String[][] getAllOverdueLendingMemberFromBook(String bookCode) {

		return SearchBookService.getInstance().getAllOverdueLendingMemberFromBook(bookCode);
	}

	public void refresh() {
		getLblSumLendingValue().setText("회");
		// 빈테이블 만들기
		data = null;
		model = new DefaultTableModel(data, TABLE_NAME){

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}

		};
		getTable().setModel(model);
		// 텍스트 필드 비우기
		clearAllTextField();
		// 라디오버튼 설정
		getRdbtnbookName().setSelected(true);

	}

	private void clearAllTextField() {
		getPbookAuthor().getTfValue().setText(null);
		getPbookCode().getTfValue().setText(null);
		getPbookMade().getTfValue().setText(null);
		getPbookName().getTfValue().setText(null);
		getPbookPrice().getTfValue().setText(null);
		getTfbookCode().setText(null);
	}
}
