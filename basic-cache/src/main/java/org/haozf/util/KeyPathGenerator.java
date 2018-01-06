package org.haozf.util;

public class KeyPathGenerator {

	public String generate() {
		String className = Thread.currentThread().getStackTrace()[3].getClassName();
		String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
		return className + "." + methodName;
	}

}
