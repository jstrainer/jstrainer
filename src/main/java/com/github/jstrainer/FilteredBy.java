package com.github.jstrainer;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.github.jstrainer.filter.Filter;

@Documented
@Target({ ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface FilteredBy {

	Class<? extends Filter<?, ?>>[] value();

}
