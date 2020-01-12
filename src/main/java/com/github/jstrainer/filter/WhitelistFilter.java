package com.github.jstrainer.filter;

import java.util.Arrays;

public class WhitelistFilter implements Filter<Whitelist, String> {

	@Override
	public String filter(String value, Whitelist annotation) {
		if (value == null || Arrays.asList(annotation.list()).contains(value)) {
			return value;
		}

		return null;
	}

}
