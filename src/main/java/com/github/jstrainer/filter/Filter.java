package com.github.jstrainer.filter;

import java.lang.annotation.Annotation;

public interface Filter<A extends Annotation, T> {

	T filter(T value, A annotation);

}
