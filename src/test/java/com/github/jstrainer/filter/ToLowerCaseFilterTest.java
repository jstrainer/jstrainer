package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.Filter;
import com.github.jstrainer.filter.ToLowerCase;
import com.github.jstrainer.filter.ToLowerCaseFilter;

public class ToLowerCaseFilterTest {

	private final Filter<ToLowerCase, String> filter = new ToLowerCaseFilter();
	
	private final ToLowerCase annotation = AnnotationFactory.getToLower();

	@ParameterizedTest
	@MethodSource("arguments")
	public void testBasic(String input, String output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
	    return Stream.of(
			Arguments.of("StRiNg", "string"),
			Arguments.of("ÂãÉé", "âãéé"),
			Arguments.of(null, null)
	    );
	}
}
