package com.github.jstrainer.filter;

import org.apache.commons.lang3.StringUtils;

public class SuffixFilter implements Filter<Suffix, String> {

	@Override
	public String filter(String value, Suffix annotation) {
		if (value == null) {
			return value;
		}

		if (annotation.ifNotPresent()) {
			return StringUtils.appendIfMissing(value, annotation.value());
		}

		return value.concat(annotation.value());
	}

}
