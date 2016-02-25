package kr.hkit.managebook.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import kr.hkit.managebook.comp.AbsMenuFrame;
import kr.hkit.managebook.main.ManageRentalFrame;
import kr.hkit.managebook.main.ManageReturnFrame;

public class ManageMenuFrame extends AbsMenuFrame implements ActionListener
{
	private static final ManageMenuFrame instance = new ManageMenuFrame();

	public static ManageMenuFrame getInstance()
	{
		return instance;
	}

	private ManageMenuFrame()
	{
		getBtnMiddle().addActionListener(this);
		setTitle("도서 출납 관리");
		getLblTitle().setText("도서 출납 관리");
		getBtnLeft().setText("대여 관리");
		getBtnMiddle().setText("반납 관리");
		getBtnRight().setText("뒤로 가기");
	}

	@Override
	protected void btnLeftActionPerformed(ActionEvent arg0)
	{
		ManageRentalFrame.getInstance().setVisible(true);
		ManageRentalFrame.getInstance().initSetting();
	}

	@Override
	protected void btnMiddleActionPerformed(ActionEvent arg0)
	{
		ManageReturnFrame.getInstance().setVisible(true);
		ManageReturnFrame.getInstance().setLendingBookList();
		Calendar cal = Calendar.getInstance();
		ManageReturnFrame.getInstance().getpDatePicker().getDatePicker().getModel().setDate(cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		ManageReturnFrame.getInstance().clear();
	}

	@Override
	protected void btnRightActionPerformed(ActionEvent arg0)
	{
		MainMenuFrame.getInstance().setVisible(true);
		setVisible(false);
	}
}
