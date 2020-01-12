package com.github.jstrainer.filter;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.jstrainer.AnnotationFactory;
import com.github.jstrainer.filter.RightPad;
import com.github.jstrainer.filter.RightPadFilter;

public class RightPadFilterTest {

	private final RightPadFilter filter = new RightPadFilter();

	@ParameterizedTest
	@MethodSource("arguments")
	public void testBasic(String input, String output, char padChar) {
		RightPad annotation = AnnotationFactory.getRightPad(10, padChar);
		
		Assertions.assertEquals(output, filter.filter(input, annotation));
	}
	
	@Test
	public void testWithEmptyLength() {
		RightPad annotation = AnnotationFactory.getRightPad(0, '0');
		
		Assertions.assertEquals("1", filter.filter("1", annotation));
	}


	private static Stream<Arguments> arguments() {
		return Stream.of(
			Arguments.of("1234567890", "1234567890", '0'), 
			Arguments.of("123", "1230000000", '0'),
			Arguments.of("0123", "0123000000", '0'), 
			Arguments.of("", "0000000000", '0'), 
			Arguments.of("BBB", "BBBAAAAAAA", 'A'),
			Arguments.of(null, null, '0')
		);
	}

}
