package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Main
 */

public class MainPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5011143138927170585L;
	private Image image;

	private String str[] = { "res/pic/main_bt1_bg.png",
			"res/pic/main_bt2_bg.png", "res/pic/main_bt3_bg.png",
			"res/pic/main_bt4_bg.png", "res/pic/main_bt6_bg.png" };

	private String str1[] = { "res/pic/main_bt1_c.png",
			"res/pic/main_bt2_c.png", "res/pic/main_bt3_c.png",
			"res/pic/main_bt4_c.png", "res/pic/main_bt6_c.png" };

	private ImageIcon exit_ico = new ImageIcon("res/pic/exit_bg.png");

	private ImageIcon exit_c_ico = new ImageIcon("res/pic/exit_bg_c.png");

	private ImageIcon logout_ico = new ImageIcon("res/pic/logout_bg.png");

	private ImageIcon logout_c_ico = new ImageIcon("res/pic/logout_bg_c.png");

	private ImageIcon que_ico = new ImageIcon("res/pic/que_bg.png");

	private ImageIcon que_c_ico = new ImageIcon("res/pic/que_bg_c.png");

	private ImageIcon main_ico[] = new ImageIcon[str.length];

	private ImageIcon main_cov[] = new ImageIcon[str1.length];

	private JButton main_bt[] = new JButton[str.length];

	private JButton bt_exit = new JButton(exit_ico);

	private JButton bt_logout = new JButton(logout_ico);

	private JButton bt_que = new JButton(que_ico);

	private static JLabel userInfo = new JLabel();

	private boolean flag[] = { true, true, true, true, true };

	public MainPanel() {
		try {
			image = ImageIO.read(new File("res/pic/main_bg.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		init();
		this.setLayout(null);

		bt_exit.setBorderPainted(false);
		bt_exit.setContentAreaFilled(false);
		bt_exit.setRolloverIcon(exit_c_ico);
		bt_exit.setPressedIcon(exit_c_ico);
		bt_exit.setBounds(935, 35, 36, 36);
		bt_exit.setToolTipText("退出系统");
		bt_exit.addActionListener(this);

		bt_que.setBorderPainted(false);
		bt_que.setContentAreaFilled(false);
		bt_que.setRolloverIcon(que_c_ico);
		bt_que.setPressedIcon(que_c_ico);
		bt_que.setBounds(890, 35, 36, 36);
		bt_que.setToolTipText("帮助");
		bt_que.addActionListener(this);

		bt_logout.setBorderPainted(false);
		bt_logout.setContentAreaFilled(false);
		bt_logout.setRolloverIcon(logout_c_ico);
		bt_logout.setPressedIcon(logout_c_ico);
		bt_logout.setBounds(845, 35, 36, 36);
		bt_logout.setToolTipText("注销");
		bt_logout.addActionListener(this);

		userInfo.setBounds(400, 270, 300, 36);
		userInfo.setForeground(Color.WHITE);
		userInfo.setFont(new Font("宋体", Font.BOLD, 20));

		main_bt[0].setBounds(150, 170, 135, 135);
		main_bt[0].setContentAreaFilled(false);
		main_bt[0].setBorderPainted(false);
		main_bt[0].setRolloverIcon(main_cov[0]);
		main_bt[0].addActionListener(this);

		main_bt[1].setBounds(425, 90, 135, 135);
		main_bt[1].setRolloverIcon(main_cov[1]);
		main_bt[1].setContentAreaFilled(false);
		main_bt[1].setBorderPainted(false);
		main_bt[1].addActionListener(this);

		main_bt[2].setBounds(700, 170, 135, 135);
		main_bt[2].setRolloverIcon(main_cov[2]);
		main_bt[2].setContentAreaFilled(false);
		main_bt[2].setBorderPainted(false);
		main_bt[2].addActionListener(this);

		main_bt[3].setBounds(300, 340, 135, 135);
		main_bt[3].setRolloverIcon(main_cov[3]);
		main_bt[3].setContentAreaFilled(false);
		main_bt[3].setBorderPainted(false);
		main_bt[3].addActionListener(this);

		main_bt[4].setBounds(550, 340, 135, 135);
		main_bt[4].setRolloverIcon(main_cov[4]);
		main_bt[4].setContentAreaFilled(false);
		main_bt[4].setBorderPainted(false);
		main_bt[4].addActionListener(this);

		this.add(bt_exit);
		this.add(bt_que);
		this.add(bt_logout);
		this.add(userInfo);
		this.add(main_bt[0]);
		this.add(main_bt[1]);
		this.add(main_bt[2]);
		this.add(main_bt[3]);
		this.add(main_bt[4]);
	}

	public void init() {
		for (int i = 0; i < str.length; i++)
			main_ico[i] = new ImageIcon(str[i]);

		for (int i = 0; i < str1.length; i++)
			main_cov[i] = new ImageIcon(str1[i]);

		for (int i = 0; i < str.length; i++)
			main_bt[i] = new JButton(main_ico[i]);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_exit) {
			int choice = JOptionPane.showConfirmDialog(null, "确认退出系统？", "提示",
					JOptionPane.YES_NO_OPTION);
			if (choice == 0)
				System.exit(0);
		} else if (e.getSource() == bt_que) {
			JOptionPane.showMessageDialog(this, "系统帮助！", "友情提示", 1);
		} else if (e.getSource() == bt_logout) {
			int choice = JOptionPane.showConfirmDialog(null, "确认注销当前用户？", "提示",
					JOptionPane.YES_NO_OPTION);
			if (choice == 0) {
				userInfo.setText("");
				Login.logout();
			}
		} else if (e.getSource() == main_bt[0]) {
			if (flag[1])
				flag[1] = LeftFrame.run();
			LeftFrame.display();
			MainFrame.hide();

		} else if (e.getSource() == main_bt[1]) {
			if (flag[2])
				flag[2] = TopFrame.run();
			TopFrame.display();
			MainFrame.hide();

		} else if (e.getSource() == main_bt[2]) {
			if (flag[3])
				flag[3] = RightFrame.run();
			RightFrame.display();
			MainFrame.hide();

		} else if (e.getSource() == main_bt[3]) {
			if (flag[4])
				flag[4] = BottomLframe.run();
			BottomLframe.display();
			MainFrame.hide();

		} else if (e.getSource() == main_bt[4]) {
			if (flag[0])
				flag[0] = BottonRframe.run();
			BottonRframe.display();
			MainFrame.hide();

		}
	}

	public static void setUserInfo(String uName, int uid) {
		userInfo.setText(uName + ",欢迎使用本系统");
		LeftPanel.setUserInfo(uid);
		TopPanel.setUserInfo(uid);
		RightPanel.setUserInfo(uid);
		BottomLpanel.setUserInfo(uid);
		BottomRpanel.setUserInfo(uName, uid);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

}