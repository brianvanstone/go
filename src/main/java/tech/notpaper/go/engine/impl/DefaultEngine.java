package tech.notpaper.go.engine.impl;

import java.awt.Point;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import tech.notpaper.go.board.Move;
import tech.notpaper.go.board.impl.DefaultBoard;
import tech.notpaper.go.board.impl.DefaultMove;
import tech.notpaper.go.messaging.entities.GoResponse;
import tech.notpaper.go.messaging.entities.complex.GoList;
import tech.notpaper.go.messaging.entities.complex.ListEntity;
import tech.notpaper.go.messaging.entities.simple.Color;
import tech.notpaper.go.messaging.entities.simple.GoMove;

public class DefaultEngine extends DefaultEngineProxy {
	
	private DefaultBoard board;
	private boolean closed;
	private boolean black;
	
	public DefaultEngine(int size, float komi, boolean black) {
		this.board = new DefaultBoard(size, komi);
		this.closed = false;
		this.black = black;
	}

	@Override
	public GoResponse boardsize(GoList<ListEntity> args) {
		this.board = new DefaultBoard(Integer.parseInt(args.get(0).toString()), board.getKomi());
		return new GoResponse();
	}

	@Override
	public GoResponse clearBoard() {
		this.board.clear();
		return new GoResponse();
	}

	@Override
	public GoResponse komi(GoList<ListEntity> args) {
		return new GoResponse(String.valueOf(board.getKomi()));
	}

	@Override
	public GoResponse fixedHandicap(GoList<ListEntity> args) {
		throw new NotYetImplementedException("fixed_handicap");
	}

	@Override
	public GoResponse placeFreeHandicap(GoList<ListEntity> args) {
		throw new NotYetImplementedException("place_free_handicap");
	}

	@Override
	public GoResponse setFreeHandicap(GoList<ListEntity> args) {
		throw new NotYetImplementedException("set_free_handicap");
	}

	@Override
	public GoResponse play(GoList<ListEntity> args) {
		String color = args.get(0).toString();
		String location = args.get(1).toString();
		GoMove moveToMake = new GoMove(color + " " + location);
		Point p = moveToMake.getVertex().getLocation();
		Move newMove = new DefaultMove(p.x, p.y, moveToMake.getColor() == Color.BLACK);
		this.board.move(newMove);
		return new GoResponse(newMove.toString());
	}

	@Override
	public GoResponse genMove(GoList<ListEntity> args) {
		Color color = Color.fromString(args.get(0).toString());
		
		List<Move> legalMoves = this.board.getLegalMoves(color == Color.BLACK);
		Move move = null;
		if(legalMoves.size() > 0) {
			move = legalMoves.get(ThreadLocalRandom.current().nextInt(legalMoves.size()));
		} else {
			move = DefaultMove.pass(color == Color.BLACK);
		}
		this.board.move(move);
		return new GoResponse(move.toString());
	}

	@Override
	public GoResponse undo() {
		this.board.undo();
		return new GoResponse();
	}

	@Override
	public GoResponse timeSettings(GoList<ListEntity> args) {
		throw new NotYetImplementedException("time_settings");
	}

	@Override
	public GoResponse timeLeft(GoList<ListEntity> args) {
		throw new NotYetImplementedException("time_left");
	}

	@Override
	public GoResponse finalScore(GoList<ListEntity> args) {
		throw new NotYetImplementedException("final_score");
	}

	@Override
	public GoResponse finalStatusList(GoList<ListEntity> args) {
		throw new NotYetImplementedException("final_status_list");
	}

	@Override
	public GoResponse loadsgf(GoList<ListEntity> args) {
		throw new NotYetImplementedException("loadsgf");
	}

	@Override
	public GoResponse regGenMove(GoList<ListEntity> args) {
		throw new NotYetImplementedException("reg_gen_move");
	}

	@Override
	public GoResponse showBoard() {
		return new GoResponse(this.board.toString());
	}

	public boolean isClosed() {
		return closed;
	}
}
