package tech.notpaper.go.messaging.entities.complex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import tech.notpaper.go.messaging.entities.simple.GoString;
import tech.notpaper.go.messaging.entities.simple.SimpleEntity;

public class Collection implements ListEntity {
	
	private List<SimpleEntity> entities;
	private int start;
	private int end;
	
	public Collection() {
		entities = new LinkedList<>();
	}
	
	public Collection(String...tokens) {
		int i = 0;
		for(String token : tokens) {
			if(SimpleEntity.isSimpleEntity(token)) {
				this.add(new GoString(token));
				i++;
			} else {
				break;
			}
		}
		this.start = 0;
		this.end = i;
	}
	
	public int getStart() {
		return this.start;
	}
	
	public int getEnd() {
		return this.end;
	}
	
	public void add(SimpleEntity entity) {
		this.entities.add(entity);
	}
	
	public List<SimpleEntity> getEntities() {
		return entities;
	}
	
	@Override
	public String toString() {
		return StringUtils.join(entities, " ");
	}
}
