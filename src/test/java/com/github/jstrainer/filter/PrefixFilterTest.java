package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;

public class PrefixFilterTest {

	private final PrefixFilter filter = new PrefixFilter();

	@ParameterizedTest
	@MethodSource("arguments")
	public void testFilter(String input, String output, boolean ifNotPresent) {
		final Prefix annotation = AnnotationFactory.getPrefix("prefix_", ifNotPresent);
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of( // @formatter:off
			Arguments.of("string", "prefix_string", true), 
			Arguments.of("prefix_string", "prefix_string", true), 
			Arguments.of("prefix_string", "prefix_prefix_string", false), 
			Arguments.of("", "prefix_", true),
			Arguments.of(null, null, true)
		); // @formatter:on
	}

}
