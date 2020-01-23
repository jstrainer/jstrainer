package com.github.jstrainer.filter;

import org.apache.commons.lang3.StringUtils;

public class LeftPadFilter implements Filter<LeftPad, String> {

	@Override
	public String filter(String value, LeftPad annotation) {
		if (value == null || annotation.length() == 0) {
			return value;
		}

		return StringUtils.leftPad(value, annotation.length(), annotation.padChar());
	}

}
