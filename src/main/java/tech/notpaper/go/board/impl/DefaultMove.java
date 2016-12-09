package tech.notpaper.go.board.impl;

import tech.notpaper.go.board.Move;

public class DefaultMove implements Move {
	
	private Vertex vertex;
	private boolean black;
	
	public DefaultMove(Vertex v, boolean black) {
		this.vertex = v;
		this.black = black;
	}
	
	@Override
	public String toString() {
		return this.black ? "b " : "w " + this.vertex.toString();
	}
}
