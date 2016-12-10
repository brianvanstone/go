package tech.notpaper.go.board.impl;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import tech.notpaper.go.board.BoardConfiguration;
import tech.notpaper.go.board.Vertex;

public class DefaultBoardConfiguration implements BoardConfiguration, Iterable<Vertex> {
	private Vertex[][] vertices;
	
	public DefaultBoardConfiguration(int size) {
		this.vertices = new Vertex[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				this.vertices[x][y] = new DefaultVertex(x, y);
			}
		}
	}
	
	public Vertex vertexAt(int x, int y) {
		return this.vertices[x][y];
	}
	
	public Vertex.State stateAt(int x, int y) {
		return this.vertices[x][y].getState();
	}
	
	public List<Vertex> getAllVerticesWithState(Vertex.State state) {
		List<Vertex> matchingVertices = new LinkedList<>();
		for (Vertex v : this) {
			if(v.getState() == state) {
				matchingVertices.add(v);
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
	public String display() {
		return "";
	}
}
