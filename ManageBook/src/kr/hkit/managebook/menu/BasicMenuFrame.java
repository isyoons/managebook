package kr.hkit.managebook.menu;

import java.awt.event.ActionEvent;

import kr.hkit.managebook.basic.ManageBookFrame;
import kr.hkit.managebook.basic.ManageMemberFrame;
import kr.hkit.managebook.comp.AbsMenuFrame;

public class BasicMenuFrame extends AbsMenuFrame {

	private static final BasicMenuFrame instance = new BasicMenuFrame();

	public static BasicMenuFrame getInstance() {
		return instance;
	}

	private BasicMenuFrame() {
		setTitle("기초 자료 관리");
		getLblTitle().setText("기초 자료 관리");
		getBtnLeft().setText("도서 관리");
		getBtnMiddle().setText("회원 관리");
		getBtnRight().setText("뒤로 가기");
	}

	@Override
	protected void btnLeftActionPerformed(ActionEvent arg0) {
		ManageBookFrame.getInstance().setVisible(true);
		ManageBookFrame.getInstance().initialSetting();
	}

	@Override
	protected void btnMiddleActionPerformed(ActionEvent arg0) {
		ManageMemberFrame.getInstance().setVisible(true);
		ManageMemberFrame.getInstance().initSetting();

	}

	@Override
	protected void btnRightActionPerformed(ActionEvent arg0) {
		MainMenuFrame.getInstance().setVisible(true);
		setVisible(false);
	}

}
