package tech.notpaper.go.messaging.entities;

import tech.notpaper.go.messaging.entities.complex.MultiLineList;

public class GoResponse implements GoMessage {
	private MultiLineList lines;
	
	//TODO differentiate between state of the response
	private Status responseStatus;
	
	public GoResponse() {
		this.lines = new MultiLineList();
		this.responseStatus = Status.PASS;
	}
	
	public GoResponse(String s) {
		if(s == null || s.trim().isEmpty()) {
			this.responseStatus = Status.BLANK;
		} else {
			this.responseStatus = String.valueOf(s.charAt(0)).equals(Status.FAIL.toString()) ? Status.FAIL : Status.PASS;
		}
		lines = new MultiLineList(s);
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
		PASS, FAIL, BLANK;
		
		@Override
		public String toString() {
			switch(this) {
			case PASS:
				return "=";
			case BLANK:
				return "";
			default:
				return "?";
			}
		}
	}
	
	@Override
	public String toString() {
		String content = lines.toString().trim();
		String tokenFromStatus = this.responseStatus.toString();
		if(content.startsWith("=") || content.startsWith("?")) {
			content = content.replaceAll("^[\\=\\?]+", "");
		}
		
		content = content + "\n\n";
		
		if (content.trim().isEmpty()) {
			return "";
		} else {
			return this.getStatus() + content;
		}
	}
}
