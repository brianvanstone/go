package tech.notpaper.go.board.impl;

import java.awt.Point;

import tech.notpaper.go.board.Vertex;


public class DefaultVertex extends Point implements Vertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4566642165438026131L;
	private State state;
	
	public DefaultVertex(int x, int y) {
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
	
	@Override
	public State getState() {
		return this.state;
	}
	
	@Override
	public void setState(State state) {
		this.state = state;
	}
	
	@Override
	public void setLocation(int x, int y) {
		this.setLocation(x, y);
	}
}
