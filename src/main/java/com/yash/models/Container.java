package com.yash.models;

public class Container {
	private double teaContainer;
	private double sugarContainer;
	private double coffeeContainer;
	private double milkContainer;
	private double waterContainer;

	
	public Container() {
		super();
		fillContainer();
	}

	public double getTeaContainer() {
		return teaContainer;
	}

	public void setTeaContainer(double teaContainer) {
		this.teaContainer = teaContainer;
	}

	public double getSugarContainer() {
		return sugarContainer;
	}

	public void setSugarContainer(double sugarContainer) {
		this.sugarContainer = sugarContainer;
	}

	public double getCoffeeContainer() {
		return coffeeContainer;
	}

	public void setCoffeeContainer(double coffeeContainer) {
		this.coffeeContainer = coffeeContainer;
	}

	public double getMilkContainer() {
		return milkContainer;
	}

	public void setMilkContainer(double milkContainer) {
		this.milkContainer = milkContainer;
	}

	public double getWaterContainer() {
		return waterContainer;
	}

	public void setWaterContainer(double waterContainer) {
		this.waterContainer = waterContainer;
	}

	public void fillContainer(){
		teaContainer = 2000;
		milkContainer = 10000;
		waterContainer = 15000;
		sugarContainer = 8000;
		coffeeContainer = 2000;
	}

}
