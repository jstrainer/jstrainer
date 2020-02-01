package com.github.jstrainer.filter;

import org.apache.commons.lang3.StringUtils;

public class PrefixFilter implements Filter<Prefix, String> {

	@Override
	public String filter(String value, Prefix annotation) {
		if (value == null) {
			return value;
		}

		if (annotation.ifNotPresent()) {
			return StringUtils.prependIfMissing(value, annotation.value());
		}

		return annotation.value().concat(value);
	}

}
