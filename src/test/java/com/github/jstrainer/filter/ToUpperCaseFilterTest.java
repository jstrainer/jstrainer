package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.Filter;
import com.github.jstrainer.filter.ToUpperCase;
import com.github.jstrainer.filter.ToUpperCaseFilter;

public class ToUpperCaseFilterTest {

	private final Filter<ToUpperCase, String> filter = new ToUpperCaseFilter();
	
	private final ToUpperCase annotation = AnnotationFactory.getToUpper();

	@ParameterizedTest
	@MethodSource("arguments")
	public void testFilter(String input, String output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
	    return Stream.of(
			Arguments.of("StRiNg", "STRING"),
			Arguments.of("ÂãÉé", "ÂÃÉÉ"),
			Arguments.of(null, null)
	    );
	}
}
