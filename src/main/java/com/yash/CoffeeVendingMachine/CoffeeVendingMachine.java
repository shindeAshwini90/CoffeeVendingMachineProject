package com.yash.CoffeeVendingMachine;


import java.util.logging.Logger;

import com.yash.CoffeeVendingMachine.util.IntegerScanner;
import com.yash.models.Container;
import com.yash.models.Drinks;

public class CoffeeVendingMachine {
	Logger logger = Logger.getLogger(CoffeeVendingMachine.class.getName());
	
	Container containers = new Container();
	VendingMachineOperationImpl machine = new VendingMachineOperationImpl();
	IntegerScanner scanner = new IntegerScanner();

	public void displayCoffeeMachineOptions(Boolean flag) {
		
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n\u001B[30m");
		
		Integer drinkChoice = 0;
		Integer quantity = 0;
		Integer containerChoice =0;
		Boolean isContinue = true;
		Integer amount =0;
		Integer refillCount = 0;
		

		do {
			logger.info("\nPlease enter your drink selection:" + "\n\n1: Tea \t \t 10" + "\n2: Black Tea \t 5"
					+ "\n3: Coffee \t 15" + "\n4: Black Coffee  10" + "\n5: Reset Container " +"\n6: Total Sale Report "+"\n7: Refill Container " +"\n8: Exit TCVM");
			
			drinkChoice = scanner.nextInt();
			switch (drinkChoice) {
			case 1:
				logger.info("Please enter how many cup of Tea do you want?");
				quantity = scanner.nextInt();
				logger.info("Preparing " + quantity + " cup of tea");
				machine.prepareDrink(containers, Drinks.DrinksType.TEA.name(), quantity);
				logger.info("Your Tea is prepared");
				break;
			case 2:
				logger.info("Please enter how many cup of Black Tea do you want?");
				quantity = scanner.nextInt();
				logger.info("Preparing " + quantity + " cup of  Black Tea");
				machine.prepareDrink(containers, Drinks.DrinksType.BLACK_TEA.name(), quantity);
				logger.info("Your Black tea is prepared");
				break;
			case 3:
				logger.info("Please enter how many cup of Coffee do you want?");
				quantity = scanner.nextInt();
				logger.info("Preparing " + quantity + " cup of Coffee");
				machine.prepareDrink(containers, Drinks.DrinksType.COFFEE.name(), quantity);
				logger.info("Your Coffee is prepared");
				break;
			case 4:
				logger.info("Please enter how many cup of Black Coffee do you want?");
				quantity = scanner.nextInt();
				logger.info("Preparing " + quantity + " cup of Black Coffee");
				machine.prepareDrink(containers, Drinks.DrinksType.BLACK_COFFEE.name(), quantity);
				logger.info("Your drink is prepared");
				break;
			case 5:
				containers.fillContainer();
				logger.info("Successfully filled all containers");
				break;
			case 6:
				machine.getReport(containers);
				break;
			case 7:
				do{
					logger.info("\nWhich container do you want to refill:" + "\n\n1: Tea Container " + "\n2: Coffee Container "
							+ "\n3: Sugar Container " + "\n4: Water Container " + "\n5: Milk Container ");
					containerChoice = scanner.nextInt();
					logger.info("Please Enter Container Amount : ");
					amount = scanner.nextInt();
					refillCount = machine.refillContainer(containers, containerChoice, amount);
					logger.info("Container Refill Count is :"+refillCount);
					logger.info("Do you want to continue if yes enter 1 else 0");
					isContinue = scanner.nextInt()==1? true: false;
				}while(isContinue);
				break;
			case 8:
				flag = false;
				logger.info("Exit TCVM");;
				break;
			default:
				logger.info("Invalid input please make valid choice");
			}
		} while (flag);
	}
}
