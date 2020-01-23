package com.github.jstrainer.filter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.jstrainer.FilteredBy;

@Documented
@FilteredBy(LeftPadFilter.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LeftPad {

	int length();

	char padChar() default '0';

}
