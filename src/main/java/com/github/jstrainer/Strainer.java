package com.github.jstrainer;

import static java.util.stream.Collectors.toList;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jstrainer.filter.Filter;
import com.github.jstrainer.filter.FilterFactory;

public class Strainer {

	private static final Logger logger = LoggerFactory.getLogger(Strainer.class);

	public void filter(final Object object) {
		logger.debug("Filtering object: {}", object.getClass());

		final Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			filterField(object, field);
		}
	}

	private void filterField(final Object object, final Field field) {
		if (field.getAnnotation(Filtered.class) != null) {
			filterChild(object, field);
			return;
		}

		final List<Annotation> annotations = getAnnotations(field);
		if (annotations.isEmpty()) {
			return;
		}

		annotations.forEach(annotation -> applyFilters(object, field, annotation));
	}

	private void filterChild(final Object object, final Field field) {
		field.setAccessible(true);

		try {
			filter(field.get(object));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.warn("An error occurred while applying filters on field {}", field.getName(), e);
		}

		field.setAccessible(false);
	}

	private void applyFilters(final Object object, final Field field, final Annotation annotation) {
		logger.debug("Filtering field: {}.{}", object.getClass(), field.getName());

		field.setAccessible(true);

		getFilters(annotation).forEach(filter -> setNewValue(object, field, annotation, filter));

		field.setAccessible(false);
	}

	private void setNewValue(final Object object, final Field field, final Annotation annotation, final Filter filter) {
		try {
			final Object oldValue = field.get(object);
			final Object newValue = filter.filter(oldValue, annotation);

			field.set(object, newValue);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.warn("An error occurred while applying the filter {} on field {}", filter.getClass(),
					field.getName(), e);
		}
	}

	private List<Filter> getFilters(final Annotation annotation) {
		final FilteredBy filteredBy = annotation.annotationType().getAnnotation(FilteredBy.class);

		return Stream.of(filteredBy.value()).map(FilterFactory::getFilter).collect(toList());
	}

	private List<Annotation> getAnnotations(final Field field) {
		return Arrays.stream(field.getAnnotations())
				.filter(a -> a.annotationType().isAnnotationPresent(FilteredBy.class)).collect(toList());
	}

}
