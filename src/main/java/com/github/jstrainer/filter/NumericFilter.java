package com.github.jstrainer.filter;

public class NumericFilter implements Filter<Numeric, String> {

	@Override
	public String filter(String value, Numeric annotation) {
		if (value == null) {
			return value;
		}

		return value.replaceAll("\\D+", "");
	}

}
