package tech.notpaper.go.board.impl;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import tech.notpaper.go.board.BoardConfiguration;
import tech.notpaper.go.board.Vertex;
import tech.notpaper.go.board.Vertex.State;

public class DefaultBoardConfiguration implements BoardConfiguration, Iterable<Vertex> {
	private Vertex[][] vertices;
	private int size;
	
	public DefaultBoardConfiguration(int size) {
		
		this.size = size;
		
		//first create them all
		this.vertices = new DefaultVertex[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				this.vertices[x][y] = new DefaultVertex(x, y);
			}
		}
		
		//now link them all
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				DefaultVertex v = (DefaultVertex) vertices[x][y];
				
				v.north = y == 0 ? null : (DefaultVertex) vertices[x][y-1];
				v.south = y == size-1 ? null: (DefaultVertex) vertices[x][y+1];
				v.east = x == size-1 ? null : (DefaultVertex) vertices[x+1][y];
				v.west = x == 0 ? null : (DefaultVertex) vertices[x-1][y];
			}
		}
	}
	
	public Vertex vertexAt(int x, int y) {
		return this.vertices[x][y];
	}
	
	public Vertex.State stateAt(int x, int y) {
		return this.vertices[x][y].getState();
	}
	
	public Vertex placeStone(int x, int y, boolean black) {
		Vertex v = new DefaultVertex(x, y);
		v.setState(black ? State.BLACK : State.WHITE);
		return v;
	}
	
	public List<DefaultVertex> getAllVerticesWithState(Vertex.State state) {
		List<DefaultVertex> matchingVertices = new LinkedList<>();
		for (Vertex v : this) {
			if(v.getState() == state) {
				matchingVertices.add((DefaultVertex)v);
			}
		}
		return matchingVertices;
	}

	@Override
	public Iterator<Vertex> iterator() {
		return new GoVertexIterator(this.vertices);
	}
	
	private class GoVertexIterator implements Iterator<Vertex> {
		
		private Vertex[][] vertices;
		private int size;
		private int nextIndex;
		
		public GoVertexIterator(Vertex[][] vertices) {
			this.vertices = vertices;
			this.size = vertices.length;
			this.nextIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return this.nextIndex < size*size;
		}

		@Override
		public Vertex next() {
			Point vertex = indexToPoint(this.nextIndex++);
			return this.vertices[vertex.x][vertex.y];
		}
		
		private Point indexToPoint(int index) {
			int x = index / this.size;
			int y = index % this.size;
			return new Point(x, y);
		}
	}

	@Override
	public String toString() {
		StringBuilder display = new StringBuilder();
		for (int x = 0; x < this.size; x++) {
			for (int y = 0; y < this.size; y++) {
				if (x > 0) {
					display.append("|");
				}
				display.append(this.vertexAt(x, y).getState().toString());
				if (x < this.size-1) {
					display.append("|");
				}
			}
			display.append("\n");
		}
		return display.toString();
	}
	
	@Override
	public String display() {
		return this.toString();
	}
	
	@Override
	public BoardConfiguration snapshot() {
		DefaultBoardConfiguration snapshot = new DefaultBoardConfiguration(this.size);
		
		for (Vertex v : this) {
			Point p = v.getLocation();
			v.setState(this.vertexAt(p.x, p.y).getState());
		}
		
		return snapshot;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DefaultBoardConfiguration)) {
			return false;
		}
		
		DefaultBoardConfiguration other = (DefaultBoardConfiguration) obj;
		
		if (this.size != other.getSize()) {
			return false;
		}
		
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (!this.vertexAt(x, y).equals(other.vertexAt(x, y))) {
					return false;
				}
			}
		}
		
		return true;
	}

	public int getSize() {
		return this.size;
	}
}
