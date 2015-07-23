package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import dao.*;

/**
 * Register
 */

public class Register extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 8828410201821956764L;
	private static JDialog dialog = new Register();
	private Image image;
	private JTextField userName = new JTextField(10);
	private JPasswordField passWord = new JPasswordField(10);
	private JPasswordField confirmPW = new JPasswordField(10);
	private ImageIcon bt_reset_ico = new ImageIcon("res/pic/reset_bt.png");
	private ImageIcon bt_cancel_ico = new ImageIcon("res/pic/cancel_bt.png");
	private ImageIcon bt_register_ico = new ImageIcon("res/pic/register_bt.png");
	private JButton bt_reset = new JButton(bt_reset_ico);
	private JButton bt_cancel = new JButton(bt_cancel_ico);
	private JButton bt_register = new JButton(bt_register_ico);

	public Register() {
		try {
			image = ImageIO.read(new File("res/pic/register_frame_bg.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		this.setLayout(null);

		userName.setBounds(140, 81, 220, 30);
		userName.setFont(new Font("宋体", Font.BOLD, 20));
		userName.addKeyListener(this);

		passWord.setBounds(140, 123, 220, 30);
		passWord.setFont(new Font("宋体", Font.BOLD, 20));
		passWord.addKeyListener(this);

		confirmPW.setBounds(140, 165, 220, 30);
		confirmPW.setFont(new Font("宋体", Font.BOLD, 20));
		confirmPW.addKeyListener(this);

		bt_register.setBounds(140, 210, 70, 30);
		bt_reset.setBounds(262, 210, 70, 30);
		bt_cancel.setBounds(380, 210, 70, 30);
		bt_reset.addActionListener(this);
		bt_cancel.addActionListener(this);
		bt_register.addActionListener(this);

		this.add(userName);
		this.add(passWord);
		this.add(confirmPW);
		this.add(bt_reset);
		this.add(bt_register);
		this.add(bt_cancel);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bt_cancel) {
			userName.setText("");
			passWord.setText("");
			confirmPW.setText("");
			dialog.dispose();
		} else if (e.getSource() == bt_register) {
			newUser();
		} else if (e.getSource() == bt_reset) {
			userName.setText("");
			passWord.setText("");
			confirmPW.setText("");
		}
	}

	private void newUser() {
		UserDao userDao = new UserDao();
		String uName = userName.getText();
		String pWord = String.valueOf(passWord.getPassword());
		String cPw = String.valueOf(confirmPW.getPassword());

		if (!uName.equals("") && cPw.equals(pWord) && pWord.length() >= 6) {
			boolean rs = userDao.newUser(uName, pWord);
			if (rs) {
				JOptionPane.showMessageDialog(dialog, "成功", "成功", 1);
				userName.setText("");
				passWord.setText("");
				confirmPW.setText("");
				dialog.dispose();
			}
		} else {
			JOptionPane.showMessageDialog(dialog, "用户名或密码不符合规范", "错误", 0);
		}
	}

	public void paint(Graphics g) {
		super.paintComponents(g);
		g.drawImage(image, 0, 0, this);
	}

	public static void run() {

		dialog.setSize(542, 297);
		dialog.setUndecorated(true);
		dialog.setResizable(false);
		dialog.setModal(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		dialog.setLocation((screenSize.width - dialog.getWidth()) / 2,
				(screenSize.height - dialog.getHeight()) / 2);
		dialog.setVisible(true);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			newUser();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}