# Fms
Financial management system, Java GUI, Windows application
## Absetact

The discussion of the system is based on C/S (Client/Server user-defined Structs) architecture, use Java language and realize GUI framework of software systems. System set up account management, savings, credit management, daily balance of payments and financial analysis of the five function module.
## Some Code

```Java
try {
	image = ImageIO.read(new File("res/pic/main_bg.jpg"));
} catch (IOException ex) {
	ex.printStackTrace();
}
protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(image, 0, 0, this);
}
frame.addMouseListener(new MouseAdapter() {
	public void mousePressed(MouseEvent e) {
		origin.x = e.getX();
		origin.y = e.getY();
	}
});
frame.addMouseMotionListener(new MouseMotionAdapter() {
	public void mouseDragged(MouseEvent e) {
		Point p = frame.getLocation();
		frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
	}
});
```
## Screen shot
![demo](/img/screenshot.png)

## One more thing
Import Access_JDBC30.jar and hibernate3.jar first!
