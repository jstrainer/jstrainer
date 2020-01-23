package com.github.jstrainer.filter;

import org.apache.commons.lang3.StringUtils;

public class ReplaceFilter implements Filter<Replace, String> {

	@Override
	public String filter(String value, Replace annotation) {
		if (value == null || annotation.search() == null || annotation.search().isEmpty()) {
			return value;
		}

		if (annotation.ignoreCase() && annotation.all()) {
			return StringUtils.replaceIgnoreCase(value, annotation.search(), annotation.replacement());
		}

		if (annotation.ignoreCase()) {
			return StringUtils.replaceIgnoreCase(value, annotation.search(), annotation.replacement(), 1);
		}

		if (annotation.all()) {
			return value.replaceAll(annotation.search(), annotation.replacement());
		}

		return value.replace(annotation.search(), annotation.replacement());
	}

}
