package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.Filter;
import com.github.jstrainer.filter.Whitelist;
import com.github.jstrainer.filter.WhitelistFilter;

public class WhitelistFilterTest {

	private final Filter<Whitelist, String> filter = new WhitelistFilter();

	private final Whitelist annotation = AnnotationFactory.getWhitelist(new String[] { "inList" });

	@ParameterizedTest
	@MethodSource("arguments")
	public void testBasic(String input, String output) {
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of(
			Arguments.of("inList", "inList"), 
			Arguments.of("notInList", null), 
			Arguments.of(null, null)
		);
	}

}
