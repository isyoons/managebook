package kr.hkit.managebook.main;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.hkit.managebook.service.ReturnService;

public class ManageReturnFrame extends AbsManageReturnFrame
{
	private final static ManageReturnFrame instance = new ManageReturnFrame();

	public static ManageReturnFrame getInstance()
	{
		return instance;
	}

	private String bookCode;
	private String lendingDate;
	private String returnDate;
	private DefaultTableModel model;

	private ManageReturnFrame()
	{
		activateReturnButton(false);
		editable(false);
		getTable().getTableHeader().setReorderingAllowed(false);
		getReturnDate();
		returnDate = getpDatePicker().getDatePicker().getJFormattedTextField().getText();
	}

	public void setLendingBookList()
	{
		model = ReturnService.getInstance().getLendingBookList();
		getTable().setModel(model);
	}

	public void editable(boolean b)
	{
		getpBookCode().getTfValue().setEditable(b);
		getpBookName().getTfValue().setEditable(b);
		getpBookAuthor().getTfValue().setEditable(b);
		getpBookMade().getTfValue().setEditable(b);
		getpBookPrice().getTfValue().setEditable(b);
		getpBookLendingCount().getTfValue().setEditable(b);
		getpMemberCode().getTfValue().setEditable(b);
		getpMemberName().getTfValue().setEditable(b);
		getpMemberTel().getTfValue().setEditable(b);
		getpLendingDate().getTfValue().setEditable(b);
	}

	@Override
	protected void tableMouseClicked(MouseEvent arg0)
	{
		int row = getTable().getSelectedRow();

		bookCode = (String) getTable().getValueAt(row, 0);
		setManageReturnTextField();
		isReturnDateValidCheck();
	}

	public void setManageReturnTextField()
	{
		String[] s = ReturnService.getInstance().getReturnDataFromTableData(bookCode);

		getpBookCode().getTfValue().setText(s[0]);
		getpBookName().getTfValue().setText(s[1]);
		getpBookAuthor().getTfValue().setText(s[2]);
		getpBookMade().getTfValue().setText(s[3]);
		getpBookPrice().getTfValue().setText(s[4]);
		getpBookLendingCount().getTfValue().setText(s[5]);
		getpMemberCode().getTfValue().setText(s[6]);
		getpMemberName().getTfValue().setText(s[7]);
		getpMemberTel().getTfValue().setText(s[8]);
		getpLendingDate().getTfValue().setText(s[9]);

		lendingDate = getpLendingDate().getTfValue().getText();
	}

	public void getReturnDate()
	{
		getpDatePicker().getDatePicker().getModel().addPropertyChangeListener(new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent arg0)
			{
				returnDate = getpDatePicker().getDatePicker().getJFormattedTextField().getText();
				isReturnDateValidCheck();
			}
		});
	}

	public boolean isReturnDateValidCheck()
	{
		boolean check = true;
		int dadd = 0;
		String a = getpBookCode().getTfValue().getText();

		lendingDate = getpLendingDate().getTfValue().getText();
		returnDate = getpDatePicker().getDatePicker().getJFormattedTextField().getText();

		Date rd = null;
		Date ld = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try
		{
			rd = sdf.parse(returnDate);
			ld = sdf.parse(lendingDate);
			dadd = (int) ((rd.getTime() - ld.getTime()) / (24 * 60 * 60 * 1000));
			if (dadd < 0)
			{
				JOptionPane.showMessageDialog(null, "반납일이 대여일보다 작습니다.");
				activateReturnButton(false);
				check = false;
			} else
			{

				if (a != null)
				{
					activateReturnButton(true);
				} else
				{
					activateReturnButton(false);
				}
			}
		} catch (ParseException e)
		{
			check = false;
		}
		return check;
	}

	private void activateReturnButton(boolean b)
	{
		getBtnReturn().setEnabled(b);
	}

	public void clear()
	{
		getpBookCode().getTfValue().setText(null);
		getpBookName().getTfValue().setText(null);
		getpBookAuthor().getTfValue().setText(null);
		getpBookMade().getTfValue().setText(null);
		getpBookPrice().getTfValue().setText(null);
		getpBookLendingCount().getTfValue().setText(null);
		getpMemberCode().getTfValue().setText(null);
		getpMemberName().getTfValue().setText(null);
		getpMemberTel().getTfValue().setText(null);
		getpLendingDate().getTfValue().setText(null);
		activateReturnButton(false);
	}

	public void updateBookLendingPossible(String bookCode, boolean b)
	{
		ReturnService.getInstance().updateBookLendingPossible(bookCode, b);
	}

	public void updateReturnDate(String bookCode)
	{
		ReturnService.getInstance().updateReturnDate(returnDate, bookCode);
	}

	@Override
	protected void btnCloseActionPerformed(ActionEvent arg0)
	{
		dispose();
	}

	@Override
	protected void btnReturnActionPerformed(ActionEvent arg0)
	{

		if (isReturnDateValidCheck())
		{
			updateReturnDate(bookCode);
			updateBookLendingPossible(bookCode, true);
			setLendingBookList();
			clear();
		}

	}
}
