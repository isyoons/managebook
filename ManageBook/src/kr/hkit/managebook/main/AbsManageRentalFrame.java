package kr.hkit.managebook.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import kr.hkit.managebook.comp.LblPlusTfPanel;
import javax.swing.border.TitledBorder;

public abstract class AbsManageRentalFrame extends JFrame implements ActionListener, MouseListener {
	private JPanel contentPane;
	private JTable table;
	private LblPlusTfPanel pBookCode;
	private LblPlusTfPanel pBookName;
	private LblPlusTfPanel pBookAuthor;
	private LblPlusTfPanel pBookMade;
	private LblPlusTfPanel pBookPrice;
	private LblPlusTfPanel pBookLendingCount;
	private LblPlusTfPanel pMemberTel;
	private LblPlusTfPanel pMemberName;
	private JButton btnSearch;
	private JButton btnRental;
	private JButton btnClose;
	private LblPlusTfPanel pMemberCode;

	public AbsManageRentalFrame() {
		setTitle("대여 관리");
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pMain = new JPanel();
		pMain.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(pMain);
		pMain.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel pLeft = new JPanel();
		pMain.add(pLeft);
		pLeft.setLayout(new GridLayout(0, 1, 0, 0));

		pBookCode = new LblPlusTfPanel();
		pBookCode.getLblName().setText("도 서 코 드");
		pLeft.add(pBookCode);

		pBookName = new LblPlusTfPanel();
		pBookName.getLblName().setText("도   서   명");
		pLeft.add(pBookName);

		pBookAuthor = new LblPlusTfPanel();
		pBookAuthor.getLblName().setText("저         자");
		pLeft.add(pBookAuthor);

		pBookMade = new LblPlusTfPanel();
		pBookMade.getLblName().setText("출   판   사");
		pLeft.add(pBookMade);

		pBookPrice = new LblPlusTfPanel();
		pBookPrice.getLblName().setText("가         격");
		pLeft.add(pBookPrice);

		pBookLendingCount = new LblPlusTfPanel();
		pBookLendingCount.getLblName().setText("총대여횟수");
		pLeft.add(pBookLendingCount);

		JPanel pRight = new JPanel();
		pMain.add(pRight);
		pRight.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pMemberCodeMain = new JPanel();
		pRight.add(pMemberCodeMain);
		pMemberCodeMain.setLayout(new BorderLayout(0, 0));

		pMemberCode = new LblPlusTfPanel();
		pMemberCode.getLblName().setText("회원코드");
		pMemberCodeMain.add(pMemberCode, BorderLayout.CENTER);

		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		pMemberCodeMain.add(btnSearch, BorderLayout.EAST);

		pMemberName = new LblPlusTfPanel();
		pMemberName.getLblName().setText("성      명");
		pRight.add(pMemberName);

		pMemberTel = new LblPlusTfPanel();
		pMemberTel.getLblName().setText("전화번호");
		pRight.add(pMemberTel);

		JPanel pEmpty = new JPanel();
		pRight.add(pEmpty);

		JPanel pEmpty2 = new JPanel();
		pRight.add(pEmpty2);

		JPanel pBtn = new JPanel();
		pRight.add(pBtn);
		pBtn.setLayout(new GridLayout(0, 3, 0, 0));

		btnRental = new JButton("대여");
		btnRental.addActionListener(this);
		pBtn.add(btnRental);

		btnClose = new JButton("닫기");
		btnClose.addActionListener(this);
		pBtn.add(btnClose);

		JPanel pTable = new JPanel();
		pTable.setBorder(new TitledBorder(null, " [\uB3C4\uC11C \uB300\uC5EC\uAC00\uB2A5 \uBAA9\uB85D]", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pTable);
		pTable.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		pTable.add(scrollPane_1);

		table = new JTable();
		table.addMouseListener(this);
		scrollPane_1.setViewportView(table);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public LblPlusTfPanel getpBookCode() {
		return pBookCode;
	}

	public void setpBookCode(LblPlusTfPanel pBookCode) {
		this.pBookCode = pBookCode;
	}

	public LblPlusTfPanel getpBookName() {
		return pBookName;
	}

	public void setpBookName(LblPlusTfPanel pBookName) {
		this.pBookName = pBookName;
	}

	public LblPlusTfPanel getpBookAuthor() {
		return pBookAuthor;
	}

	public void setpBookAuthor(LblPlusTfPanel pBookAuthor) {
		this.pBookAuthor = pBookAuthor;
	}

	public LblPlusTfPanel getpBookMade() {
		return pBookMade;
	}

	public void setpBookMade(LblPlusTfPanel pBookMade) {
		this.pBookMade = pBookMade;
	}

	public LblPlusTfPanel getpBookPrice() {
		return pBookPrice;
	}

	public void setpBookPrice(LblPlusTfPanel pBookPrice) {
		this.pBookPrice = pBookPrice;
	}

	public LblPlusTfPanel getpBookLendingCount() {
		return pBookLendingCount;
	}

	public void setpBookLendingCount(LblPlusTfPanel pBookLendingCount) {
		this.pBookLendingCount = pBookLendingCount;
	}

	public LblPlusTfPanel getpMemberTel() {
		return pMemberTel;
	}

	public void setpMemberTel(LblPlusTfPanel pMemberTel) {
		this.pMemberTel = pMemberTel;
	}

	public LblPlusTfPanel getpMemberName() {
		return pMemberName;
	}

	public void setpMemberName(LblPlusTfPanel pMemberName) {
		this.pMemberName = pMemberName;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnClose) {
			btnCloseActionPerformed(arg0);
		}
		if (arg0.getSource() == btnRental) {
			btnRentalActionPerformed(arg0);
		}
		if (arg0.getSource() == btnSearch) {
			btnSearchActionPerformed(arg0);
		}
	}

	abstract protected void btnSearchActionPerformed(ActionEvent arg0);

	abstract protected void btnRentalActionPerformed(ActionEvent arg0);

	abstract protected void btnCloseActionPerformed(ActionEvent arg0);

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public JButton getBtnRental() {
		return btnRental;
	}

	public void setBtnRental(JButton btnRental) {
		this.btnRental = btnRental;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public void setBtnClose(JButton btnClose) {
		this.btnClose = btnClose;
	}

	public LblPlusTfPanel getpMemberCode() {
		return pMemberCode;
	}

	public void setpMemberCode(LblPlusTfPanel pMemberCode) {
		this.pMemberCode = pMemberCode;
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == table) {
			tableMouseClicked(arg0);
		}
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	abstract public void tableMouseClicked(MouseEvent arg0);

}
