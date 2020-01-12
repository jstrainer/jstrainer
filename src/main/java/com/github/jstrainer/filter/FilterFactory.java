package com.github.jstrainer.filter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilterFactory {

	private static final Logger logger = LoggerFactory.getLogger(FilterFactory.class);

	private static Map<Class<? extends Filter<?, ?>>, Filter<?, ?>> filterMap = new HashMap<>();

	public static Filter<?, ?> getFilter(Class<? extends Filter<?, ?>> type) {
		if (filterMap.containsKey(type)) {
			return filterMap.get(type);
		}

		try {
			return type.getConstructor().newInstance();
		} catch (Exception e) {
			logger.error("An error occurred while creating the filter {}", type, e);
			throw new RuntimeException(e);
		}
	}

}
