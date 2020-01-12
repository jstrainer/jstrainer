package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.Filter;
import com.github.jstrainer.filter.StripNewlines;
import com.github.jstrainer.filter.StripNewlinesFilter;

public class StripNewlinesFilterTest {

	private final Filter<StripNewlines, String> filter = new StripNewlinesFilter();

	private final StripNewlines annotation = AnnotationFactory.getStripNewlines();
	
	@ParameterizedTest
	@MethodSource("arguments")
	public void testBasic(String input, String output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
	    return Stream.of(
			Arguments.of("\nLorem ipsum\n dolor\r sit amet\r", "Lorem ipsum dolor sit amet"),
			Arguments.of(null, null)
	    );
	}
}
