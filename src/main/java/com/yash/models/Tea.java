package com.yash.models;

public enum Tea {
	TEA(5.0, 1.0), MILK(40.0, 4.0), SUGAR(15.0, 2.0), WATER(60.0, 5.0);

	private final double consumption;
	private final Double waste;

	Tea(Double consumption, Double waste) {
		this.consumption = consumption;
		this.waste = waste;
	}

	public double getConsumptionAndWasteTotal() {
		return consumption+waste;
	}

}
