package com.yash.models;

public enum Coffee {
	COFFEE(4.0, 1.0), MILK(80.0, 8.0), SUGAR(15.0, 2.0), WATER(20.0, 3.0);

	private final double consumption;
	private final Double waste;

	Coffee(Double consumption, Double waste) {
		this.consumption = consumption;
		this.waste = waste;
	}

	public double getConsumptionAndWasteTotal() {
		return consumption+waste;
	}
}
