package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.Filter;
import com.github.jstrainer.filter.Prefix;
import com.github.jstrainer.filter.PrefixFilter;

public class PrefixFilterTest {

	private final Filter<Prefix, String> filter = new PrefixFilter();

	private final Prefix annotation = AnnotationFactory.getPrefix("prefix_");

	@ParameterizedTest
	@MethodSource("arguments")
	public void testBasic(String input, String output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of(
			Arguments.of("string", "prefix_string"), 
			Arguments.of("", "prefix_"),
			Arguments.of(null, null)
		);
	}

}
