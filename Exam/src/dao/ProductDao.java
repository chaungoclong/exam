package dao;

import java.sql.ResultSet;
import java.util.Comparator;
import java.util.List;

import database.*;
import entity.Product;

public class ProductDao {
	private DB db;

	public ProductDao(DB db) {
		this.db = db;
	}

	// get all product
	public List<Product> all() throws Exception {
		ResultSet rs = this.db.selectAll("product", "1", null);
		return Product.listFromData(rs);
	}

	// add product
	public int add(Product product) throws Exception {
		Object[] params = { product.getCode(), product.getName(), product.getPrice(), product.getDiscount() };
		return this.db.insert("product", "code, name, price, discount", params);
	}

	public static void main(String[] args) {
		DB db = new DB("localhost", "3306", "exam", "long", "tnt");
		ProductDao dao = new ProductDao(db);

		try {
			Product a = new Product("product3", "galaxy2", 100, 90);
			dao.add(a);

			List<Product> list = dao.all();
			list.sort(Comparator.comparing(Product::getRealPrice));
			for (Product product : list) {
				System.out.println(product.print());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
