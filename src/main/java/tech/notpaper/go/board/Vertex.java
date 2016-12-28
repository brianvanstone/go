package tech.notpaper.go.board;

import java.awt.Point;

public interface Vertex {
	
	public enum State {
		WHITE, BLACK, NEUTRAL;
		
		@Override
		public String toString() {
			switch(this) {
			case WHITE:
				return "w";
			case BLACK:
				return "b";
			default:
				return "-";
			}
		}
	}

	public State getState();
	public void setState(State state);
	public Point getLocation();
	public void setLocation(int x, int y);
}
