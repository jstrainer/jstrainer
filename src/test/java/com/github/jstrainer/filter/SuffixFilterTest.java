package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;

public class SuffixFilterTest {

	private final SuffixFilter filter = new SuffixFilter();

	@ParameterizedTest
	@MethodSource("arguments")
	public void testFilter(String input, String output, boolean ifNotPresent) {
		final Suffix annotation = AnnotationFactory.getSuffix("_suffix", ifNotPresent);
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of( // @formatter:off
			Arguments.of("string", "string_suffix", true), 
			Arguments.of("string_suffix", "string_suffix", true), 
			Arguments.of("string_suffix", "string_suffix_suffix", false), 
			Arguments.of("", "_suffix", true),
			Arguments.of(null, null, true)
		); // @formatter:on
	}

}
