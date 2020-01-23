package com.github.jstrainer.filter;

import org.apache.commons.lang3.StringUtils;

public class TrimFilter implements Filter<Trim, String> {

	@Override
	public String filter(String value, Trim annotation) {
		if (value == null) {
			return value;
		}

		if (Trim.DEFAULT.contentEquals(annotation.stripChar())) {
			return value.trim();
		}

		return StringUtils.strip(value, annotation.stripChar());
	}

}
