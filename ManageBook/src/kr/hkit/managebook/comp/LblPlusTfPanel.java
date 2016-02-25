package kr.hkit.managebook.comp;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LblPlusTfPanel extends JPanel {
	private JTextField tfValue;
	private JLabel lblName;

	public LblPlusTfPanel() {
		setLayout(new GridLayout(1, 0, 0, 0));

		lblName = new JLabel("");
		lblName.setFont(new Font("굴림", Font.PLAIN, 13));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblName);

		tfValue = new JTextField();
		add(tfValue);
		tfValue.setColumns(10);

	}

	public JTextField getTfValue() {
		return tfValue;
	}

	public void setTfValue(JTextField tfValue) {
		this.tfValue = tfValue;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}

}
