package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.LeftPad;
import com.github.jstrainer.filter.LeftPadFilter;

public class LeftPadFilterTest {

	private final LeftPadFilter filter = new LeftPadFilter();

	@ParameterizedTest
	@MethodSource("arguments")
	public void testFilter(String input, String output, char padChar) {
		LeftPad annotation = AnnotationFactory.getPadLeft(10, padChar);
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}
	
	@Test
	public void testWithEmptyLength() {
		LeftPad annotation = AnnotationFactory.getPadLeft(0, '0');
		Assertions.assertEquals("1", filter.filter("1", annotation));
	}

	private static Stream<Arguments> arguments() {
		return Stream.of(
			Arguments.of("1234567890", "1234567890", '0'), 
			Arguments.of("123", "0000000123", '0'),
			Arguments.of("0123", "0000000123", '0'), 
			Arguments.of("", "0000000000", '0'), 
			Arguments.of("123", "AAAAAAA123", 'A'),
			Arguments.of(null, null, '0')
		);
	}

}
