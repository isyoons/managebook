package kr.hkit.managebook.menu;

import java.awt.event.ActionEvent;

import kr.hkit.managebook.comp.AbsMenuFrame;
import kr.hkit.managebook.search.SearchBookFrame;
import kr.hkit.managebook.search.SearchMemberFrame;

public class SearchMenuFrame extends AbsMenuFrame {

	private static final SearchMenuFrame instance = new SearchMenuFrame();

	public static SearchMenuFrame getInstance() {
		return instance;
	}

	private SearchMenuFrame() {
		setTitle("도서 출납 통계");
		getLblTitle().setText("도서 출납 통계");
		getBtnLeft().setText("회원 검색");
		getBtnMiddle().setText("도서 검색");
		getBtnRight().setText("뒤로 가기");
	}

	@Override
	protected void btnLeftActionPerformed(ActionEvent arg0) {
		SearchMemberFrame.getInstance().setVisible(true);
	}

	@Override
	protected void btnMiddleActionPerformed(ActionEvent arg0) {
		SearchBookFrame.getInstance().setVisible(true);
		SearchBookFrame.getInstance().refresh();
	}

	@Override
	protected void btnRightActionPerformed(ActionEvent arg0) {
		MainMenuFrame.getInstance().setVisible(true);

		setVisible(false);

	}

}
