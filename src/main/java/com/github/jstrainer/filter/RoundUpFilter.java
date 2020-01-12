package com.github.jstrainer.filter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundUpFilter implements Filter<RoundUp, Double> {

	@Override
	public Double filter(Double value, RoundUp annotation) {
		if (value == null) {
			return null;
		}

		return BigDecimal.valueOf(value).setScale(annotation.scale(), RoundingMode.UP).doubleValue();
	}

}
