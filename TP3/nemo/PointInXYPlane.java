package nemo;

public class PointInXYPlane {
	int xCoord;
	int yCoord;
	
	public PointInXYPlane(int x, int y) {
		xCoord = x;
		yCoord = y;
	}
	
	public int getX() {
		return xCoord;
	}
	
	public int getY() {
		return yCoord;
	}
	
	public PointInXYPlane addWith(PointInXYPlane pointInXYPlane) {
		int newX = xCoord + pointInXYPlane.getX();
		int newY = yCoord + pointInXYPlane.getY();
		return new PointInXYPlane(newX, newY);
	}
}
