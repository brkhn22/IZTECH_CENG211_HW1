package sales.management;

public class Transaction {
	public static final int PRODUCT_NUMBER = 3;
	
	private long id;
	private Product[] products;
	private int quantities[];
	private float totalPrice;
	private float transactionFee;
	private int currentIndex;
	
	public Transaction(long id) {
		this.id = id;
		this.products = new Product[PRODUCT_NUMBER];
		this.quantities = new int[PRODUCT_NUMBER];
		this.currentIndex = 0;
		this.transactionFee = 0;
	}
	
	public Transaction(Transaction transaction) {
		this.id = transaction.getId();
		this.products = transaction.getProducts();
		this.quantities = transaction.getQuantities();
		this.totalPrice = transaction.getTotalPrice();
		this.transactionFee = transaction.getTransactionFee();
		this.currentIndex = transaction.getCurrentIndex();
	}
	
	public boolean addProduct(Product product, int quantity) {
		boolean result = false;
		if(hasSlot()) {
			boolean checker = true;
			int i = 0;
			while(i < currentIndex && checker) {
				if(product.equals(products[i])) 
					checker = false;
					
				i++;
			}
			if(checker) {
				products[currentIndex] = product;
				quantities[currentIndex] = quantity;
				totalPrice += product.getPrice() * quantity;
				currentIndex++;
				result = true;
				updateTransactionFee();
			}
		}
		return result;
	}
	
	public long getId() {
		return id;
	}

	public int[] getQuantities() {
		return quantities;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public float getTransactionFee() {
		return transactionFee;
	}

	public boolean hasSlot() {
		return currentIndex < PRODUCT_NUMBER;
	}
	
	private void updateTransactionFee() {
		if(this.totalPrice < 500) {
			this.transactionFee = 0.01f * totalPrice;
		}else if(this.totalPrice < 800) {
			this.transactionFee = 0.03f * totalPrice;
		}else if(this.totalPrice < 1000) {
			this.transactionFee = 0.05f * totalPrice;
		}else {
			this.transactionFee = 0.09f * totalPrice;
		}
	}
	
	public Product getTheMostExpensiveProduct() {
		Product mostExpensiveProduct = null;
		if(currentIndex > 0) {
			float max = Float.NEGATIVE_INFINITY;
			for(int i = 0; i < currentIndex; i++) {
				float price = products[i].getPrice()*quantities[i];
				if(max < price) {
					mostExpensiveProduct = products[i];
					max = price;
				}
			}
				
		}
		return new Product(mostExpensiveProduct);
	}
	
	public int getQuantityOfAnProduct(Product product) {
		int quantity = 0;
		for(int i = 0; i < currentIndex; i++) {
			if(product.equals(products[i])) {
				quantity = quantities[i];
				break;
			}
		}
		return quantity;
	}
	
	public Product[] getProducts() {
		return products;
		
	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}
}
