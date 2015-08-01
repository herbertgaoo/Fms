# Fms
financial management system, Java GUI, Windows application
##Absetact
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
