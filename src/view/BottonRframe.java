package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BottonRframe {

	private static JFrame frame = new JFrame();

	private static Point origin = new Point();

	public static boolean run() {

		frame.getContentPane().add(new BottomRpanel());
		frame.setSize(1000, 600);
		frame.setUndecorated(true);
		frame.setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - frame.getWidth()) / 2,
				(screenSize.height - frame.getHeight()) / 2);
		frame.addMouseListener(new MouseAdapter() {
			// 按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
			public void mousePressed(MouseEvent e) {
				// 当鼠标按下的时候获得窗口当前的位置
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});

		frame.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point p = frame.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
						- origin.y);
			}
		});
		return false;
	}
	
	public static void hide() {
		frame.dispose();
	}
	
	public static void display() {
		frame.setVisible(true);
		
	}
}
