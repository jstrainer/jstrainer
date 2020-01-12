package com.github.jstrainer.filter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundDownFilter implements Filter<RoundDown, Double> {

	@Override
	public Double filter(Double value, RoundDown annotation) {
		if (value == null) {
			return null;
		}

		return BigDecimal.valueOf(value).setScale(annotation.scale(), RoundingMode.DOWN).doubleValue();
	}

}
