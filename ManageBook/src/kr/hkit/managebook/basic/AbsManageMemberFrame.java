package kr.hkit.managebook.basic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import kr.hkit.managebook.comp.LblPlusTfPanel;
import javax.swing.border.TitledBorder;

public abstract class AbsManageMemberFrame extends JFrame implements ActionListener, MouseListener, KeyListener {

	private JPanel contentPane;

	private LblPlusTfPanel pMemberName;
	private LblPlusTfPanel pMemberTel;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnClose;
	private JPanel pBtn2;
	private JTable table;
	private LblPlusTfPanel pMemberCode;
	private JScrollPane scrollPane;

	public LblPlusTfPanel getpMemberName() {
		return pMemberName;
	}

	public void setpMemberName(LblPlusTfPanel pMemberName) {
		this.pMemberName = pMemberName;
	}

	public LblPlusTfPanel getpMemberTel() {
		return pMemberTel;
	}

	public void setpMemberTel(LblPlusTfPanel pMemberTel) {
		this.pMemberTel = pMemberTel;
	}

	public LblPlusTfPanel getpMemberCode() {
		return pMemberCode;
	}

	public void setpMemberCode(LblPlusTfPanel pMemberCode) {
		this.pMemberCode = pMemberCode;
	}

	public AbsManageMemberFrame() {
		setTitle("회원 관리");
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel pMain = new JPanel();
		pMain.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(pMain);
		pMain.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel pLeft = new JPanel();
		pMain.add(pLeft);
		pLeft.setLayout(new GridLayout(0, 1, 0, 0));

		pMemberCode = new LblPlusTfPanel();
		pMemberCode.getTfValue().addKeyListener(this);
		pMemberCode.getLblName().setText("회 원 코 드");
		pLeft.add(pMemberCode);

		pMemberName = new LblPlusTfPanel();
		pMemberName.getTfValue().addKeyListener(this);
		pMemberName.getLblName().setText("성 명");
		pLeft.add(pMemberName);

		pMemberTel = new LblPlusTfPanel();
		pMemberTel.getTfValue().addKeyListener(this);
		pMemberTel.getLblName().setText("전 화 번 호");
		pLeft.add(pMemberTel);

		JPanel pEmpty4 = new JPanel();
		pLeft.add(pEmpty4);

		JPanel pEmpty5 = new JPanel();
		pLeft.add(pEmpty5);

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

		JPanel pEmpty6 = new JPanel();
		pRight.add(pEmpty6);

		JPanel pEmpty7 = new JPanel();
		pRight.add(pEmpty7);

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
		pTable.setBorder(new TitledBorder(null, " [\uD68C\uC6D0\uBAA9\uB85D]", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pTable);
		pTable.setLayout(new GridLayout(0, 1, 0, 0));

		scrollPane = new JScrollPane();
		pTable.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column", "New column",
						"New column" }));
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

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			tableMouseClicked(e);
		}
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

	abstract protected void tableMouseClicked(MouseEvent e);

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == pMemberTel.getTfValue()) {
			pMemberTfValueKeyReleased(arg0);
		}
		if (arg0.getSource() == pMemberName.getTfValue()) {
			pMemberTfValueKeyReleased(arg0);
		}
		if (arg0.getSource() == pMemberCode.getTfValue()) {
			pMemberTfValueKeyReleased(arg0);
		}
	}

	public void keyTyped(KeyEvent arg0) {
	}

	abstract protected void pMemberTfValueKeyReleased(KeyEvent arg0);

}
