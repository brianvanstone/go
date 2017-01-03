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
	private List<BoardConfiguration> boardHistory;
	//TODO track changes in captures
	private float komi;
	private TimeSettings timeSettings;
	
	public DefaultBoard(int size, float komi) {
		this.size = size;
		this.config = new DefaultBoardConfiguration(size);
		this.whiteCaps = 0;
		this.blackCaps = 0;
		this.komi = komi;
		this.boardHistory = new LinkedList<>();
		
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
		List<Move> legalMoves = new LinkedList<>();
		for (int x = 0; x < this.getSize(); x++) {
			for (int y = 0; y < this.getSize(); y++) {
				Move move = new DefaultMove(new DefaultVertex(x,y), black);
				if (isMoveLegal(move)) {
					legalMoves.add(move);
				}
			}
		}
		
		return legalMoves;
	}

	@Override
	public void move(Move move) {
		//first let's push the current state to the top of the history
		this.boardHistory.add(config.snapshot());
		
		//what color is the move?
		boolean black = move.getVertex().getState() == State.BLACK;
		
		//if the move is legal, place the stone
		if (isMoveLegal(move)) {
			Vertex v = move.getVertex();
			this.getBoardConfiguration()
				.placeStone(v.getLocation().x, v.getLocation().y, black);
		}
		
		//now to capture the opposite colored pieces
		//first let's find them all
		State enemy = black ? State.WHITE : State.BLACK;
		List<DefaultVertex> stonesToCapture = new LinkedList<>();
		for (DefaultVertex v : this.config.getAllVerticesWithState(enemy)) {
			if (v.countLiberties() == 0) {
				stonesToCapture.add(v);
			}
		}
		
		//now let's capture them and be sure to record them
		for (DefaultVertex v : stonesToCapture) {
			v.setState(State.NEUTRAL);
			if (black) {
				whiteCaps++;
			} else {
				blackCaps++;
			}
		}
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
			
			int caps = 0;
			//now let's capture them
			for (DefaultVertex v : stonesToCapture) {
				v.setState(State.NEUTRAL);
				caps++;
			}
			
			//now let's see if we restored a liberty to the piece
				//if not then the move is not legal
			if (existingVertex.countLiberties() == 0) {
				//let's restore the board state and return false
				this.config = (DefaultBoardConfiguration) oldBoard;
				return false;
			} else {
				//even if the piece now has a liberty, the last thing to check
					//is if this move violated Ko
				DefaultBoardConfiguration now = this.config;
				if (now.equals(this.boardHistory.get(this.boardHistory.size()-2))
						&& caps == 1) {
					//ko violated, return false after restting board state
					this.config = (DefaultBoardConfiguration) oldBoard;
					return false;
				}
			}
		}
		
		//before we say the move is legal, let's make sure the board state is restored
		this.config = (DefaultBoardConfiguration) oldBoard;
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder repr = new StringBuilder();
		
		repr.append("Moves made: " + this.boardHistory.size() + "\n");
		repr.append("White caps: " + this.whiteCaps + "\n");
		repr.append("Black caps: "  + this.blackCaps + "\n");
		repr.append("Board:\n");
		repr.append(this.config.display());
		
		return repr.toString();
	}
	
	public void undo() {
		this.config = (DefaultBoardConfiguration) this.boardHistory.remove(this.boardHistory.size()-1);
	}

	@Override
	public List<BoardConfiguration> getMoveHistory() {
		return this.boardHistory;
	}
}
