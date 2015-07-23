package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import dao.DealRecordDao;
import entities.DailyDeal;
import entities.DateChooser;

/**
 * LeftPaneljk
 */

public class RightPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5011143138927170585L;

	private Image image;

	private static int uid = 0;

	private Font font1 = new Font("宋体", Font.BOLD, 14);

	Date now = new Date();

	SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");

	private DealRecordDao dr = new DealRecordDao();

	private JPanel spending = new JPanel();

	private JPanel report = new JPanel();

	private ImageIcon back_ico = new ImageIcon("res/pic/back_bt.png");

	private ImageIcon back_cov = new ImageIcon("res/pic/back_bt_c.png");

	private static ImageIcon spending_ico = new ImageIcon(
			"res/pic/spending_bt.png");

	private ImageIcon report_ico = new ImageIcon("res/pic/report_bt.png");

	private JButton bt_report = new JButton(report_ico);

	private static JButton bt_spending = new JButton(spending_ico);

	private JButton bt_back = new JButton(back_ico);

	private JButton bt_week = new JButton("七天内");

	private JButton bt_month = new JButton("一个月内");

	private JButton bt_query = new JButton("查询");

	private JLabel l_startTime = new JLabel("开始日期");

	private JLabel l_endTime = new JLabel("结束日期");

	private JLabel l_balanceOfBorrow = new JLabel("借贷情况");

	private JLabel l_balanceOfIncome = new JLabel("收支情况");

	private JTextArea l_display1 = new JTextArea();

	private JTextArea l_display2 = new JTextArea();

	private DateChooser date_start = new DateChooser(spending, 125);

	private DateChooser date_end = new DateChooser(spending, 125);

	private JTable tb_spending = new JTable();

	DefaultTableModel tableMod_i = new DefaultTableModel(new String[0][0],
			new String[] { "       流水号       ", "       日    期       ",
					"    方    式    ", "   金   额(元)   ", "备注" });

	DefaultTableCellRenderer tableCell = new DefaultTableCellRenderer();

	private JScrollPane js_spending = null;

	public RightPanel() {
		try {
			image = ImageIO.read(new File("res/pic/frame_bg.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		this.setLayout(null);
		spending.setLayout(null);
		report.setLayout(null);
		spending.setOpaque(false);
		report.setOpaque(false);
		tb_spending.setModel(tableMod_i);
		js_spending = new JScrollPane(tb_spending);
		tableCell.setHorizontalAlignment(JLabel.CENTER);
		tb_spending.setDefaultRenderer(Object.class, tableCell);
		FitTableColumns(tb_spending);
		spending.setBounds(370, 130, 550, 350);
		report.setBounds(370, 130, 550, 350);
		bt_spending.setBounds(100, 200, 150, 35);
		bt_report.setBounds(100, 350, 150, 35);

		l_startTime.setBounds(30, 30, 60, 30);
		date_start.setBounds(95, 30, 150, 30);
		l_endTime.setBounds(300, 30, 60, 30);
		date_end.setBounds(365, 30, 150, 30);
		js_spending.setBounds(30, 120, 490, 200);
		bt_week.setBounds(30, 75, 120, 30);
		bt_month.setBounds(210, 75, 120, 30);
		bt_query.setBounds(380, 75, 120, 30);
		bt_week.setForeground(Color.RED);
		bt_month.setForeground(Color.RED);

		spending.add(l_startTime);
		spending.add(l_endTime);
		spending.add(date_start);
		spending.add(date_end);
		spending.add(js_spending);
		spending.add(bt_week);
		spending.add(bt_month);
		spending.add(bt_query);

		l_balanceOfBorrow.setBounds(30, 30, 80, 30);
		l_balanceOfIncome.setBounds(30, 170, 80, 30);
		l_display1.setBounds(30, 70, 500, 100);
		l_display2.setBounds(30, 210, 500, 100);
		l_balanceOfBorrow.setFont(font1);
		l_balanceOfIncome.setFont(font1);
		l_display1.setFont(font1);
		l_display2.setFont(font1);
		l_display1.setForeground(Color.RED);
		l_display2.setForeground(Color.RED);
		l_display1.setOpaque(false);
		l_display2.setOpaque(false);
		l_display1.setEditable(false);
		l_display2.setEditable(false);

		report.add(l_balanceOfIncome);
		report.add(l_balanceOfBorrow);
		report.add(l_display1);
		report.add(l_display2);

		bt_back.setBorderPainted(false);
		bt_back.setContentAreaFilled(false);
		bt_back.setRolloverIcon(back_cov);
		bt_back.setPressedIcon(back_cov);
		bt_back.setBounds(890, 35, 36, 36);
		bt_back.setToolTipText("返回主界面");

		bt_back.addActionListener(this);
		bt_spending.addActionListener(this);
		bt_report.addActionListener(this);
		bt_week.addActionListener(this);
		bt_month.addActionListener(this);
		bt_query.addActionListener(this);

		bt_spending.doClick();
		this.add(bt_back);
		this.add(bt_spending);
		this.add(bt_report);
		this.add(spending);
		this.add(report);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_back) {
			RightFrame.hide();
			MainFrame.display();
		} else if (e.getSource() == bt_spending) {
			tableMod_i.setRowCount(0);
			spending.setVisible(true);
			report.setVisible(false);
		} else if (e.getSource() == bt_report) {
			spending.setVisible(false);
			report.setVisible(true);
			counter();
		} else if (e.getSource() == bt_week) {
			tableMod_i.setRowCount(0);
			queOfTime(lastWeek(), matter1.format(now));
		} else if (e.getSource() == bt_month) {
			tableMod_i.setRowCount(0);
			queOfTime(lastMonth(1), matter1.format(now));
		} else if (e.getSource() == bt_query) {
			tableMod_i.setRowCount(0);
			queOfTime(date_start.getText(), date_end.getText());
		}
	}

	private void counter() {
		float[] data = dr.counter(lastMonth(1), matter1.format(now), getUid());
		String info1 = "最近一个月内借入" + data[2] + "元，借出" + data[3]
				+ "元。\n\n系统温馨提示：\n";
		String info2 = "最近一个月内收入" + data[0] + "元，支出" + data[1]
				+ "元。\n\n系统温馨提示：\n";
		if (data[2] > data[3]) {
			info1 = info1 + "近一个月内借入较多，请记得及时还款。";
		} else {
			info1 = info1 + "近一个月内借出较多，请注意及时收回借款。";
		}
		if (data[0] > data[1]) {
			info2 = info2 + "近一个月内收支情况良好，请继续保持！";
		} else {
			info2 = info2 + "近一个月内透支" + (data[1] - data[0]) + "元，请适当控制消费。";
		}
		l_display1.setText(info1);
		l_display2.setText(info2);
	}

	private void queOfTime(String startDate, String endDate) {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date start = sim.parse(startDate);
			Date end = sim.parse(endDate);
			if (start.before(end) || start.equals(end)) {
				ArrayList records = null;
				records = dr.queOfDate(startDate, endDate, getUid());
				if(records.size()!=0) {
					Iterator it = records.iterator(); // 迭代器 遍历Arraylist
					while (it.hasNext()) {
						DailyDeal record = (DailyDeal) it.next();
						tableMod_i.addRow(new String[] { record.getDeal_id(),
								record.getDeal_date(), record.getMethods(),
								record.getAmount() + "", record.getComment() });
					}
				} else {
					JOptionPane.showMessageDialog(this, "未能找到任何记录！", "提示", 1);
				}
				
			} else {
				JOptionPane.showMessageDialog(this, "开始日期不能大于结束日期！", "错误", 0);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setUserInfo(int uId) {
		setUid(uId);
		bt_spending.doClick();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	public static int getUid() {
		return uid;
	}

	public static void setUid(int uid) {
		RightPanel.uid = uid;
	}

	public void FitTableColumns(JTable myTable) { // JTable 自适应列宽
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(
					column.getIdentifier());
			int width = (int) myTable
					.getTableHeader()
					.getDefaultRenderer()
					.getTableCellRendererComponent(myTable,
							column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable
						.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable,
								myTable.getValueAt(row, col), false, false,
								row, col).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column);
			column.setWidth(width + myTable.getIntercellSpacing().width);
		}
	}

	public String lastWeek() {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date)) - 6;

		if (day < 1) {
			month -= 1;
			if (month == 0) {
				year -= 1;
				month = 12;
			}
			if (month == 4 || month == 6 || month == 9 || month == 11) {
				day = 30 + day;
			} else if (month == 1 || month == 3 || month == 5 || month == 7
					|| month == 8 || month == 10 || month == 12) {
				day = 31 + day;
			} else if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
					day = 29 + day;
				else
					day = 28 + day;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10)
			m = "0" + month;
		else
			m = month + "";
		if (day < 10)
			d = "0" + day;
		else
			d = day + "";

		return y + "-" + m + "-" + d;
	}

	public String lastMonth(int allMonth) {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date))
				- allMonth;
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
		if (month <= 0) {
			int yearFlag = (month * (-1)) / 12 + 1;
			int monthFlag = (month * (-1)) % 12;
			year -= yearFlag;
			month = monthFlag * (-1) + 12;
		} else if (day > 28) {
			if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
					day = 29;
				} else
					day = 28;
			} else if ((month == 4 || month == 6 || month == 9 || month == 11)
					&& day == 31) {
				day = 30;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10)
			m = "0" + month;
		else
			m = month + "";
		if (day < 10)
			d = "0" + day;
		else
			d = day + "";

		return y + "-" + m + "-" + d;
	}
}