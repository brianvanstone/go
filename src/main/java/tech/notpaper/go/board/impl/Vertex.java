package tech.notpaper.go.board.impl;

import java.awt.Point;


public class Vertex extends Point {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4566642165438026131L;
	private State state;
	
	public Vertex(int x, int y) {
		super(x,y);
		this.state = State.NEUTRAL;
	}
	
	@Override
	public String toString() {
		//return the move like a Go move
		// i.e. A11, C9
		// so take the x and add 97 for the ascii offset to lowercase a
		return String.valueOf(((char)(x+97))) + String.valueOf(y);
	}
	
	public State getState() {
		return this.state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public enum State {
		WHITE, BLACK, NEUTRAL;
	}
}
