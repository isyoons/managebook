package kr.hkit.managebook.datepicker;

import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class DatePickerPanel extends JPanel
{
	private JDatePickerImpl datePicker;

	public DatePickerPanel()
	{
		setLayout(new GridLayout(1, 4, 5, 0));

		Properties prop = new Properties();
		prop.setProperty("text.today", "\uc624\ub298");
		prop.setProperty("text.month", "\uc6d4");
		prop.setProperty("text.year", "\ub144");

		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);
		JDatePanelImpl datePanel = new JDatePanelImpl(model, prop);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		add(datePicker);
	}

	public JDatePickerImpl getDatePicker()
	{
		return datePicker;
	}
}
