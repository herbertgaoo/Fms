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
import dao.BankAccountDao;
import entities.*;

/**
 * LeftPaneljk
 */

public class TopPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5011143138927170585L;

	private Image image;

	private static int uid = 0;

	private BankAccountDao bad = new BankAccountDao();

	private BankAccount ba = new BankAccount();

	private JPanel cards = new JPanel();

	private JPanel records = new JPanel();

	private JLabel l_account = new JLabel("账    号");

	private JLabel l_type = new JLabel("类   型");

	private JLabel l_bank = new JLabel("开户行");

	private JLabel l_site = new JLabel("开户网点");

	private JLabel l_date = new JLabel("开户日期");

	private JLabel l_amount = new JLabel("开户金额");

	private JLabel l_dealDate = new JLabel("交易日期");

	private JLabel l_dealAmount = new JLabel("金额");

	private JLabel l_dealType = new JLabel("交易类型");

	private JLabel l_sel_account = new JLabel("银行账号");

	private String[] type = { "活期", "定期" };

	private String[] dealType = { "存款", "取款" };

	private JComboBox cb_dealType = new JComboBox(dealType);

	private JComboBox cb_type = new JComboBox(type);

	private JComboBox cb_account = new JComboBox();

	private DateChooser openDate = new DateChooser(cards, 125);

	private DateChooser dealDate = new DateChooser(cards, 125);

	private JTextField t_dealAmount = new JTextField();

	private JTextField t_account = new JTextField();

	private JTextField t_bank = new JTextField();

	private JTextField t_site = new JTextField();

	private JTextField t_amount = new JTextField();

	private ImageIcon back_ico = new ImageIcon("res/pic/back_bt.png");

	private ImageIcon back_cov = new ImageIcon("res/pic/back_bt_c.png");

	private ImageIcon account_ico = new ImageIcon("res/pic/account_bt.png");

	private ImageIcon records_ico = new ImageIcon("res/pic/records_bt.png");

	private JButton bt_records = new JButton(records_ico);

	private JButton bt_account = new JButton(account_ico);

	private JButton bt_back = new JButton(back_ico);

	private JButton bt_add_account = new JButton("添加");

	private JButton bt_del_account = new JButton("删除选中");

	private JButton bt_mod_account = new JButton("修改选中");

	private JButton bt_mod_confirm = new JButton("确认修改");

	private JButton bt_add_records = new JButton("添加记录");

	private JButton bt_del_records = new JButton("删除选中");

	private JButton bt_que_records = new JButton("查询记录");

	private JTable tb_accounts = new JTable();

	DefaultTableModel tableMod_a = new DefaultTableModel(new String[0][0],
			new String[] { "     日    期     ",
					"                 帐    号                 ", " 类别 ",
					"  开户行名称  ", "       开户网点       ", "开户金额(元)" });

	DefaultTableCellRenderer tableCell = new DefaultTableCellRenderer();

	private JScrollPane js_account = null;

	private JTable tb_dealRecords = new JTable();

	DefaultTableModel tableMod_d = new DefaultTableModel(new String[0][0],
			new String[] { "      编号      ", "     日    期     ", "  交易类型   ",
					"  交易金额  ", });

	private JScrollPane js_dealRecords = null;

	public TopPanel() {
		try {
			image = ImageIO.read(new File("res/pic/frame_bg.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		this.setLayout(null);
		cards.setLayout(null);
		records.setLayout(null);
		cards.setOpaque(false);
		cards.setBounds(370, 130, 550, 350);
		records.setBounds(370, 130, 550, 350);
		records.setOpaque(false);
		bt_account.setBounds(100, 200, 150, 35);
		bt_records.setBounds(100, 350, 150, 35);

		tb_accounts.setModel(tableMod_a);
		tb_dealRecords.setModel(tableMod_d);
		js_account = new JScrollPane(tb_accounts);
		js_dealRecords = new JScrollPane(tb_dealRecords);
		tableCell.setHorizontalAlignment(JLabel.CENTER);
		tb_accounts.setDefaultRenderer(Object.class, tableCell);
		tb_dealRecords.setDefaultRenderer(Object.class, tableCell);
		FitTableColumns(tb_accounts);

		l_account.setBounds(25, 20, 60, 30);
		t_account.setBounds(70, 20, 140, 30);
		l_bank.setBounds(225, 20, 60, 30);
		t_bank.setBounds(265, 20, 90, 30);
		l_site.setBounds(375, 20, 60, 30);
		t_site.setBounds(430, 20, 100, 30);
		l_type.setBounds(25, 70, 60, 30);
		cb_type.setBounds(70, 70, 80, 30);
		l_amount.setBounds(170, 70, 60, 30);
		t_amount.setBounds(225, 70, 80, 30);
		l_date.setBounds(325, 70, 60, 30);
		openDate.setBounds(380, 70, 150, 30);
		js_account.setBounds(30, 165, 490, 165);
		bt_add_account.setBounds(50, 120, 100, 30);
		bt_del_account.setBounds(175, 120, 100, 30);
		bt_mod_account.setBounds(300, 120, 100, 30);
		bt_mod_confirm.setBounds(425, 120, 100, 30);

		l_sel_account.setBounds(30, 30, 80, 30);
		cb_account.setBounds(90, 30, 180, 30);
		l_dealDate.setBounds(30, 70, 80, 30);
		dealDate.setBounds(90, 70, 150, 30);
		l_dealAmount.setBounds(260, 70, 80, 30);
		t_dealAmount.setBounds(290, 70, 100, 30);
		l_dealType.setBounds(410, 70, 80, 30);
		cb_dealType.setBounds(465, 70, 60, 30);
		bt_add_records.setBounds(100, 120, 100, 30);
		bt_del_records.setBounds(220, 120, 100, 30);
		bt_que_records.setBounds(340, 120, 100, 30);
		js_dealRecords.setBounds(30, 165, 490, 165);

		cards.add(l_account);
		cards.add(t_account);
		cards.add(l_type);
		cards.add(cb_type);
		cards.add(l_amount);
		cards.add(t_amount);
		cards.add(l_site);
		cards.add(t_site);
		cards.add(l_bank);
		cards.add(t_bank);
		cards.add(l_date);
		cards.add(openDate);
		cards.add(l_site);
		cards.add(bt_add_account);
		cards.add(bt_del_account);
		cards.add(bt_mod_account);
		cards.add(bt_mod_confirm);
		cards.add(js_account);

		records.add(l_sel_account);
		records.add(cb_account);
		records.add(l_dealDate);
		records.add(dealDate);
		records.add(l_dealAmount);
		records.add(t_dealAmount);
		records.add(l_dealType);
		records.add(cb_dealType);
		records.add(bt_add_records);
		records.add(bt_del_records);
		records.add(bt_que_records);
		records.add(js_dealRecords);

		bt_back.setBorderPainted(false);
		bt_back.setContentAreaFilled(false);
		bt_back.setRolloverIcon(back_cov);
		bt_back.setPressedIcon(back_cov);
		bt_back.setBounds(890, 35, 36, 36);
		bt_back.setToolTipText("返回主界面");

		bt_mod_confirm.setEnabled(false);
		bt_mod_confirm.setForeground(Color.RED);
		bt_back.addActionListener(this);
		bt_account.addActionListener(this);
		bt_records.addActionListener(this);
		bt_add_account.addActionListener(this);
		bt_del_account.addActionListener(this);
		bt_mod_account.addActionListener(this);
		bt_mod_confirm.addActionListener(this);
		bt_add_records.addActionListener(this);
		bt_del_records.addActionListener(this);
		bt_que_records.addActionListener(this);

		bt_account.doClick();
		this.add(bt_back);
		this.add(bt_account);
		this.add(bt_records);
		this.add(cards);
		this.add(records);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_back) {
			TopFrame.hide();
			MainFrame.display();
		} else if (e.getSource() == bt_account) {
			cards.setVisible(true);
			records.setVisible(false);
			tableMod_a.setRowCount(0);
			queAccount();

		} else if (e.getSource() == bt_records) {
			records.setVisible(true);
			cards.setVisible(false);
			initAccount();
		} else if (e.getSource() == bt_add_account) {
			addAccount();
			bt_account.doClick();
		} else if (e.getSource() == bt_del_account) {
			if (-1 == tb_accounts.getSelectedRow()) {
				JOptionPane.showMessageDialog(this, "请先选择一行记录！", "错误", 0);
			} else {
				delAccount();
				tableMod_a.removeRow(tb_accounts.getSelectedRow());
				bt_account.doClick();
			}

		} else if (e.getSource() == bt_mod_account) {
			if (-1 == tb_accounts.getSelectedRow()) {
				JOptionPane.showMessageDialog(this, "请先选择一行记录！", "错误", 0);
			} else {
				loadAccount();
				bt_mod_confirm.setEnabled(true);
				bt_mod_account.setEnabled(false);
				bt_add_account.setEnabled(false);
				bt_del_account.setEnabled(false);
				t_account.setEditable(false);
			}
		} else if (e.getSource() == bt_mod_confirm) {
			modAccount();
			bt_mod_confirm.setEnabled(false);
			bt_mod_account.setEnabled(true);
			bt_add_account.setEnabled(true);
			bt_del_account.setEnabled(true);
			bt_account.doClick();
		} else if (e.getSource() == bt_add_records) {
			addRecords();
			queRecords();
		} else if (e.getSource() == bt_del_records) {
			if (-1 == tb_dealRecords.getSelectedRow()) {
				JOptionPane.showMessageDialog(this, "请先选择一行记录！", "错误", 0);
			} else {
				delRecords();
				tableMod_d.removeRow(tb_dealRecords.getSelectedRow());
				queRecords();
			}
		} else if (e.getSource() == bt_que_records) {
			queRecords();
		}
	}

	private void delRecords() {
		String id = (String) tableMod_d.getValueAt(
				tb_dealRecords.getSelectedRow(), 0);
		boolean rs = bad.delRecords(id);
		if (rs) {
			JOptionPane.showMessageDialog(this, "删除记录成功", "成功", 1);
		} else {
			JOptionPane.showMessageDialog(this, "删除记录异常，请联系系统管理员", "错误", 0);
		}
	}

	private void addRecords() {
		DealRecords record = new DealRecords();
		if (isNumeric(t_dealAmount.getText())) {
			record.setAccount((String) cb_account.getSelectedItem());
			record.setAmount(Float.parseFloat(t_dealAmount.getText()));
			record.setDate(dealDate.getText());
			record.setUid(getUid());
			if (cb_dealType.getSelectedItem().equals("存款")) {
				record.setType((String) cb_dealType.getSelectedItem());
			} else {
				record.setType((String) cb_dealType.getSelectedItem());
			}
			boolean rs = bad.addRecords(record);
			if (rs) {
				JOptionPane.showMessageDialog(this, "添加存取款记录成功", "成功", 1);
			} else {
				JOptionPane.showMessageDialog(this, "添加存取款记录异常，请联系系统管理员", "错误",
						0);
			}
		} else {
			JOptionPane.showMessageDialog(this, "请填写正确的金额！", "错误", 0);
		}
		t_dealAmount.setText("");
	}

	private void queRecords() {
		tableMod_d.setRowCount(0);
		ArrayList records = null;
		if (isNumeric((String) cb_account.getSelectedItem())) {
			records = bad.queRecords((String) cb_account.getSelectedItem(),
					getUid());
			Iterator it = records.iterator(); // 迭代器 遍历Arraylist
			while (it.hasNext()) {
				DealRecords record = (DealRecords) it.next();
				tableMod_d.addRow(new String[] { record.getRecordID(),
						record.getType(), record.getDate(),
						record.getAmount() + "" });
			}
		} else {
			JOptionPane.showMessageDialog(this, "请先选择一个银行账户！", "错误", 0);
		}
	}

	private void initAccount() {
		cb_account.removeAllItems();
		ArrayList accounts = bad.initAccount(getUid());
		Iterator it = accounts.iterator();
		cb_account.addItem("请先选择一个银行帐号");
		while (it.hasNext()) {
			cb_account.addItem(it.next());
		}
	}

	private void queAccount() {
		ArrayList accounts = null;
		accounts = bad.queAccount(getUid());
		Iterator it = accounts.iterator(); // 迭代器 遍历Arraylist
		while (it.hasNext()) {
			BankAccount record = (BankAccount) it.next();
			tableMod_a.addRow(new String[] { record.getDate(),
					record.getAccount(), record.getType(), record.getBank(),
					record.getSite(), record.getAmount() + "" });
		}
	}

	private void loadAccount() {
		t_account.setText((String) tableMod_a.getValueAt(
				tb_accounts.getSelectedRow(), 1));
		t_bank.setText((String) tableMod_a.getValueAt(
				tb_accounts.getSelectedRow(), 3));
		t_site.setText((String) tableMod_a.getValueAt(
				tb_accounts.getSelectedRow(), 4));
		t_amount.setText((String) tableMod_a.getValueAt(
				tb_accounts.getSelectedRow(), 5));
		openDate.setText((String) tableMod_a.getValueAt(
				tb_accounts.getSelectedRow(), 0));
		cb_type.setSelectedItem(((String) tableMod_a.getValueAt(
				tb_accounts.getSelectedRow(), 2)).trim());
	}

	private void modAccount() {
		if (isNumeric(t_account.getText()) && isNumeric(t_amount.getText())) {
			ba.setAccount(t_account.getText());
			ba.setAmount(Float.parseFloat(t_amount.getText()));
			ba.setBank(t_bank.getText());
			ba.setDate(openDate.getText());
			ba.setSite(t_site.getText());
			ba.setUid(getUid());
			if (cb_type.getSelectedItem().equals("活期")) {
				ba.setType((String) cb_type.getSelectedItem());
			} else {
				ba.setType((String) cb_type.getSelectedItem());
			}
			boolean rs = bad.modAccount(ba);
			if (rs) {
				JOptionPane.showMessageDialog(this, "修改账号成功", "成功", 1);
			} else {
				JOptionPane.showMessageDialog(this, "修改账号异常，请联系系统管理员", "错误", 0);
			}
		} else {
			JOptionPane.showMessageDialog(this, "请填写正确的金额或卡号！", "错误", 0);
		}
		t_bank.setText("");
		t_site.setText("");
		t_amount.setText("");
		t_account.setText("");
		t_account.setEditable(true);
	}

	private void delAccount() {
		String account = (String) tableMod_a.getValueAt(
				tb_accounts.getSelectedRow(), 1);
		boolean rs = bad.delAccount(account, getUid());
		if (rs) {
			JOptionPane.showMessageDialog(this, "删除账号成功", "成功", 1);
		} else {
			JOptionPane.showMessageDialog(this, "删除账号异常\n请先删除账号下所有存取款记录或联系系统管理员", "错误", 0);
		}
	}

	private void addAccount() {
		if (isNumeric(t_account.getText()) && isNumeric(t_amount.getText())) {
			ba.setAccount(t_account.getText());
			ba.setAmount(Float.parseFloat(t_amount.getText()));
			ba.setBank(t_bank.getText());
			ba.setDate(openDate.getText());
			ba.setSite(t_site.getText());
			ba.setUid(getUid());
			if (cb_type.getSelectedItem().equals("活期")) {
				ba.setType((String) cb_type.getSelectedItem());
			} else {
				ba.setType((String) cb_type.getSelectedItem());
			}
			boolean rs = bad.addAccount(ba);
			if (rs) {
				JOptionPane.showMessageDialog(this, "添加账户成功", "成功", 1);
			} else {
				JOptionPane.showMessageDialog(this, "添加账户异常，请联系系统管理员", "错误", 0);
			}
		} else {
			JOptionPane.showMessageDialog(this, "请填写正确的金额或卡号！", "错误", 0);
		}
		t_bank.setText("");
		t_site.setText("");
		t_amount.setText("");
		t_account.setText("");
	}

	public static void setUserInfo(int uId) {
		setUid(uId);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	public static int getUid() {
		return uid;
	}

	public static void setUid(int uid) {
		TopPanel.uid = uid;
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

	// 验证数字
	public static boolean isNumeric(String str) {
		boolean strResult = str.matches("-?[0-9]+.*[0-9]*");
		if (strResult == true) {
			return true;
		} else {
			return false;
		}
	}

}