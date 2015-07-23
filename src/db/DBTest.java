package db;

import java.awt.event.*;
import javax.swing.*;


public class DBTest extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7634096623529195745L;
	private JButton test1 = null;
	private ImageIcon test2 = new ImageIcon("res/pic/test2.png");
	private ImageIcon test = new ImageIcon("res/pic/test.png");
	
	public DBTest(){
		
		//test1 = new CircleButton("res/pic/test.png");
		test1 = new JButton(test2);
		
		this.setLayout(null);
		
		test1.setBounds(50, 50, 135, 135);
		test1.addActionListener(this);
		test1.setRolloverIcon(test);
		test1.setContentAreaFilled(false);
		test1.setBorderPainted(false);
		this.add(test1);
		
	}

	public static void main(String []ar) {
		DBTest db = new DBTest();
		db.setVisible(true);
		db.setSize(300, 300);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == test1) {
			JOptionPane.showMessageDialog(this, "成功", "成功", 1);
		}
	}
}
