package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.Filter;
import com.github.jstrainer.filter.Trim;
import com.github.jstrainer.filter.TrimFilter;

public class TrimFilterTest {

	private final Filter<Trim, String> filter = new TrimFilter();

	private final Trim annotation = AnnotationFactory.getTrim(Trim.DEFAULT);

	@ParameterizedTest
	@MethodSource("arguments")
	public void testFilter(String input, String output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	@Test
	public void testWithChar() {
		Trim annotation = AnnotationFactory.getTrim("D");
		Assertions.assertEquals("ABC", filter.filter("ABCD", annotation));
	}

	@Test
	public void testWithString() {
		Trim annotation = AnnotationFactory.getTrim("Def");
		Assertions.assertEquals("ABC", filter.filter("ABCDef", annotation));
		Assertions.assertEquals("", filter.filter("Def", annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of(Arguments.of("string", "string"), Arguments.of(" string", "string"),
				Arguments.of("string ", "string"), Arguments.of("string    ", "string"),
				Arguments.of("\nstring\t", "string"), Arguments.of(null, null));
	}
}
