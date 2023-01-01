package entity;

import java.util.Date;
import java.util.List;

public class History {
	private String invoice;
	private Date orderDate;
	private List<ShoppingCart> shoppingCart;
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public List<ShoppingCart> getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(List<ShoppingCart> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	public double totalPayment() {
		double total = 0;
		for(ShoppingCart i: this.shoppingCart ) {
			total += i.getPrice()*i.getQuantity();
		}
		return total;
	}
		
}
