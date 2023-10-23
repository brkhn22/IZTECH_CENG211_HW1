package sales.management;

public class Product {

	public static final int ID_INDEX = 0;
	public static final int NAME_INDEX = 1;
	public static final int PRICE_INDEX = 2;
	
	private int id;
	private String name;
	private float price;
	
	public Product(int id, String name, float price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public Product(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}
	
	public boolean equals(Product other) {
		if(other == null)
			return false;
		return this.id == other.getId();
	}
}
