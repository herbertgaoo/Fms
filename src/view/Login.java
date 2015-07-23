package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import dao.*;

/**
 * Login
 */

public class Login extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = -631402058072673148L;
	private static JDialog dialog = new Login();
	private Image image;
	private JTextField userName = new JTextField(10);
	private JPasswordField passWord = new JPasswordField(20);
	private ImageIcon bt_login_ico = new ImageIcon("res/pic/login_bt.png");
	private ImageIcon bt_cancel_ico = new ImageIcon("res/pic/cancel_bt.png");
	private ImageIcon bt_register_ico = new ImageIcon("res/pic/register_bt.png");
	private JButton bt_login = new JButton(bt_login_ico);
	private JButton bt_cancel = new JButton(bt_cancel_ico);
	private JButton bt_register = new JButton(bt_register_ico);
	private int uid = 0;

	public Login() {
		try {
			image = ImageIO.read(new File("res/pic/login_frame_bg.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		this.setLayout(null);

		userName.setBounds(140, 125, 310, 30);
		userName.setFont(new Font("宋体", Font.BOLD, 20));
		userName.addKeyListener(this);

		userName.setText("admin");
		passWord.setText("admin888");

		passWord.setBounds(140, 166, 310, 30);
		passWord.setFont(new Font("宋体", Font.BOLD, 20));
		passWord.addKeyListener(this);

		bt_login.setBounds(140, 210, 70, 30);
		bt_register.setBounds(262, 210, 70, 30);
		bt_cancel.setBounds(380, 210, 70, 30);
		bt_login.addActionListener(this);
		bt_cancel.addActionListener(this);
		bt_register.addActionListener(this);

		this.add(userName);
		this.add(passWord);
		this.add(bt_login);
		this.add(bt_register);
		this.add(bt_cancel);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bt_cancel) {
			System.exit(0);
		} else if (e.getSource() == bt_register) {
			Register.run();
		} else if (e.getSource() == bt_login) {
			checkUser();
		}
	}

	private void checkUser() {
		UserDao userDao = new UserDao();
		String uName = userName.getText();
		String pWord = String.valueOf(passWord.getPassword());
		uid = userDao.checkLogin(uName, pWord);
		if (uid != 0) {
			JOptionPane.showMessageDialog(dialog, "登录成功", "提示", 1);
			dialog.dispose();
			MainPanel.setUserInfo(uName, uid);
		} else {
			JOptionPane.showMessageDialog(dialog, "用户名或密码错误，请重新输入！", "提示", 0);
		}
	}

	public static void logout() {
		dialog.setVisible(true);
	}

	public void paint(Graphics g) {
		super.paintComponents(g);
		g.drawImage(image, 0, 0, this);
	}

	public static void main(String[] args) {

		MainFrame.run();
		dialog.setSize(541, 313);
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
			checkUser();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}
