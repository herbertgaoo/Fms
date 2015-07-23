package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;
import dao.*;
import entities.*;

/**
 * BottomLpaneljk
 */

public class BottomLpanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Image image;

	private static int uid = 0;

	private BorrowRecordDao br = new BorrowRecordDao();

	private Font font1 = new Font("宋体", Font.BOLD, 14);

	private JPanel p_borrow = new JPanel();

	private JPanel p_loan = new JPanel();

	private JLabel l_date = new JLabel("日   期");

	private JLabel l_borrower = new JLabel("借款人");

	private JLabel l_amount = new JLabel("金额(元)");

	private JLabel l_transactor = new JLabel("经手人");

	private JLabel l_date_l = new JLabel("日   期");

	private JLabel l_borrower_l = new JLabel("借款人");

	private JLabel l_amount_l = new JLabel("金额(元)");

	private JLabel l_transactor_l = new JLabel("出借人");

	private JTextField t_amount = new JTextField(10);

	private JTextField t_transactor = new JTextField(10);

	private JTextField t_amount_l = new JTextField(10);

	private JTextField t_borrower = new JTextField(10);

	private JTextField t_borrower_l = new JTextField(10);

	private JTextField t_transactor_l = new JTextField(10);

	private DateChooser date_b = new DateChooser(p_borrow, 125);

	private DateChooser date_l = new DateChooser(p_loan, 125);

	private JButton bt_mod_l = new JButton("修改");

	private JButton bt_mod_b = new JButton("修改");

	private JButton bt_add_l = new JButton("添加");

	private JButton bt_add_b = new JButton("添加");

	private JButton bt_del_l = new JButton("删除选中行");

	private JButton bt_del_b = new JButton("删除选中行");

	private ImageIcon back_ico = new ImageIcon("res/pic/back_bt.png");

	private ImageIcon back_cov = new ImageIcon("res/pic/back_bt_c.png");

	private static ImageIcon borrow_ico = new ImageIcon("res/pic/borrow_bt.png");

	private ImageIcon loan_ico = new ImageIcon("res/pic/loan_bt.png");

	private JButton bt_back = new JButton(back_ico);

	private static JButton bt_borrow = new JButton(borrow_ico);

	private JButton bt_loan = new JButton(loan_ico);

	private JRadioButton jc_true = new JRadioButton("已还");

	private JRadioButton jc_false = new JRadioButton("未还");

	private JRadioButton jc_true_l = new JRadioButton("已还");

	private JRadioButton jc_false_l = new JRadioButton("未还");

	private JTable tb_borrow = new JTable();

	private JTable tb_loan = new JTable();

	private ButtonGroup bg1 = new ButtonGroup();

	private ButtonGroup bg2 = new ButtonGroup();

	DefaultTableModel tableMod_b = new DefaultTableModel(new String[0][0],
			new String[] { "     流水号     ", "      日    期      ",
					"  金   额(元)  ", "    借款人    ", "    经手人    ", "还款情况" });

	DefaultTableModel tableMod_l = new DefaultTableModel(new String[0][0],
			new String[] { "     流水号     ", "      日    期      ",
					"  金   额(元)  ", "    借款人    ", "    出借人    ", "还款情况" });

	DefaultTableCellRenderer tableCell = new DefaultTableCellRenderer();

	private JScrollPane js_borrow = null;

	private JScrollPane js_loan = null;

	public BottomLpanel() {
		try {
			image = ImageIO.read(new File("res/pic/frame_bg.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		this.setLayout(null);
		p_borrow.setLayout(null);
		p_loan.setLayout(null);
		p_borrow.setBounds(370, 130, 550, 350);
		p_loan.setBounds(370, 130, 550, 350);
		bt_borrow.setBounds(100, 200, 150, 35);
		bt_loan.setBounds(100, 350, 150, 35);
		p_loan.setOpaque(false);
		p_borrow.setOpaque(false);
		p_loan.setVisible(false);
		tb_borrow.setModel(tableMod_b);
		tb_loan.setModel(tableMod_l);
		js_borrow = new JScrollPane(tb_borrow);
		js_loan = new JScrollPane(tb_loan);
		tableCell.setHorizontalAlignment(JLabel.CENTER);
		tb_borrow.setDefaultRenderer(Object.class, tableCell);
		tb_loan.setDefaultRenderer(Object.class, tableCell);

		l_date.setBounds(30, 20, 70, 30);
		date_b.setBounds(90, 20, 150, 30);
		l_amount.setBounds(280, 20, 70, 30);
		t_amount.setBounds(340, 20, 180, 30);
		l_borrower.setBounds(30, 70, 70, 30);
		t_borrower.setBounds(340, 70, 180, 30);
		l_transactor.setBounds(280, 70, 70, 30);
		t_transactor.setBounds(90, 70, 150, 30);
		bt_add_b.setBounds(440, 120, 80, 30);
		bt_del_b.setBounds(200, 120, 120, 30);
		bt_mod_b.setBounds(340, 120, 80, 30);
		js_borrow.setBounds(30, 165, 490, 165);
		jc_true.setBounds(30, 120, 80, 30);
		jc_false.setBounds(120, 120, 80, 30);
		jc_true.setContentAreaFilled(false);
		jc_false.setContentAreaFilled(false);
		jc_false.setSelected(true);
		bg1.add(jc_true);
		bg1.add(jc_false);
		l_date.setFont(font1);
		l_amount.setFont(font1);
		l_borrower.setFont(font1);
		l_transactor.setFont(font1);
		t_amount.setFont(font1);
		t_transactor.setFont(font1);

		l_date_l.setBounds(30, 20, 70, 30);
		date_l.setBounds(90, 20, 150, 30);
		l_amount_l.setBounds(280, 20, 70, 30);
		t_amount_l.setBounds(340, 20, 180, 30);
		l_borrower_l.setBounds(30, 70, 70, 30);
		t_borrower_l.setBounds(90, 70, 150, 30);
		l_transactor_l.setBounds(280, 70, 70, 30);
		t_transactor_l.setBounds(340, 70, 180, 30);
		bt_add_l.setBounds(440, 120, 80, 30);
		bt_del_l.setBounds(200, 120, 120, 30);
		bt_mod_l.setBounds(340, 120, 80, 30);
		jc_true_l.setBounds(30, 120, 80, 30);
		jc_false_l.setBounds(120, 120, 80, 30);
		js_loan.setBounds(30, 165, 490, 165);
		jc_true_l.setContentAreaFilled(false);
		jc_false_l.setContentAreaFilled(false);
		jc_false_l.setSelected(true);
		bg2.add(jc_true_l);
		bg2.add(jc_false_l);
		l_date_l.setFont(font1);
		l_amount_l.setFont(font1);
		l_borrower_l.setFont(font1);
		l_transactor_l.setFont(font1);
		t_amount_l.setFont(font1);
		t_transactor_l.setFont(font1);

		p_borrow.add(l_date);
		p_borrow.add(date_b);
		p_borrow.add(l_borrower);
		p_borrow.add(t_borrower);
		p_borrow.add(l_amount);
		p_borrow.add(t_amount);
		p_borrow.add(l_transactor);
		p_borrow.add(t_transactor);
		p_borrow.add(bt_add_b);
		p_borrow.add(js_borrow);
		p_borrow.add(bt_del_b);
		p_borrow.add(jc_true);
		p_borrow.add(jc_false);
		p_borrow.add(bt_mod_b);

		p_loan.add(l_date_l);
		p_loan.add(date_l);
		p_loan.add(l_borrower_l);
		p_loan.add(t_borrower_l);
		p_loan.add(l_amount_l);
		p_loan.add(t_amount_l);
		p_loan.add(l_transactor_l);
		p_loan.add(t_transactor_l);
		p_loan.add(bt_add_l);
		p_loan.add(js_loan);
		p_loan.add(bt_del_l);
		p_loan.add(jc_true_l);
		p_loan.add(jc_false_l);
		p_loan.add(bt_mod_l);

		bt_back.setBorderPainted(false);
		bt_back.setContentAreaFilled(false);
		bt_back.setRolloverIcon(back_cov);
		bt_back.setPressedIcon(back_cov);
		bt_back.setBounds(890, 35, 36, 36);
		bt_back.setToolTipText("返回主界面");

		bt_back.addActionListener(this);
		bt_borrow.addActionListener(this);
		bt_loan.addActionListener(this);
		bt_add_l.addActionListener(this);
		bt_add_b.addActionListener(this);
		bt_del_b.addActionListener(this);
		bt_del_l.addActionListener(this);
		bt_mod_l.addActionListener(this);
		bt_mod_b.addActionListener(this);

		bt_borrow.doClick();
		this.add(p_borrow);
		this.add(p_loan);
		this.add(bt_back);
		this.add(bt_borrow);
		this.add(bt_loan);

		this.requestFocus();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_back) {
			BottomLframe.hide();
			MainFrame.display();
		} else if (e.getSource() == bt_borrow) {
			p_borrow.setVisible(true);
			p_loan.setVisible(false);
			tableMod_b.setRowCount(0);
			queRecord("borrow");
		} else if (e.getSource() == bt_loan) {
			p_loan.setVisible(true);
			p_borrow.setVisible(false);
			tableMod_l.setRowCount(0);
			queRecord("loan");
		} else if (e.getSource() == bt_add_b) {
			addRecord("borrow");
			bt_borrow.doClick();
			t_amount.setText("");
			t_borrower.setText("");
			t_transactor.setText("");
		} else if (e.getSource() == bt_add_l) {
			addRecord("loan");
			bt_loan.doClick();
			t_amount_l.setText("");
			t_borrower_l.setText("");
			t_transactor_l.setText("");
		} else if (e.getSource() == bt_del_b) {
			delRecord("borrow");
			tableMod_b.removeRow(tb_borrow.getSelectedRow());
		} else if (e.getSource() == bt_del_l) {
			delRecord("loan");
			tableMod_l.removeRow(tb_loan.getSelectedRow());
		} else if (e.getSource() == bt_mod_b) {
			if (-1 == tb_borrow.getSelectedRow()) {
				JOptionPane.showMessageDialog(this, "请先选择一行记录！", "错误", 0);
			} else {
				if (bt_mod_b.getText().equals("确认修改")){
					modRecord("borrow");
				}
				else {
					readRecord("borrow");
					bt_mod_b.setBounds(330, 120, 100, 30);
					bt_mod_b.setForeground(Color.RED);
					bt_mod_b.setText("确认修改");
				}
			}
		} else if (e.getSource() == bt_mod_l) {
			if (-1 == tb_loan.getSelectedRow()) {
				JOptionPane.showMessageDialog(this, "请先选择一行记录！", "错误", 0);
			} else {
				if (bt_mod_l.getText().equals("确认修改")){
					modRecord("loan");
				}
				else {
					readRecord("loan");
					bt_mod_l.setBounds(330, 120, 100, 30);
					bt_mod_l.setForeground(Color.RED);
					bt_mod_l.setText("确认修改");
				}
			}
		}
	}

	private void modRecord(String str) {
		BorrowingRecord dealRecord = new BorrowingRecord();
		if ((!t_amount.getText().equals("") && isNumeric(t_amount.getText()))
				|| (!t_amount_l.getText().equals("") && isNumeric(t_amount_l
						.getText()))) {

			if (str.equals("borrow")) {
				dealRecord.setDeal_id((String) tableMod_b.getValueAt(
						tb_borrow.getSelectedRow(), 0));
				dealRecord.setDeal_date(date_b.getText());
				dealRecord.setAmount(Float.parseFloat(t_amount.getText()));
				dealRecord.setBorrower(t_borrower.getText());
				dealRecord.setTransactor(t_transactor.getText());
				dealRecord.setUid(getUid());
				if (jc_true.isSelected())
					dealRecord.setTof(jc_true.getText());
				else
					dealRecord.setTof(jc_false.getText());
			} else {
				dealRecord.setDeal_id((String) tableMod_l.getValueAt(
						tb_loan.getSelectedRow(), 0));
				dealRecord.setDeal_date(date_l.getText());
				dealRecord.setAmount(Float.parseFloat(t_amount_l.getText()));
				dealRecord.setBorrower(t_borrower_l.getText());
				dealRecord.setTransactor(t_transactor_l.getText());
				dealRecord.setUid(getUid());
				if (jc_true_l.isSelected())
					dealRecord.setTof(jc_true_l.getText());
				else
					dealRecord.setTof(jc_false_l.getText());
			}
			boolean rs = br.modRecord(str, dealRecord);
			if (rs) {
				JOptionPane.showMessageDialog(this, "修改记录成功", "成功", 1);
				if (str.equals("borrow")) {
					bt_mod_b.setBounds(340, 120, 80, 30);
					bt_mod_b.setForeground(Color.BLACK);
					bt_mod_b.setText("修改");
					bt_del_b.setEnabled(true);
					bt_add_b.setEnabled(true);
					bt_borrow.doClick();
				} else {
					bt_mod_l.setBounds(340, 120, 80, 30);
					bt_mod_l.setForeground(Color.BLACK);
					bt_mod_l.setText("修改");
					bt_del_l.setEnabled(true);
					bt_add_l.setEnabled(true);
					bt_loan.doClick();
				}
				t_amount.setText("");
				t_borrower.setText("");
				t_transactor.setText("");
				t_amount_l.setText("");
				t_borrower_l.setText("");
				t_transactor_l.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "修改记录异常，请联系系统管理员", "错误", 0);
			}
		} else {
			JOptionPane.showMessageDialog(this, "请填写正确的金额！", "错误", 0);
		}
	}

	private void readRecord(String str) {
		String tof = null;
		if (str.equals("borrow")) {
			bt_del_b.setEnabled(false);
			bt_add_b.setEnabled(false);
			date_b.setText((String) tableMod_b.getValueAt(
					tb_borrow.getSelectedRow(), 1));
			t_amount.setText((String) tableMod_b.getValueAt(
					tb_borrow.getSelectedRow(), 2));
			t_borrower.setText((String) tableMod_b.getValueAt(
					tb_borrow.getSelectedRow(), 3));
			t_transactor.setText((String) tableMod_b.getValueAt(
					tb_borrow.getSelectedRow(), 4));
			tof = ((String) tableMod_b
					.getValueAt(tb_borrow.getSelectedRow(), 5)).trim();
			if (tof.equals("已还")) {
				jc_true.setSelected(true);
			} else {
				jc_false.setSelected(true);
			}
		} else {
			bt_del_l.setEnabled(false);
			bt_add_l.setEnabled(false);
			date_l.setText((String) tableMod_l.getValueAt(
					tb_loan.getSelectedRow(), 1));
			t_amount_l.setText((String) tableMod_l.getValueAt(
					tb_loan.getSelectedRow(), 2));
			t_borrower_l.setText((String) tableMod_l.getValueAt(
					tb_loan.getSelectedRow(), 3));
			t_transactor_l.setText((String) tableMod_l.getValueAt(
					tb_loan.getSelectedRow(), 4));
			tof = ((String) tableMod_l.getValueAt(tb_loan.getSelectedRow(), 5))
					.trim();
			if (tof.equals("已还")) {
				jc_true_l.setSelected(true);
			} else {
				jc_false_l.setSelected(true);
			}
		}
	}

	private void delRecord(String str) {
		String deal_id = null;
		if (str.equals("borrow")) {
			deal_id = (String) tableMod_b.getValueAt(
					tb_borrow.getSelectedRow(), 0);
		} else {
			deal_id = (String) tableMod_l.getValueAt(tb_loan.getSelectedRow(),
					0);
		}
		boolean rs = br.delRecord(str, deal_id, getUid());
		if (rs) {
			JOptionPane.showMessageDialog(this, "删除记录成功", "成功", 1);
		} else {
			JOptionPane.showMessageDialog(this, "删除记录异常，请联系系统管理员", "错误", 0);
		}
	}

	private void queRecord(String string) {
		DefaultTableModel tmp = null;
		ArrayList records = null;
		if (string.equals("borrow")) {
			records = br.queRecord(string, getUid());
			tmp = tableMod_b;
		} else {
			records = br.queRecord(string, getUid());
			tmp = tableMod_l;
		}

		Iterator it = records.iterator(); // 迭代器 遍历Arraylist
		while (it.hasNext()) {
			BorrowingRecord record = (BorrowingRecord) it.next();
			tmp.addRow(new String[] { record.getDeal_id(),
					record.getDeal_date(), record.getAmount() + "",
					record.getBorrower(), record.getTransactor(),
					record.getTof() });
		}
	}

	private void addRecord(String str) {
		BorrowingRecord dealRecord = new BorrowingRecord();
		if ((!t_amount.getText().equals("") && isNumeric(t_amount.getText()))
				|| (!t_amount_l.getText().equals("") && isNumeric(t_amount_l
						.getText()))) {
			if (str.equals("borrow")) {
				dealRecord.setDeal_date(date_b.getText());
				dealRecord.setAmount(Float.parseFloat(t_amount.getText()));
				dealRecord.setBorrower(t_borrower.getText());
				dealRecord.setTransactor(t_transactor.getText());
				if (jc_true.isSelected())
					dealRecord.setTof(jc_true.getText());
				else
					dealRecord.setTof(jc_false.getText());
			} else {
				dealRecord.setDeal_date(date_l.getText());
				dealRecord.setAmount(Float.parseFloat(t_amount_l.getText()));
				dealRecord.setBorrower(t_borrower_l.getText());
				dealRecord.setTransactor(t_transactor_l.getText());
				if (jc_true_l.isSelected())
					dealRecord.setTof(jc_true_l.getText());
				else
					dealRecord.setTof(jc_false_l.getText());
			}
			dealRecord.setUid(getUid());
			boolean rs = br.addRecord(dealRecord, str);
			if (rs) {
				JOptionPane.showMessageDialog(this, "添加记录成功", "成功", 1);
			} else {
				JOptionPane.showMessageDialog(this, "添加记录异常，请联系系统管理员", "错误", 0);
			}
		} else {
			JOptionPane.showMessageDialog(this, "请填写正确的金额！", "错误", 0);
		}
	}

	public static void setUserInfo(int uId) {
		setUid(uId);
		bt_borrow.doClick();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	public static int getUid() {
		return uid;
	}

	public static void setUid(int uid) {
		BottomLpanel.uid = uid;
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

	public static boolean isNumeric(String str) {
		boolean strResult = str.matches("-?[0-9]+.*[0-9]*");
		if (strResult == true) {
			return true;
		} else {
			return false;
		}
	}
}