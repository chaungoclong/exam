package entity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Product {
	private String code;
	private String name;
	private double price;
	private double discount;

	public Product(String code, String name, double price, double discount) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.discount = discount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String printDetail() {
		String price = String.format("%-10.1f",this.price);
		String realPrice = String.format("%-10.1f",this.getRealPrice());
		
		return "Product [code=" + this.code + ", name=" + this.name + ", price=" + price + ", discount=" + this.discount
				+ "%, real price=" + realPrice + "]";
	}

	public double getRealPrice() {
		return this.price * (1 - this.discount / 100);
	}

	public static List<Product> listFromData(ResultSet rs) throws Exception {
		List<Product> list = new ArrayList<Product>();

		while (rs.next()) {
			Product product = new Product(rs.getString("code"), rs.getString("name"), rs.getDouble("price"),
					rs.getDouble("discount"));
			list.add(product);
		}

		return list;
	}

}
