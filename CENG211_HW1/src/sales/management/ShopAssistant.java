package sales.management;

import java.time.LocalDate;

public class ShopAssistant {

	private int id;
	private String name;
	private String surname;
	private String phoneNumber;
	private LocalDate startDate;
	private float weeklySalary;
	private float comission;
	
	public ShopAssistant(int id, String name, String surname, String phoneNumber, LocalDate startDate) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.startDate = LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth());
		setWeeklySalary();
		
	}
	
	public int getSeniority() {
		return LocalDate.now().getYear() - startDate.getYear();
	}

	private void setWeeklySalary() {
		int seniority = getSeniority();
		if(seniority >= 5) {
			weeklySalary = 3000f;
		}else if(seniority >= 3) {
			weeklySalary = 2500f;
		}else if(seniority >= 1) {
			weeklySalary = 2000f;
		}else {
			weeklySalary = 1500f;
		}
	}
	
	public void setComission(float totalRevenue) {
		if(totalRevenue > 7500) 
			comission = 0.03f * totalRevenue;
		else
			comission = 0.01f * totalRevenue;
	}
	
	public float getMonthlySalary() {
		return comission + weeklySalary * 4;
	}
	
	public float getWeeklySalary() {
		return this.weeklySalary;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public float getComission() {
		return comission;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

}
