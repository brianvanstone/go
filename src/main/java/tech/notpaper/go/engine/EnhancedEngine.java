package tech.notpaper.go.engine;

import tech.notpaper.go.messaging.entities.GoResponse;
import tech.notpaper.go.messaging.entities.complex.GoList;
import tech.notpaper.go.messaging.entities.complex.ListEntity;

public interface EnhancedEngine extends Engine {
	public GoResponse protocolVersion();
	public GoResponse name();
	public GoResponse version();
	public GoResponse knownCommand(GoList<ListEntity> args);
	public GoResponse listCommands();
	public GoResponse quit();
	public GoResponse boardsize(GoList<ListEntity> args);
	public GoResponse clearBoard();
	public GoResponse komi(GoList<ListEntity> args);
	public GoResponse fixedHandicap(GoList<ListEntity> args);
	public GoResponse placeFreeHandicap(GoList<ListEntity> args);
	public GoResponse setFreeHandicap(GoList<ListEntity> args);
	public GoResponse play(GoList<ListEntity> args);
	public GoResponse genMove(GoList<ListEntity> args);
	public GoResponse undo();
	public GoResponse timeSettings(GoList<ListEntity> args);
	public GoResponse timeLeft(GoList<ListEntity> args);
	public GoResponse finalScore(GoList<ListEntity> args);
	public GoResponse finalStatusList(GoList<ListEntity> args);
	public GoResponse loadsgf(GoList<ListEntity> args);
	public GoResponse regGenMove(GoList<ListEntity> args);
	public GoResponse showBoard();
}
