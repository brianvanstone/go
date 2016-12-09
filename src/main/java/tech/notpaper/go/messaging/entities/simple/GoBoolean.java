package tech.notpaper.go.messaging.entities.simple;

public class GoBoolean implements SimpleEntity, Comparable<GoBoolean> {
	
	private Boolean wrappedBoolean;
	
	public GoBoolean(boolean b) {
		this.wrappedBoolean = Boolean.valueOf(b);
	}
	
	public GoBoolean(Boolean b) {
		this.wrappedBoolean = b;
	}

	@Override
	public int compareTo(GoBoolean o) {
		return this.wrappedBoolean.compareTo(o.toBoolean());
	}
	
	public Boolean toBoolean() {
		return wrappedBoolean;
	}
	
	@Override
	public Type getType() {
		return SimpleEntity.Type.BOOLEAN;
	}
	
	@Override
	public String toString() {
		return wrappedBoolean.toString();
	}
}
