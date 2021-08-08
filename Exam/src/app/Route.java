package app;

import java.util.Scanner;

import controllers.ProductController;
import database.DB;

public class Route {
	private int controller;
	private int action;
	private Scanner sc = new Scanner(System.in);
	private DB db;

	public Route(DB db) {
		this.controller = 1;
		this.action = 1;
		this.db = db;
	}

	public int select() {
		System.out.println("nhap lua chon:");
		while (true) {
			try {
				int choice = Integer.parseInt(sc.nextLine());
				return choice;
			} catch (Exception e) {
				System.out.println("nhap lai:");
			}
		}
	}

	public int renderMenu(int width, String symbol, String... options) {
		// title
		System.out.println("\n\n" + options[0]);

		// top and bottom border
		String topAndBot = symbol.repeat(width);

		// display top border
		System.out.println(topAndBot);

		for (int i = 1; i < options.length; i++) {
			String space = " ".repeat(width - options[i].length() - 3);
			String option = i + ":" + options[i] + space + symbol;
			System.out.println(option);
		}
		
		String exitOption = "0:THOAT" + " ".repeat(width - 8) + symbol;
		System.out.println(exitOption);
		
		// display bottom border
		System.out.println(topAndBot);
		return this.select();
	}

	public void run() {
		while (this.controller != 0) {
			// select controller
			this.controller = this.renderMenu(20, "=", "MY APP", "SAN PHAM");
			switch (this.controller) {

			// product
			case 1:
				ProductController productController = new ProductController(this.db);
				while (this.action != 0) {
					// select action
					this.action = this.renderMenu(20, "=", "QUAN LY SAN PHAM", "DANH SACH", "SAP XEP", "THEM");
					switch (this.action) {

					// index
					case 1:
						productController.index();
						break;
					
					// sort
					case 2:
						productController.sortByRealPrice();
						break;
					
					case 3:
						productController.add();
						break;
					
					case 0:
					default:
						break;
					}
				}
				break;
				
			case 0:
				
			default:
				System.out.println("THANK YOU");
				break;
			}
		}
	}

	public static void main(String[] args) {
		DB db = new DB("localhost", "3306", "exam", "long", "tnt");
		Route route = new Route(db);
		route.run();
	}
}
