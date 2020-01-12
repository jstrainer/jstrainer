package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.Round;
import com.github.jstrainer.filter.RoundFilter;

public class RoundFilterTest {

	private RoundFilter filter = new RoundFilter();
	
	private Round annotation = AnnotationFactory.getRound(2);

	@ParameterizedTest
	@MethodSource("arguments")
	public void testBasic(Double input, Double output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
	    return Stream.of(
			Arguments.of(1.50499, 1.50),
			Arguments.of(1.50500, 1.50),
			Arguments.of(1.50501, 1.51),
			Arguments.of(null, null)
	    );
	}

}
