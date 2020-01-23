package com.github.jstrainer.filter;

public class PrefixFilter implements Filter<Prefix, String> {

	@Override
	public String filter(String value, Prefix annotation) {
		if (value == null) {
			return value;
		}

		return annotation.value().concat(value);
	}

}
