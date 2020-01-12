package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.Filter;
import com.github.jstrainer.filter.Numeric;
import com.github.jstrainer.filter.NumericFilter;

public class NumericFilterTest {

	private Filter<Numeric, String> filter = new NumericFilter();
	
	private Numeric annotation = AnnotationFactory.getNumeric();

	@ParameterizedTest
	@MethodSource("arguments")
	public void testBasic(String input, String output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
	    return Stream.of(
			Arguments.of("1$2@3.45%6-7*8|9", "123456789"),
			Arguments.of("123\n456\r789", "123456789"),
			Arguments.of("Abc", ""),
			Arguments.of(null, null)
	    );
	}
}
