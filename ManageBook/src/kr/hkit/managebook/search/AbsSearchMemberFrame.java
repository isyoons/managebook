package kr.hkit.managebook.search;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.FlowLayout;

import kr.hkit.managebook.comp.LblPlusTfPanel;

public abstract class AbsSearchMemberFrame extends JFrame implements ActionListener, MouseListener {
	private JPanel contentPane;
	private LblPlusTfPanel pMemberName;
	private LblPlusTfPanel pMemberCode;
	private LblPlusTfPanel pMemberTel;
	private LblPlusTfPanel pBookOverdue;
	private LblPlusTfPanel pBookLending;
	private LblPlusTfPanel pMemberLending;
	private JPanel pTable;
	private JButton btnSearch;
	private JButton btnClose;
	private JTable table;
	private JButton btnAnother;

	public AbsSearchMemberFrame() {
		setTitle("회원 검색");
		setBounds(100, 100, 600, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		pMemberName = new LblPlusTfPanel();
		pMemberName.getLblName().setText("성       명 :");
		panel_3.add(pMemberName, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_12 = new JPanel();
		panel_4.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new GridLayout(0, 4, 0, 0));

		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		panel_12.add(btnSearch);

		btnClose = new JButton("닫기");
		btnClose.addActionListener(this);
		panel_12.add(btnClose);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "[\uD68C\uC6D0\uC815\uBCF4]", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_1.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new GridLayout(4, 1, 0, 0));

		JPanel panel_13 = new JPanel();
		panel_6.add(panel_13);
		panel_13.setLayout(new GridLayout(0, 2, 0, 0));

		pMemberCode = new LblPlusTfPanel();
		pMemberCode.getTfValue().setEditable(false);
		pMemberCode.getLblName().setText("회원코드 :");
		panel_13.add(pMemberCode);

		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_8.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_13.add(panel_8);

		btnAnother = new JButton("다른 사람");
		btnAnother.addActionListener(this);
		panel_8.add(btnAnother);

		JPanel panel_15 = new JPanel();
		panel_6.add(panel_15);
		panel_15.setLayout(new GridLayout(0, 2, 0, 0));

		pMemberTel = new LblPlusTfPanel();
		pMemberTel.getTfValue().setEditable(false);
		pMemberTel.getLblName().setText("전화번호 :");
		panel_15.add(pMemberTel);

		JPanel panel_16 = new JPanel();
		panel_6.add(panel_16);
		panel_16.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));

		JPanel panel_23 = new JPanel();
		panel_7.add(panel_23, BorderLayout.NORTH);
		panel_23.setLayout(new BorderLayout(0, 0));

		pTable = new JPanel(); // db패널
		pTable.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "[\uB300\uC5EC\uC815\uBCF4]",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.add(pTable, BorderLayout.CENTER);
		pTable.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		pTable.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 4, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);

		JLabel label = new JLabel("총계 :");
		panel_5.add(label);

		pBookOverdue = new LblPlusTfPanel();
		pBookOverdue.getTfValue().setEditable(false);
		pBookOverdue.getLblName().setText("연체 :");
		panel_2.add(pBookOverdue);

		pBookLending = new LblPlusTfPanel();
		pBookLending.getTfValue().setEditable(false);
		pBookLending.getLblName().setText("대여중 :");
		panel_2.add(pBookLending);

		pMemberLending = new LblPlusTfPanel();
		pMemberLending.getTfValue().setEditable(false);
		pMemberLending.getLblName().setText("총 :");
		panel_2.add(pMemberLending);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAnother) {
			btnAnother_actionPerformed(e);
		}
		if (e.getSource() == btnClose) {
			btnCloseActionPerformed(e);
		}
		if (e.getSource() == btnSearch) {
			btnSearchActionPerformed(e);
		}
	}

	abstract protected void btnSearchActionPerformed(ActionEvent e);

	abstract protected void btnCloseActionPerformed(ActionEvent e);

	public LblPlusTfPanel getpMemberName() {
		return pMemberName;
	}

	public void setpMemberName(LblPlusTfPanel pMemberName) {
		this.pMemberName = pMemberName;
	}

	public LblPlusTfPanel getpMemberCode() {
		return pMemberCode;
	}

	public void setpMemberCode(LblPlusTfPanel string) {
		this.pMemberCode = string;
	}

	public LblPlusTfPanel getpMemberTel() {
		return pMemberTel;
	}

	public void setpMemberTel(LblPlusTfPanel pMemberTel) {
		this.pMemberTel = pMemberTel;
	}

	public LblPlusTfPanel getpBookOverdue() {
		return pBookOverdue;
	}

	public void setpBookOverdue(LblPlusTfPanel pBookOverdue) {
		this.pBookOverdue = pBookOverdue;
	}

	public LblPlusTfPanel getpBookLending() {
		return pBookLending;
	}

	public void setpBookLending(LblPlusTfPanel pBookLending) {
		this.pBookLending = pBookLending;
	}

	public LblPlusTfPanel getpMemberLending() {
		return pMemberLending;
	}

	public void setpMemberLending(LblPlusTfPanel pMemberLending) {
		this.pMemberLending = pMemberLending;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			tableMouseClicked(e);
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

	abstract protected void tableMouseClicked(MouseEvent e);

	abstract protected void btnAnother_actionPerformed(ActionEvent e);

	public JButton getBtnAnother() {
		return btnAnother;
	}

	public void setBtnAnother(JButton btnAnother) {
		this.btnAnother = btnAnother;
	}
	
}
