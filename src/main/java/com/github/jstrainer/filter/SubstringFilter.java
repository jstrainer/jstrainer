package com.github.jstrainer.filter;

import org.apache.commons.lang3.StringUtils;

public class SubstringFilter implements Filter<Substring, String> {

	@Override
	public String filter(String value, Substring annotation) {
		return StringUtils.substring(value, annotation.start(), annotation.end());
	}

}
