package com.github.jstrainer.filter;

public class AlphanumFilter implements Filter<Alphanum, String> {

	@Override
	public String filter(String value, Alphanum annotation) {
		if (value == null) {
			return value;
		}

		if (annotation.allowSpace()) {
			return value.replaceAll("[^a-zA-Z0-9\\s]", "");
		}

		return value.replaceAll("[^a-zA-Z0-9]", "");

	}

}
