package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;

public class StripTagsFilterTest {

	private final StripTagsFilter filter = new StripTagsFilter();

	private final StripTags annotation = AnnotationFactory.getStripTags();

	@ParameterizedTest
	@MethodSource("arguments")
	public void testFilter(String input, String output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of(Arguments.of("<a href=\"example.com\">foo</a>", "foo"),
				Arguments.of("<a href=\"http://example.com\" Some Text", ""),
				Arguments.of("<a href=\"example.com\">foo</a><b>bar</b>", "foobar"),
				Arguments.of("äöü<!--üßüßüß-->äöü", "äöüäöü"),
				Arguments.of("text<!-- not closed comment at the end", "text"), 
				Arguments.of(null, null));
	}
}
