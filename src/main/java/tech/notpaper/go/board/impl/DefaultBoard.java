package tech.notpaper.go.board.impl;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import tech.notpaper.go.board.Board;
import tech.notpaper.go.board.BoardConfiguration;
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
		return new LinkedList<>();
	}

	@Override
	public void move(Move move) {
		//if the move is legal, place the stone
		if (isMoveLegal(move)) {
			Vertex v = move.getVertex();
			this.getBoardConfiguration()
				.placeStone(v.getLocation().x, v.getLocation().y, v.getState() == State.BLACK);
		}
		
		//now remove captured stones, if any
		
		
	}
	
	private boolean isMoveLegal(Move move) {
		//need algorithm to determine if move is legal
		//no matter what we need to restore the board state
			//after we are done transforming it
		BoardConfiguration oldBoard = this.config.snapshot();
		
		//get the location this move is supposed to play at
		Point location = move.getVertex().getLocation();
		
		//for starters, move is not legal if space is occupied
		DefaultVertex existingVertex = (DefaultVertex) this.config.vertexAt(location.x, location.y);
		if (existingVertex.getState() != State.NEUTRAL) {
			return false;
		}
		
		//also, move is illegal if it violates Ko by resulting in an identical board state
		//to just before the last opposing move so long as each move would be a capture
		//rules for Ko,
		//	1. this move captures one piece
		//	2. last move captured one piece
		//	3. this move results in identical board state to before last move
		
		//determine the color tile being placed by the move
		boolean black = move.getVertex().getState() == State.BLACK;
		
		//try to color the tile and see if it runs out of liberties
		existingVertex.setState(black ? State.BLACK : State.WHITE);
		if (existingVertex.countLiberties() == 0) {
			//this means we have placed a stone with 0 liberties
			//we need to determine if this results in a capture
			//which will restore one or more liberties to the placed piece
			//look for pieces of the opposite color with 0 liberties
			//and capture them. After the capture, check the current move
			//again to see if any liberties are restored
			
			
			//now to capture the opposite colored pieces
			//first let's find them all
			State enemyColor = black ? State.BLACK : State.WHITE;
			List<DefaultVertex> stonesToCapture = new LinkedList<>();
			for (DefaultVertex v : this.config.getAllVerticesWithState(enemyColor)) {
				if (v.countLiberties() == 0) {
					stonesToCapture.add(v);
				}
			}
			
			//now let's capture them
			for (DefaultVertex v : stonesToCapture) {
				v.setState(State.NEUTRAL);
			}
			
			//now let's see if we restored a liberty to the piece
				//if not then the move is not legal
			if (existingVertex.countLiberties() == 0) {
				//let's restore the board state and return false
				this.config = (DefaultBoardConfiguration) oldBoard;
				return false;
			} else {
				//if it now has one or more liberties, the move is only legal if it didn't violate Ko
				//TODO determine if move violated Ko
			}
		}
		
		//before we say the move is legal, let's make sure the board state is restored
		this.config = (DefaultBoardConfiguration) oldBoard;
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder repr = new StringBuilder();
		
		repr.append("Moves made: " + this.moveHistory.getAllMoves().size() + "\n");
		repr.append("White caps: " + this.whiteCaps + "\n");
		repr.append("Black caps: "  + this.blackCaps + "\n");
		repr.append("Board:\n");
		repr.append(this.config.display());
		
		return repr.toString();
	}
}
