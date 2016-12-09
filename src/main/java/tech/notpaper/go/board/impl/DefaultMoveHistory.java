package tech.notpaper.go.board.impl;

import java.util.LinkedList;
import java.util.List;

import tech.notpaper.go.board.Move;
import tech.notpaper.go.board.MoveHistory;
import tech.notpaper.go.messaging.entities.complex.GoList;
import tech.notpaper.go.messaging.entities.simple.GoString;

public class DefaultMoveHistory implements MoveHistory {
	
	private List<Move> moves;
	
	public DefaultMoveHistory() {
		this.moves = new LinkedList<>();
	}

	@Override
	public List<Move> getAllMoves() {
		return moves;
	}

	@Override
	public Move getLastMove() {
		return moves.get(moves.size()-1);
	}
	
	@Override
	public boolean empty() {
		return moves.isEmpty();
	}
	
	@Override
	public void pushMove(Move move) {
		moves.add(move);
	}
	
	@Override
	public Move popMove(Move move) {
		return moves.remove(moves.size()-1);
	}

	@Override
	public GoList<GoString> asGoList() {
		GoList<GoString> moveList = new GoList<>();
		for (Move m : this.moves) {
			moveList.add(new GoString(m.toString()));
		}
		return moveList;
	}
}
