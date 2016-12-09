package tech.notpaper.go.messaging.entities.simple;

public class Move implements SimpleEntity {
	
	private Color color;
	private Vertex vertex;
	
	public Move(Color color, Vertex vertex) {
		this.color = color;
		this.vertex = vertex;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(color);
		sb.append(" ");
		sb.append(vertex);
		
		return sb.toString();
	}
	
	public Color getColor() {
		return color;
	}
	
	public Vertex getVertex() {
		return vertex;
	}
	
	@Override
	public Type getType() {
		return SimpleEntity.Type.MOVE;
	}
}
