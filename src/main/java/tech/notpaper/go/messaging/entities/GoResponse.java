package tech.notpaper.go.messaging.entities;

import tech.notpaper.go.messaging.entities.complex.MultiLineList;
import tech.notpaper.go.messaging.entities.simple.GoString;
import tech.notpaper.go.messaging.entities.simple.SimpleEntity;

public class GoResponse implements GoMessage {
	private MultiLineList lines;
	
	private Status responseStatus;
	private SimpleEntity id;
	
	public GoResponse() {
		this.lines = new MultiLineList();
		this.id = new GoString("");
		this.responseStatus = Status.PASS;
	}
	
	public GoResponse(String s) {
		if(s == null || s.trim().isEmpty()) {
			this.responseStatus = Status.PASS;
		} else {
			this.responseStatus = String.valueOf(s.charAt(0)).equals(Status.FAIL.toString()) ? Status.FAIL : Status.PASS;
		}
		lines = new MultiLineList(s);
	}
	
	public GoResponse setId(SimpleEntity id) {
		this.id = id;
		return this;
	}
	
	public SimpleEntity getId() {
		return this.id == null ? new GoString("") : this.id;
	}
	
	public void addLine(String line) {
		lines.addLine(line);
	}
	
	public void setStatus(Status s) {
		this.responseStatus = s;
	}
	
	public Status getStatus() {
		return this.responseStatus;
	}
	
	public enum Status {
		PASS, FAIL;
		
		@Override
		public String toString() {
			switch(this) {
			case FAIL:
				return "?";
			default:
				return "=";
			}
		}
	}
	
	@Override
	public String toString() {
		String content = lines.toString().trim();
		if(content.startsWith("=") || content.startsWith("?")) {
			content = content.replaceAll("^[\\=\\?]+", "");
		}
		
		if (content.trim().isEmpty()) {
			return "=" + this.getId().toString() + "\n\n";
		} else {
			if(this.getId().toString().isEmpty()) {
				return this.getStatus() + content + "\n\n";
			} else {
				return this.getStatus() + this.getId().toString() + " " + content + "\n\n";
			}
		}
	}
}
