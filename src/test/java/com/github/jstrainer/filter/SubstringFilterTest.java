package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;

public class SubstringFilterTest {

	private final SubstringFilter filter = new SubstringFilter();

	@ParameterizedTest
	@MethodSource("arguments")
	public void testFilter(String input, int start, int end, String output) {
		final Substring annotation = AnnotationFactory.getSubstring(start, end);

		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of( // @formatter:off
			Arguments.of("abcde", 0, 2, "ab"),
			Arguments.of("abcde", 2, 4, "cd"),
			Arguments.of("abcde", 1, 0, ""),
			Arguments.of("abcde", -4, -3, "b"),
			Arguments.of("abcde", -4, 3, "bc")
		); // @formatter:on
	}

}
