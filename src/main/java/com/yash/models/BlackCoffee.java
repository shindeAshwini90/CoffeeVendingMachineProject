package com.yash.models;

public enum BlackCoffee {
	BLACK_COFFEE(3.0, 0.0), SUGAR(15.0, 2.0), WATER(100.0, 12.0);

	private final double consumption;
	private final Double waste;

	BlackCoffee(Double consumption, Double waste) {
		this.consumption = consumption;
		this.waste = waste;
	}

	public double getConsumptionAndWasteTotal() {
		return consumption+waste;
	}

}
