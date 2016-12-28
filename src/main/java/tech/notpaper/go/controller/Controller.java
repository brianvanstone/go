package tech.notpaper.go.controller;

import tech.notpaper.go.messaging.entities.*;
import tech.notpaper.go.messaging.entities.complex.GoList;
import tech.notpaper.go.messaging.entities.simple.Color;
import tech.notpaper.go.messaging.entities.simple.GoMove;
import tech.notpaper.go.messaging.entities.simple.Vertex;

public interface Controller {
	public GoResponse protocolVersion();
	public GoResponse name();
	public GoResponse version();
	public GoResponse knownCommand(String commandName);
	public GoResponse listCommands();
	public GoResponse quit();
	public GoResponse boardsize(int size);
	public GoResponse clearBoard();
	public GoResponse komi(float newKomi);
	public GoResponse fixedHandicap(int numberOfStones);
	public GoResponse placeFreeHandicap(int numberOfStones);
	public GoResponse setFreeHandicap(GoList<Vertex> vertices);
	public GoResponse play(GoMove move);
	public GoResponse genMove(Color color);
	public GoResponse undo();
	public GoResponse timeSettings(int mainTime, int byoYomiTime, int byoYomiStones);
	public GoResponse timeLeft(Color color, int time, int stones);
	public GoResponse finalScore(String score);
	public GoResponse finalStatusList(String status);
	public GoResponse loadsgf(String filename, int moveNumber);
	public GoResponse regGenMove(Color color);
	public GoResponse showBoard();
}
