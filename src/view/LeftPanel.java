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

import dao.DealRecordDao;
import entities.*;

/**
 * LeftPaneljk
 */

public class LeftPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5011143138927170585L;

	private Image image;

	private static int uid = 0;

	private DealRecordDao dr = new DealRecordDao();

	private Font font1 = new Font("����", Font.BOLD, 14);

	private String[] in_methods = { "�ֽ�", "ת��" };

	private String[] out_methods = { "����", "ת��" };

	private JPanel p_income = new JPanel();

	private JPanel p_pay = new JPanel();

	private JLabel l_date = new JLabel("��   ��");

	private JLabel l_methods = new JLabel("��   ʽ");

	private JLabel l_amount = new JLabel("���(Ԫ)");

	private JLabel l_comment = new JLabel("ժ   Ҫ");

	private JLabel l_date_p = new JLabel("��   ��");

	private JLabel l_methods_p = new JLabel("��   ʽ");

	private JLabel l_amount_p = new JLabel("���(Ԫ)");

	private JLabel l_comment_p = new JLabel("ժ   Ҫ");

	private JComboBox c_income = new JComboBox(in_methods);

	private JComboBox c_pay = new JComboBox(out_methods);

	private JTextField t_amount = new JTextField(10);

	private JTextField t_comment = new JTextField(10);

	private JTextField t_amount_p = new JTextField(10);

	private JTextField t_comment_p = new JTextField(10);

	private DateChooser date_i = new DateChooser(p_income, 125);

	private DateChooser date_p = new DateChooser(p_pay, 125);

	private JButton bt_add_p = new JButton("���");

	private JButton bt_add_i = new JButton("���");

	private JButton bt_del_p = new JButton("ɾ��ѡ����");

	private JButton bt_del_i = new JButton("ɾ��ѡ����");

	private ImageIcon back_ico = new ImageIcon("res/pic/back_bt.png");

	private ImageIcon back_cov = new ImageIcon("res/pic/back_bt_c.png");

	private static ImageIcon income_ico = new ImageIcon("res/pic/income_bt.png");

	private ImageIcon pay_ico = new ImageIcon("res/pic/pay_bt.png");

	private JButton bt_back = new JButton(back_ico);

	private static JButton bt_income = new JButton(income_ico);

	private JButton bt_pay = new JButton(pay_ico);

	private JTable tb_income = new JTable();

	private JTable tb_pay = new JTable();

	DefaultTableModel tableMod_i = new DefaultTableModel(new String[0][0],
			new String[] { "     ��ˮ��     ", "      ��    ��      ", "  ��    ʽ  ",
					"  ��   ��(Ԫ)  ", "��ע" });

	DefaultTableModel tableMod_p = new DefaultTableModel(new String[0][0],
			new String[] { "     ��ˮ��     ", "      ��    ��      ", "  ��    ʽ  ",
					"  ��   ��(Ԫ)  ", "��ע" });

	DefaultTableCellRenderer tableCell = new DefaultTableCellRenderer();

	private JScrollPane js_income = null;

	private JScrollPane js_pay = null;

	public LeftPanel() {
		try {
			image = ImageIO.read(new File("res/pic/frame_bg.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		this.setLayout(null);
		p_income.setLayout(null);
		p_pay.setLayout(null);
		p_income.setBounds(370, 130, 550, 350);
		p_pay.setBounds(370, 130, 550, 350);
		bt_income.setBounds(100, 200, 150, 35);
		bt_pay.setBounds(100, 350, 150, 35);
		p_pay.setOpaque(false);
		p_income.setOpaque(false);
		p_pay.setVisible(false);
		tb_income.setModel(tableMod_i);
		tb_pay.setModel(tableMod_p);
		js_income = new JScrollPane(tb_income);
		js_pay = new JScrollPane(tb_pay);
		tableCell.setHorizontalAlignment(JLabel.CENTER);
		tb_income.setDefaultRenderer(Object.class, tableCell);
		tb_pay.setDefaultRenderer(Object.class, tableCell);
		FitTableColumns(tb_income);
		FitTableColumns(tb_pay);

		l_date.setBounds(30, 20, 70, 30);
		date_i.setBounds(90, 20, 150, 30);
		l_amount.setBounds(280, 20, 70, 30);
		t_amount.setBounds(340, 20, 180, 30);
		l_methods.setBounds(30, 70, 70, 30);
		c_income.setBounds(90, 70, 150, 30);
		l_comment.setBounds(280, 70, 70, 30);
		t_comment.setBounds(340, 70, 180, 30);
		bt_add_i.setBounds(420, 120, 100, 30);
		bt_del_i.setBounds(280, 120, 120, 30);
		js_income.setBounds(30, 165, 490, 165);
		l_date.setFont(font1);
		l_amount.setFont(font1);
		l_methods.setFont(font1);
		l_comment.setFont(font1);
		t_amount.setFont(font1);
		t_comment.setFont(font1);
		c_income.setFont(font1);

		l_date_p.setBounds(30, 20, 70, 30);
		date_p.setBounds(90, 20, 150, 30);
		l_amount_p.setBounds(280, 20, 70, 30);
		t_amount_p.setBounds(340, 20, 180, 30);
		l_methods_p.setBounds(30, 70, 70, 30);
		c_pay.setBounds(90, 70, 150, 30);
		l_comment_p.setBounds(280, 70, 70, 30);
		t_comment_p.setBounds(340, 70, 180, 30);
		bt_add_p.setBounds(420, 120, 100, 30);
		bt_del_p.setBounds(280, 120, 120, 30);
		js_pay.setBounds(30, 165, 490, 165);
		l_date_p.setFont(font1);
		l_amount_p.setFont(font1);
		l_methods_p.setFont(font1);
		l_comment_p.setFont(font1);
		t_amount_p.setFont(font1);
		t_comment_p.setFont(font1);
		c_pay.setFont(font1);

		p_income.add(l_date);
		p_income.add(date_i);
		p_income.add(l_methods);
		p_income.add(c_income);
		p_income.add(l_amount);
		p_income.add(t_amount);
		p_income.add(l_comment);
		p_income.add(t_comment);
		p_income.add(bt_add_i);
		p_income.add(js_income);
		p_income.add(bt_del_i);

		p_pay.add(l_date_p);
		p_pay.add(date_p);
		p_pay.add(l_methods_p);
		p_pay.add(c_pay);
		p_pay.add(l_amount_p);
		p_pay.add(t_amount_p);
		p_pay.add(l_comment_p);
		p_pay.add(t_comment_p);
		p_pay.add(bt_add_p);
		p_pay.add(js_pay);
		p_pay.add(bt_del_p);

		bt_back.setBorderPainted(false);
		bt_back.setContentAreaFilled(false);
		bt_back.setRolloverIcon(back_cov);
		bt_back.setPressedIcon(back_cov);
		bt_back.setBounds(890, 35, 36, 36);
		bt_back.setToolTipText("����������");

		bt_back.addActionListener(this);
		bt_income.addActionListener(this);
		bt_pay.addActionListener(this);
		bt_add_p.addActionListener(this);
		bt_add_i.addActionListener(this);
		bt_del_i.addActionListener(this);
		bt_del_p.addActionListener(this);

		bt_income.doClick();
		this.add(p_income);
		this.add(p_pay);
		this.add(bt_back);
		this.add(bt_income);
		this.add(bt_pay);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_back) {
			LeftFrame.hide();
			MainFrame.display();
		} else if (e.getSource() == bt_income) {
			p_income.setVisible(true);
			p_pay.setVisible(false);
			tableMod_i.setRowCount(0);
			queRecord("income");
		} else if (e.getSource() == bt_pay) {
			p_pay.setVisible(true);
			p_income.setVisible(false);
			tableMod_p.setRowCount(0);
			queRecord("pay");
		} else if (e.getSource() == bt_add_i) {
			addRecord("income");
			bt_income.doClick();
			t_amount.setText("");
			t_comment.setText("");
		} else if (e.getSource() == bt_add_p) {
			addRecord("pay");
			bt_pay.doClick();
			t_amount_p.setText("");
			t_comment_p.setText("");
		} else if (e.getSource() == bt_del_i) {
			if (-1 == tb_income.getSelectedRow()) {
				JOptionPane.showMessageDialog(this, "����ѡ��һ�м�¼��", "����", 0);
			} else {
				delRecord("income");
				tableMod_i.removeRow(tb_income.getSelectedRow());
			}
		} else if (e.getSource() == bt_del_p) {
			if (-1 == tb_pay.getSelectedRow()) {
				JOptionPane.showMessageDialog(this, "����ѡ��һ�м�¼��", "����", 0);
			} else {
				delRecord("pay");
				tableMod_p.removeRow(tb_pay.getSelectedRow());
			}
		}
	}

	private void delRecord(String string) {
		String deal_id = null;
		if (string.equals("income")) {
			deal_id = (String) tableMod_i.getValueAt(
					tb_income.getSelectedRow(), 0);
		} else {
			deal_id = (String) tableMod_p
					.getValueAt(tb_pay.getSelectedRow(), 0);
		}
		boolean rs = dr.delRecord(string, deal_id, getUid());
		if (rs) {
			JOptionPane.showMessageDialog(this, "ɾ����¼�ɹ�", "�ɹ�", 1);
		} else {
			JOptionPane.showMessageDialog(this, "ɾ����¼�쳣������ϵϵͳ����Ա", "����", 0);
		}
	}

	private void queRecord(String string) {
		DefaultTableModel tmp = null;
		ArrayList records = null;
		if (string.equals("income")) {
			records = dr.queRecord(string, getUid());
			tmp = tableMod_i;
		} else {
			records = dr.queRecord(string, getUid());
			tmp = tableMod_p;
		}

		Iterator it = records.iterator(); // ������ ����Arraylist
		while (it.hasNext()) {
			DailyDeal record = (DailyDeal) it.next();
			tmp.addRow(new String[] { record.getDeal_id(),
					record.getDeal_date(), record.getMethods(),
					record.getAmount() + "", record.getComment() });
		}
	}

	private void addRecord(String str) {
		DailyDeal dealRecord = new DailyDeal();
		if ((!t_amount.getText().equals("") && isNumeric(t_amount.getText()))
				|| (!t_amount_p.getText().equals("") && isNumeric(t_amount_p
						.getText()))) {
			if (str.equals("income")) {
				dealRecord.setDeal_date(date_i.getText());
				dealRecord.setMethods((String) c_income.getSelectedItem());
				dealRecord.setAmount(Float.parseFloat(t_amount.getText()));
				dealRecord.setComment(t_comment.getText());
			} else {
				dealRecord.setDeal_date(date_p.getText());
				dealRecord.setMethods((String) c_pay.getSelectedItem());
				dealRecord.setAmount(Float.parseFloat(t_amount_p.getText()));
				dealRecord.setComment(t_comment_p.getText());
			}
			dealRecord.setUid(getUid());
			boolean rs = dr.addRecord(dealRecord, str);
			if (rs) {
				JOptionPane.showMessageDialog(this, "��Ӽ�¼�ɹ�", "�ɹ�", 1);
			} else {
				JOptionPane.showMessageDialog(this, "��Ӽ�¼�쳣������ϵϵͳ����Ա", "����", 0);
			}
		} else {
			JOptionPane.showMessageDialog(this, "����д��", "����", 0);
		}
	}

	public static void setUserInfo(int uId) {
		setUid(uId);
		bt_income.doClick();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	public static int getUid() {
		return uid;
	}

	public static void setUid(int uid) {
		LeftPanel.uid = uid;
	}

	public void FitTableColumns(JTable myTable) { // JTable ����Ӧ�п�
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

	// ��֤����
	public static boolean isNumeric(String str) {
		boolean strResult = str.matches("-?[0-9]+.*[0-9]*");
		if (strResult == true) {
			return true;
		} else {
			return false;
		}
	}
}