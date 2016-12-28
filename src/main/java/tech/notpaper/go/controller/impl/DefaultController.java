package tech.notpaper.go.controller.impl;

import tech.notpaper.go.controller.Controller;
import tech.notpaper.go.controller.commands.Commands;
import tech.notpaper.go.engine.Engine;
import tech.notpaper.go.messaging.entities.GoResponse;
import tech.notpaper.go.messaging.entities.complex.GoList;
import tech.notpaper.go.messaging.entities.simple.Color;
import tech.notpaper.go.messaging.entities.simple.GoMove;
import tech.notpaper.go.messaging.entities.simple.Vertex;

public class DefaultController implements Controller {
	
	private IdGenerator id;
	private Engine engine;
	
	public DefaultController(Engine engine) {
		id = new IdGenerator();
		this.engine = engine;
	}

	@Override
	public GoResponse protocolVersion() {
		return new GoResponse(engine.accept(Commands.protocolVersion().toString()));
	}

	@Override
	public GoResponse name() {
		return new GoResponse(engine.accept(Commands.name().toString()));
	}

	@Override
	public GoResponse version() {
		return new GoResponse(engine.accept(Commands.version().toString()));
	}

	@Override
	public GoResponse knownCommand(String commandName) {
		return new GoResponse(engine.accept(Commands.knownCommand(commandName).toString()));
	}

	@Override
	public GoResponse listCommands() {
		return new GoResponse(engine.accept(Commands.listCommands().toString()));
	}

	@Override
	public GoResponse quit() {
		return new GoResponse(engine.accept(Commands.listCommands().toString()));
	}

	@Override
	public GoResponse boardsize(int size) {
		return new GoResponse(engine.accept(Commands.boardsize(id.next(), size).toString()));
	}

	@Override
	public GoResponse clearBoard() {
		return new GoResponse(engine.accept(Commands.clearBoard(id.next()).toString()));
	}
	
	@Override
	public GoResponse komi(float newKomi) {
		return new GoResponse(engine.accept(Commands.komi(id.next(), newKomi).toString()));
	}

	@Override
	public GoResponse fixedHandicap(int numberOfStones) {
		return new GoResponse(engine.accept(Commands.fixedHandicap(id.next(), numberOfStones).toString()));
	}

	@Override
	public GoResponse placeFreeHandicap(int numberOfStones) {
		return new GoResponse(engine.accept(Commands.placeFreeHandicap(id.next(), numberOfStones).toString()));
	}

	@Override
	public GoResponse setFreeHandicap(GoList<Vertex> vertices) {
		return new GoResponse(engine.accept(Commands.setFreeHandicap(id.next(), vertices).toString()));
	}

	@Override
	public GoResponse play(GoMove move) {
		return new GoResponse(engine.accept(Commands.play(id.next(), move).toString()));
	}

	@Override
	public GoResponse genMove(Color color) {
		return new GoResponse(engine.accept(Commands.genMove(id.next(), color).toString()));
	}

	@Override
	public GoResponse undo() {
		return new GoResponse(engine.accept(Commands.undo(id.next()).toString()));
	}

	@Override
	public GoResponse timeSettings(int mainTime, int byoYomiTime, int byoYomiStones) {
		return new GoResponse(engine.accept(Commands.timeSettings(id.next(), mainTime, byoYomiTime, byoYomiStones).toString()));
	}

	@Override
	public GoResponse timeLeft(Color color, int time, int stones) {
		return new GoResponse(engine.accept(Commands.timeLeft(id.next(), color, time, stones).toString()));
	}

	@Override
	public GoResponse finalScore(String score) {
		return new GoResponse(engine.accept(Commands.finalScore(id.next(), score).toString()));
	}

	@Override
	public GoResponse finalStatusList(String status) {
		return new GoResponse(engine.accept(Commands.finalStatusList(id.next(), status).toString()));
	}

	@Override
	public GoResponse loadsgf(String filename, int moveNumber) {
		return new GoResponse(engine.accept(Commands.loadsgf(id.next(), filename, moveNumber).toString()));
	}

	@Override
	public GoResponse regGenMove(Color color) {
		return new GoResponse(engine.accept(Commands.regGenMove(id.next(), color).toString()));
	}

	@Override
	public GoResponse showBoard() {
		return new GoResponse(engine.accept(Commands.showBoard(id.next()).toString()));
	}
	
	private class IdGenerator {
		
		private int i;
		
		public IdGenerator() {
			i = 1;
		}
		
		public int next() {
			return i++;
		}
	}
}
