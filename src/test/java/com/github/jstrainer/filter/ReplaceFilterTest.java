package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;

public class ReplaceFilterTest {

	private final ReplaceFilter filter = new ReplaceFilter();

	@ParameterizedTest
	@MethodSource("arguments")
	public void testBasicWith(String input, String search, String replacement, String output, boolean ignoreCase, boolean all) {
		final Replace annotation = AnnotationFactory.getReplace(search, replacement, ignoreCase, all);
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
	    return Stream.of( // @formatter:off
			Arguments.of(null, null, null, null, false, true),
			Arguments.of("aabb", "a", "c", "ccbb", false, true),
			Arguments.of("", "a", "c", "", false, true),
			Arguments.of("abc", "", "c", "abc", false, true),
			Arguments.of("abc", null, "c", "abc", false, true),
			Arguments.of("abc", "b", "", "ac", false, true),
			Arguments.of("aBc", "b", "", "aBc", false, true),
			Arguments.of("aBcB", "b", "", "ac", true, true),
			Arguments.of("aBcB", "b", "", "acB", true, false)
	    ); // @formatter:on
	}

}
