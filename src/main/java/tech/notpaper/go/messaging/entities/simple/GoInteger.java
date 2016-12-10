package tech.notpaper.go.messaging.entities.simple;

public class GoInteger extends Number implements SimpleEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1932960996236894233L;
	private Integer wrappedInteger;
	
	public GoInteger(int value) {
		this.wrappedInteger = Integer.valueOf(value);
	}
	
	public GoInteger(Integer value) {
		this.wrappedInteger = value;
	}

	@Override
	public int intValue() {
		return wrappedInteger.intValue();
	}

	@Override
	public long longValue() {
		return wrappedInteger.longValue();
	}

	@Override
	public float floatValue() {
		return wrappedInteger.floatValue();
	}

	@Override
	public double doubleValue() {
		return wrappedInteger.doubleValue();
	}
	
	@Override
	public Type getType() {
		return SimpleEntity.Type.INT;
	}
	
	@Override
	public String toString() {
		return wrappedInteger.toString();
	}
}
