package tech.notpaper.go.board;

import java.util.List;

import tech.notpaper.go.messaging.entities.complex.GoList;
import tech.notpaper.go.messaging.entities.simple.GoString;

public interface MoveHistory {
	public List<Move> getAllMoves();
	public Move getLastMove();
	public GoList<GoString> asGoList();
	public boolean empty();
	public void pushMove(Move move);
	public Move popMove(Move move);
}
