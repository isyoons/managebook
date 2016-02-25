package kr.hkit.managebook.main;

import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import kr.hkit.managebook.comp.LblPlusTfPanel;
import kr.hkit.managebook.datepicker.DatePickerPanel;
import kr.hkit.managebook.service.ReturnService;

public abstract class AbsManageReturnFrame extends JFrame implements ActionListener, MouseListener
{
	private JPanel contentPane;
	private JTable table;
	private LblPlusTfPanel pBookCode;
	private LblPlusTfPanel pBookName;
	private LblPlusTfPanel pBookAuthor;
	private LblPlusTfPanel pBookMade;
	private LblPlusTfPanel pBookPrice;
	private LblPlusTfPanel pBookLendingCount;
	private LblPlusTfPanel pMemberCode;
	private LblPlusTfPanel pMemberName;
	private LblPlusTfPanel pMemberTel;
	private LblPlusTfPanel pLendingDate;
	private JPanel pReturnDate;
	private JLabel lblReturnDate;
	private JButton btnReturn;
	private JButton btnClose;
	private DatePickerPanel pDatePicker;

	public JTable getTable()
	{
		return table;
	}

	public void setTable(JTable table)
	{
		this.table = table;
	}

	public LblPlusTfPanel getpBookCode()
	{
		return pBookCode;
	}

	public void setpBookCode(LblPlusTfPanel pBookCode)
	{
		this.pBookCode = pBookCode;
	}

	public LblPlusTfPanel getpBookName()
	{
		return pBookName;
	}

	public void setpBookName(LblPlusTfPanel pBookName)
	{
		this.pBookName = pBookName;
	}

	public LblPlusTfPanel getpBookAuthor()
	{
		return pBookAuthor;
	}

	public void setpBookAuthor(LblPlusTfPanel pBookAuthor)
	{
		this.pBookAuthor = pBookAuthor;
	}

	public LblPlusTfPanel getpBookMade()
	{
		return pBookMade;
	}

	public void setpBookMade(LblPlusTfPanel pBookMade)
	{
		this.pBookMade = pBookMade;
	}

	public LblPlusTfPanel getpBookPrice()
	{
		return pBookPrice;
	}

	public void setpBookPrice(LblPlusTfPanel pBookPrice)
	{
		this.pBookPrice = pBookPrice;
	}

	public LblPlusTfPanel getpBookLendingCount()
	{
		return pBookLendingCount;
	}

	public void setpBookLendingCount(LblPlusTfPanel pBookLendingCount)
	{
		this.pBookLendingCount = pBookLendingCount;
	}

	public LblPlusTfPanel getpMemberCode()
	{
		return pMemberCode;
	}

	public void setpMemberCode(LblPlusTfPanel pMemberCode)
	{
		this.pMemberCode = pMemberCode;
	}

	public LblPlusTfPanel getpMemberTel()
	{
		return pMemberTel;
	}

	public void setpMemberTel(LblPlusTfPanel pMemberTel)
	{
		this.pMemberTel = pMemberTel;
	}

	public LblPlusTfPanel getpMemberName()
	{
		return pMemberName;
	}

	public void setpMemberName(LblPlusTfPanel pMemberName)
	{
		this.pMemberName = pMemberName;
	}

	public LblPlusTfPanel getpLendingDate()
	{
		return pLendingDate;
	}

	public void setpLendingDate(LblPlusTfPanel pLendingDate)
	{
		this.pLendingDate = pLendingDate;
	}

	public JButton getBtnClose()
	{
		return btnClose;
	}

	public void setBtnClose(JButton btnClose)
	{
		this.btnClose = btnClose;
	}

	public JPanel getpReturnDate()
	{
		return pReturnDate;
	}

	public void setpReturnDate(JPanel pReturnDate)
	{
		this.pReturnDate = pReturnDate;
	}

	public JLabel getLblReturnDate()
	{
		return lblReturnDate;
	}

	public void setLblReturnDate(JLabel lblReturnDate)
	{
		this.lblReturnDate = lblReturnDate;
	}

	public JButton getBtnReturn()
	{
		return btnReturn;
	}

	public void setBtnReturn(JButton btnReturn)
	{
		this.btnReturn = btnReturn;
	}

	public DatePickerPanel getpDatePicker()
	{
		return pDatePicker;
	}

	public void setpDatePicker(DatePickerPanel pDatePicker)
	{
		this.pDatePicker = pDatePicker;
	}

	public AbsManageReturnFrame()
	{
		setTitle("반납 관리");
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

		pMemberCode = new LblPlusTfPanel();
		pMemberCode.getLblName().setText("회 원 코 드");
		pRight.add(pMemberCode);

		pMemberName = new LblPlusTfPanel();
		pMemberName.getLblName().setText("성         명");
		pRight.add(pMemberName);

		pMemberTel = new LblPlusTfPanel();
		pMemberTel.getLblName().setText("전 화 번 호");
		pRight.add(pMemberTel);

		pLendingDate = new LblPlusTfPanel();
		pLendingDate.getLblName().setText("대   여   일");
		pRight.add(pLendingDate);

		pReturnDate = new JPanel();
		pRight.add(pReturnDate);
		pReturnDate.setLayout(new GridLayout(0, 2, 0, 0));

		lblReturnDate = new JLabel("반   납   일");
		lblReturnDate.setFont(new Font("굴림", Font.PLAIN, 13));
		lblReturnDate.setHorizontalAlignment(SwingConstants.CENTER);
		pReturnDate.add(lblReturnDate);

		pDatePicker = new DatePickerPanel();
		pReturnDate.add(pDatePicker);

		JPanel pBtn = new JPanel();
		pRight.add(pBtn);
		pBtn.setLayout(new GridLayout(0, 3, 0, 0));

		btnReturn = new JButton("반납");
		btnReturn.addActionListener(this);
		pBtn.add(btnReturn);

		btnClose = new JButton("닫기");
		btnClose.addActionListener(this);
		pBtn.add(btnClose);

		JPanel pTable = new JPanel();
		pTable.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " [\uBC18\uB0A9\uD544\uC694 \uB3C4\uC11C\uBAA9\uB85D]", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pTable);
		pTable.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		pTable.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, ReturnService.COL_NAMES));

		scrollPane.setViewportView(table);
	}

	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource() == btnReturn)
		{
			btnReturnActionPerformed(arg0);
		}
		if (arg0.getSource() == btnClose)
		{
			btnCloseActionPerformed(arg0);
		}
	}

	abstract protected void btnCloseActionPerformed(ActionEvent arg0);

	abstract protected void btnReturnActionPerformed(ActionEvent arg0);

	public void mouseClicked(MouseEvent arg0)
	{
		if (arg0.getSource() == table)
		{
			tableMouseClicked(arg0);
		}
	}

	public void mouseEntered(MouseEvent arg0)
	{
	}

	public void mouseExited(MouseEvent arg0)
	{
	}

	public void mousePressed(MouseEvent arg0)
	{
	}

	public void mouseReleased(MouseEvent arg0)
	{
	}

	abstract protected void tableMouseClicked(MouseEvent arg0);
}
