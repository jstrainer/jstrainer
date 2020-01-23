package com.github.jstrainer.filter;

public class StripNewlinesFilter implements Filter<StripNewlines, String> {

	@Override
	public String filter(String value, StripNewlines annotation) {
		if (value == null) {
			return value;
		}

		return value.replace("\n", "").replace("\r", "");
	}

}
