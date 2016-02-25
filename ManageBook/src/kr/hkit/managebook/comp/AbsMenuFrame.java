package kr.hkit.managebook.comp;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public abstract class AbsMenuFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnLeft;
	private JButton btnMiddle;
	private JButton btnRight;
	private JLabel lblTitle;

	public AbsMenuFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		lblTitle = new JLabel("");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 3, 10, 20));

		btnLeft = new JButton("");
		btnLeft.addActionListener(this);
		panel.add(btnLeft);

		btnMiddle = new JButton("");
		btnMiddle.addActionListener(this);
		panel.add(btnMiddle);

		btnRight = new JButton("");
		btnRight.addActionListener(this);
		panel.add(btnRight);
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public JButton getBtnLeft() {
		return btnLeft;
	}

	public JButton getBtnMiddle() {
		return btnMiddle;
	}

	public JButton getBtnRight() {
		return btnRight;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnRight) {
			btnRightActionPerformed(arg0);
		}
		if (arg0.getSource() == btnMiddle) {
			btnMiddleActionPerformed(arg0);
		}
		if (arg0.getSource() == btnLeft) {
			btnLeftActionPerformed(arg0);
		}
	}

	protected abstract void btnLeftActionPerformed(ActionEvent arg0);

	protected abstract void btnMiddleActionPerformed(ActionEvent arg0);

	protected abstract void btnRightActionPerformed(ActionEvent arg0);
}
