package com.github.jstrainer.filter;

public class ToUpperCaseFilter implements Filter<ToUpperCase, String> {

	@Override
	public String filter(String value, ToUpperCase annotation) {
		if (value == null) {
			return value;
		}

		return value.toUpperCase();
	}

}
