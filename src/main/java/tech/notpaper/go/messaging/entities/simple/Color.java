package tech.notpaper.go.messaging.entities.simple;

public enum Color implements SimpleEntity {
	WHITE, BLACK;
	
	public static Color fromString(String in) {
		switch(in) {
		case "white":
		case "w":
			return WHITE;
		case "black":
		case "b":
			return BLACK;
		default:
			throw new IllegalArgumentException("Attempted to construct Color from bad input [" + in + "]");
		}
	}
	
	@Override
	public String toString() {
		switch(this) {
		case WHITE:
			return "w";
		case BLACK:
			return "b";
		}
		
		throw new IllegalStateException("Somehow Color object in memory was in illegal state. Should not be possible.");
	}
	
	@Override
	public Type getType() {
		return SimpleEntity.Type.COLOR;
	}
}
