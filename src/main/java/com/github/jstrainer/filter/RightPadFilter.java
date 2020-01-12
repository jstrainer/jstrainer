package com.github.jstrainer.filter;

import org.apache.commons.lang3.StringUtils;

public class RightPadFilter implements Filter<RightPad, String> {

	@Override
	public String filter(String value, RightPad annotation) {
		if (value == null || annotation.length() == 0) {
			return value;
		}

		return StringUtils.rightPad(value, annotation.length(), annotation.padChar());
	}

}
