package com.github.jstrainer.filter;

import java.util.Arrays;

public class BlacklistFilter implements Filter<Blacklist, String> {

	@Override
	public String filter(String value, Blacklist annotation) {
		if (value == null || !Arrays.asList(annotation.list()).contains(value)) {
			return value;
		}

		return null;
	}

}
