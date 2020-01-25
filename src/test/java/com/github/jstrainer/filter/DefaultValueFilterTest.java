package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;

public class DefaultValueFilterTest {

	private final DefaultValueFilter filter = new DefaultValueFilter();

	private final DefaultValue annotation = AnnotationFactory.getDefaultValue("abc");

	@ParameterizedTest
	@MethodSource("arguments")
	public void testFilter(String input, String output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of( // @formatter:off
			Arguments.of("def", "def"), 
			Arguments.of(null, "abc")
		); // @formatter:on
	}

}
