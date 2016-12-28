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
	
	public DefaultMove(int x, int y, boolean black) {
		this.vertex = new DefaultVertex(x, y);
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
	
	@Override
	public boolean equals(Object obj) {
		if (!Move.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		
		Move other = (Move) obj;
		
		Vertex thisV = this.getVertex();
		Vertex otherV = other.getVertex();
		
		if (!thisV.getLocation().equals(otherV.getLocation())) {
			return false;
		}
		
		return thisV.getState().equals(otherV.getState());
	}
}
