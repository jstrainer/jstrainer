package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.Blacklist;
import com.github.jstrainer.filter.BlacklistFilter;
import com.github.jstrainer.filter.Filter;

public class BlacklistFilterTest {

	private final Filter<Blacklist, String> filter = new BlacklistFilter();

	private final Blacklist annotation = AnnotationFactory.getBlacklist(new String[] { "inList" });

	@ParameterizedTest
	@MethodSource("arguments")
	public void testFilter(String input, String output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of(
				Arguments.of("inList", null), 
				Arguments.of("notInList", "notInList"), 
				Arguments.of(null, null));
	}

}
