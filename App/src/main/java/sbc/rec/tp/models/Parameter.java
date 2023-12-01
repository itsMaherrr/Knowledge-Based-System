package sbc.rec.tp.models;

public class Parameter implements Cloneable {
	
	public Parameter(String value, Type type, DataType dataType) {
		this.value = value;
		this.type = type;
		this.dataType = dataType;
	}
	
	private Type type;
	private String value;
	private DataType dataType;
	
	public static enum Type {
		CONSTANT, VARIABLE, ANY;
	}
	
	public static enum DataType {
		ALPHABETIC, NUMERIC;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	
	public boolean canUnify() {
		return getType() == Type.VARIABLE || getType() == Type.ANY;
	}
	
	public void unify(String value) {
		setType(Type.CONSTANT);
		setValue(value);
	}
	
	public boolean equals(Parameter parameter) {
		return getValue().equals(parameter.getValue()) && getType().equals(parameter.getType());
	}

}
