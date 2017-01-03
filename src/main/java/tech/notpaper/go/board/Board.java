package tech.notpaper.go.board;

import java.util.List;

public interface Board {
	public int getSize();
	public BoardConfiguration getBoardConfiguration();
	public int getWhiteCaptures();
	public int getBlackCaptures();
	public List<BoardConfiguration> getMoveHistory();
	public float getKomi();
	public TimeSettings getTimeSettings();
	public void clear();
	public List<Move> getLegalMoves(boolean black);
	public void move(Move move);
}
