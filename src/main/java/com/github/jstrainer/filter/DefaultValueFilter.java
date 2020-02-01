package com.github.jstrainer.filter;

public class DefaultValueFilter implements Filter<DefaultValue, String> {

	@Override
	public String filter(String value, DefaultValue annotation) {
		return (value == null) ? annotation.value() : value;
	}

}
