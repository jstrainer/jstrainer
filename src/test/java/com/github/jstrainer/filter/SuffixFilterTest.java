package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.Filter;
import com.github.jstrainer.filter.Suffix;
import com.github.jstrainer.filter.SuffixFilter;

public class SuffixFilterTest {

	private final Filter<Suffix, String> filter = new SuffixFilter();

	private final Suffix annotation = AnnotationFactory.getSuffix("_suffix");

	@ParameterizedTest
	@MethodSource("arguments")
	public void testBasic(String input, String output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of(
			Arguments.of("string", "string_suffix"), 
			Arguments.of("", "_suffix"),
			Arguments.of(null, null)
		);
	}

}
