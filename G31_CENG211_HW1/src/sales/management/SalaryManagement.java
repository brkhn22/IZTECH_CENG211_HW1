package sales.management;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

public class SalaryManagement {
	
	private ShopAssistant shopAssistants[];
	
	public SalaryManagement() {
		shopAssistants = new ShopAssistant[TransactionManagement.TOTAL_NUMBER_OF_ASSISTANTS];
		
	}
	
	public void readShopAssistants() {
		try {
			// Create a BufferedReader using FileReader
			BufferedReader br = new BufferedReader(new FileReader("./resources/shopAssistants.csv"));
			String line = "";
			int count = 0;
			// While there is a line
			while((line = br.readLine())!= null) {
				String[] elements = line.split(";");
				// get all the data
				int id = Integer.parseInt(elements[TransactionManagement.ID_INDEX]);
				String name = elements[TransactionManagement.NAME_INDEX];
				String surname = elements[TransactionManagement.SURNAME_INDEX];
				String phoneNumber = elements[TransactionManagement.PHONE_INDEX];
				
				Random random = new Random();
				// Generates integer between 0 - parsed value
				int year = random.nextInt(LocalDate.now().getYear()-2017)+2018;
				int month = random.nextInt(12)+1;
				// Every months have at least 28 days.
				int day = random.nextInt(28)+1;
				LocalDate startDate = LocalDate.of(year, month, day);	
				shopAssistants[count] = new ShopAssistant(id, name, surname, phoneNumber, startDate);
				count++;
			//	System.out.printf("id: %s, name: %s, surname: %s, phoneNumber: %s \n",id, name, surname, phoneNumber);
				
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	public ShopAssistant getShopAssistantById(int target) {
		// Their id is equivalent to their index in the array.
		if(target >= 0 && target < TransactionManagement.TOTAL_NUMBER_OF_ASSISTANTS)
			return shopAssistants[target];
		else
			return null;
	}
	
	public ShopAssistant getHighestEarnedShopAssistant() {
		// Start from the lowest number of Float.
		float max = Float.NEGATIVE_INFINITY;
		ShopAssistant highestAssistant = null;
		// Loop entire shopAssistants
		for(int i = 0; i < shopAssistants.length; i++) {
			// Change if max is small
			if(max < shopAssistants[i].getMonthlySalary()) {
				max = shopAssistants[i].getMonthlySalary();
				highestAssistant = shopAssistants[i];
			}
		}
		return highestAssistant;
	}
	
	public float getTotalMonthlySalaries() {
		float totalSalaries = 0;
		for(int i = 0; i < shopAssistants.length; i++) {
			totalSalaries += shopAssistants[i].getMonthlySalary();
		}
		return totalSalaries;
	}
}
