package com.github.jstrainer.filter;

public class AlphaFilter implements Filter<Alpha, String> {

	private static final String LATIN_RANGE = "a-zA-Z";

	private static final String ACCENT_RANGE = "À-ú";

	private static final String SPACE = "\\s";

	@Override
	public String filter(String value, Alpha annotation) {
		if (value == null) {
			return value;
		}

		String characters = LATIN_RANGE;

		if (annotation.allowAccents()) {
			characters += ACCENT_RANGE;
		}

		if (annotation.allowSpace()) {
			characters += SPACE;
		}

		return value.replaceAll("[^" + characters + "]", "");
	}
}
