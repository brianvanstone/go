package tech.notpaper.go.messaging.entities.simple;

import tech.notpaper.go.messaging.entities.complex.ListEntity;

public class GoString implements CharSequence, SimpleEntity, ListEntity, Comparable<GoString> {
	
	private String wrappedString;
	
	public GoString(String s) {
		if (s == null || s.trim().isEmpty()) {
			this.wrappedString = s;
		}
		
		this.wrappedString = s;
	}

	@Override
	public int length() {
		return wrappedString.length();
	}

	@Override
	public char charAt(int index) {
		return wrappedString.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return wrappedString.subSequence(start, end);
	}

	@Override
	public int compareTo(GoString o) {
		return this.wrappedString.compareTo(o.toString());
	}
	
	@Override
	public String toString() {
		return wrappedString.toString();
	}
	
	@Override
	public Type getType() {
		return SimpleEntity.Type.STRING;
	}
}
