package tech.notpaper.go.messaging.entities.simple;

import java.awt.Point;

public class Vertex implements SimpleEntity {
	
	private Point location;
	
	public Vertex(String in) {
		in = in.toLowerCase();
		try {
			int x = in.charAt(0)-97;
			int y = Integer.parseInt(in.substring(1,in.length()));
			this.location = new Point(x,y);
		} catch (IndexOutOfBoundsException e) {
			location = null;
		}
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	@Override
	public Type getType() {
		return SimpleEntity.Type.VERTEX;
	}
	
	@Override
	public String toString() {
		if (location == null) {
			return "pass";
		} else {
			return String.valueOf((char) (location.x+97)) + Integer.toString(location.y);
		}
	}
}
