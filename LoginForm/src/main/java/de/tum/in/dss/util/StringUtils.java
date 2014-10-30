package de.tum.in.dss.util;

public final class StringUtils {
	public static final boolean isEmpty(String input) {
		if (null == input) {
			return true;
		} else if (input.equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}
}
