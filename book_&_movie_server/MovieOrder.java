import java.io.Serializable;
public class MovieOrder implements Serializable {
	private double quantity;
	private double price;
	private double tax = 0.3;
	private double taxCost;
	private double totalBill;
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getQuantity() {
		return this.quantity;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return this.price;
	}
	
	public double getTax() {
		return this.tax;
	}
	
	public void executeTask() {
		totalBill = 0;
		totalBill = quantity * price;
		taxCost = totalBill * tax;
		totalBill = totalBill + taxCost;
	}
	public double getResult() {
		return totalBill;
	}

	@Override
	public String toString() {
		return "" + quantity + price + totalBill;
	}
}
