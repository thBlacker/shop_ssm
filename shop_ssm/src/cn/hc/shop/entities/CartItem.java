package cn.hc.shop.entities;

public class CartItem {

	private Product product;
	private Integer count;
	private double subtotal;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public double getSubtotal() {
		return count * product.getShopPrice();
	}
	
}
