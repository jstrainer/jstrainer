package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.Alphanum;
import com.github.jstrainer.filter.AlphanumFilter;
import com.github.jstrainer.filter.Filter;

public class AlphanumFilterTest {

	private final Filter<Alphanum, String> filter = new AlphanumFilter();

	@ParameterizedTest
	@MethodSource("arguments")
	public void testFilter(String input, String output, boolean allowSpace) {
		final Alphanum annotation = AnnotationFactory.getAlphanum(allowSpace);
		
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of(
				Arguments.of("A1B2B3", "A1B2B3", false), 
				Arguments.of("A1B2B3", "A1B2B3", true),
				Arguments.of("A1 B2 B3", "A1B2B3", false), 
				Arguments.of("A1 B2 B3", "A1 B2 B3", true),
				Arguments.of("A1*B2%B3", "A1B2B3", false), 
				Arguments.of(null, null, false));
	}
}
