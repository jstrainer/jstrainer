package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.RoundUp;
import com.github.jstrainer.filter.RoundUpFilter;

public class RoundUpFilterTest {

	private RoundUpFilter filter = new RoundUpFilter();
	
	private RoundUp annotation = AnnotationFactory.getRoundUp(2);

	@ParameterizedTest
	@MethodSource("arguments")
	public void testFilter(Double input, Double output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
	    return Stream.of(
			Arguments.of(1.50499, 1.51),
			Arguments.of(1.50501, 1.51),
			Arguments.of(null, null)
	    );
	}

}
