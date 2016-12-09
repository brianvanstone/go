package tech.notpaper.go.controller.commands;

import tech.notpaper.go.messaging.entities.GoCommand;
import tech.notpaper.go.messaging.entities.complex.GoList;
import tech.notpaper.go.messaging.entities.simple.Color;
import tech.notpaper.go.messaging.entities.simple.Move;
import tech.notpaper.go.messaging.entities.simple.Vertex;

public class Commands {
	
	public static GoCommand protocolVersion() {
		return new GoCommand("protocol_version");
	}
	
	public static GoCommand name() {
		return new GoCommand("name");
	}
	
	public static GoCommand version() {
		return new GoCommand("version");
	}
	
	public static GoCommand knownCommand(String commandName) {
		return new GoCommand("known_command " + commandName);
	}
	
	public static GoCommand listCommands() {
		return new GoCommand("list_commands");
	}
	
	public static GoCommand quit() {
		return new GoCommand("quit");
	}
	
	public static GoCommand boardsize(int id, int size) {
		return new GoCommand(id + " boardsize " + size);
	}
	
	public static GoCommand clearBoard(int id) {
		return new GoCommand(id + " clear_board ");
	}
	
	public static GoCommand komi(int id, float newKomi) {
		return new GoCommand(id + " komi " + Float.toString(newKomi));
	}
	
	public static GoCommand fixedHandicap(int id, int numberOfStones) {
		return new GoCommand(id + " fixed_handicap " + numberOfStones);
	}
	
	public static GoCommand placeFreeHandicap(int id, int numberOfStones) {
		return new GoCommand(id + " place_free_handicap " + numberOfStones);
	}
	
	public static GoCommand setFreeHandicap(int id, GoList<Vertex> vertices) {
		return new GoCommand(id + " set_free_handicap " + vertices);
	}
	
	public static GoCommand play(int id, Move move) {
		return new GoCommand(id + " play " + move);
	}
	
	public static GoCommand genMove(int id, Color color) {
		return new GoCommand(id + " genmove " + color);
	}
	
	public static GoCommand undo(int id) {
		return new GoCommand(id + " undo");
	}
	
	public static GoCommand timeSettings(int id, int mainTime, int byoYomiTime, int byoYomiStones) {
		return new GoCommand(id + " time_settings " + mainTime + " " + byoYomiTime + " " + byoYomiStones);
	}
	
	public static GoCommand timeLeft(int id, Color color, int time, int stones) {
		return new GoCommand(id + " time_left " + color + " " + time + " " + stones);
	}
	
	public static GoCommand finalScore(int id, String score) {
		return new GoCommand(id + " final_score " + score);
	}
	
	public static GoCommand finalStatusList(int id, String status) {
		return new GoCommand(id + " final_status_list " + status);
	}
	
	public static GoCommand loadsgf(int id, String filename, int moveNumber) {
		return new GoCommand(id + " loadsgf " + filename + " " + moveNumber);
	}
	
	public static GoCommand regGenMove(int id, Color color) {
		return new GoCommand(id + " reg_genmove " + color);
	}
	
	public static GoCommand showBoard(int id) {
		return new GoCommand(id + " showboard");
	}
}
