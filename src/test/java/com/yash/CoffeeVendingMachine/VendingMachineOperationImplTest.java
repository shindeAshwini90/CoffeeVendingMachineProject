package com.yash.CoffeeVendingMachine;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.CoffeeVendingMachine.util.IntegerScanner;
import com.yash.models.Container;
import com.yash.models.DrinksType;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineOperationImplTest {
	@InjectMocks
	private VendingMachineOperationImpl VendingMachineOperationImpl;

	@Mock
	private Container container;

	@Mock
	IntegerScanner scanner;

	@Mock
	private Logger logger;

	@Test
	public void prepareTeaWhenAllRequiredContainersHaveSufficientMaterialTest() {

		when(container.getTeaContainer()).thenReturn(2000.0);

		when(container.getMilkContainer()).thenReturn(15000.0);

		when(container.getSugarContainer()).thenReturn(8000.0);

		when(container.getWaterContainer()).thenReturn(15000.0);

		VendingMachineOperationImpl.prepareDrink(container, DrinksType.TEA.name(), 2);

		verify(container).getTeaContainer();
		verify(container).getWaterContainer();
		verify(container).getSugarContainer();
		verify(container).getMilkContainer();

	}

	@Test
	public void prepareBlackTeaWhenAllRequiredContainersHaveSufficientMaterialTest() {

		when(container.getTeaContainer()).thenReturn(2000.0);

		when(container.getCoffeeContainer()).thenReturn(2000.0);

		when(container.getSugarContainer()).thenReturn(8000.0);

		when(container.getWaterContainer()).thenReturn(15000.0);

		VendingMachineOperationImpl.prepareDrink(container, DrinksType.BLACK_TEA.name(), 2);

		verify(container).getTeaContainer();
		verify(container).getWaterContainer();
		verify(container).getSugarContainer();
	}

	@Test
	public void prepareCoffeeWhenAllRequiredContainersHaveSufficientMaterialTest() {

		when(container.getCoffeeContainer()).thenReturn(2000.0);

		when(container.getMilkContainer()).thenReturn(15000.0);

		when(container.getSugarContainer()).thenReturn(8000.0);

		when(container.getWaterContainer()).thenReturn(15000.0);

		VendingMachineOperationImpl.prepareDrink(container, DrinksType.COFFEE.name(), 2);

		verify(container).getCoffeeContainer();
		verify(container).getMilkContainer();
		verify(container).getWaterContainer();
		verify(container).getSugarContainer();
	}

	@Test
	public void prepareBlackCoffeeWhenAllRequiredContainersHaveSufficientMaterialTest() {

		when(container.getCoffeeContainer()).thenReturn(2000.0);

		when(container.getSugarContainer()).thenReturn(8000.0);

		when(container.getWaterContainer()).thenReturn(15000.0);

		VendingMachineOperationImpl.prepareDrink(container, DrinksType.BLACK_COFFEE.name(), 2);
		VendingMachineOperationImpl.prepareDrink(container, DrinksType.BLACK_COFFEE.name(), 2);

		verify(container, times(2)).getCoffeeContainer();
		verify(container, times(2)).getWaterContainer();
		verify(container, times(2)).getSugarContainer();
	}

	@Test
	public void doNothingWhenDrinkTypeIsEmptyTest() {

		VendingMachineOperationImpl.prepareDrink(container, "", 2);
	}

	@Test
	public void showNotSufficientSugarWhenSugarIsLessThanRequiredTest() {

		when(container.getCoffeeContainer()).thenReturn(2000.0);

		when(container.getSugarContainer()).thenReturn(10.0);

		when(container.getWaterContainer()).thenReturn(15000.0);

		VendingMachineOperationImpl.prepareDrink(container, DrinksType.BLACK_COFFEE.name(), 6);

		verify(container).getCoffeeContainer();
		verify(container).getWaterContainer();
		verify(container).getSugarContainer();

		doNothing().when(logger).info("No enough material available to prepare drink");
		verify(logger).warning("No enough material available to prepare drink");
	}

	@Test
	public void showNotSufficientWaterWhenWaterIsLessThanRequiredTest() {

		when(container.getCoffeeContainer()).thenReturn(2000.0);

		when(container.getSugarContainer()).thenReturn(10.0);

		when(container.getWaterContainer()).thenReturn(15.0);

		VendingMachineOperationImpl.prepareDrink(container, DrinksType.BLACK_COFFEE.name(), 6);

		verify(container).getCoffeeContainer();
		verify(container).getWaterContainer();
		verify(container).getSugarContainer();
		doNothing().when(logger).info("No enough material available to prepare drink");
		verify(logger).warning("No enough material available to prepare drink");
	}

	@Test
	public void showInsufficientMilkWhenMilkIsLessThanRequiredTest() {

		when(container.getTeaContainer()).thenReturn(2000.0);

		when(container.getMilkContainer()).thenReturn(15.0);

		when(container.getSugarContainer()).thenReturn(10000.0);

		when(container.getWaterContainer()).thenReturn(15000.0);

		VendingMachineOperationImpl.prepareDrink(container, DrinksType.TEA.name(), 6);

		verify(container).getTeaContainer();
		verify(container).getMilkContainer();
		verify(container).getWaterContainer();
		verify(container).getSugarContainer();

		doNothing().when(logger).info("Insufficient milk in Container please fill the container");
		verify(logger).warning("Insufficient milk in Container please fill the container");
	}

	@Test
	public void showInsufficientCoffeeWhenCoffeeIsLessThanRequiredTest() {

		when(container.getCoffeeContainer()).thenReturn(10.0);

		when(container.getMilkContainer()).thenReturn(150.0);

		when(container.getSugarContainer()).thenReturn(10000.0);

		when(container.getWaterContainer()).thenReturn(15000.0);

		VendingMachineOperationImpl.prepareDrink(container, DrinksType.COFFEE.name(), 6);

		verify(container).getCoffeeContainer();
		verify(container).getMilkContainer();
		verify(container).getWaterContainer();
		verify(container).getSugarContainer();

		doNothing().when(logger).info("Insufficient coffee in Container please fill the container");
		verify(logger).warning("Insufficient coffee in Container please fill the container");
	}

	@Test
	public void showInsufficientTeaWhenTeaIsLessThanRequiredTest() {

		when(container.getTeaContainer()).thenReturn(10.0);

		when(container.getMilkContainer()).thenReturn(150.0);

		when(container.getSugarContainer()).thenReturn(10000.0);

		when(container.getWaterContainer()).thenReturn(15000.0);

		VendingMachineOperationImpl.prepareDrink(container, DrinksType.TEA.name(), 6);

		verify(container).getTeaContainer();
		verify(container).getMilkContainer();
		verify(container).getWaterContainer();
		verify(container).getSugarContainer();

		doNothing().when(logger).info("Insufficient tea in Container please fill the container");
		verify(logger).warning("Insufficient tea in Container please fill the container");
	}

	@Test
	public void shouldReturnTeaDrinkTypeAndItsPriceOnItsQuantityTest() {
		Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();
		drinkTypeAndQuantity.put("TEA", 2);

		Map<String, Double> expected = new HashMap<>();
		expected.put("TEA", 20.0);

		Map<String, Double> actual = VendingMachineOperationImpl
				.checkTotalTeaCoffeeSaleAndWasteMaterialReport(drinkTypeAndQuantity);

		assertEquals(expected.get("TEA"), actual.get("TEA"));

	}

	@Test
	public void shouldReturnBlackTeaDrinkTypeAndItsPriceOnItsQuantityTest() {

		Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();
		drinkTypeAndQuantity.put("BLACK_TEA", 2);

		Map<String, Double> expected = new HashMap<>();
		expected.put("BLACK_TEA", 10.0);

		Map<String, Double> actual = VendingMachineOperationImpl
				.checkTotalTeaCoffeeSaleAndWasteMaterialReport(drinkTypeAndQuantity);

		assertEquals(expected.get("BLACK_TEA"), actual.get("BLACK_TEA"));

	}

	@Test
	public void shouldReturnCoffeeDrinkTypeAndItsPriceOnItsQuantityTest() {

		Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();
		drinkTypeAndQuantity.put("COFFEE", 2);

		Map<String, Double> expected = new HashMap<>();
		expected.put("COFFEE", 30.0);

		Map<String, Double> actual = VendingMachineOperationImpl
				.checkTotalTeaCoffeeSaleAndWasteMaterialReport(drinkTypeAndQuantity);

		assertEquals(expected.get("COFFEE"), actual.get("COFFEE"));

	}

	@Test
	public void shouldReturnBlackCoffeeDrinkTypeAndItsPriceOnItsQuantityTest() {

		Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();
		drinkTypeAndQuantity.put("BLACK_COFFEE", 2);

		Map<String, Double> expected = new HashMap<>();
		expected.put("BLACK_COFFEE", 20.0);

		Map<String, Double> actual = VendingMachineOperationImpl
				.checkTotalTeaCoffeeSaleAndWasteMaterialReport(drinkTypeAndQuantity);

		assertEquals(expected.get("COFFEE"), actual.get("COFFEE"));

	}

	@Test
	public void shouldReturnReportForWasteMaterialAndDrinkWiseCostTest() {
		when(container.getCoffeeContainer()).thenReturn(2000.0);

		when(container.getMilkContainer()).thenReturn(1000.0);

		when(container.getSugarContainer()).thenReturn(10000.0);

		when(container.getWaterContainer()).thenReturn(15000.0);
		when(container.getTeaContainer()).thenReturn(2000.0);

		Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();
		drinkTypeAndQuantity.put("BLACK_COFFEE", 6);
		drinkTypeAndQuantity.put("COFFEE", 6);
		drinkTypeAndQuantity.put("COFFEE", 16);

		VendingMachineOperationImpl.prepareDrink(container, DrinksType.COFFEE.name(), 6);
		VendingMachineOperationImpl.prepareDrink(container, DrinksType.BLACK_COFFEE.name(), 16);

		VendingMachineOperationImpl.checkTotalTeaCoffeeSaleAndWasteMaterialReport(drinkTypeAndQuantity);

		VendingMachineOperationImpl.getReport(container);

	}

	@Test
	public void shouldRefillTeaContainerIfEnteredTeaAmountIsLessThanContainerCapacityTest() {

		when(container.getTeaContainer()).thenReturn(200.0);

		Integer actual = VendingMachineOperationImpl.refillContainer(container, 1, 150);
		Integer expected = 1;

		assertEquals(expected, actual);

	}

	@Test
	public void shouldNotRefillTeaContainerIfEnteredTeaAmountIsGreaterThanContainerCapacityTest() {

		when(container.getTeaContainer()).thenReturn(2000.0);

		VendingMachineOperationImpl.refillContainer(container, 1, 250);

		verify(logger).warning("Amount Is Greater Than Tea Container Capacity");

	}

	@Test
	public void shouldRefillCoffeeContainerIfEnteredCoffeeAmountIsLessThanContainerCapacityTest() {

		when(container.getCoffeeContainer()).thenReturn(200.0);

		Integer actual = VendingMachineOperationImpl.refillContainer(container, 2, 150);
		Integer expected = 1;

		assertEquals(expected, actual);

	}

	@Test
	public void shouldNotRefillCoffeeContainerIfEnteredCoffeeAmountIsGreaterThanContainerCapacityTest() {

		when(container.getCoffeeContainer()).thenReturn(2000.0);

		VendingMachineOperationImpl.refillContainer(container, 2, 250);

		verify(logger).warning("Amount Is Greater Than Coffee Container Capacity");

	}

	@Test
	public void shouldRefillMilkContainerIfEnteredMilkAmountIsLessThanContainerCapacityTest() {

		when(container.getMilkContainer()).thenReturn(200.0);

		Integer actual = VendingMachineOperationImpl.refillContainer(container, 3, 150);
		Integer expected = 1;

		assertEquals(expected, actual);

	}

	@Test
	public void shouldNotRefillMilkContainerIfEnteredMilkAmountIsGreaterThanContainerCapacityTest() {

		when(container.getMilkContainer()).thenReturn(10000.0);

		VendingMachineOperationImpl.refillContainer(container, 3, 1100);

		verify(logger).warning("Amount Is Greater Than Milk Container Capacity");

	}

	@Test
	public void shouldRefillSugarContainerIfEnteredSugarAmountIsLessThanContainerCapacityTest() {

		when(container.getSugarContainer()).thenReturn(200.0);

		Integer actual = VendingMachineOperationImpl.refillContainer(container, 4, 150);
		Integer expected = 1;

		assertEquals(expected, actual);

	}

	@Test
	public void shouldNotRefillSugarContainerIfEnteredSugarAmountIsGreaterThanContainerCapacityTest() {

		when(container.getSugarContainer()).thenReturn(8000.0);

		VendingMachineOperationImpl.refillContainer(container, 4, 9000);

		verify(logger).warning("Amount Is Greater Than Sugar Container Capacity");

	}

	@Test
	public void shouldRefillWaterContainerIfEnteredWaterAmountIsLessThanContainerCapacityTest() {

		when(container.getWaterContainer()).thenReturn(200.0);

		Integer actual = VendingMachineOperationImpl.refillContainer(container, 5, 150);
		Integer expected = 1;

		assertEquals(expected, actual);

	}

	@Test
	public void shouldNotRefillWaterContainerIfEnteredWaterAmountIsGreaterThanContainerCapacityTest() {

		when(container.getWaterContainer()).thenReturn(8000.0);

		VendingMachineOperationImpl.refillContainer(container, 5, 9000);

		verify(logger).warning("Amount Is Greater Than Water Container Capacity");

	}
}
