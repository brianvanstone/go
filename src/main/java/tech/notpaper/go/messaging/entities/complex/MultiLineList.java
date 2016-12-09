package tech.notpaper.go.messaging.entities.complex;

import java.util.LinkedList;
import java.util.List;

public class MultiLineList implements ComplexEntity {
	private List<GoList<ListEntity>> lists;
	
	public MultiLineList() {
		this.lists = new LinkedList<>();
	}
	
	public MultiLineList(String s) {
		lists = new LinkedList<>();
		
		for (String line : s.split("\\n")) {
			lists.add(new GoList<>(line));
		}
	}
	
	public void addLine(String line) {
		this.lists.add(new GoList<>(line));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (GoList<ListEntity> list : lists) {
			sb.append(list.toString());
		}
		sb.append("\n");
		return sb.toString();
	}
}
