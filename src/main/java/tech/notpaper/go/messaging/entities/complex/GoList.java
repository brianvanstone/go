package tech.notpaper.go.messaging.entities.complex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import tech.notpaper.go.messaging.entities.simple.GoString;

public class GoList<T extends ListEntity> extends LinkedList<T>  implements ComplexEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3612680701917622853L;
	
	private List<T> entities;
	
	public GoList() {
		this.entities = new LinkedList<>();
	}
	
	@SuppressWarnings("unchecked")
	public GoList(List<String> tokens) {
		this.entities = new LinkedList<>();
		for (String token : tokens) {
			this.entities.add((T)new GoString(token));
		}
	}

	@SuppressWarnings("unchecked")
	public GoList(String s) {
		this.entities = new LinkedList<>();
		
		List<String> tokens = Arrays.asList(s.split(" "));
		//just treat everything as GoString for now. Controller
		//can handle the rest at run time based on rules
		for (String token : tokens) {
			if(token.endsWith("\n")) {
				this.entities.add((T)new GoString(token.substring(0, tokens.size())));
				break;
			}
			this.entities.add((T)new GoString(token));
		}
	}
	
	@Override
	public T get(int index) {
		return (T) this.entities.get(index);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, ListEntity element) {
		this.entities.add(index, (T) new GoString(element.toString()));
	}
	
	public List<T> getEntities() {
		return this.entities;
	}
	
	@Override
	public String toString() {
		return StringUtils.join(this.entities, " ") + "\n";
	}
	
	@Override
	public boolean isEmpty() {
		return this.entities.isEmpty();
	}
}
