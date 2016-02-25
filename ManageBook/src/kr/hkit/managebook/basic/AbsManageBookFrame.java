
package kr.hkit.managebook.basic;

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
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;

public abstract class AbsManageBookFrame extends JFrame implements ActionListener, MouseListener, KeyListener {

	private JPanel contentPane;
	private LblPlusTfPanel pBookName;
	private LblPlusTfPanel pBookAuthor;
	private LblPlusTfPanel pBookMade;
	private LblPlusTfPanel pBookPrice;
	private LblPlusTfPanel pBookCode;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnClose;
	private JPanel pBtn2;
	private JTable table;

	public AbsManageBookFrame() {
		setTitle("도서 관리");
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
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
		pBookCode.getTfValue().addKeyListener(this);
		pBookCode.getLblName().setText("도 서 코 드");
		pLeft.add(pBookCode);

		pBookName = new LblPlusTfPanel();
		pBookName.getTfValue().addKeyListener(this);
		pBookName.getLblName().setText("도  서  명");
		pLeft.add(pBookName);

		pBookAuthor = new LblPlusTfPanel();
		pBookAuthor.getTfValue().addKeyListener(this);
		pBookAuthor.getLblName().setText("저       자");
		pLeft.add(pBookAuthor);

		pBookMade = new LblPlusTfPanel();
		pBookMade.getTfValue().addKeyListener(this);
		pBookMade.getLblName().setText("출  판  사");
		pLeft.add(pBookMade);

		pBookPrice = new LblPlusTfPanel();
		pBookPrice.getTfValue().addKeyListener(this);
		pBookPrice.getLblName().setText("가      격");
		pLeft.add(pBookPrice);

		JPanel pBtn = new JPanel();
		pLeft.add(pBtn);
		pBtn.setLayout(new GridLayout(1, 5, 0, 0));

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnSave = new JButton("저장");
		btnSave.addActionListener(this);
		pBtn.add(btnSave);

		btnEdit = new JButton("수정");
		btnEdit.addActionListener(this);
		pBtn.add(btnEdit);

		JPanel pRight = new JPanel();
		pMain.add(pRight);
		pRight.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pEmpty = new JPanel();
		pRight.add(pEmpty);

		JPanel pEmpty2 = new JPanel();
		pRight.add(pEmpty2);

		JPanel pEmpty3 = new JPanel();
		pRight.add(pEmpty3);

		JPanel pEmpty4 = new JPanel();
		pRight.add(pEmpty4);

		JPanel pEmpty5 = new JPanel();
		pRight.add(pEmpty5);

		pBtn2 = new JPanel();
		pRight.add(pBtn2);
		pBtn2.setLayout(new GridLayout(0, 3, 0, 0));

		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(this);
		pBtn2.add(btnDelete);

		btnClose = new JButton("닫기");
		btnClose.addActionListener(this);
		pBtn2.add(btnClose);

		JPanel pTable = new JPanel();
		pTable.setBorder(new TitledBorder(null, " [\uB3C4\uC11C\uBAA9\uB85D]", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pTable);
		pTable.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		pTable.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		scrollPane.setViewportView(table);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClose) {
			btnCloseActionPerformed(e);
		}
		if (e.getSource() == btnDelete) {
			btnDeleteActionPerformed(e);
		}
		if (e.getSource() == btnEdit) {
			btnEditActionPerformed(e);
		}
		if (e.getSource() == btnSave) {
			btnSaveActionPerformed(e);
		}
		if (e.getSource() == btnAdd) {
			btnAddActionPerformed(e);
		}
	}

	abstract protected void btnAddActionPerformed(ActionEvent e);

	abstract protected void btnSaveActionPerformed(ActionEvent e);

	abstract protected void btnEditActionPerformed(ActionEvent e);

	abstract protected void btnDeleteActionPerformed(ActionEvent e);

	abstract protected void btnCloseActionPerformed(ActionEvent e);

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

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public void setBtnClose(JButton btnClose) {
		this.btnClose = btnClose;
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == table) {
			tableMouseClicked(arg0);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	abstract protected void tableMouseClicked(MouseEvent arg0);

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == pBookPrice.getTfValue()) {
			pBookTfValueKeyReleased(arg0);
		}
		if (arg0.getSource() == pBookMade.getTfValue()) {
			pBookTfValueKeyReleased(arg0);
		}
		if (arg0.getSource() == pBookAuthor.getTfValue()) {
			pBookTfValueKeyReleased(arg0);
		}
		if (arg0.getSource() == pBookName.getTfValue()) {
			pBookTfValueKeyReleased(arg0);
		}
		if (arg0.getSource() == pBookCode.getTfValue()) {
			pBookTfValueKeyReleased(arg0);
		}
	}

	public void keyTyped(KeyEvent arg0) {
	}

	abstract protected void pBookTfValueKeyReleased(KeyEvent arg0);
}
