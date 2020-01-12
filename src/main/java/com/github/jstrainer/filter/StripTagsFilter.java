package com.github.jstrainer.filter;

public class StripTagsFilter implements Filter<StripTags, String> {

	@Override
	public String filter(String value, StripTags annotation) {
		if (value == null) {
			return value;
		}

		int start = value.indexOf('<');
		int end;
		while (start != -1) {
			end = value.indexOf('>', start);
			value = value.substring(0, start) + (end != -1 ? value.substring(end + 1) : "");
			start = value.indexOf('<');
		}

		return value;
	}

}
