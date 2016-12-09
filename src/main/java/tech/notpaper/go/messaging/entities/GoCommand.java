package tech.notpaper.go.messaging.entities;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import tech.notpaper.go.messaging.entities.complex.GoList;
import tech.notpaper.go.messaging.entities.complex.ListEntity;
import tech.notpaper.go.messaging.entities.simple.GoString;
import tech.notpaper.go.messaging.entities.simple.SimpleEntity;

public class GoCommand implements GoMessage {
	
	private static final Pattern ID_PATTERN = Pattern.compile("\\d+");
	
	private SimpleEntity id;
	private SimpleEntity command;
	private GoList<ListEntity> args;
	
	public GoCommand(String s) {
		List<String> tokens = Arrays.asList(s.split(" "));
		this.args = new GoList<>(new LinkedList<>());
		if(ID_PATTERN.matcher(tokens.get(0)).matches()) {
			tokens.set(tokens.size()-1, tokens.get(tokens.size()-1).trim());
			this.id = new GoString(tokens.get(0));
			this.command = new GoString(tokens.get(1));
			
			if (tokens.size() > 2) {
				this.args = new GoList<>(tokens.subList(2, tokens.size()));
			}
		} else {
			this.id = null;
			this.command = new GoString(tokens.get(0));
			if (tokens.size() > 1) {
				this.args = new GoList<>(tokens.subList(1, tokens.size()));
			}
		}
	}
	
	public SimpleEntity getId() {
		return this.id;
	}
	
	public SimpleEntity getCommand() {
		return this.command;
	}
	
	public GoList<ListEntity> getArgs() {
		return this.args;
	}
	
	@Override
	public String toString() {
		return (id == null ? "" : (id.toString() + " ")) + command.toString() + (args.isEmpty() ? "" : " " + args.toString()) + "\n";
	}
}
