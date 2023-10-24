package sales.management;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class TransactionManagement {
	// int values that contains information about data in unchangable variables.
	public static final int TOTAL_NUMBER_OF_ASSISTANTS = 100;
	public static final int TRANSACTIONS_PER_ASSISTANT = 15;
	public static final int TOTAL_NUMBER_OF_PRODUCTS = 90;
	
	public static final int ID_INDEX = 0;
	public static final int NAME_INDEX = 1;
	public static final int SURNAME_INDEX = 2;
	public static final int PHONE_INDEX = 3;
	
	private Transaction transactions[][];
	private Product[] products;
	private SalaryManagement salaryManagement;
	
	public TransactionManagement(SalaryManagement salaryManagement) {
		transactions = new Transaction[TransactionManagement.TOTAL_NUMBER_OF_ASSISTANTS][TRANSACTIONS_PER_ASSISTANT];
		products = new Product[TOTAL_NUMBER_OF_PRODUCTS];
		this.salaryManagement = salaryManagement;
	}
	
	private void readProducts() {
		int count = 0;
		try {
			// Create a BufferedReader using FileReader
			BufferedReader br = new BufferedReader(new FileReader("./resources/products.csv"));
			String line = "";
			// While there is a line
			while((line = br.readLine())!= null) {
				String[] elements = line.split(";");
				int id = Integer.parseInt(elements[Product.ID_INDEX]);
				String name = elements[Product.NAME_INDEX];
				// Replace ',' with '.' to convert float.
				float price = Float.parseFloat(elements[Product.PRICE_INDEX].replace(',', '.'));
				products[count] = new Product(id, name, price);
				count++;
				
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	public void simulateTransactions() {
		readProducts();
		long transactionId = 0;
		// Create 15 Transactions for each shopAssistant
		for(int i = 0; i < TOTAL_NUMBER_OF_ASSISTANTS; i++) {
			float totalRevenue = 0; 
			for(int j = 0; j < TRANSACTIONS_PER_ASSISTANT; j++) {
				Transaction newTransaction = new Transaction(transactionId);
				addProductsToTransaction(newTransaction);
				transactions[i][j] = newTransaction;
				// Calculate each shopAssistant's total revenue.
				totalRevenue += (newTransaction.getTotalPrice() + newTransaction.getTransactionFee());
				transactionId++;
			}
			salaryManagement.setCommissionById(totalRevenue, i);
		}
	}
	
	
	private void addProductsToTransaction(Transaction newTransaction) {
		Random random = new Random();
		int additionTry = 20;
		// Try to add 3 products to a transaction.
		while(newTransaction.hasSlot() && additionTry > 0) {
			int productIndex = random.nextInt(TOTAL_NUMBER_OF_PRODUCTS);
			int quantity = random.nextInt(10)+1;
			if(!newTransaction.addProduct(products[productIndex], quantity))
				additionTry--;
			
		}
		if(newTransaction.hasSlot() && additionTry <= 0) 
			System.err.println("Product Addition has been failed!");
		
	}
	public Transaction getTheHighestTransaction() {
		// Start from the lowest number of Float.
		float max = Float.NEGATIVE_INFINITY;
		Transaction highestTransaction = null;
		// Loop entire Transactions
		for(int i = 0; i < TOTAL_NUMBER_OF_ASSISTANTS; i++) {
			for(int j = 0; j < TRANSACTIONS_PER_ASSISTANT; j++) {
				// Change if max is small
				if(max < transactions[i][j].getTotalPrice()) {
					max = transactions[i][j].getTotalPrice();
					highestTransaction = transactions[i][j];
				}
			}
		}
		return new Transaction(highestTransaction);
	}
	public Transaction getTheLowestTransaction() {
		// Start from the HIGHEST number of Float.
		float min = Float.POSITIVE_INFINITY;
		Transaction lowestTransaction = null;
		// Loop entire Transactions
		for(int i = 0; i < TOTAL_NUMBER_OF_ASSISTANTS; i++) {
			for(int j = 0; j < TRANSACTIONS_PER_ASSISTANT; j++) {
				// Change if min is bigger
				if(min > transactions[i][j].getTotalPrice()) {
					min = transactions[i][j].getTotalPrice();
					lowestTransaction = transactions[i][j];
				}
			}
		}
		
		return new Transaction(lowestTransaction);
	}
	
	public Transaction getTheLowestTransactionFee() {
		float min = Float.POSITIVE_INFINITY;
		Transaction lowestTransaction = null;
		for(int i = 0; i < TOTAL_NUMBER_OF_ASSISTANTS; i++) {
			for(int j = 0; j < TRANSACTIONS_PER_ASSISTANT; j++) {
				if(min > transactions[i][j].getTransactionFee()) {
					min = transactions[i][j].getTransactionFee();
					lowestTransaction = transactions[i][j];
				}
			}
		}
		return new Transaction(lowestTransaction);
	}
	
	public float getTotalRevenue() {
		float totalRevenue = 0;
		for(int i = 0; i < TOTAL_NUMBER_OF_ASSISTANTS; i++) {
			for(int j = 0; j < TRANSACTIONS_PER_ASSISTANT; j++) {
				totalRevenue += transactions[i][j].getTotalPrice() + transactions[i][j].getTransactionFee();
			}
		}
		return totalRevenue;
	}
}
