package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import dao.UserDao;
import entities.*;

/**
 * LeftPaneljk
 */

public class BottomRpanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5011143138927170585L;

	private Image image;

	private static int uid = 0;
	
	private UserDao dao = new UserDao();

	private static String uname = "";

	private Font font1 = new Font("宋体", Font.BOLD, 16);

	private JPanel changePW = new JPanel();

	private JPanel userInfo = new JPanel();

	private JLabel l_username = new JLabel("用 户 名");

	private JLabel l_password = new JLabel("旧 密 码");

	private JLabel l_confirmpw = new JLabel("确认密码");

	private JLabel l_newpw = new JLabel("新 密 码");

	private JLabel L_name = new JLabel("姓    名");

	private JLabel l_birthday = new JLabel("生    日");

	private JLabel l_company = new JLabel("公    司");

	private JLabel l_phone = new JLabel("手    机");

	private JTextField t_name = new JTextField(10);

	private JTextField t_company = new JTextField(10);

	private JTextField t_phone = new JTextField(10);

	private DateChooser t_birthday = new DateChooser(userInfo, 180);

	private static JTextField t_username = new JTextField(10);

	private JPasswordField p_password = new JPasswordField(10);

	private JPasswordField p_confirmpw = new JPasswordField(10);

	private JPasswordField p_newpw = new JPasswordField(10);

	private ImageIcon back_ico = new ImageIcon("res/pic/back_bt.png");

	private ImageIcon back_cov = new ImageIcon("res/pic/back_bt_c.png");

	private ImageIcon change_pw = new ImageIcon("res/pic/change_bt.png");

	private ImageIcon info_ico = new ImageIcon("res/pic/info_bt.png");

	private JButton bt_back = new JButton(back_ico);

	private JButton bt_info = new JButton(info_ico);

	private JButton bt_change = new JButton(change_pw);

	private JButton bt_ok = new JButton("确认");

	private JButton bt_reset = new JButton("重置");

	private JButton bt_save = new JButton("保存");

	private JButton bt_modify = new JButton("修改");

	public BottomRpanel() {
		try {
			image = ImageIO.read(new File("res/pic/frame_bg.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		this.setLayout(null);
		changePW.setLayout(null);
		changePW.setBounds(370, 130, 550, 350);
		changePW.setOpaque(false);
		userInfo.setOpaque(false);
		userInfo.setLayout(null);
		userInfo.setBounds(370, 130, 550, 350);
		userInfo.setVisible(false);
		bt_change.setBounds(100, 200, 150, 35);
		bt_info.setBounds(100, 350, 150, 35);

		l_username.setHorizontalAlignment(JLabel.RIGHT);
		l_username.setFont(font1);
		t_username.setFont(font1);
		t_username.setEditable(false);
		l_username.setBounds(10, 30, 185, 30);
		t_username.setBounds(230, 30, 200, 30);

		l_password.setHorizontalAlignment(JLabel.RIGHT);
		l_password.setFont(font1);
		p_password.setFont(font1);
		l_password.setBounds(10, 90, 185, 30);
		p_password.setBounds(230, 90, 200, 30);

		l_newpw.setHorizontalAlignment(JLabel.RIGHT);
		l_newpw.setFont(font1);
		p_newpw.setFont(font1);
		l_newpw.setBounds(10, 150, 185, 30);
		p_newpw.setBounds(230, 150, 200, 30);

		l_confirmpw.setHorizontalAlignment(JLabel.RIGHT);
		l_confirmpw.setFont(font1);
		p_confirmpw.setFont(font1);
		l_confirmpw.setBounds(10, 210, 185, 30);
		p_confirmpw.setBounds(230, 210, 200, 30);

		bt_ok.setFont(font1);
		bt_reset.setFont(font1);
		bt_ok.setBounds(130, 270, 130, 30);
		bt_reset.setBounds(300, 270, 130, 30);

		bt_back.setBorderPainted(false);
		bt_back.setContentAreaFilled(false);
		bt_back.setRolloverIcon(back_cov);
		bt_back.setPressedIcon(back_cov);
		bt_back.setBounds(890, 35, 36, 36);
		bt_back.setToolTipText("返回主界面");

		L_name.setHorizontalAlignment(JLabel.RIGHT);
		L_name.setFont(font1);
		t_name.setFont(font1);
		L_name.setBounds(10, 30, 185, 30);
		t_name.setBounds(230, 30, 200, 30);

		l_birthday.setHorizontalAlignment(JLabel.RIGHT);
		l_birthday.setFont(font1);
		t_birthday.setFont(font1);
		l_birthday.setBounds(10, 90, 185, 30);
		t_birthday.setBounds(230, 90, 200, 30);

		l_company.setHorizontalAlignment(JLabel.RIGHT);
		l_company.setFont(font1);
		t_company.setFont(font1);
		l_company.setBounds(10, 150, 185, 30);
		t_company.setBounds(230, 150, 200, 30);

		l_phone.setHorizontalAlignment(JLabel.RIGHT);
		l_phone.setFont(font1);
		t_phone.setFont(font1);
		l_phone.setBounds(10, 210, 185, 30);
		t_phone.setBounds(230, 210, 200, 30);
		t_phone.setToolTipText("请输入11位手机号码");

		bt_save.setFont(font1);
		bt_modify.setFont(font1);
		bt_save.setBounds(130, 270, 130, 30);
		bt_modify.setBounds(300, 270, 130, 30);

		changePW.add(l_username);
		changePW.add(t_username);
		changePW.add(l_password);
		changePW.add(p_password);
		changePW.add(l_newpw);
		changePW.add(p_newpw);
		changePW.add(l_confirmpw);
		changePW.add(p_confirmpw);
		changePW.add(bt_ok);
		changePW.add(bt_reset);

		userInfo.add(L_name);
		userInfo.add(t_name);
		userInfo.add(l_birthday);
		userInfo.add(t_birthday);
		userInfo.add(l_company);
		userInfo.add(t_company);
		userInfo.add(l_phone);
		userInfo.add(t_phone);
		userInfo.add(bt_save);
		userInfo.add(bt_modify);

		bt_change.addActionListener(this);
		bt_info.addActionListener(this);
		bt_back.addActionListener(this);
		bt_reset.addActionListener(this);
		bt_ok.addActionListener(this);
		bt_save.addActionListener(this);
		bt_modify.addActionListener(this);

		this.add(bt_change);
		this.add(bt_info);
		this.add(bt_back);
		this.add(userInfo);
		this.add(changePW);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_back) {
			BottonRframe.hide();
			MainFrame.display();
		} else if (e.getSource() == bt_reset) {
			p_password.setText("");
			p_confirmpw.setText("");
			p_newpw.setText("");
		} else if (e.getSource() == bt_ok) {
			changePassword();
		} else if (e.getSource() == bt_change) {
			userInfo.setVisible(false);
			changePW.setVisible(true);
		} else if (e.getSource() == bt_info) {
			changePW.setVisible(false);
			userInfo.setVisible(true);
			getUserInfo();
		} else if (e.getSource() == bt_save) {
			modifyUserInfo("save");
		} else if (e.getSource() == bt_modify) {
			modifyUserInfo("modify");
		}
	}

	private void getUserInfo() {
		UserInfo ui = new UserInfo();
		ui = dao.getUserInfo(uid);
		if (ui == null) {
			t_name.setText("");
			t_birthday.setText("");
			t_company.setText("");
			t_phone.setText("");
		} else {
			t_name.setText(ui.getName());
			t_birthday.setText(ui.getBirthday());
			t_company.setText(ui.getCompany());
			t_phone.setText(ui.getPhone());
		}
	}

	private void modifyUserInfo(String action) {
		UserInfo ui = new UserInfo();
		ui.setName(t_name.getText());
		ui.setBirthday(t_birthday.getText());
		ui.setCompany(t_company.getText());
		ui.setPhone(t_phone.getText());
		ui.setUid(uid);
		boolean rs = dao.modifyUserInfo(ui, action);
		if (rs) {
			JOptionPane.showMessageDialog(this, "成功", "成功", 1);
		} else {
			JOptionPane.showMessageDialog(this, "请再次确认输入信息的格式！", "错误", 0);
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	private void changePassword() {
		String pWord = String.valueOf(p_password.getPassword());
		String cPw = String.valueOf(p_confirmpw.getPassword());
		String newPW = String.valueOf(p_newpw.getPassword());

		if (cPw.equals(newPW) && newPW.length() >= 6) {
			boolean rs = dao.changePassword(uid, pWord, newPW);

			if (rs) {
				JOptionPane.showMessageDialog(this, "修改密码成功，请重新登录", "成功", 1);
				p_password.setText("");
				p_confirmpw.setText("");
				p_newpw.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "旧密码错误或新密码不符合规范", "错误", 0);
			}
		} else {
			JOptionPane.showMessageDialog(this, "旧密码错误或新密码不符合规范", "错误", 0);
		}
	}

	public static void setUserInfo(String uName, int uId) {
		setUid(uId);
		setUname(uName);
		t_username.setText(uName);
	}

	public static int getUid() {
		return uid;
	}

	public static void setUid(int uid) {
		BottomRpanel.uid = uid;
	}

	public static String getUname() {
		return uname;
	}

	public static void setUname(String uname) {
		BottomRpanel.uname = uname;
	}

}