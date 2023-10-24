package sales.management;

import java.text.NumberFormat;

public class Query {

	private SalaryManagement salaryManagement;
	private TransactionManagement transactionManagement;
	private NumberFormat moneyFormater;
	
	public Query() {
		salaryManagement = new SalaryManagement();
		transactionManagement = new TransactionManagement(salaryManagement);
		moneyFormater = NumberFormat.getCurrencyInstance();
	}
	
	public void initialize() {
		salaryManagement.readShopAssistants();
		transactionManagement.simulateTransactions();
	}
	
	public void displayAll() {
		displayTheHighestTransaction();
		displayTheMostExpensiveProductInTheLowestTransaction();
		displayTheLowestTransactionFee();
		displayTheHighestSalaryAssistant();
		displayTotalRevenue();
		displayTotalProfit();
	}
	
	private void displayTheHighestTransaction() {
		Transaction highestTransaction = transactionManagement.getTheHighestTransaction();
		if(highestTransaction != null) {
			System.out.printf("1- The highest-total-price transaction id: %s has %s price\n",
					highestTransaction.getId(),
					moneyFormater.format(highestTransaction.getTotalPrice()));
		}
	}
	private void displayTheMostExpensiveProductInTheLowestTransaction() {
		Transaction lowestTransaction = transactionManagement.getTheLowestTransaction();
		if(lowestTransaction != null) {
			Product mostExpensiveProduct = lowestTransaction.getTheMostExpensiveProduct();
			System.out.printf("2- The most expensive product in the lowest-price transaction id: %s has %s price\n",
					mostExpensiveProduct.getId(),
					moneyFormater.format(
							mostExpensiveProduct.getPrice()*
							lowestTransaction.getQuantityOfAnProduct(
									mostExpensiveProduct)));
		}
	}
	private void displayTheLowestTransactionFee() {
		Transaction lowestFee = transactionManagement.getTheLowestTransactionFee();
		
		if(lowestFee != null) {
			System.out.printf("3- The lowest transaction fee id: %s has %s fee.\n",
					lowestFee.getId(),moneyFormater.format(lowestFee.getTransactionFee()));
		}
	}
	
	private void displayTheHighestSalaryAssistant() {
		ShopAssistant highestAssistant = salaryManagement.getHighestEarnedShopAssistant();
		
		if(highestAssistant != null) {
			System.out.printf("4- The highest-salary shop assistant id: %s,"
					+ " name: %s %s, seniority: %s, comission: %s, salary: %s.\n",
					highestAssistant.getId(), highestAssistant.getName(),
					highestAssistant.getSurname(), highestAssistant.getSeniority(),
					moneyFormater.format(highestAssistant.getComission()),
					moneyFormater.format(highestAssistant.getMonthlySalary()));
		}
	}
	
	private void displayTotalRevenue() {
		System.out.printf("5- The total revenue that is earned from 1500 transactions is %s.\n",
				moneyFormater.format(transactionManagement.getTotalRevenue()));
	}
	
	private void displayTotalProfit() {
		System.out.printf("6- The total profit that is earned is %s.\n",
				moneyFormater.format(transactionManagement.getTotalRevenue()-salaryManagement.getTotalMonthlySalaries()));
	}
}
