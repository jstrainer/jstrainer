package com.github.jstrainer.filter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundFilter implements Filter<Round, Double> {

	@Override
	public Double filter(Double value, Round annotation) {
		if (value == null) {
			return null;
		}

		return BigDecimal.valueOf(value).setScale(annotation.scale(), RoundingMode.HALF_EVEN).doubleValue();
	}
}
