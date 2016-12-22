package tech.notpaper.go.board.impl;

import java.util.List;

import tech.notpaper.go.board.Board;
import tech.notpaper.go.board.Move;
import tech.notpaper.go.board.MoveHistory;
import tech.notpaper.go.board.TimeSettings;
import tech.notpaper.go.board.Vertex;
import tech.notpaper.go.board.Vertex.State;

public class DefaultBoard implements Board {
	
	private DefaultBoardConfiguration config;
	private int size;
	private int whiteCaps;
	private int blackCaps;
	private MoveHistory moveHistory;
	private float komi;
	private TimeSettings timeSettings;
	
	private List<Move> legalMoves;
	
	public DefaultBoard(int size, float komi) {
		this.size = size;
		this.config = new DefaultBoardConfiguration(size);
		this.whiteCaps = 0;
		this.blackCaps = 0;
		this.komi = komi;
		this.moveHistory = new DefaultMoveHistory();
		
		//TODO implement time settings
		this.timeSettings = null;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public DefaultBoardConfiguration getBoardConfiguration() {
		return this.config;
	}

	@Override
	public int getWhiteCaptures() {
		return this.whiteCaps;
	}

	@Override
	public int getBlackCaptures() {
		return this.blackCaps;
	}

	@Override
	public MoveHistory getMoveHistory() {
		return this.moveHistory;
	}
	
	@Override
	public float getKomi() {
		return this.komi;
	}
	
	@Override
	public TimeSettings getTimeSettings() {
		return this.timeSettings;
	}

	@Override
	public void clear() {
		this.config = new DefaultBoardConfiguration(this.size);
	}

	@Override
	public List<Move> getLegalMoves(boolean black) {
		return legalMoves;
	}

	@Override
	public void move(Move move) {
		if (legalMoves.contains(move)) {
			Vertex v = move.getVertex();
			this.getBoardConfiguration()
				.placeStone(v.getLocation().x, v.getLocation().y, v.getState() == State.BLACK);
		}
	}
}
