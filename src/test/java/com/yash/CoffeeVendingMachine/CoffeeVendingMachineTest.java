package com.yash.CoffeeVendingMachine;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.CoffeeVendingMachine.util.IntegerScanner;
import com.yash.models.Container;
import com.yash.models.Drinks;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeVendingMachineTest {

	@InjectMocks
	private CoffeeVendingMachine coffeeVendingMachine;

	@Mock
	private Container containers;

	@Mock
	VendingMachineOperationImpl machine;

	@Mock
	IntegerScanner scanner;
	
	@Mock
	private Logger logger;

	@Test
	public void shouldRunTeaChoiceSwitchBlockTest() {

		when(scanner.nextInt()).thenReturn(1, 2);

		doNothing().when(machine).prepareDrink(containers, Drinks.DrinksType.TEA.name(), 2);

		coffeeVendingMachine.displayCoffeeMachineOptions(false);

		verify(machine).prepareDrink(containers, Drinks.DrinksType.TEA.name(), 2);
		
		doNothing().when(logger).info("Your drink is prepared");
	}

	@Test
	public void shouldRunBlackTeaChoiceSwitchBlockTest() {
		
		when(scanner.nextInt()).thenReturn(2, 2);
		
		doNothing().when(machine).prepareDrink(containers, Drinks.DrinksType.BLACK_TEA.name(), 2);
		
		coffeeVendingMachine.displayCoffeeMachineOptions(false);
		
		verify(machine).prepareDrink(containers, Drinks.DrinksType.BLACK_TEA.name(), 2);

	}

	@Test
	public void shouldRunCoffeeChoiceSwitchBlockTest() {

		when(scanner.nextInt()).thenReturn(3, 2);

		doNothing().when(machine).prepareDrink(containers, Drinks.DrinksType.COFFEE.name(), 2);

		coffeeVendingMachine.displayCoffeeMachineOptions(false);

		verify(machine).prepareDrink(containers, Drinks.DrinksType.COFFEE.name(), 2);

	}

	@Test
	public void shouldRunBlackCoffeeChoiceSwitchBlockTest() {

		when(scanner.nextInt()).thenReturn(4, 2);

		doNothing().when(machine).prepareDrink(containers, Drinks.DrinksType.BLACK_COFFEE.name(), 2);

		coffeeVendingMachine.displayCoffeeMachineOptions(false);

		verify(machine).prepareDrink(containers, Drinks.DrinksType.BLACK_COFFEE.name(), 2);

	}
	
	@Test
	public void shouldRunFillContainerChoiceSwitchBlockTest() {

		when(scanner.nextInt()).thenReturn(5);

		doNothing().when(containers).fillContainer();

		coffeeVendingMachine.displayCoffeeMachineOptions(false);

		verify(containers).fillContainer();

	}

	@Test
	public void shouldReturnTotalSaleOfDrinkAndWasteMaterialReportTest() {

		when(scanner.nextInt()).thenReturn(6);

		coffeeVendingMachine.displayCoffeeMachineOptions(false);
		verify(machine).getReport(containers);

	}
	
	@Test
	public void shouldReturnExitTCVMfUserChoiceIsInvalidTest() {

		when(scanner.nextInt()).thenReturn(8);

		coffeeVendingMachine.displayCoffeeMachineOptions(false);
		
		verify(logger).info("Exit TCVM");
	}
	
	@Test
	public void shouldAskUserForValidChoiceIfChoiceIsInvalidTest() {

		when(scanner.nextInt()).thenReturn(9);

		coffeeVendingMachine.displayCoffeeMachineOptions(false);
		doNothing().when(logger).info("Invalid input please make valid choice");
		verify(logger).info("Invalid input please make valid choice");

	}
	
	@Test
	public void shouldRunRefillContainerSwitchCaseTest() {

		when(scanner.nextInt()).thenReturn(7, 1, 200);

		when(machine.refillContainer(containers, 1, 200)).thenReturn(1);

		coffeeVendingMachine.displayCoffeeMachineOptions(false);

		verify(machine).refillContainer(containers, 1, 200);
		
		doNothing().when(logger).info("Please Enter Container Amount : ");
		verify(logger).info("Please Enter Container Amount : ");
	}
	
	@Test
	public void shouldRunRefillContainerSwitchCaseAndCoverWhileConditionTest() {

		when(scanner.nextInt()).thenReturn(7, 0, 200,1,0);

		when(machine.refillContainer(containers, 0, 200)).thenReturn(0);

		coffeeVendingMachine.displayCoffeeMachineOptions(false);

		verify(machine).refillContainer(containers, 0, 200);
		
		doNothing().when(logger).info("Please Enter Container Amount : ");
		verify(logger, times(2)).info("Do you want to continue if yes enter 1 else 0");
	}
	
}
