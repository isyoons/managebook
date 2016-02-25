package kr.hkit.managebook.menu;

import java.awt.event.ActionEvent;

import kr.hkit.managebook.comp.AbsMenuFrame;
//알림추가
public class MainMenuFrame extends AbsMenuFrame
{
	private static final MainMenuFrame instance = new MainMenuFrame();

	public static MainMenuFrame getInstance()
	{
		return instance;
	}

	public static void main(String[] args)
	{
		MainMenuFrame.getInstance().setVisible(true);
	}

	private MainMenuFrame()
	{
		setTitle("도서 관리 프로그램");
		getLblTitle().setText("도서 관리 프로그램");
		getBtnLeft().setText("기초 자료 관리");
		getBtnMiddle().setText("도서 출납 관리");
		getBtnRight().setText("도서 출납 통계");
	}

	@Override
	protected void btnLeftActionPerformed(ActionEvent arg0)
	{
		BasicMenuFrame.getInstance().setVisible(true);
		setVisible(false);
	}

	@Override
	protected void btnMiddleActionPerformed(ActionEvent arg0)
	{
		ManageMenuFrame.getInstance().setVisible(true);
		setVisible(false);
	}

	@Override
	protected void btnRightActionPerformed(ActionEvent arg0)
	{
		SearchMenuFrame.getInstance().setVisible(true);
		setVisible(false);

	}

}
