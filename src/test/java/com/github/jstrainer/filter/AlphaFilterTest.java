package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.Alpha;
import com.github.jstrainer.filter.AlphaFilter;
import com.github.jstrainer.filter.Filter;

public class AlphaFilterTest {

	private static final String ACCENTS = "áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ";
	private Filter<Alpha, String> filter = new AlphaFilter();

	@ParameterizedTest
	@MethodSource("arguments")
	public void testFilter(String input, String output, boolean allowSpace) {
		Alpha annotation = AnnotationFactory.getAlpha(allowSpace, false);
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	@Test
	public void testWithAllowedAcents() {
		Alpha annotation = AnnotationFactory.getAlpha(false, true);
		Assertions.assertEquals(ACCENTS, filter.filter(ACCENTS, annotation));
	}

	@Test
	public void testWithNotAllowedAcents() {
		Alpha annotation = AnnotationFactory.getAlpha(false, false);
		Assertions.assertEquals("", filter.filter(ACCENTS, annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of(
			Arguments.of("1A$B0C", "ABC", false), 
			Arguments.of("1A$ B 0C", "A B C", true),
			Arguments.of(null, null, false)
		);
	}
}
