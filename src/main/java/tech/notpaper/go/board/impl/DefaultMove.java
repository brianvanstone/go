package tech.notpaper.go.board.impl;

import tech.notpaper.go.board.Move;
import tech.notpaper.go.board.Vertex;
import tech.notpaper.go.board.Vertex.State;

public class DefaultMove implements Move {
	
	private Vertex vertex;
	
	public DefaultMove(Vertex v, boolean black) {
		this.vertex = v;
		this.vertex.setState(black ? State.BLACK : State.WHITE);
	}
	
	@Override
	public String toString() {
		return this.vertex.getState() == State.BLACK ? "b " : "w " + this.vertex.toString();
	}

	@Override
	public Vertex getVertex() {
		return this.vertex;
	}
}
