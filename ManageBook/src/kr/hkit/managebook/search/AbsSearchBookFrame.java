package kr.hkit.managebook.search;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import kr.hkit.managebook.comp.LblPlusTfPanel;

public abstract class AbsSearchBookFrame extends JFrame implements ActionListener, MouseListener
{
	private JPanel contentPane;
	private LblPlusTfPanel pbookCode;
	private LblPlusTfPanel pbookName;
	private LblPlusTfPanel pbookMade;
	private LblPlusTfPanel pbookAuthor;
	private LblPlusTfPanel pbookPrice;
	private JRadioButton rdbtnbookName;
	private JRadioButton rdbtnbookCode;
	private JButton btnSearch;
	private JButton btnClose;
	private JTable table;
	private JLabel lblSumLendingValue;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tfbookCode;

	public AbsSearchBookFrame()
	{
		setTitle("도서 검색");
		setBounds(100, 100, 600, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pSearchCode = new JPanel();
		contentPane.add(pSearchCode, BorderLayout.NORTH);
		pSearchCode.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		pSearchCode.add(panel);

		rdbtnbookName = new JRadioButton("도서명");
		panel.add(rdbtnbookName);
		buttonGroup.add(rdbtnbookName);
		rdbtnbookName.setSelected(true);

		rdbtnbookCode = new JRadioButton("도서코드");
		panel.add(rdbtnbookCode);
		buttonGroup.add(rdbtnbookCode);

		tfbookCode = new JTextField();
		pSearchCode.add(tfbookCode);
		tfbookCode.setColumns(10);

		JPanel panel_1 = new JPanel();
		pSearchCode.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		btnSearch = new JButton("검색");
		panel_1.add(btnSearch);

		btnClose = new JButton("닫기");
		panel_1.add(btnClose);
		btnClose.addActionListener(this);
		btnSearch.addActionListener(this);

		JPanel pInfo = new JPanel();
		contentPane.add(pInfo);
		pInfo.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pbookInfoE = new JPanel();
		pbookInfoE.setBorder(new TitledBorder(null, " [\uB3C4\uC11C\uC815\uBCF4]", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pInfo.add(pbookInfoE);
		pbookInfoE.setLayout(new GridLayout(5, 0, 0, 0));

		pbookCode = new LblPlusTfPanel();
		GridLayout gridLayout = (GridLayout) pbookCode.getLayout();
		gridLayout.setColumns(3);
		gridLayout.setRows(0);
		pbookInfoE.add(pbookCode);
		pbookCode.getLblName().setText("도서코드 :");

		pbookName = new LblPlusTfPanel();
		GridLayout gridLayout_1 = (GridLayout) pbookName.getLayout();
		gridLayout_1.setColumns(3);
		gridLayout_1.setRows(0);
		pbookInfoE.add(pbookName);
		pbookName.getLblName().setText("도 서 명 :");

		pbookMade = new LblPlusTfPanel();
		GridLayout gridLayout_2 = (GridLayout) pbookMade.getLayout();
		gridLayout_2.setColumns(3);
		gridLayout_2.setRows(0);
		pbookInfoE.add(pbookMade);
		pbookMade.getLblName().setText("출 판 사 :");

		pbookAuthor = new LblPlusTfPanel();
		GridLayout gridLayout_3 = (GridLayout) pbookAuthor.getLayout();
		gridLayout_3.setColumns(3);
		gridLayout_3.setRows(0);
		pbookInfoE.add(pbookAuthor);
		pbookAuthor.getLblName().setText("저     자 :");

		pbookPrice = new LblPlusTfPanel();
		GridLayout gridLayout_4 = (GridLayout) pbookPrice.getLayout();
		gridLayout_4.setColumns(3);
		gridLayout_4.setRows(0);
		pbookInfoE.add(pbookPrice);
		pbookPrice.getLblName().setText("가     격 :");

		JPanel plentalInfo = new JPanel();
		plentalInfo.setBorder(new TitledBorder(null, " [\uB300\uC5EC\uC815\uBCF4]", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pInfo.add(plentalInfo);
		plentalInfo.setLayout(new BorderLayout(0, 0));

		JScrollPane splentalInfo = new JScrollPane();
		plentalInfo.add(splentalInfo, BorderLayout.CENTER);

		table = new JTable();
		table.addMouseListener(this);

		splentalInfo.setViewportView(table);

		JPanel plentalSum = new JPanel();
		plentalInfo.add(plentalSum, BorderLayout.SOUTH);
		plentalSum.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblSumLending = new JLabel("총대여 횟수  :");
		lblSumLending.setHorizontalAlignment(SwingConstants.RIGHT);
		plentalSum.add(lblSumLending);

		lblSumLendingValue = new JLabel(" 회");
		plentalSum.add(lblSumLendingValue);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnClose)
		{
			btnCloseActionPerformed(e);
		}
		if (e.getSource() == btnSearch)
		{
			btnSearchActionPerformed(e);
		}
	}

	abstract protected void btnSearchActionPerformed(ActionEvent e);

	abstract protected void btnCloseActionPerformed(ActionEvent e);

	public JTable getTable()
	{
		return table;
	}

	public void setTable(JTable table)
	{
		this.table = table;
	}

	public LblPlusTfPanel getPbookCode()
	{
		return pbookCode;
	}

	public void setPbookCode(LblPlusTfPanel pbookCode)
	{
		this.pbookCode = pbookCode;
	}

	public LblPlusTfPanel getPbookName()
	{
		return pbookName;
	}

	public void setPbookName(LblPlusTfPanel pbookName)
	{
		this.pbookName = pbookName;
	}

	public LblPlusTfPanel getPbookMade()
	{
		return pbookMade;
	}

	public void setPbookMade(LblPlusTfPanel pbookMade)
	{
		this.pbookMade = pbookMade;
	}

	public LblPlusTfPanel getPbookAuthor()
	{
		return pbookAuthor;
	}

	public void setPbookAuthor(LblPlusTfPanel pbookAuthor)
	{
		this.pbookAuthor = pbookAuthor;
	}

	public LblPlusTfPanel getPbookPrice()
	{
		return pbookPrice;
	}

	public void setPbookPrice(LblPlusTfPanel pbookPrice)
	{
		this.pbookPrice = pbookPrice;
	}

	public JRadioButton getRdbtnbookName()
	{
		return rdbtnbookName;
	}

	public void setRdbtnbookName(JRadioButton rdbtnbookName)
	{
		this.rdbtnbookName = rdbtnbookName;
	}

	public JRadioButton getRdbtnbookCode()
	{
		return rdbtnbookCode;
	}

	public void setRdbtnbookCode(JRadioButton rdbtnbookCode)
	{
		this.rdbtnbookCode = rdbtnbookCode;
	}

	public JLabel getLblSumLendingValue()
	{
		return lblSumLendingValue;
	}

	public void setLblSumLendingValue(JLabel lblSumLendingValue)
	{
		this.lblSumLendingValue = lblSumLendingValue;
	}

	public ButtonGroup getButtonGroup()
	{
		return buttonGroup;
	}

	public void mouseClicked(MouseEvent e)
	{
		if (e.getSource() == table)
		{
			tableMouseClicked(e);
		}
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void mousePressed(MouseEvent e)
	{
	}

	public void mouseReleased(MouseEvent e)
	{
	}

	abstract protected void tableMouseClicked(MouseEvent e);

	public JTextField getTfbookCode()
	{
		return tfbookCode;
	}

	public void setTfbookCode(JTextField tfbookCode)
	{
		this.tfbookCode = tfbookCode;
	}

}
