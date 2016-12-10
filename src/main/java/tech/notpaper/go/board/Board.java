package tech.notpaper.go.board;

import java.util.LinkedList;
import java.util.List;

public abstract class Board {
	public abstract int getSize();
	public abstract BoardConfiguration getBoardConfiguration();
	public abstract int getWhiteCaptures();
	public abstract int getBlackCaptures();
	public abstract MoveHistory getMoveHistory();
	public abstract float getKomi();
	public abstract TimeSettings getTimeSettings();
	public abstract void clear();
	
	public final List<Move> getLegalMoves(boolean black) {
		BoardConfiguration config = this.getBoardConfiguration();
		
		
		
		return new LinkedList<>();
	}
}
