package com.yash.CoffeeVendingMachine;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.yash.models.BlackCoffee;
import com.yash.models.BlackTea;
import com.yash.models.Coffee;
import com.yash.models.Container;
import com.yash.models.DrinksType;
import com.yash.models.Tea;

public class VendingMachineOperationImpl implements VendingMachineOperations {

	Logger logger = Logger.getLogger(VendingMachineOperationImpl.class.getName());

	public VendingMachineOperationImpl() {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n\u001B[30m");
	}

	private double teaConsumptionAndWasteTtl = 0.0;
	private double milkConsumptionAndWasteTtl = 0.0;
	private double waterConsumptionAndWasteTtl = 0.0;
	private double sugarConsumptionAndWasteTtl = 0.0;
	private double coffeeConsumptionAndWasteTtl = 0.0;
	private Integer refillCounter = 0;

	private Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();
	private Map<String, Double> drinkTypeAndPrice = new HashMap<>();

	@Override
	public void prepareDrink(Container containers, String drinkType, Integer quantity) {

		double teaContainer = containers.getTeaContainer();
		double sugarContainer = containers.getSugarContainer();
		double milkContainer = containers.getMilkContainer();
		double waterContainer = containers.getWaterContainer();
		double coffeeContainer = containers.getCoffeeContainer();

		calculateTotalWasteAndConsumptionOfMaterial(drinkType, quantity);
		Boolean containerStatus = checkContainerUnderFlowStatus(teaContainer, sugarContainer, coffeeContainer,
				waterContainer, milkContainer);

		if (!containerStatus) {

			containers.setTeaContainer(teaContainer - teaConsumptionAndWasteTtl);
			containers.setMilkContainer(milkContainer - milkConsumptionAndWasteTtl);
			containers.setSugarContainer(sugarContainer - sugarConsumptionAndWasteTtl);
			containers.setWaterContainer(waterContainer - waterConsumptionAndWasteTtl);
			containers.setCoffeeContainer(coffeeContainer - coffeeConsumptionAndWasteTtl);

			if (drinkTypeAndQuantity.containsKey(drinkType)) {
				drinkTypeAndQuantity.put(drinkType, drinkTypeAndQuantity.get(drinkType) + quantity);
			} else {
				drinkTypeAndQuantity.put(drinkType, quantity);
			}
		}
	}
	
	

	public Boolean checkContainerUnderFlowStatus(double teaContainer, double sugarContainer, double coffeeContainer,
			double waterContainer, double milkContainer) {
		Boolean isContainerUnderFlow = false;

		if (waterContainer > waterConsumptionAndWasteTtl && sugarContainer > sugarConsumptionAndWasteTtl) {

			if (teaContainer < teaConsumptionAndWasteTtl) {
				logger.warning("Insufficient tea in Container please fill the container");
				isContainerUnderFlow = true;
			}
			if (milkContainer < milkConsumptionAndWasteTtl) {
				logger.warning("Insufficient milk in Container please fill the container");
				isContainerUnderFlow = true;
			}
			if (coffeeContainer < coffeeConsumptionAndWasteTtl) {
				logger.warning("Insufficient coffee in Container please fill the container");
				isContainerUnderFlow = true;
			}
		} else {
			logger.warning("No enough material available to prepare drink");
			isContainerUnderFlow = true;
		}
		return isContainerUnderFlow;
	}

	public void calculateTotalWasteAndConsumptionOfMaterial(String drinkType, Integer quantity) {

		if (drinkType.equalsIgnoreCase(DrinksType.TEA.name())) {
			teaConsumptionAndWasteTtl = (Tea.TEA.getConsumptionAndWasteTotal()) * quantity;
			milkConsumptionAndWasteTtl = (Tea.MILK.getConsumptionAndWasteTotal()) * quantity;
			waterConsumptionAndWasteTtl = (Tea.WATER.getConsumptionAndWasteTotal()) * quantity;
			sugarConsumptionAndWasteTtl = (Tea.SUGAR.getConsumptionAndWasteTotal()) * quantity;
			coffeeConsumptionAndWasteTtl = 0.0;
		} else if (drinkType.equalsIgnoreCase(DrinksType.BLACK_TEA.name())) {
			teaConsumptionAndWasteTtl = (BlackTea.BLACK_TEA.getConsumptionAndWasteTotal()) * quantity;
			waterConsumptionAndWasteTtl = (BlackTea.WATER.getConsumptionAndWasteTotal()) * quantity;
			sugarConsumptionAndWasteTtl = (BlackTea.SUGAR.getConsumptionAndWasteTotal()) * quantity;
			coffeeConsumptionAndWasteTtl = 0.0;
			milkConsumptionAndWasteTtl = 0.0;
		} else if (drinkType.equalsIgnoreCase(DrinksType.COFFEE.name())) {
			coffeeConsumptionAndWasteTtl = (Coffee.COFFEE.getConsumptionAndWasteTotal()) * quantity;
			milkConsumptionAndWasteTtl = (Coffee.MILK.getConsumptionAndWasteTotal()) * quantity;
			waterConsumptionAndWasteTtl = (Coffee.WATER.getConsumptionAndWasteTotal()) * quantity;
			sugarConsumptionAndWasteTtl = (Coffee.SUGAR.getConsumptionAndWasteTotal()) * quantity;
			teaConsumptionAndWasteTtl = 0.0;
		} else if (drinkType.equalsIgnoreCase(DrinksType.BLACK_COFFEE.name())) {
			coffeeConsumptionAndWasteTtl = (BlackCoffee.BLACK_COFFEE.getConsumptionAndWasteTotal()) * quantity;
			waterConsumptionAndWasteTtl = (BlackCoffee.WATER.getConsumptionAndWasteTotal()) * quantity;
			sugarConsumptionAndWasteTtl = (BlackCoffee.SUGAR.getConsumptionAndWasteTotal()) * quantity;
			teaConsumptionAndWasteTtl = 0.0;
			milkConsumptionAndWasteTtl = 0.0;
		}
	}

	public Map<String, Double> checkTotalTeaCoffeeSaleAndWasteMaterialReport(Map<String, Integer> report) {

		Map<String, Double> totalTeaCoffeeSaleReportmap = new HashMap<>();
		Map<String, Double> drinkWisePrice = new HashMap<>();

		double teaWastage = 0.0;
		double milkWastage = 0.0;
		double sugarWastage = 0.0;
		double waterWastage = 0.0;
		double coffeeWastage = 0.0;

		if (report.containsKey("TEA")) {

			drinkWisePrice.put("TEA", report.get("TEA") * DrinksType.TEA.getPrice());
			String str = "Total tea sale is " + report.get("TEA") + " Total cost of tea is ";
			totalTeaCoffeeSaleReportmap.put(str, report.get("TEA") * DrinksType.TEA.getPrice());

			teaWastage += report.get("TEA") * 1;
			milkWastage += report.get("TEA") * 4;
			sugarWastage += report.get("TEA") * 2;
			waterWastage += report.get("TEA") * 5;
		}
		if (report.containsKey("COFFEE")) {

			drinkWisePrice.put("COFFEE", report.get("COFFEE") * DrinksType.COFFEE.getPrice());
			String str = "Total Coffee sale is " + report.get("COFFEE") + " Total cost of Coffee is ";
			totalTeaCoffeeSaleReportmap.put(str, report.get("COFFEE") * DrinksType.COFFEE.getPrice());

			coffeeWastage += report.get("COFFEE") * 1;
			milkWastage += report.get("COFFEE") * 8;
			sugarWastage += report.get("COFFEE") * 2;
			waterWastage += report.get("COFFEE") * 3;
		}
		if (report.containsKey("BLACK_TEA")) {

			drinkWisePrice.put("BLACK_TEA", report.get("BLACK_TEA") * DrinksType.BLACK_TEA.getPrice());
			String str = "Total Balck Tea sale is " + report.get("BLACK_TEA") + " Total cost of Black Tea is ";
			totalTeaCoffeeSaleReportmap.put(str, report.get("BLACK_TEA") * DrinksType.BLACK_TEA.getPrice());

			sugarWastage += report.get("BLACK_TEA") * 2;
			waterWastage += report.get("BLACK_TEA") * 12;
		}
		if (report.containsKey("BLACK_COFFEE")) {

			drinkWisePrice.put("BLACK_COFFEE", report.get("BLACK_COFFEE") * DrinksType.BLACK_COFFEE.getPrice());
			String str = "Total Black Coffee sale is " + report.get("BLACK_COFFEE") + " Total cost of Black Coffee is ";
			totalTeaCoffeeSaleReportmap.put(str,
					report.get("BLACK_COFFEE") * DrinksType.BLACK_COFFEE.getPrice());

			sugarWastage += report.get("BLACK_COFFEE") * 2;
			waterWastage += report.get("BLACK_COFFEE") * 12;
		}

		logger.info("\n================== Total Tea-Coffee Sale Report Drink Wise ====================\n");
		totalTeaCoffeeSaleReportmap.forEach((k, v) -> logger.info(k + "" + v));

		logger.info("\n================== Waste Material Total====================");

		logger.info("Waste material calculation :" + "\nTotal Tea Wastage is " + teaWastage + " gm"
				+ "\nTotal Coffee Wastage is " + coffeeWastage + " gm" + "\nTotal Sugar Wastage is " + sugarWastage
				+ " gm" + "\nTotal Water Wastage is " + waterWastage + " ml" + "\nTotal Milk Wastage is " + milkWastage
				+ " ml" + "\n");
		
		return drinkWisePrice;

	}

	public void getReport(Container container) {

		drinkTypeAndPrice = checkTotalTeaCoffeeSaleAndWasteMaterialReport(drinkTypeAndQuantity);

		Double totalDrinkQuantity = drinkTypeAndQuantity.entrySet().stream()
				.collect(Collectors.summingDouble(x -> x.getValue()));
		Double totalTeaCoffeeSaleReport = drinkTypeAndPrice.entrySet().stream()
				.collect(Collectors.summingDouble(x -> x.getValue()));

		logger.info("\n================== Total Tea-Coffee Sale (Cup and Cost) ====================\n");
		logger.info("Total Drink Quantity is " + totalDrinkQuantity + " Total Cost Is " + totalTeaCoffeeSaleReport);

		logger.info("\n=============== Container Status Report (Quantity of Material Present) ================\n");
		logger.info("Tea Container Status :" + container.getTeaContainer() + "\nCoffee Container Status :"
				+ container.getCoffeeContainer() + "\nMilk Container Status :" + container.getMilkContainer()
				+ "\nSugar Container Status :" + container.getSugarContainer() + "\nWater Container Status :"
				+ container.getWaterContainer());
	}

	public Integer refillContainer(Container container, Integer choice, Integer amount) {
		Boolean isRefilled = false;
		switch (choice) {
		case 1:
			if (amount <= 2000 - container.getTeaContainer()) {
				refillCounter += 1;
				isRefilled = true;
			} else {
				logger.warning("Amount Is Greater Than Tea Container Capacity");
			}
			break;
		case 2:
			if (amount <= 2000 - container.getCoffeeContainer()) {
				refillCounter += 1;
				isRefilled = true;
			} else {
				logger.warning("Amount Is Greater Than Coffee Container Capacity");
			}
			break;
		case 3:
			if (amount <= 10000 - container.getMilkContainer()) {
				refillCounter += 1;
				isRefilled = true;
			} else {
				logger.warning("Amount Is Greater Than Milk Container Capacity");
			}
			break;

		case 4:
			if (amount <= 8000 - container.getSugarContainer()) {
				refillCounter += 1;
				isRefilled = true;
			} else {
				logger.warning("Amount Is Greater Than Sugar Container Capacity");
			}
			break;
		case 5:
			if (amount <= 15000 - container.getWaterContainer()) {
				refillCounter += 1;
				isRefilled = true;
			} else {
				logger.warning("Amount Is Greater Than Water Container Capacity");
			}
			break;
		}
		return isRefilled ? refillCounter : 0;
	}
	
}
