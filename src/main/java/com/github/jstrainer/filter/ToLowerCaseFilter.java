package com.github.jstrainer.filter;

public class ToLowerCaseFilter implements Filter<ToLowerCase, String> {

	@Override
	public String filter(String value, ToLowerCase annotation) {
		if (value == null) {
			return value;
		}

		return value.toLowerCase();
	}

}
