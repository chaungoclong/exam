package controllers;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import dao.ProductDao;
import database.DB;
import entity.Product;

public class ProductController {
	private ProductDao dao;
	private Scanner sc = new Scanner(System.in);

	public ProductController(DB db) {
		this.dao = new ProductDao(db);
	}

	// show all product
	public void index() {
		try {
			List<Product> products = this.dao.all();

			System.out.println("\n***DANH SACH SAN PHAM***");
			for (Product product : products) {
				System.out.println(product.printDetail());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// sort by real price
	public void sortByPrice() {
		try {
			List<Product> products = this.dao.all();
			products.sort(Comparator.comparing(Product::getPrice).reversed());

			System.out.println("\n***DANH SACH SAN PHAM SAU KHI SAP XEP***");
			for (Product product : products) {
				System.out.println(product.printDetail());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// add product
	public void add() {
		System.out.println("\n***THEM SAN PHAM***");
		int choice = 1;

		while (choice == 1) {
			String code = inputString("code:");
			String name = inputString("name:");
			double price = inputDouble("price:");
			double discount = inputDouble("discount:");

			try {
				Product product = new Product(code, name, price, discount);
				this.dao.add(product);
				System.out.println("---Them Thanh Cong---");
				choice = inputInt("1:tiep tuc\t\t0.thoat");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// input string
	public String inputString(String message) {
		System.out.println(message);
		return this.sc.nextLine();
	}

	// input double
	public double inputDouble(String message) {
		System.out.println(message);
		while (true) {
			try {
				double value = Double.parseDouble(this.sc.nextLine());
				return value;
			} catch (Exception e) {
				System.out.println("nhap lai:");
			}
		}
	}

	// input int
	public int inputInt(String message) {
		System.out.println(message);
		while (true) {
			try {
				int value = Integer.parseInt(this.sc.nextLine());
				return value;
			} catch (Exception e) {
				System.out.println("nhap lai:");
			}
		}
	}
}
