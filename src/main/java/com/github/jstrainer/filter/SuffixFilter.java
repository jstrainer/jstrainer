package com.github.jstrainer.filter;

public class SuffixFilter implements Filter<Suffix, String> {

	@Override
	public String filter(String value, Suffix annotation) {
		if (value == null) {
			return value;
		}

		return value.concat(annotation.value());
	}

}
