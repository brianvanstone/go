package tech.notpaper.go.messaging.entities.simple;

public class GoFloat extends Number implements SimpleEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4799150704333660115L;
	private Float wrappedFloat;
	
	public GoFloat(float f) {
		this.wrappedFloat = Float.valueOf(f);
	}
	
	public GoFloat(Float f) {
		this.wrappedFloat = f;
	}

	@Override
	public int intValue() {
		return wrappedFloat.intValue();
	}

	@Override
	public long longValue() {
		return wrappedFloat.longValue();
	}

	@Override
	public float floatValue() {
		return wrappedFloat.floatValue();
	}

	@Override
	public double doubleValue() {
		return wrappedFloat.doubleValue();
	}
	
	@Override
	public Type getType() {
		return SimpleEntity.Type.FLOAT;
	}
	
	@Override
	public String toString() {
		return wrappedFloat.toString();
	}
}
