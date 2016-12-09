package tech.notpaper.go.engine.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.exception.ExceptionUtils;

import tech.notpaper.go.engine.EnhancedEngine;
import tech.notpaper.go.messaging.entities.GoCommand;
import tech.notpaper.go.messaging.entities.GoResponse;
import tech.notpaper.go.messaging.entities.complex.GoList;
import tech.notpaper.go.messaging.entities.complex.ListEntity;
import tech.notpaper.go.messaging.entities.simple.GoString;
import tech.notpaper.go.messaging.entities.simple.SimpleEntity;

public class DefaultEngineProxy implements EnhancedEngine {
	
	private static List<String> supportedCommands;
	private static boolean debug = true;
	
	static {
		supportedCommands = new LinkedList<String>();
		supportedCommands.add("protocol_version");
		supportedCommands.add("name");
		supportedCommands.add("version");
		supportedCommands.add("list_commands");
		supportedCommands.add("quit");
		supportedCommands.add("boardsize");
		supportedCommands.add("clear_board");
		supportedCommands.add("komi");
		supportedCommands.add("play");
		supportedCommands.add("undo");
		supportedCommands.add("show_board");
	}

	@Override
	public String accept(String s) {
		
		//first scrub the command that you received
		s = scrubCommand(s);
		
		GoCommand goCommand = new GoCommand(s);
		SimpleEntity id = goCommand.getId();
		SimpleEntity command = goCommand.getCommand();
		GoList<ListEntity> args = goCommand.getArgs();
		
		if(debug) {
			System.err.print(goCommand);
		}
		
		try {
			switch(command.toString()) {
			case "protocol_version":
				return this.protocolVersion().toString();
			case "name":
				return this.name().toString();
			case "version":
				return this.version().toString();
			case "known_command":
				return this.knownCommand(args).toString();
			case "list_commands":
				return this.listCommands().toString();
			case "quit":
				return this.quit().toString();
			case "boardsize":
				return this.boardsize(args).toString();
			case "clear_board":
				return this.clearBoard().toString();
			case "komi":
				return this.komi(args).toString();
			case "fixed_handicap":
				return this.fixedHandicap(args).toString();
			case "place_free_handicap":
				return this.placeFreeHandicap(args).toString();
			case "set_free_handicap":
				return this.setFreeHandicap(args).toString();
			case "play":
				return this.play(args).toString();
			case "gen_move":
				return this.genMove(args).toString();
			case "undo":
				return this.undo().toString();
			case "time_settings":
				return this.timeSettings(args).toString();
			case "time_left":
				return this.timeLeft(args).toString();
			case "final_score":
				return this.finalScore(args).toString();
			case "final_status_list":
				return this.finalStatusList(args).toString();
			case "loadsgf":
				return this.loadsgf(args).toString();
			case "reg_gen_move":
				return this.regGenMove(args).toString();
			case "show_board":
				return this.showBoard().toString();
				default:
					throw new IllegalArgumentException("Failed to parse message received by engine [" + s + "]");
			}
		} catch (Exception e) {
			return renderException(e).toString();
		}
	}
	
	private static final Pattern ILLEGAL_PATTERN = Pattern.compile("[^-_ \\tA-Za-z0-9]");
	private static final Pattern CR_PATTERN = Pattern.compile("\r");
	private static final Pattern COMMENT_PATTERN = Pattern.compile("^\\s+#.*");
	private static final Pattern HT_PATTERN = Pattern.compile("\\t");
	private static final Pattern BLANK_LINE_PATTERN = Pattern.compile("^\\s+\\n$");
	private String scrubCommand(String s) {
		s = s.replaceAll("\r", "");
		s = ILLEGAL_PATTERN.matcher(s).replaceAll("");
		s = COMMENT_PATTERN.matcher(s).replaceAll("");
		s = HT_PATTERN.matcher(s).replaceAll("  ");
		s = BLANK_LINE_PATTERN.matcher(s).replaceAll("");
		return s;
	}
	
	private GoResponse renderException(Throwable e) {
		Throwable re = new RuntimeException("An exception was encountered when parsing the command\n" +
				"\tProtocol" + toGoString(this.protocolVersion().toString()).toString() + ", " +
				" Name" + toGoString(this.name().toString()).toString() + ", " +
				" Version" + toGoString(this.version().toString()).toString() + "\n" +
				"Caused by: " + toGoString(e.getMessage()).toString() + "\t", e);
		GoResponse response = new GoResponse(re.getMessage() + "\n" + ExceptionUtils.getStackTrace(re));
		response.setStatus(GoResponse.Status.FAIL);
		return response;
	}
	
	private static GoString toGoString(String s) {
		return s==null || s.trim().isEmpty() ? new GoString("") : new GoString(s.trim());
	}
	
	@Override
	public GoResponse protocolVersion() {
		return new GoResponse("2");
	}
	
	@Override
	public GoResponse name() {
		return new GoResponse("tech.notpaper.go");
	}
	
	@Override
	public GoResponse version() {
		return new GoResponse("0.0.1-SNAPSHOT");
	}

	@Override
	public GoResponse knownCommand(GoList<ListEntity> args) {
		throw new UnsupportedCommandException("known_command");
	}

	@Override
	public GoResponse listCommands() {
		GoResponse response = new GoResponse();
		for (String c : supportedCommands) {
			response.addLine(c);
		}
		return response;
	}

	@Override
	public GoResponse quit() {
		throw new NotYetImplementedException("quit");
	}

	@Override
	public GoResponse boardsize(GoList<ListEntity> args) {
		throw new NotYetImplementedException("boardsize");
	}

	@Override
	public GoResponse clearBoard() {
		throw new NotYetImplementedException("clear_board");
	}

	@Override
	public GoResponse komi(GoList<ListEntity> args) {
		throw new NotYetImplementedException("komi");
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
		throw new UnsupportedCommandException("play");
	}

	@Override
	public GoResponse genMove(GoList<ListEntity> args) {
		throw new NotYetImplementedException("gen_move");
	}

	@Override
	public GoResponse undo() {
		throw new NotYetImplementedException("undo");
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
		throw new NotYetImplementedException("undo");
	}

	public final class UnsupportedCommandException extends UnsupportedOperationException {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 9044760197558384736L;

		public UnsupportedCommandException(String message, Throwable cause) {
			super(message, cause);
		}
		
		public UnsupportedCommandException(String command) {
			super("unsupported command [" + command + "]");
		}
	}
	
	public final class NotYetImplementedException extends UnsupportedOperationException {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3464614784137968017L;

		public NotYetImplementedException(String message, Throwable cause) {
			super(message, cause);
		}
		
		public NotYetImplementedException(String command) {
			super("unsupported command [" + command + "] is not yet implemented");
		}
	}
}
