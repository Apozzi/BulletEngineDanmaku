package Utils;

import java.lang.reflect.Array;

public class ObjectRecicler<T> {
	private T[] objects;
	private int length = 0;
	private int size = 0;
	private Class<T> clazz;

	public ObjectRecicler(Class<T> clazz,int size) {
		super();
		this.clazz = clazz;
		this.size = size;
		objects = (T[])Array.newInstance(clazz, size+2);
	}

	public T getNewObject() {
		length++;
		if (length > size) {
			return objects[length % size]; 
		} else {
			try {
				objects[length-1] =  clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return objects[length-1];
		}
	}

	public T[] getObjects() {
		return objects;
	}
	
	public int getLength() {
		return length % size;
	}
	
}
