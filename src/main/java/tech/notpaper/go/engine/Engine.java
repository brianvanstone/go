package tech.notpaper.go.engine;

public interface Engine {
	public String accept(String command);
	public boolean isClosed();
}
